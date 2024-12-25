//ecd:212048476H20241223210702_V1.0
package com.mobillug.leaserent.estimator.biz.dto;

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Join;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import com.mobillug.leaserent.estimator.biz.entity.*;
import com.mobillug.leaserent.estimator.biz.dto.*;
import com.mobillug.leaserent.estimator.biz.enums.*;

import com.mobillug.leaserent.estimator.biz.dto.*;
import com.mobillug.leaserent.estimator.biz.entity.*;
import com.mobillug.leaserent.estimator.biz.enums.*;
import io.elasticore.runtime.security.TransformPermissionChecker;
import com.mobillug.leaserent.estimator.common.mapper.BaseMapper;

/**


 */


public class EstimateMapper extends BaseMapper {

    private static TransformPermissionChecker permissionChecker;

    public static void setTransformPermissionChecker(TransformPermissionChecker checker) {
        permissionChecker = checker;
    }


    protected static void checkPermission(Object from, Object to) {
        if(permissionChecker !=null) {
            if( !permissionChecker.hasPermission(from, to)) {
                throw new PermissionDeniedDataAccessException(from.getClass().getName()+ " access error" ,new RuntimeException());
            }
        }
    }

    
    public static void mapping(UploadFile from, UploadFileDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getFileKind(), to::setFileKind, isSkipNull);
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getFileUrl(), to::setFileUrl, isSkipNull);
        setVal(from.getFilePath(), to::setFilePath, isSkipNull);
        setVal(from.getFileType(), to::setFileType, isSkipNull);
        setVal(from.getSize(), to::setSize, isSkipNull);
    }
    
    
    public static void mapping(UploadFile from, UploadFileDTO to){
        mapping(from,to,false);
    }
    
    
    public static UploadFileDTO toDTO(UploadFile from){
        if(from==null) return null;
        UploadFileDTO to = new UploadFileDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<UploadFileDTO> toUploadFileDTOList(List<UploadFile> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<UploadFileDTO> toUploadFileDTOList(List<UploadFile> fromList, BiFunction<UploadFile, UploadFileDTO, UploadFileDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                UploadFileDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(BrandInfo from, BrandInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getBrandName(), to::setBrandName, isSkipNull);
        setVal(from.getBrandType(), to::setBrandType, isSkipNull);
        setVal(from.getNation(), to::setNation, isSkipNull);
        setVal(from.getImgUrl(), to::setImgUrl, isSkipNull);
    }
    
    
    public static void mapping(BrandInfo from, BrandInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static BrandInfoDTO toDTO(BrandInfo from){
        if(from==null) return null;
        BrandInfoDTO to = new BrandInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BrandInfoDTO> toBrandInfoDTOList(List<BrandInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<BrandInfoDTO> toBrandInfoDTOList(List<BrandInfo> fromList, BiFunction<BrandInfo, BrandInfoDTO, BrandInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BrandInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(BrandInfo from, BrandInfoResultDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getBrandName(), to::setBrandName, isSkipNull);
        setVal(from.getBrandType(), to::setBrandType, isSkipNull);
        setVal(from.getNation(), to::setNation, isSkipNull);
        setVal(from.getImgUrl(), to::setImgUrl, isSkipNull);
    }
    
    
    public static void mapping(BrandInfo from, BrandInfoResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static BrandInfoResultDTO toBrandInfoResultDTO(BrandInfo from){
        if(from==null) return null;
        BrandInfoResultDTO to = new BrandInfoResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BrandInfoResultDTO> toBrandInfoResultDTOList(List<BrandInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toBrandInfoResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<BrandInfoResultDTO> toBrandInfoResultDTOList(List<BrandInfo> fromList, BiFunction<BrandInfo, BrandInfoResultDTO, BrandInfoResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BrandInfoResultDTO to = toBrandInfoResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(SeriesInfo from, SeriesInfoResultDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getSeriesName(), to::setSeriesName, isSkipNull);
        setVal(from.getType(), to::setType, isSkipNull);
        setVal(from.getImgUrl(), to::setImgUrl, isSkipNull);
    }
    
    
    public static void mapping(SeriesInfo from, SeriesInfoResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static SeriesInfoResultDTO toSeriesInfoResultDTO(SeriesInfo from){
        if(from==null) return null;
        SeriesInfoResultDTO to = new SeriesInfoResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<SeriesInfoResultDTO> toSeriesInfoResultDTOList(List<SeriesInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toSeriesInfoResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<SeriesInfoResultDTO> toSeriesInfoResultDTOList(List<SeriesInfo> fromList, BiFunction<SeriesInfo, SeriesInfoResultDTO, SeriesInfoResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                SeriesInfoResultDTO to = toSeriesInfoResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(SeriesInfo from, SeriesInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(hasValue(from.getBrandInfo()))
            to.setBrandInfoId(from.getBrandInfo().getId());
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getSeriesName(), to::setSeriesName, isSkipNull);
        setVal(from.getType(), to::setType, isSkipNull);
        setVal(from.getImgUrl(), to::setImgUrl, isSkipNull);
    }
    
    
    public static void mapping(SeriesInfo from, SeriesInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static SeriesInfoDTO toDTO(SeriesInfo from){
        if(from==null) return null;
        SeriesInfoDTO to = new SeriesInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<SeriesInfoDTO> toSeriesInfoDTOList(List<SeriesInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<SeriesInfoDTO> toSeriesInfoDTOList(List<SeriesInfo> fromList, BiFunction<SeriesInfo, SeriesInfoDTO, SeriesInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                SeriesInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CarLineupInfo from, CarLineupInfoResultDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getModelName(), to::setModelName, isSkipNull);
        setVal(from.getDisplacement(), to::setDisplacement, isSkipNull);
        setVal(from.getFuelType(), to::setFuelType, isSkipNull);
    }
    
    
    public static void mapping(CarLineupInfo from, CarLineupInfoResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static CarLineupInfoResultDTO toCarLineupInfoResultDTO(CarLineupInfo from){
        if(from==null) return null;
        CarLineupInfoResultDTO to = new CarLineupInfoResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CarLineupInfoResultDTO> toCarLineupInfoResultDTOList(List<CarLineupInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toCarLineupInfoResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<CarLineupInfoResultDTO> toCarLineupInfoResultDTOList(List<CarLineupInfo> fromList, BiFunction<CarLineupInfo, CarLineupInfoResultDTO, CarLineupInfoResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarLineupInfoResultDTO to = toCarLineupInfoResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CarLineupInfo from, CarLineupInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(hasValue(from.getSeriesInfo()))
            to.setSeriesInfoId(from.getSeriesInfo().getId());
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getModelName(), to::setModelName, isSkipNull);
        setVal(from.getDisplacement(), to::setDisplacement, isSkipNull);
        setVal(from.getFuelType(), to::setFuelType, isSkipNull);
    }
    
    
    public static void mapping(CarLineupInfo from, CarLineupInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static CarLineupInfoDTO toDTO(CarLineupInfo from){
        if(from==null) return null;
        CarLineupInfoDTO to = new CarLineupInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CarLineupInfoDTO> toCarLineupInfoDTOList(List<CarLineupInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CarLineupInfoDTO> toCarLineupInfoDTOList(List<CarLineupInfo> fromList, BiFunction<CarLineupInfo, CarLineupInfoDTO, CarLineupInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarLineupInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(BaseCarInfo from, BaseCarInfoResultDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getType(), to::setType, isSkipNull);
        setVal(from.getTaxExempt(), to::setTaxExempt, isSkipNull);
        setVal(from.getCarBasePrice(), to::setCarBasePrice, isSkipNull);
        setVal(from.getSeater(), to::setSeater, isSkipNull);
        setVal(from.getTrim(), to::setTrim, isSkipNull);
        setVal(from.getCarFullName(), to::setCarFullName, isSkipNull);
        setVal(from.getNation(), to::setNation, isSkipNull);
        setVal(from.getCarModelCode(), to::setCarModelCode, isSkipNull);
        setVal(from.getDisplacement(), to::setDisplacement, isSkipNull);
        setVal(from.getEfficiency(), to::setEfficiency, isSkipNull);
        setVal(from.getFuelName(), to::setFuelName, isSkipNull);
        setVal(from.getClassifyName(), to::setClassifyName, isSkipNull);
        setVal(from.getFuelType(), to::setFuelType, isSkipNull);
        setVal(from.getCarClassType(), to::setCarClassType, isSkipNull);
        setVal(from.getOverallLength(), to::setOverallLength, isSkipNull);
        setVal(from.getOverallWidth(), to::setOverallWidth, isSkipNull);
        setVal(from.getOverallHeight(), to::setOverallHeight, isSkipNull);
        setVal(from.getWheelbase(), to::setWheelbase, isSkipNull);
        setVal(from.getTreadFront(), to::setTreadFront, isSkipNull);
        setVal(from.getTreadRear(), to::setTreadRear, isSkipNull);
        setVal(from.getCurbWeight(), to::setCurbWeight, isSkipNull);
        setVal(from.getHighOutput(), to::setHighOutput, isSkipNull);
        setVal(from.getMaxTorque(), to::setMaxTorque, isSkipNull);
        setVal(from.getPayment36M(), to::setPayment36M, isSkipNull);
        setVal(from.getPayment48M(), to::setPayment48M, isSkipNull);
        setVal(from.getPayment60M(), to::setPayment60M, isSkipNull);
    }
    
    
    public static void mapping(BaseCarInfo from, BaseCarInfoResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static BaseCarInfoResultDTO toBaseCarInfoResultDTO(BaseCarInfo from){
        if(from==null) return null;
        BaseCarInfoResultDTO to = new BaseCarInfoResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BaseCarInfoResultDTO> toBaseCarInfoResultDTOList(List<BaseCarInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toBaseCarInfoResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<BaseCarInfoResultDTO> toBaseCarInfoResultDTOList(List<BaseCarInfo> fromList, BiFunction<BaseCarInfo, BaseCarInfoResultDTO, BaseCarInfoResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BaseCarInfoResultDTO to = toBaseCarInfoResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(BaseCarInfo from, BaseCarInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getInnerColors(), to::setInnerColors, isSkipNull, EstimateMapper::toColorInfoDTOList);
        setVal(from.getOutsideColor(), to::setOutsideColor, isSkipNull, EstimateMapper::toColorInfoDTOList);
        setVal(from.getOptions(), to::setOptions, isSkipNull, EstimateMapper::toCarOptionDTOList);
        if(hasValue(from.getLineupInfo()))
            to.setLineupInfoId(from.getLineupInfo().getId());
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getType(), to::setType, isSkipNull);
        setVal(from.getTaxExempt(), to::setTaxExempt, isSkipNull);
        setVal(from.getCarBasePrice(), to::setCarBasePrice, isSkipNull);
        setVal(from.getSeater(), to::setSeater, isSkipNull);
        setVal(from.getTrim(), to::setTrim, isSkipNull);
        setVal(from.getCarFullName(), to::setCarFullName, isSkipNull);
        setVal(from.getNation(), to::setNation, isSkipNull);
        setVal(from.getCarModelCode(), to::setCarModelCode, isSkipNull);
        setVal(from.getDisplacement(), to::setDisplacement, isSkipNull);
        setVal(from.getEfficiency(), to::setEfficiency, isSkipNull);
        setVal(from.getFuelName(), to::setFuelName, isSkipNull);
        setVal(from.getClassifyName(), to::setClassifyName, isSkipNull);
        setVal(from.getFuelType(), to::setFuelType, isSkipNull);
        setVal(from.getCarClassType(), to::setCarClassType, isSkipNull);
        setVal(from.getOverallLength(), to::setOverallLength, isSkipNull);
        setVal(from.getOverallWidth(), to::setOverallWidth, isSkipNull);
        setVal(from.getOverallHeight(), to::setOverallHeight, isSkipNull);
        setVal(from.getWheelbase(), to::setWheelbase, isSkipNull);
        setVal(from.getTreadFront(), to::setTreadFront, isSkipNull);
        setVal(from.getTreadRear(), to::setTreadRear, isSkipNull);
        setVal(from.getCurbWeight(), to::setCurbWeight, isSkipNull);
        setVal(from.getHighOutput(), to::setHighOutput, isSkipNull);
        setVal(from.getMaxTorque(), to::setMaxTorque, isSkipNull);
        setVal(from.getPayment36M(), to::setPayment36M, isSkipNull);
        setVal(from.getPayment48M(), to::setPayment48M, isSkipNull);
        setVal(from.getPayment60M(), to::setPayment60M, isSkipNull);
    }
    
    
    public static void mapping(BaseCarInfo from, BaseCarInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static BaseCarInfoDTO toDTO(BaseCarInfo from){
        if(from==null) return null;
        BaseCarInfoDTO to = new BaseCarInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BaseCarInfoDTO> toBaseCarInfoDTOList(List<BaseCarInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<BaseCarInfoDTO> toBaseCarInfoDTOList(List<BaseCarInfo> fromList, BiFunction<BaseCarInfo, BaseCarInfoDTO, BaseCarInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BaseCarInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(DiscountInfo from, DiscountInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getBaseCarInfo(), to::setBaseCarInfo, isSkipNull, EstimateMapper::toDTO);
        if(hasValue(from.getBaseCarInfo()))
            to.setBaseCarInfoCarId(from.getBaseCarInfo().getCarId());
        setVal(from.getDiscountPrice(), to::setDiscountPrice, isSkipNull);
        setVal(from.getDiscountRate(), to::setDiscountRate, isSkipNull);
        setVal(from.getOptName(), to::setOptName, isSkipNull);
    }
    
    
    public static void mapping(DiscountInfo from, DiscountInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static DiscountInfoDTO toDTO(DiscountInfo from){
        if(from==null) return null;
        DiscountInfoDTO to = new DiscountInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static void mapping(CarOption from, CarOptionDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getOptionName(), to::setOptionName, isSkipNull);
        setVal(from.getOptionCode(), to::setOptionCode, isSkipNull);
        setVal(from.getOptionPrice(), to::setOptionPrice, isSkipNull);
        setVal(from.getRequiredOption(), to::setRequiredOption, isSkipNull);
        setVal(from.getOptionDescription(), to::setOptionDescription, isSkipNull);
        setVal(from.getDuplicatedOptionCode(), to::setDuplicatedOptionCode, isSkipNull);
        setVal(from.getCarModelCode(), to::setCarModelCode, isSkipNull);
        setVal(from.getIsTuneAcc(), to::setIsTuneAcc, isSkipNull);
        setVal(from.getRestriction(), to::setRestriction, isSkipNull);
    }
    
    
    public static void mapping(CarOption from, CarOptionDTO to){
        mapping(from,to,false);
    }
    
    
    public static CarOptionDTO toDTO(CarOption from){
        if(from==null) return null;
        CarOptionDTO to = new CarOptionDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CarOptionDTO> toCarOptionDTOList(List<CarOption> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<CarOptionDTO> toCarOptionDTOList(List<CarOption> fromList, BiFunction<CarOption, CarOptionDTO, CarOptionDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarOptionDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ColorInfo from, ColorInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getColorName(), to::setColorName, isSkipNull);
        setVal(from.getColorFrontCode(), to::setColorFrontCode, isSkipNull);
        setVal(from.getColorCode(), to::setColorCode, isSkipNull);
        setVal(from.getColorPrice(), to::setColorPrice, isSkipNull);
        setVal(from.getRequiredOptions(), to::setRequiredOptions, isSkipNull);
        setVal(from.getUnavailableColorCodes(), to::setUnavailableColorCodes, isSkipNull);
    }
    
    
    public static void mapping(ColorInfo from, ColorInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static ColorInfoDTO toDTO(ColorInfo from){
        if(from==null) return null;
        ColorInfoDTO to = new ColorInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ColorInfoDTO> toColorInfoDTOList(List<ColorInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<ColorInfoDTO> toColorInfoDTOList(List<ColorInfo> fromList, BiFunction<ColorInfo, ColorInfoDTO, ColorInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ColorInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(BaseCompanyCarInfo from, BaseCompanyCarInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getSeriesInfo(), to::setSeriesInfo, isSkipNull, EstimateMapper::toDTO);
        setVal(from.getBaseCarInfo(), to::setBaseCarInfo, isSkipNull, EstimateMapper::toDTO);
        if(hasValue(from.getCompany()))
            to.setCompanyComId(from.getCompany().getComId());
        if(hasValue(from.getSeriesInfo()))
            to.setSeriesInfoId(from.getSeriesInfo().getId());
        if(hasValue(from.getBaseCarInfo()))
            to.setBaseCarInfoCarId(from.getBaseCarInfo().getCarId());
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getType(), to::setType, isSkipNull);
        setVal(from.getModelName(), to::setModelName, isSkipNull);
        setVal(from.getFuelType(), to::setFuelType, isSkipNull);
        setVal(from.getCarBasePrice(), to::setCarBasePrice, isSkipNull);
        setVal(from.getSimilarity(), to::setSimilarity, isSkipNull);
        setVal(from.getSimilarityChkDate(), to::setSimilarityChkDate, isSkipNull);
    }
    
    
    public static void mapping(BaseCompanyCarInfo from, BaseCompanyCarInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static BaseCompanyCarInfoDTO toDTO(BaseCompanyCarInfo from){
        if(from==null) return null;
        BaseCompanyCarInfoDTO to = new BaseCompanyCarInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BaseCompanyCarInfoDTO> toBaseCompanyCarInfoDTOList(List<BaseCompanyCarInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<BaseCompanyCarInfoDTO> toBaseCompanyCarInfoDTOList(List<BaseCompanyCarInfo> fromList, BiFunction<BaseCompanyCarInfo, BaseCompanyCarInfoDTO, BaseCompanyCarInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BaseCompanyCarInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(SaveEstimator from, SaveEstimatorDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(to.getOptionList() != null && from.getOptionList()!=null) {
            to.getOptionList().clear();
            to.getOptionList().addAll(toCarOptionDTOList(from.getOptionList()));
        } else {
            to.setOptionList(toCarOptionDTOList(from.getOptionList()));
        }
        to.setInnerColor(toDTO(from.getInnerColor()));
        to.setOutsideColor(toDTO(from.getOutsideColor()));
        to.setCustomerInfo(toDTO(from.getCustomerInfo()));
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getBaseCarPrice(), to::setBaseCarPrice, isSkipNull);
        setVal(from.getOptionPrice(), to::setOptionPrice, isSkipNull);
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getInnerColorName(), to::setInnerColorName, isSkipNull);
        setVal(from.getExtraOpts(), to::setExtraOpts, isSkipNull);
        setVal(from.getConsignmentPrice(), to::setConsignmentPrice, isSkipNull);
        setVal(from.getMemo(), to::setMemo, isSkipNull);
        setVal(from.getEstimateType(), to::setEstimateType, isSkipNull);
        setVal(from.getMonthRentalPrice(), to::setMonthRentalPrice, isSkipNull);
    }
    
    
    public static void mapping(SaveEstimator from, SaveEstimatorDTO to){
        mapping(from,to,false);
    }
    
    
    public static SaveEstimatorDTO toDTO(SaveEstimator from){
        if(from==null) return null;
        SaveEstimatorDTO to = new SaveEstimatorDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<SaveEstimatorDTO> toSaveEstimatorDTOList(List<SaveEstimator> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<SaveEstimatorDTO> toSaveEstimatorDTOList(List<SaveEstimator> fromList, BiFunction<SaveEstimator, SaveEstimatorDTO, SaveEstimatorDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                SaveEstimatorDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CustomerInfo from, CustomerInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCustomerName(), to::setCustomerName, isSkipNull);
    }
    
    
    public static void mapping(CustomerInfo from, CustomerInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static CustomerInfoDTO toDTO(CustomerInfo from){
        if(from==null) return null;
        CustomerInfoDTO to = new CustomerInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static void mapping(RentDetails from, RentDetailsDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getAdvancedPaymentPrice(), to::setAdvancedPaymentPrice, isSkipNull);
        setVal(from.getDepositPrice(), to::setDepositPrice, isSkipNull);
        setVal(from.getAgFeePercent(), to::setAgFeePercent, isSkipNull);
        setVal(from.getCmFeePercent(), to::setCmFeePercent, isSkipNull);
    }
    
    
    public static void mapping(RentDetails from, RentDetailsDTO to){
        mapping(from,to,false);
    }
    
    
    public static RentDetailsDTO toDTO(RentDetails from){
        if(from==null) return null;
        RentDetailsDTO to = new RentDetailsDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static void mapping(TransCity from, TransCityResultDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getHasChild(), to::setHasChild, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getGrpCd(), to::setGrpCd, isSkipNull);
        setVal(from.getCityIdx(), to::setCityIdx, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(TransCity from, TransCityResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static TransCityResultDTO toTransCityResultDTO(TransCity from){
        if(from==null) return null;
        TransCityResultDTO to = new TransCityResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TransCityResultDTO> toTransCityResultDTOList(List<TransCity> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toTransCityResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<TransCityResultDTO> toTransCityResultDTOList(List<TransCity> fromList, BiFunction<TransCity, TransCityResultDTO, TransCityResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TransCityResultDTO to = toTransCityResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TransCity from, TransCityDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getHasChild(), to::setHasChild, isSkipNull);
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getGrpCd(), to::setGrpCd, isSkipNull);
        setVal(from.getCityIdx(), to::setCityIdx, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(TransCity from, TransCityDTO to){
        mapping(from,to,false);
    }
    
    
    public static TransCityDTO toDTO(TransCity from){
        if(from==null) return null;
        TransCityDTO to = new TransCityDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TransCityDTO> toTransCityDTOList(List<TransCity> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<TransCityDTO> toTransCityDTOList(List<TransCity> fromList, BiFunction<TransCity, TransCityDTO, TransCityDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TransCityDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TransArea from, TransAreaResultDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getGrpCd(), to::setGrpCd, isSkipNull);
        setVal(from.getCityIdx(), to::setCityIdx, isSkipNull);
        setVal(from.getAreaIdx(), to::setAreaIdx, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(TransArea from, TransAreaResultDTO to){
        mapping(from,to,false);
    }
    
    
    public static TransAreaResultDTO toTransAreaResultDTO(TransArea from){
        if(from==null) return null;
        TransAreaResultDTO to = new TransAreaResultDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TransAreaResultDTO> toTransAreaResultDTOList(List<TransArea> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toTransAreaResultDTO).collect(Collectors.toList());
    }
    
    
    public static List<TransAreaResultDTO> toTransAreaResultDTOList(List<TransArea> fromList, BiFunction<TransArea, TransAreaResultDTO, TransAreaResultDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TransAreaResultDTO to = toTransAreaResultDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TransArea from, TransAreaDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getGrpCd(), to::setGrpCd, isSkipNull);
        setVal(from.getCityIdx(), to::setCityIdx, isSkipNull);
        setVal(from.getAreaIdx(), to::setAreaIdx, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(TransArea from, TransAreaDTO to){
        mapping(from,to,false);
    }
    
    
    public static TransAreaDTO toDTO(TransArea from){
        if(from==null) return null;
        TransAreaDTO to = new TransAreaDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TransAreaDTO> toTransAreaDTOList(List<TransArea> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<TransAreaDTO> toTransAreaDTOList(List<TransArea> fromList, BiFunction<TransArea, TransAreaDTO, TransAreaDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TransAreaDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(RcmdCarInfo from, RcmdCarInfoDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        if(to.getOptionList() != null && from.getOptionList()!=null) {
            to.getOptionList().clear();
            to.getOptionList().addAll(toCarOptionDTOList(from.getOptionList()));
        } else {
            to.setOptionList(toCarOptionDTOList(from.getOptionList()));
        }
        to.setInnerColor(toDTO(from.getInnerColor()));
        to.setOutsideColor(toDTO(from.getOutsideColor()));
        to.setCapital(toDTO(from.getCapital()));
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getRecommendType(), to::setRecommendType, isSkipNull);
        setVal(from.getEstimateType(), to::setEstimateType, isSkipNull);
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getBaseCarPrice(), to::setBaseCarPrice, isSkipNull);
        setVal(from.getConsignmentPrice(), to::setConsignmentPrice, isSkipNull);
        setVal(from.getPayment(), to::setPayment, isSkipNull);
        setVal(from.getExtraDesc(), to::setExtraDesc, isSkipNull);
        setVal(from.getTakeover(), to::setTakeover, isSkipNull);
        setVal(from.getTotalAmount(), to::setTotalAmount, isSkipNull);
        setVal(from.getDistance(), to::setDistance, isSkipNull);
        setVal(from.getWon(), to::setWon, isSkipNull);
        setVal(from.getDeposit(), to::setDeposit, isSkipNull);
        setVal(from.getPreExp(), to::setPreExp, isSkipNull);
        setVal(from.getEm(), to::setEm, isSkipNull);
        setVal(from.getIns(), to::setIns, isSkipNull);
        setVal(from.getPenalty(), to::setPenalty, isSkipNull);
        setVal(from.getStockQuantity(), to::setStockQuantity, isSkipNull);
    }
    
    
    public static void mapping(RcmdCarInfo from, RcmdCarInfoDTO to){
        mapping(from,to,false);
    }
    
    
    public static RcmdCarInfoDTO toDTO(RcmdCarInfo from){
        if(from==null) return null;
        RcmdCarInfoDTO to = new RcmdCarInfoDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<RcmdCarInfoDTO> toRcmdCarInfoDTOList(List<RcmdCarInfo> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<RcmdCarInfoDTO> toRcmdCarInfoDTOList(List<RcmdCarInfo> fromList, BiFunction<RcmdCarInfo, RcmdCarInfoDTO, RcmdCarInfoDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                RcmdCarInfoDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LeaseRentalCapital from, LeaseRentalCapitalDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(LeaseRentalCapital from, LeaseRentalCapitalDTO to){
        mapping(from,to,false);
    }
    
    
    public static LeaseRentalCapitalDTO toDTO(LeaseRentalCapital from){
        if(from==null) return null;
        LeaseRentalCapitalDTO to = new LeaseRentalCapitalDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LeaseRentalCapitalDTO> toLeaseRentalCapitalDTOList(List<LeaseRentalCapital> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<LeaseRentalCapitalDTO> toLeaseRentalCapitalDTOList(List<LeaseRentalCapital> fromList, BiFunction<LeaseRentalCapital, LeaseRentalCapitalDTO, LeaseRentalCapitalDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LeaseRentalCapitalDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(SmartSearchLog from, SmartSearchLogDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getLogId(), to::setLogId, isSkipNull);
        setVal(from.getSearchQuery(), to::setSearchQuery, isSkipNull);
        setVal(from.getSearchResult(), to::setSearchResult, isSkipNull);
        setVal(from.getExecutionTime(), to::setExecutionTime, isSkipNull);
        setVal(from.getResultCount(), to::setResultCount, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(SmartSearchLog from, SmartSearchLogDTO to){
        mapping(from,to,false);
    }
    
    
    public static SmartSearchLogDTO toDTO(SmartSearchLog from){
        if(from==null) return null;
        SmartSearchLogDTO to = new SmartSearchLogDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<SmartSearchLogDTO> toSmartSearchLogDTOList(List<SmartSearchLog> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<SmartSearchLogDTO> toSmartSearchLogDTOList(List<SmartSearchLog> fromList, BiFunction<SmartSearchLog, SmartSearchLogDTO, SmartSearchLogDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                SmartSearchLogDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TxLog from, TxLogDTO to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getTxType(), to::setTxType, isSkipNull);
        setVal(from.getTxStatus(), to::setTxStatus, isSkipNull);
        setVal(from.getTxId(), to::setTxId, isSkipNull);
        setVal(from.getPodName(), to::setPodName, isSkipNull);
        setVal(from.getTitle(), to::setTitle, isSkipNull);
        setVal(from.getLogTxt(), to::setLogTxt, isSkipNull);
        setVal(from.getStartDate(), to::setStartDate, isSkipNull);
        setVal(from.getEndDate(), to::setEndDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(TxLog from, TxLogDTO to){
        mapping(from,to,false);
    }
    
    
    public static TxLogDTO toDTO(TxLog from){
        if(from==null) return null;
        TxLogDTO to = new TxLogDTO();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TxLogDTO> toTxLogDTOList(List<TxLog> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toDTO).collect(Collectors.toList());
    }
    
    
    public static List<TxLogDTO> toTxLogDTOList(List<TxLog> fromList, BiFunction<TxLog, TxLogDTO, TxLogDTO> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TxLogDTO to = toDTO(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(BrandInfoDTO from, BrandInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getBrandName(), to::setBrandName, isSkipNull);
        setVal(from.getBrandType(), to::setBrandType, isSkipNull);
        setVal(from.getNation(), to::setNation, isSkipNull);
        setVal(from.getImgUrl(), to::setImgUrl, isSkipNull);
    }
    
    
    public static void mapping(BrandInfoDTO from, BrandInfo to){
        mapping(from,to,false);
    }
    
    
    public static BrandInfo toEntity(BrandInfoDTO from){
        if(from==null) return null;
        BrandInfo to = new BrandInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BrandInfo> toBrandInfoList(List<BrandInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<BrandInfo> toBrandInfoList(List<BrandInfoDTO> fromList, BiFunction<BrandInfoDTO, BrandInfo, BrandInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BrandInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(BaseCarInfoDTO from, BaseCarInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getType(), to::setType, isSkipNull);
        setVal(from.getTaxExempt(), to::setTaxExempt, isSkipNull);
        setVal(from.getCarBasePrice(), to::setCarBasePrice, isSkipNull);
        setVal(from.getSeater(), to::setSeater, isSkipNull);
        setVal(from.getTrim(), to::setTrim, isSkipNull);
        setVal(from.getCarFullName(), to::setCarFullName, isSkipNull);
        setVal(from.getNation(), to::setNation, isSkipNull);
        setVal(from.getCarModelCode(), to::setCarModelCode, isSkipNull);
        setVal(from.getDisplacement(), to::setDisplacement, isSkipNull);
        setVal(from.getEfficiency(), to::setEfficiency, isSkipNull);
        setVal(from.getFuelName(), to::setFuelName, isSkipNull);
        setVal(from.getClassifyName(), to::setClassifyName, isSkipNull);
        setVal(from.getFuelType(), to::setFuelType, isSkipNull);
        setVal(from.getCarClassType(), to::setCarClassType, isSkipNull);
        setVal(from.getOverallLength(), to::setOverallLength, isSkipNull);
        setVal(from.getOverallWidth(), to::setOverallWidth, isSkipNull);
        setVal(from.getOverallHeight(), to::setOverallHeight, isSkipNull);
        setVal(from.getWheelbase(), to::setWheelbase, isSkipNull);
        setVal(from.getTreadFront(), to::setTreadFront, isSkipNull);
        setVal(from.getTreadRear(), to::setTreadRear, isSkipNull);
        setVal(from.getCurbWeight(), to::setCurbWeight, isSkipNull);
        setVal(from.getHighOutput(), to::setHighOutput, isSkipNull);
        setVal(from.getMaxTorque(), to::setMaxTorque, isSkipNull);
        setVal(from.getPayment36M(), to::setPayment36M, isSkipNull);
        setVal(from.getPayment48M(), to::setPayment48M, isSkipNull);
        setVal(from.getPayment60M(), to::setPayment60M, isSkipNull);
        
        
        if(hasValue(from.getLineupInfoId())){
            CarLineupInfo t = new CarLineupInfo();
            t.setId(from.getLineupInfoId());
            to.setLineupInfo(t);
        }
    }
    
    
    public static void mapping(BaseCarInfoDTO from, BaseCarInfo to){
        mapping(from,to,false);
    }
    
    
    public static BaseCarInfo toEntity(BaseCarInfoDTO from){
        if(from==null) return null;
        BaseCarInfo to = new BaseCarInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BaseCarInfo> toBaseCarInfoList(List<BaseCarInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<BaseCarInfo> toBaseCarInfoList(List<BaseCarInfoDTO> fromList, BiFunction<BaseCarInfoDTO, BaseCarInfo, BaseCarInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BaseCarInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CarLineupInfoDTO from, CarLineupInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getModelName(), to::setModelName, isSkipNull);
        setVal(from.getDisplacement(), to::setDisplacement, isSkipNull);
        setVal(from.getFuelType(), to::setFuelType, isSkipNull);
        
        
        if(hasValue(from.getSeriesInfoId())){
            SeriesInfo t = new SeriesInfo();
            t.setId(from.getSeriesInfoId());
            to.setSeriesInfo(t);
        }
    }
    
    
    public static void mapping(CarLineupInfoDTO from, CarLineupInfo to){
        mapping(from,to,false);
    }
    
    
    public static CarLineupInfo toEntity(CarLineupInfoDTO from){
        if(from==null) return null;
        CarLineupInfo to = new CarLineupInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CarLineupInfo> toCarLineupInfoList(List<CarLineupInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CarLineupInfo> toCarLineupInfoList(List<CarLineupInfoDTO> fromList, BiFunction<CarLineupInfoDTO, CarLineupInfo, CarLineupInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarLineupInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(SaveEstimatorDTO from, SaveEstimator to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getBaseCarPrice(), to::setBaseCarPrice, isSkipNull);
        setVal(from.getOptionPrice(), to::setOptionPrice, isSkipNull);
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getInnerColorName(), to::setInnerColorName, isSkipNull);
        setVal(from.getExtraOpts(), to::setExtraOpts, isSkipNull);
        setVal(from.getConsignmentPrice(), to::setConsignmentPrice, isSkipNull);
        setVal(from.getMemo(), to::setMemo, isSkipNull);
        setVal(from.getEstimateType(), to::setEstimateType, isSkipNull);
        setVal(from.getMonthRentalPrice(), to::setMonthRentalPrice, isSkipNull);
        if(to.getOptionList() != null && from.getOptionList()!=null) {
            to.getOptionList().clear();
            to.getOptionList().addAll(toCarOptionList(from.getOptionList()));
        } else {
            to.setOptionList(toCarOptionList(from.getOptionList()));
        }
        to.setInnerColor(toEntity(from.getInnerColor()));
        to.setOutsideColor(toEntity(from.getOutsideColor()));
        to.setCustomerInfo(toEntity(from.getCustomerInfo()));
    }
    
    
    public static void mapping(SaveEstimatorDTO from, SaveEstimator to){
        mapping(from,to,false);
    }
    
    
    public static SaveEstimator toEntity(SaveEstimatorDTO from){
        if(from==null) return null;
        SaveEstimator to = new SaveEstimator();
        mapping(from, to);
        return to;
    }
    
    
    public static List<SaveEstimator> toSaveEstimatorList(List<SaveEstimatorDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<SaveEstimator> toSaveEstimatorList(List<SaveEstimatorDTO> fromList, BiFunction<SaveEstimatorDTO, SaveEstimator, SaveEstimator> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                SaveEstimator to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(RcmdCarInfoDTO from, RcmdCarInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getRecommendType(), to::setRecommendType, isSkipNull);
        setVal(from.getEstimateType(), to::setEstimateType, isSkipNull);
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getBaseCarPrice(), to::setBaseCarPrice, isSkipNull);
        setVal(from.getConsignmentPrice(), to::setConsignmentPrice, isSkipNull);
        setVal(from.getPayment(), to::setPayment, isSkipNull);
        setVal(from.getExtraDesc(), to::setExtraDesc, isSkipNull);
        setVal(from.getTakeover(), to::setTakeover, isSkipNull);
        setVal(from.getTotalAmount(), to::setTotalAmount, isSkipNull);
        setVal(from.getDistance(), to::setDistance, isSkipNull);
        setVal(from.getWon(), to::setWon, isSkipNull);
        setVal(from.getDeposit(), to::setDeposit, isSkipNull);
        setVal(from.getPreExp(), to::setPreExp, isSkipNull);
        setVal(from.getEm(), to::setEm, isSkipNull);
        setVal(from.getIns(), to::setIns, isSkipNull);
        setVal(from.getPenalty(), to::setPenalty, isSkipNull);
        setVal(from.getStockQuantity(), to::setStockQuantity, isSkipNull);
        if(to.getOptionList() != null && from.getOptionList()!=null) {
            to.getOptionList().clear();
            to.getOptionList().addAll(toCarOptionList(from.getOptionList()));
        } else {
            to.setOptionList(toCarOptionList(from.getOptionList()));
        }
        to.setInnerColor(toEntity(from.getInnerColor()));
        to.setOutsideColor(toEntity(from.getOutsideColor()));
        to.setCapital(toEntity(from.getCapital()));
    }
    
    
    public static void mapping(RcmdCarInfoDTO from, RcmdCarInfo to){
        mapping(from,to,false);
    }
    
    
    public static RcmdCarInfo toEntity(RcmdCarInfoDTO from){
        if(from==null) return null;
        RcmdCarInfo to = new RcmdCarInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<RcmdCarInfo> toRcmdCarInfoList(List<RcmdCarInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<RcmdCarInfo> toRcmdCarInfoList(List<RcmdCarInfoDTO> fromList, BiFunction<RcmdCarInfoDTO, RcmdCarInfo, RcmdCarInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                RcmdCarInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(SeriesInfoDTO from, SeriesInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getSeriesName(), to::setSeriesName, isSkipNull);
        setVal(from.getType(), to::setType, isSkipNull);
        setVal(from.getImgUrl(), to::setImgUrl, isSkipNull);
        
        
        if(hasValue(from.getBrandInfoId())){
            BrandInfo t = new BrandInfo();
            t.setId(from.getBrandInfoId());
            to.setBrandInfo(t);
        }
    }
    
    
    public static void mapping(SeriesInfoDTO from, SeriesInfo to){
        mapping(from,to,false);
    }
    
    
    public static SeriesInfo toEntity(SeriesInfoDTO from){
        if(from==null) return null;
        SeriesInfo to = new SeriesInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<SeriesInfo> toSeriesInfoList(List<SeriesInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<SeriesInfo> toSeriesInfoList(List<SeriesInfoDTO> fromList, BiFunction<SeriesInfoDTO, SeriesInfo, SeriesInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                SeriesInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(UploadFileDTO from, UploadFile to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getFileKind(), to::setFileKind, isSkipNull);
        setVal(from.getFileUrl(), to::setFileUrl, isSkipNull);
        setVal(from.getFilePath(), to::setFilePath, isSkipNull);
        setVal(from.getFileType(), to::setFileType, isSkipNull);
        setVal(from.getSize(), to::setSize, isSkipNull);
    }
    
    
    public static void mapping(UploadFileDTO from, UploadFile to){
        mapping(from,to,false);
    }
    
    
    public static UploadFile toEntity(UploadFileDTO from){
        if(from==null) return null;
        UploadFile to = new UploadFile();
        mapping(from, to);
        return to;
    }
    
    
    public static List<UploadFile> toUploadFileList(List<UploadFileDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<UploadFile> toUploadFileList(List<UploadFileDTO> fromList, BiFunction<UploadFileDTO, UploadFile, UploadFile> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                UploadFile to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(DiscountInfoDTO from, DiscountInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getDiscountPrice(), to::setDiscountPrice, isSkipNull);
        setVal(from.getDiscountRate(), to::setDiscountRate, isSkipNull);
        setVal(from.getOptName(), to::setOptName, isSkipNull);
        
        
        if(hasValue(from.getBaseCarInfoCarId())){
            BaseCarInfo t = new BaseCarInfo();
            t.setCarId(from.getBaseCarInfoCarId());
            to.setBaseCarInfo(t);
        }
    }
    
    
    public static void mapping(DiscountInfoDTO from, DiscountInfo to){
        mapping(from,to,false);
    }
    
    
    public static DiscountInfo toEntity(DiscountInfoDTO from){
        if(from==null) return null;
        DiscountInfo to = new DiscountInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static void mapping(CarOptionDTO from, CarOption to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getOptionName(), to::setOptionName, isSkipNull);
        setVal(from.getOptionCode(), to::setOptionCode, isSkipNull);
        setVal(from.getOptionPrice(), to::setOptionPrice, isSkipNull);
        setVal(from.getRequiredOption(), to::setRequiredOption, isSkipNull);
        setVal(from.getOptionDescription(), to::setOptionDescription, isSkipNull);
        setVal(from.getDuplicatedOptionCode(), to::setDuplicatedOptionCode, isSkipNull);
        setVal(from.getCarModelCode(), to::setCarModelCode, isSkipNull);
        setVal(from.getIsTuneAcc(), to::setIsTuneAcc, isSkipNull);
        setVal(from.getRestriction(), to::setRestriction, isSkipNull);
    }
    
    
    public static void mapping(CarOptionDTO from, CarOption to){
        mapping(from,to,false);
    }
    
    
    public static CarOption toEntity(CarOptionDTO from){
        if(from==null) return null;
        CarOption to = new CarOption();
        mapping(from, to);
        return to;
    }
    
    
    public static List<CarOption> toCarOptionList(List<CarOptionDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<CarOption> toCarOptionList(List<CarOptionDTO> fromList, BiFunction<CarOptionDTO, CarOption, CarOption> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                CarOption to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(ColorInfoDTO from, ColorInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getColorName(), to::setColorName, isSkipNull);
        setVal(from.getColorFrontCode(), to::setColorFrontCode, isSkipNull);
        setVal(from.getColorCode(), to::setColorCode, isSkipNull);
        setVal(from.getColorPrice(), to::setColorPrice, isSkipNull);
        setVal(from.getRequiredOptions(), to::setRequiredOptions, isSkipNull);
        setVal(from.getUnavailableColorCodes(), to::setUnavailableColorCodes, isSkipNull);
    }
    
    
    public static void mapping(ColorInfoDTO from, ColorInfo to){
        mapping(from,to,false);
    }
    
    
    public static ColorInfo toEntity(ColorInfoDTO from){
        if(from==null) return null;
        ColorInfo to = new ColorInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<ColorInfo> toColorInfoList(List<ColorInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<ColorInfo> toColorInfoList(List<ColorInfoDTO> fromList, BiFunction<ColorInfoDTO, ColorInfo, ColorInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                ColorInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(BaseCompanyCarInfoDTO from, BaseCompanyCarInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCarId(), to::setCarId, isSkipNull);
        setVal(from.getType(), to::setType, isSkipNull);
        setVal(from.getModelName(), to::setModelName, isSkipNull);
        setVal(from.getFuelType(), to::setFuelType, isSkipNull);
        setVal(from.getCarBasePrice(), to::setCarBasePrice, isSkipNull);
        setVal(from.getSimilarity(), to::setSimilarity, isSkipNull);
        setVal(from.getSimilarityChkDate(), to::setSimilarityChkDate, isSkipNull);
        
        
        if(hasValue(from.getCompanyComId())){
            BaseCompany t = new BaseCompany();
            t.setComId(from.getCompanyComId());
            to.setCompany(t);
        }
        
        
        if(hasValue(from.getSeriesInfoId())){
            SeriesInfo t = new SeriesInfo();
            t.setId(from.getSeriesInfoId());
            to.setSeriesInfo(t);
        }
        
        
        if(hasValue(from.getBaseCarInfoCarId())){
            BaseCarInfo t = new BaseCarInfo();
            t.setCarId(from.getBaseCarInfoCarId());
            to.setBaseCarInfo(t);
        }
    }
    
    
    public static void mapping(BaseCompanyCarInfoDTO from, BaseCompanyCarInfo to){
        mapping(from,to,false);
    }
    
    
    public static BaseCompanyCarInfo toEntity(BaseCompanyCarInfoDTO from){
        if(from==null) return null;
        BaseCompanyCarInfo to = new BaseCompanyCarInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static List<BaseCompanyCarInfo> toBaseCompanyCarInfoList(List<BaseCompanyCarInfoDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<BaseCompanyCarInfo> toBaseCompanyCarInfoList(List<BaseCompanyCarInfoDTO> fromList, BiFunction<BaseCompanyCarInfoDTO, BaseCompanyCarInfo, BaseCompanyCarInfo> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                BaseCompanyCarInfo to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(CustomerInfoDTO from, CustomerInfo to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getCustomerName(), to::setCustomerName, isSkipNull);
    }
    
    
    public static void mapping(CustomerInfoDTO from, CustomerInfo to){
        mapping(from,to,false);
    }
    
    
    public static CustomerInfo toEntity(CustomerInfoDTO from){
        if(from==null) return null;
        CustomerInfo to = new CustomerInfo();
        mapping(from, to);
        return to;
    }
    
    
    public static void mapping(RentDetailsDTO from, RentDetails to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getAdvancedPaymentPrice(), to::setAdvancedPaymentPrice, isSkipNull);
        setVal(from.getDepositPrice(), to::setDepositPrice, isSkipNull);
        setVal(from.getAgFeePercent(), to::setAgFeePercent, isSkipNull);
        setVal(from.getCmFeePercent(), to::setCmFeePercent, isSkipNull);
    }
    
    
    public static void mapping(RentDetailsDTO from, RentDetails to){
        mapping(from,to,false);
    }
    
    
    public static RentDetails toEntity(RentDetailsDTO from){
        if(from==null) return null;
        RentDetails to = new RentDetails();
        mapping(from, to);
        return to;
    }
    
    
    public static void mapping(TransCityDTO from, TransCity to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getGrpCd(), to::setGrpCd, isSkipNull);
        setVal(from.getCityIdx(), to::setCityIdx, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
        setVal(from.getHasChild(), to::setHasChild, isSkipNull);
    }
    
    
    public static void mapping(TransCityDTO from, TransCity to){
        mapping(from,to,false);
    }
    
    
    public static TransCity toEntity(TransCityDTO from){
        if(from==null) return null;
        TransCity to = new TransCity();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TransCity> toTransCityList(List<TransCityDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<TransCity> toTransCityList(List<TransCityDTO> fromList, BiFunction<TransCityDTO, TransCity, TransCity> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TransCity to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TransAreaDTO from, TransArea to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getSeq(), to::setSeq, isSkipNull);
        setVal(from.getGrpCd(), to::setGrpCd, isSkipNull);
        setVal(from.getCityIdx(), to::setCityIdx, isSkipNull);
        setVal(from.getAreaIdx(), to::setAreaIdx, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(TransAreaDTO from, TransArea to){
        mapping(from,to,false);
    }
    
    
    public static TransArea toEntity(TransAreaDTO from){
        if(from==null) return null;
        TransArea to = new TransArea();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TransArea> toTransAreaList(List<TransAreaDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<TransArea> toTransAreaList(List<TransAreaDTO> fromList, BiFunction<TransAreaDTO, TransArea, TransArea> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TransArea to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(LeaseRentalCapitalDTO from, LeaseRentalCapital to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getId(), to::setId, isSkipNull);
        setVal(from.getName(), to::setName, isSkipNull);
    }
    
    
    public static void mapping(LeaseRentalCapitalDTO from, LeaseRentalCapital to){
        mapping(from,to,false);
    }
    
    
    public static LeaseRentalCapital toEntity(LeaseRentalCapitalDTO from){
        if(from==null) return null;
        LeaseRentalCapital to = new LeaseRentalCapital();
        mapping(from, to);
        return to;
    }
    
    
    public static List<LeaseRentalCapital> toLeaseRentalCapitalList(List<LeaseRentalCapitalDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<LeaseRentalCapital> toLeaseRentalCapitalList(List<LeaseRentalCapitalDTO> fromList, BiFunction<LeaseRentalCapitalDTO, LeaseRentalCapital, LeaseRentalCapital> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                LeaseRentalCapital to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(SmartSearchLogDTO from, SmartSearchLog to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getLogId(), to::setLogId, isSkipNull);
        setVal(from.getSearchQuery(), to::setSearchQuery, isSkipNull);
        setVal(from.getSearchResult(), to::setSearchResult, isSkipNull);
        setVal(from.getExecutionTime(), to::setExecutionTime, isSkipNull);
        setVal(from.getResultCount(), to::setResultCount, isSkipNull);
        setVal(from.getCreateDate(), to::setCreateDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(SmartSearchLogDTO from, SmartSearchLog to){
        mapping(from,to,false);
    }
    
    
    public static SmartSearchLog toEntity(SmartSearchLogDTO from){
        if(from==null) return null;
        SmartSearchLog to = new SmartSearchLog();
        mapping(from, to);
        return to;
    }
    
    
    public static List<SmartSearchLog> toSmartSearchLogList(List<SmartSearchLogDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<SmartSearchLog> toSmartSearchLogList(List<SmartSearchLogDTO> fromList, BiFunction<SmartSearchLogDTO, SmartSearchLog, SmartSearchLog> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                SmartSearchLog to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static void mapping(TxLogDTO from, TxLog to, boolean isSkipNull){
        checkPermission(from, to);
        if(from ==null || to ==null) return;
        setVal(from.getTxId(), to::setTxId, isSkipNull);
        setVal(from.getTxType(), to::setTxType, isSkipNull);
        setVal(from.getPodName(), to::setPodName, isSkipNull);
        setVal(from.getTxStatus(), to::setTxStatus, isSkipNull);
        setVal(from.getTitle(), to::setTitle, isSkipNull);
        setVal(from.getLogTxt(), to::setLogTxt, isSkipNull);
        setVal(from.getStartDate(), to::setStartDate, isSkipNull);
        setVal(from.getEndDate(), to::setEndDate, isSkipNull);
        setVal(from.getCreatedBy(), to::setCreatedBy, isSkipNull);
    }
    
    
    public static void mapping(TxLogDTO from, TxLog to){
        mapping(from,to,false);
    }
    
    
    public static TxLog toEntity(TxLogDTO from){
        if(from==null) return null;
        TxLog to = new TxLog();
        mapping(from, to);
        return to;
    }
    
    
    public static List<TxLog> toTxLogList(List<TxLogDTO> fromList){
        if(fromList==null) return null;
        return fromList.stream().map(EstimateMapper::toEntity).collect(Collectors.toList());
    }
    
    
    public static List<TxLog> toTxLogList(List<TxLogDTO> fromList, BiFunction<TxLogDTO, TxLog, TxLog> modifier){
        if(fromList==null) return null;
        return fromList.stream()
            .map(from -> {
                TxLog to = toEntity(from);
                return modifier.apply(from, to);
            }
            )
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
        
    }
    
    
    public static Specification<BaseCarInfo> toSpec(BaseCarInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<BaseCarInfo> toSpec(BaseCarInfoSrchDTO searchDTO, Specification<BaseCarInfo> sp){
        String lineupInfoId = searchDTO.getLineupInfoId();
        if(hasValue(lineupInfoId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("lineupInfo").get("id"),lineupInfoId));
        }
        String effectiveDate = searchDTO.getEffectiveDate();
        if(hasValue(effectiveDate)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("effectiveDate"),effectiveDate));
        }
        String endDate = searchDTO.getEndDate();
        if(hasValue(endDate)){
            sp = sp.and((r,q,c) -> c.greaterThan(r.get("endDate"),endDate));
        }
        return sp;
    }
    
    
    public static Specification<RcmdCarInfo> toSpec(RcmdCarInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<RcmdCarInfo> toSpec(RcmdCarInfoSrchDTO searchDTO, Specification<RcmdCarInfo> sp){
        String innerColorId = searchDTO.getInnerColorId();
        if(hasValue(innerColorId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("innerColor").get("id"),innerColorId));
        }
        String outsideColorId = searchDTO.getOutsideColorId();
        if(hasValue(outsideColorId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("outsideColor").get("id"),outsideColorId));
        }
        String estimateType = searchDTO.getEstimateType();
        if(hasValue(estimateType)){
            sp = sp.and((r,q,c) -> c.equal(r.get("estimateType"),estimateType));
        }
        String carId = searchDTO.getCarId();
        if(hasValue(carId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("carId"),carId));
        }
        Long paymentFrom = searchDTO.getPaymentFrom();
        Long paymentTo = searchDTO.getPaymentTo();
        if(hasValue(paymentFrom) && hasValue(paymentTo)){
            sp = sp.and((r,q,c) -> c.between(r.get("payment"),paymentFrom,paymentTo));
        }
        else if(hasValue(paymentFrom)){
            sp = sp.and((r,q,c) -> c.greaterThanOrEqualTo(r.get("payment"),paymentFrom));
        }
        else if(hasValue(paymentTo)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("payment"),paymentTo));
        }
        String effectiveDate = searchDTO.getEffectiveDate();
        if(hasValue(effectiveDate)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("effectiveDate"),effectiveDate));
        }
        String endDate = searchDTO.getEndDate();
        if(hasValue(endDate)){
            sp = sp.and((r,q,c) -> c.greaterThan(r.get("endDate"),endDate));
        }
        return sp;
    }
    
    
    public static Specification<CarLineupInfo> toSpec(CarLineupInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CarLineupInfo> toSpec(CarLineupInfoSrchDTO searchDTO, Specification<CarLineupInfo> sp){
        String seriesInfoId = searchDTO.getSeriesInfoId();
        if(hasValue(seriesInfoId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("seriesInfo").get("id"),seriesInfoId));
        }
        String effectiveDate = searchDTO.getEffectiveDate();
        if(hasValue(effectiveDate)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("effectiveDate"),effectiveDate));
        }
        String endDate = searchDTO.getEndDate();
        if(hasValue(endDate)){
            sp = sp.and((r,q,c) -> c.greaterThan(r.get("endDate"),endDate));
        }
        return sp;
    }
    
    
    public static Specification<SeriesInfo> toSpec(SeriesInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<SeriesInfo> toSpec(SeriesInfoSrchDTO searchDTO, Specification<SeriesInfo> sp){
        String brandInfoId = searchDTO.getBrandInfoId();
        if(hasValue(brandInfoId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("brandInfo").get("id"),brandInfoId));
        }
        String effectiveDate = searchDTO.getEffectiveDate();
        if(hasValue(effectiveDate)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("effectiveDate"),effectiveDate));
        }
        String endDate = searchDTO.getEndDate();
        if(hasValue(endDate)){
            sp = sp.and((r,q,c) -> c.greaterThan(r.get("endDate"),endDate));
        }
        return sp;
    }
    
    
    public static Specification<BrandInfo> toSpec(BrandInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<BrandInfo> toSpec(BrandInfoSrchDTO searchDTO, Specification<BrandInfo> sp){
        String brandName = searchDTO.getBrandName();
        if(hasValue(brandName)){
            sp = sp.and((r,q,c) -> c.equal(r.get("brandName"),brandName));
        }
        String effectiveDate = searchDTO.getEffectiveDate();
        if(hasValue(effectiveDate)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("effectiveDate"),effectiveDate));
        }
        String endDate = searchDTO.getEndDate();
        if(hasValue(endDate)){
            sp = sp.and((r,q,c) -> c.greaterThan(r.get("endDate"),endDate));
        }
        return sp;
    }
    
    
    public static Specification<UploadFile> toSpec(UploadFileSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<UploadFile> toSpec(UploadFileSrchDTO searchDTO, Specification<UploadFile> sp){
        FileKind fileKind = searchDTO.getFileKind();
        if(hasValue(fileKind)){
            sp = sp.and((r,q,c) -> c.equal(r.get("fileKind"),fileKind));
        }
        sp=setSpec(sp, "id", searchDTO.getId());
        String fileUrl = searchDTO.getFileUrl();
        if(hasValue(fileUrl)){
            sp = sp.and((r,q,c) -> c.equal(r.get("fileUrl"),fileUrl));
        }
        sp=setSpec(sp, "filePath", searchDTO.getFilePath());
        sp=setSpec(sp, "fileType", searchDTO.getFileType());
        Long size = searchDTO.getSize();
        if(hasValue(size)){
            sp = sp.and((r,q,c) -> c.equal(r.get("size"),size));
        }
        return sp;
    }
    
    
    public static Specification<DiscountInfo> toSpec(DiscountInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<DiscountInfo> toSpec(DiscountInfoSrchDTO searchDTO, Specification<DiscountInfo> sp){
        String baseCarInfoCarId = searchDTO.getBaseCarInfoCarId();
        if(hasValue(baseCarInfoCarId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("baseCarInfo").get("carId"),baseCarInfoCarId));
        }
        Long discountPrice = searchDTO.getDiscountPrice();
        if(hasValue(discountPrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("discountPrice"),discountPrice));
        }
        Float discountRate = searchDTO.getDiscountRate();
        if(hasValue(discountRate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("discountRate"),discountRate));
        }
        sp=setSpec(sp, "optName", searchDTO.getOptName());
        return sp;
    }
    
    
    public static Specification<CarOption> toSpec(CarOptionSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CarOption> toSpec(CarOptionSrchDTO searchDTO, Specification<CarOption> sp){
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "optionName", searchDTO.getOptionName());
        sp=setSpec(sp, "optionCode", searchDTO.getOptionCode());
        Long optionPrice = searchDTO.getOptionPrice();
        if(hasValue(optionPrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("optionPrice"),optionPrice));
        }
        String[] requiredOption = searchDTO.getRequiredOption();
        if(hasValue(requiredOption)){
            sp = sp.and((r,q,c) -> c.equal(r.get("requiredOption"),requiredOption));
        }
        sp=setSpec(sp, "optionDescription", searchDTO.getOptionDescription());
        String[] duplicatedOptionCode = searchDTO.getDuplicatedOptionCode();
        if(hasValue(duplicatedOptionCode)){
            sp = sp.and((r,q,c) -> c.equal(r.get("duplicatedOptionCode"),duplicatedOptionCode));
        }
        sp=setSpec(sp, "carModelCode", searchDTO.getCarModelCode());
        Boolean isTuneAcc = searchDTO.getIsTuneAcc();
        if(hasValue(isTuneAcc)){
            sp = sp.and((r,q,c) -> c.equal(r.get("isTuneAcc"),isTuneAcc));
        }
        sp=setSpec(sp, "restriction", searchDTO.getRestriction());
        return sp;
    }
    
    
    public static Specification<ColorInfo> toSpec(ColorInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<ColorInfo> toSpec(ColorInfoSrchDTO searchDTO, Specification<ColorInfo> sp){
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "colorName", searchDTO.getColorName());
        sp=setSpec(sp, "colorFrontCode", searchDTO.getColorFrontCode());
        sp=setSpec(sp, "colorCode", searchDTO.getColorCode());
        Long colorPrice = searchDTO.getColorPrice();
        if(hasValue(colorPrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("colorPrice"),colorPrice));
        }
        String[] requiredOptions = searchDTO.getRequiredOptions();
        if(hasValue(requiredOptions)){
            sp = sp.and((r,q,c) -> c.equal(r.get("requiredOptions"),requiredOptions));
        }
        String[] unavailableColorCodes = searchDTO.getUnavailableColorCodes();
        if(hasValue(unavailableColorCodes)){
            sp = sp.and((r,q,c) -> c.equal(r.get("unavailableColorCodes"),unavailableColorCodes));
        }
        return sp;
    }
    
    
    public static Specification<BaseCompanyCarInfo> toSpec(BaseCompanyCarInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<BaseCompanyCarInfo> toSpec(BaseCompanyCarInfoSrchDTO searchDTO, Specification<BaseCompanyCarInfo> sp){
        String companyComId = searchDTO.getCompanyComId();
        if(hasValue(companyComId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("company").get("comId"),companyComId));
        }
        String seriesInfoId = searchDTO.getSeriesInfoId();
        if(hasValue(seriesInfoId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("seriesInfo").get("id"),seriesInfoId));
        }
        String baseCarInfoCarId = searchDTO.getBaseCarInfoCarId();
        if(hasValue(baseCarInfoCarId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("baseCarInfo").get("carId"),baseCarInfoCarId));
        }
        sp=setSpec(sp, "carId", searchDTO.getCarId());
        sp=setSpec(sp, "type", searchDTO.getType());
        sp=setSpec(sp, "modelName", searchDTO.getModelName());
        sp=setSpec(sp, "fuelType", searchDTO.getFuelType());
        Long carBasePrice = searchDTO.getCarBasePrice();
        if(hasValue(carBasePrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("carBasePrice"),carBasePrice));
        }
        Double similarity = searchDTO.getSimilarity();
        if(hasValue(similarity)){
            sp = sp.and((r,q,c) -> c.equal(r.get("similarity"),similarity));
        }
        java.time.LocalDateTime similarityChkDate = searchDTO.getSimilarityChkDate();
        if(hasValue(similarityChkDate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("similarityChkDate"),similarityChkDate));
        }
        return sp;
    }
    
    
    public static Specification<SaveEstimator> toSpec(SaveEstimatorSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<SaveEstimator> toSpec(SaveEstimatorSrchDTO searchDTO, Specification<SaveEstimator> sp){
        String carInfoCarId = searchDTO.getCarInfoCarId();
        if(hasValue(carInfoCarId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("carInfo").get("carId"),carInfoCarId));
        }
        String innerColorId = searchDTO.getInnerColorId();
        if(hasValue(innerColorId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("innerColor").get("id"),innerColorId));
        }
        String outsideColorId = searchDTO.getOutsideColorId();
        if(hasValue(outsideColorId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("outsideColor").get("id"),outsideColorId));
        }
        sp=setSpec(sp, "id", searchDTO.getId());
        Long baseCarPrice = searchDTO.getBaseCarPrice();
        if(hasValue(baseCarPrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("baseCarPrice"),baseCarPrice));
        }
        Long optionPrice = searchDTO.getOptionPrice();
        if(hasValue(optionPrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("optionPrice"),optionPrice));
        }
        sp=setSpec(sp, "carId", searchDTO.getCarId());
        sp=setSpec(sp, "innerColorName", searchDTO.getInnerColorName());
        Long consignmentPrice = searchDTO.getConsignmentPrice();
        if(hasValue(consignmentPrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("consignmentPrice"),consignmentPrice));
        }
        sp=setSpec(sp, "memo", searchDTO.getMemo());
        sp=setSpec(sp, "estimateType", searchDTO.getEstimateType());
        Long monthRentalPrice = searchDTO.getMonthRentalPrice();
        if(hasValue(monthRentalPrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("monthRentalPrice"),monthRentalPrice));
        }
        return sp;
    }
    
    
    public static Specification<CustomerInfo> toSpec(CustomerInfoSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<CustomerInfo> toSpec(CustomerInfoSrchDTO searchDTO, Specification<CustomerInfo> sp){
        sp=setSpec(sp, "customerName", searchDTO.getCustomerName());
        return sp;
    }
    
    
    public static Specification<RentDetails> toSpec(RentDetailsSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<RentDetails> toSpec(RentDetailsSrchDTO searchDTO, Specification<RentDetails> sp){
        Long advancedPaymentPrice = searchDTO.getAdvancedPaymentPrice();
        if(hasValue(advancedPaymentPrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("advancedPaymentPrice"),advancedPaymentPrice));
        }
        Long depositPrice = searchDTO.getDepositPrice();
        if(hasValue(depositPrice)){
            sp = sp.and((r,q,c) -> c.equal(r.get("depositPrice"),depositPrice));
        }
        Float agFeePercent = searchDTO.getAgFeePercent();
        if(hasValue(agFeePercent)){
            sp = sp.and((r,q,c) -> c.equal(r.get("agFeePercent"),agFeePercent));
        }
        Float cmFeePercent = searchDTO.getCmFeePercent();
        if(hasValue(cmFeePercent)){
            sp = sp.and((r,q,c) -> c.equal(r.get("cmFeePercent"),cmFeePercent));
        }
        return sp;
    }
    
    
    public static Specification<TransCity> toSpec(TransCitySrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<TransCity> toSpec(TransCitySrchDTO searchDTO, Specification<TransCity> sp){
        Indicator hasChild = searchDTO.getHasChild();
        if(hasValue(hasChild)){
            sp = sp.and((r,q,c) -> c.equal(r.get("hasChild"),hasChild));
        }
        Long seq = searchDTO.getSeq();
        if(hasValue(seq)){
            sp = sp.and((r,q,c) -> c.equal(r.get("seq"),seq));
        }
        sp=setSpec(sp, "grpCd", searchDTO.getGrpCd());
        sp=setSpec(sp, "cityIdx", searchDTO.getCityIdx());
        sp=setSpec(sp, "name", searchDTO.getName());
        return sp;
    }
    
    
    public static Specification<TransArea> toSpec(TransAreaSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<TransArea> toSpec(TransAreaSrchDTO searchDTO, Specification<TransArea> sp){
        Long seq = searchDTO.getSeq();
        if(hasValue(seq)){
            sp = sp.and((r,q,c) -> c.equal(r.get("seq"),seq));
        }
        sp=setSpec(sp, "grpCd", searchDTO.getGrpCd());
        sp=setSpec(sp, "cityIdx", searchDTO.getCityIdx());
        sp=setSpec(sp, "areaIdx", searchDTO.getAreaIdx());
        sp=setSpec(sp, "name", searchDTO.getName());
        return sp;
    }
    
    
    public static Specification<LeaseRentalCapital> toSpec(LeaseRentalCapitalSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<LeaseRentalCapital> toSpec(LeaseRentalCapitalSrchDTO searchDTO, Specification<LeaseRentalCapital> sp){
        sp=setSpec(sp, "id", searchDTO.getId());
        sp=setSpec(sp, "name", searchDTO.getName());
        String effectiveDate = searchDTO.getEffectiveDate();
        if(hasValue(effectiveDate)){
            sp = sp.and((r,q,c) -> c.lessThanOrEqualTo(r.get("effectiveDate"),effectiveDate));
        }
        String endDate = searchDTO.getEndDate();
        if(hasValue(endDate)){
            sp = sp.and((r,q,c) -> c.greaterThan(r.get("endDate"),endDate));
        }
        return sp;
    }
    
    
    public static Specification<SmartSearchLog> toSpec(SmartSearchLogSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<SmartSearchLog> toSpec(SmartSearchLogSrchDTO searchDTO, Specification<SmartSearchLog> sp){
        Long logId = searchDTO.getLogId();
        if(hasValue(logId)){
            sp = sp.and((r,q,c) -> c.equal(r.get("logId"),logId));
        }
        sp=setSpec(sp, "searchQuery", searchDTO.getSearchQuery());
        sp=setSpec(sp, "searchResult", searchDTO.getSearchResult());
        Long executionTime = searchDTO.getExecutionTime();
        if(hasValue(executionTime)){
            sp = sp.and((r,q,c) -> c.equal(r.get("executionTime"),executionTime));
        }
        Integer resultCount = searchDTO.getResultCount();
        if(hasValue(resultCount)){
            sp = sp.and((r,q,c) -> c.equal(r.get("resultCount"),resultCount));
        }
        java.time.LocalDateTime createDate = searchDTO.getCreateDate();
        if(hasValue(createDate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("createDate"),createDate));
        }
        sp=setSpec(sp, "createdBy", searchDTO.getCreatedBy());
        return sp;
    }
    
    
    public static Specification<TxLog> toSpec(TxLogSrchDTO searchDTO){
        return toSpec(searchDTO, Specification.where(null));
    }
    
    
    public static Specification<TxLog> toSpec(TxLogSrchDTO searchDTO, Specification<TxLog> sp){
        TxType txType = searchDTO.getTxType();
        if(hasValue(txType)){
            sp = sp.and((r,q,c) -> c.equal(r.get("txType"),txType));
        }
        TxStatus txStatus = searchDTO.getTxStatus();
        if(hasValue(txStatus)){
            sp = sp.and((r,q,c) -> c.equal(r.get("txStatus"),txStatus));
        }
        sp=setSpec(sp, "txId", searchDTO.getTxId());
        sp=setSpec(sp, "podName", searchDTO.getPodName());
        String title = searchDTO.getTitle();
        if(hasValue(title)){
            sp = sp.and((r,q,c) -> c.like(c.lower(r.get("title")),"%" +title.toLowerCase()+ "%"));
        }
        sp=setSpec(sp, "logTxt", searchDTO.getLogTxt());
        java.time.LocalDateTime startDate = searchDTO.getStartDate();
        if(hasValue(startDate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("startDate"),startDate));
        }
        java.time.LocalDateTime endDate = searchDTO.getEndDate();
        if(hasValue(endDate)){
            sp = sp.and((r,q,c) -> c.equal(r.get("endDate"),endDate));
        }
        sp=setSpec(sp, "createdBy", searchDTO.getCreatedBy());
        return sp;
    }
    

}
