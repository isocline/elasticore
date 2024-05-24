package io.elasticore.demo.linkone.spec;

import io.elasticore.demo.linkone.dto.LoanCarSearchDTO;
import io.elasticore.demo.linkone.entity.LoanCar;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class LoanCarSpecifications {


    public static Specification<LoanCar> toSpec(LoanCarSearchDTO searchDTO) {

        Specification<LoanCar> specification = Specification.where(null);


        String customerName = searchDTO.getCustomName();
        String ph = searchDTO.getCustomPh();

        LocalDateTime dt1 = searchDTO.getCreateDateFrom();
        LocalDateTime dt2 = searchDTO.getCreateDateTo();

        Long lcCode = searchDTO.getLcCode();
        if(lcCode!=null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("lcCode"), searchDTO.getLcCode()));


        }

        if(lcCode!=null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("lcCode"), searchDTO.getLcCode()));


        }


        if (dt1 != null && dt2 != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("createDate"), dt1, dt2));
        } else if (dt1 != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("createDate"), dt1));
        } else if (dt2 != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("createDate"), dt2));
        }





        return specification;
    }
}
