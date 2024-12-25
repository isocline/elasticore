//ecd:1246614718H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.repository;

import io.elasticore.base.util.ModelTransList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import com.mobillug.leaserent.estimator.biz.entity.*;
import com.mobillug.leaserent.estimator.biz.dto.*;



@Getter
@Service
@AllArgsConstructor
public class EstimateRepositoryHelper {

    private final EntityManager entityManager;

    private final RcmdCarInfoRepository rcmdCarInfo;
    
    private final BrandInfoRepository brandInfo;
    
    private final SeriesInfoRepository seriesInfo;
    
    private final CarLineupInfoRepository carLineupInfo;
    
    private final BaseCarInfoRepository baseCarInfo;
    
    private final UploadFileRepository uploadFile;
    
    private final CarOptionRepository carOption;
    
    private final ColorInfoRepository colorInfo;
    
    private final DirectReleaseInfoRepository directReleaseInfo;
    
    private final BaseCompanyRepository baseCompany;
    
    private final BaseCompanyCarInfoRepository baseCompanyCarInfo;
    
    private final SaveEstimatorRepository saveEstimator;
    
    private final TransCityRepository transCity;
    
    private final TransAreaRepository transArea;
    
    private final LeaseRentalCapitalRepository leaseRentalCapital;
    
    private final SmartSearchLogRepository smartSearchLog;
    
    private final TxLogRepository txLog;
    

    public List<CarInfoSrchResultDTO> findCarInfoByKeyword(String keyword ,String lowYn ,String whereSql ,String sortSql ,String limitSql) {
        String[] fieldNames = {"estimateType" ,"carId" ,"carBasePrice" ,"carFullName" ,"carModelCode" ,"classifyName" ,"displacement" ,"efficiency" ,"fuelName" ,"seater" ,"taxExempt" ,"trim" ,"curbWeight" ,"highOutput" ,"maxTorque" ,"overallHeight" ,"overallLength" ,"overallWidth" ,"treadFront" ,"treadRear" ,"wheelbase" ,"payment36m" ,"payment48m" ,"payment60m" ,"totalStockQuantity" ,"estimateTypes" ,"recommendTypes" ,"capitalIds" ,"capitalNames"};
        StringBuilder sb = new StringBuilder();
        sb.append("WITH search_query AS ( ");
        sb.append("  SELECT websearch_to_tsquery('simple', :keyword) AS query ");
        sb.append("), ");
        sb.append("aggregated_rcmd_car_info AS ( ");
        sb.append("  SELECT ");
        sb.append("    r.car_id, ");
        sb.append("    STRING_AGG(DISTINCT r.estimate_type, ' ') AS estimate_types, ");
        sb.append("    STRING_AGG(DISTINCT r.recommend_type, ' ') AS recommend_types, ");
        sb.append("    SUM(r.stock_quantity) AS total_stock_quantity, ");
        sb.append("    STRING_AGG(DISTINCT r.capital_id, ' ') AS capital_ids, ");
        sb.append("    STRING_AGG(DISTINCT lr.name, ' ') AS capital_names ");
        sb.append("  FROM ");
        sb.append("    rcmd_car_info r ");
        sb.append("  LEFT JOIN ");
        sb.append("    Lease_Rental_Capital lr ON r.capital_id = lr.id ");
        sb.append("  WHERE ");
        sb.append("    r.effective_date <= TO_CHAR(CURRENT_DATE, 'YYYYMMDD') ");
        sb.append("    AND r.end_date > TO_CHAR(CURRENT_DATE, 'YYYYMMDD') ");
        sb.append("  GROUP BY ");
        sb.append("    r.car_id ");
        sb.append(") ");
        sb.append("SELECT ");
        sb.append("  bfi.estimate_type, ");
        sb.append("  c.car_id AS car_id, ");
        sb.append("  c.car_base_price AS car_base_price, ");
        sb.append("  c.car_full_name AS car_full_name, ");
        sb.append("  c.car_model_code AS car_model_code, ");
        sb.append("  c.classify_name AS classify_name, ");
        sb.append("  c.displacement AS displacement, ");
        sb.append("  c.efficiency AS efficiency, ");
        sb.append("  c.fuel_name AS fuel_name, ");
        sb.append("  c.seater AS seater, ");
        sb.append("  c.tax_exempt AS tax_exempt, ");
        sb.append("  c.trim AS trim, ");
        sb.append("  c.curb_weight AS curb_weight, ");
        sb.append("  c.high_output AS high_output, ");
        sb.append("  c.max_torque AS max_torque, ");
        sb.append("  c.overall_height AS overall_height, ");
        sb.append("  c.overall_length AS overall_length, ");
        sb.append("  c.overall_width AS overall_width, ");
        sb.append("  c.tread_front AS tread_front, ");
        sb.append("  c.tread_rear AS tread_rear, ");
        sb.append("  c.wheelbase AS wheelbase, ");
        sb.append("  c.payment36m AS payment36m, ");
        sb.append("  c.payment48m AS payment48m, ");
        sb.append("  c.payment60m AS payment60m, ");
        sb.append(" ");
        sb.append("  a.total_stock_quantity, ");
        sb.append("  a.estimate_types, ");
        sb.append("  a.recommend_types, ");
        sb.append("  a.capital_ids, ");
        sb.append("  a.capital_names, ");
        sb.append(" ");
        sb.append("  ts_rank_cd(to_tsvector('simple', c.car_full_name), query) AS ts_rank_cd_score, ");
        sb.append("  ts_rank(to_tsvector('simple', c.car_full_name), query) AS rank ");
        sb.append("FROM ");
        sb.append("  base_car_info c ");
        sb.append("LEFT JOIN ");
        sb.append("  aggregated_rcmd_car_info a ON c.car_id = a.car_id ");
        sb.append("LEFT  JOIN LATERAL ( ");
        sb.append("  SELECT * ");
        sb.append("  FROM Base_Formula_Info bfi ");
        sb.append("  WHERE bfi.base_car_info_id = c.car_id ");
        sb.append("    AND bfi.estimate_type IN ('LR', 'LL') ");
        sb.append(") bfi ON :lowYn = 'Y' ");
        sb.append("CROSS JOIN ");
        sb.append("  search_query ");
        sb.append("WHERE ");
        sb.append("  (:lowYn = 'N' OR bfi.base_car_info_id IS NOT NULL) ");
        sb.append("  AND c.effective_date <= TO_CHAR(CURRENT_DATE, 'YYYYMMDD') ");
        sb.append("  AND c.end_date > TO_CHAR(CURRENT_DATE, 'YYYYMMDD') ");
        sb.append("  AND (:keyword IS NULL OR :keyword = '' OR (to_tsvector('simple', c.car_full_name) @@ query AND ts_rank(to_tsvector('simple', c.car_full_name), query) > 0)) ");
        sb.append(" ");
        sb.append("  /*${whereSql}*/ ");
        sb.append(" ");
        sb.append("ORDER BY ");
        sb.append("  /*${sortSql}*/ ts_rank_cd_score ASC, rank DESC ");
        sb.append(" ");
        sb.append("/*${limitSql}*/; ");
        java.util.Map<String, Object> placeholders = new java.util.HashMap<>();
        placeholders.put("whereSql" ,whereSql);
        placeholders.put("sortSql" ,sortSql);
        placeholders.put("limitSql" ,limitSql);
        String sql = ModelTransList.replace(sb.toString(),  placeholders);
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("keyword" , keyword);
        query.setParameter("lowYn" , lowYn);
        return new ModelTransList<CarInfoSrchResultDTO>(query.getResultList(), CarInfoSrchResultDTO.class, fieldNames);
    }
    
    public List<EstimateTypeDTO> selectEstimateTypeInfo() {
        String[] fieldNames = {"baseCarId" ,"lineupInfoId" ,"seriesInfoId" ,"brandInfoId" ,"estimateType"};
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT  ");
        sb.append("  bci.car_Id AS base_car_id, ");
        sb.append("  bci.lineup_Info_id AS lineup_info_id, ");
        sb.append("  cli.series_Info_id AS series_info_id, ");
        sb.append("  si.brand_Info_id AS brand_info_id, ");
        sb.append("  bfi.estimate_type AS estimate_type ");
        sb.append("FROM ");
        sb.append("  Base_Car_Info bci ");
        sb.append("INNER JOIN ");
        sb.append("  Car_Lineup_Info cli ON bci.lineup_Info_id = cli.id ");
        sb.append("INNER JOIN ");
        sb.append("  Series_Info si ON cli.series_Info_id = si.id ");
        sb.append("INNER JOIN ");
        sb.append("  Brand_Info bi ON si.brand_Info_id = bi.id ");
        sb.append("INNER JOIN ( ");
        sb.append("  SELECT base_car_info_id, estimate_type ");
        sb.append("  FROM Base_Formula_Info ");
        sb.append("  GROUP BY base_car_info_id, estimate_type ");
        sb.append(") bfi ON bfi.base_car_info_id = bci.car_Id ");
        java.util.Map<String, Object> placeholders = new java.util.HashMap<>();
        String sql = ModelTransList.replace(sb.toString(),  placeholders);
        Query query = entityManager.createNativeQuery(sql);
        return new ModelTransList<EstimateTypeDTO>(query.getResultList(), EstimateTypeDTO.class, fieldNames);
    }
    
    public List<CarFullNameInfoDTO> getCarFullInfoDTO(String carId) {
        String[] fieldNames = {"brandInfoId" ,"brandName" ,"brandImgUrl" ,"seriesInfoId" ,"seriesName" ,"seriesImgUrl" ,"lineupInfoId" ,"lineupName" ,"carId" ,"carName"};
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("  bi.id as brand_info_id , ");
        sb.append("  bi.brand_name AS brand_name,     ");
        sb.append("  bi.img_url AS brand_img_url,    ");
        sb.append("  cli.series_Info_id AS series_info_id, ");
        sb.append("  si.series_name  AS series_name, ");
        sb.append("  si.img_url AS series_img_url, ");
        sb.append("  bci.lineup_Info_id AS lineup_info_id, ");
        sb.append("  cli.name AS lineup_name, ");
        sb.append("  bci.car_id AS car_id, ");
        sb.append("  bci.trim AS car_name ");
        sb.append("FROM ");
        sb.append("  Base_Car_Info bci ");
        sb.append("  inner join ");
        sb.append("  Car_Lineup_Info cli on ");
        sb.append("  bci.lineup_Info_id = cli.id ");
        sb.append("  inner join ");
        sb.append("  Series_Info si on ");
        sb.append("  cli.series_Info_id = si.id ");
        sb.append("  inner join ");
        sb.append("  Brand_Info bi on ");
        sb.append("  si.brand_Info_id = bi.id ");
        sb.append("WHERE ");
        sb.append("  bci.car_Id = :carId ");
        java.util.Map<String, Object> placeholders = new java.util.HashMap<>();
        String sql = ModelTransList.replace(sb.toString(),  placeholders);
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("carId" , carId);
        return new ModelTransList<CarFullNameInfoDTO>(query.getResultList(), CarFullNameInfoDTO.class, fieldNames);
    }
    
}



