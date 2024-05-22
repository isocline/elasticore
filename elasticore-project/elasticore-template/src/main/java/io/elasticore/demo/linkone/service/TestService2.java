package io.elasticore.demo.linkone.service;

import io.elasticore.demo.linkone.entity.LoanCar;
import io.elasticore.demo.linkone.enums.AaccidentType;
import io.elasticore.demo.linkone.enums.Indicator;
import io.elasticore.demo.linkone.repository.LinkoneRepositoryHelper2;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestService2 {

    private final LinkoneRepositoryHelper2 repositoryHelper;


    public void test(){
        LoanCar loanCar = new LoanCar();
        loanCar.setLcCode(1L);
        loanCar.setAgentName("test");


        repositoryHelper.getLoanCar().save(loanCar);


        loanCar = new LoanCar();
        loanCar.setLcCode(2L);
        loanCar.setAgentName("test");
        repositoryHelper.getLoanCar().save(loanCar);


        loanCar = new LoanCar();
        loanCar.setLcCode(3L);
        loanCar.setAgentName("test");
        loanCar.setRentAlarmStatus(Indicator.Y);
        loanCar.setAccidentType(AaccidentType.D);
        repositoryHelper.getLoanCar().save(loanCar);


        //List<LoanCar> list = repositoryHelper.getLoanCar().findAllByOrderByCustomNameDesc();
        //System.err.println(list);



        PageRequest pageRequest = PageRequest.of(0, 2);

        Page<LoanCar> list2 = repositoryHelper.listByInsuranceCodeAndLcRepairShopName2(null, null, pageRequest);

        List<LoanCar> x=  list2.getContent();
        System.err.println(list2);
        System.err.println(list2.getTotalElements());
        System.err.println(x);

    }
}
