//ecd:653636980H20250521180046_V1.0
package io.elasticore.blueprint.proto;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.elasticore.springboot3.mapper.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class CarInfoPortServiceClient {

    private final CarInfoPortServiceGrpc.CarInfoPortServiceBlockingStub blockingStub;

    public HelloGrpcClient(@Value("grpc.service.ip:localhost") String grpcSvcIp
                          ,@Value("grpc.service.port:9090") int grpcPort) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(grpcSvcIp, grpcPort)
                .usePlaintext()
                .build();
        blockingStub = CarInfoPortServiceGrpc.newBlockingStub(channel);
    }

    /*
     * 브랜드별 차량 정보 조회
    */
    public io.elasticore.blueprint.domain.bbs.dto.CarInfo2Output findByBrand(io.elasticore.blueprint.domain.bbs.dto.CarInfo2Input input) {
      CarInfo2Input.Builder builder = CarInfo2Input.newBuilder();
      Mapper.of(input, builder).execute();
      CarInfo2Input request = builder.build();
      CarInfo2Output response = blockingStub.sayHello(request);
      io.elasticore.blueprint.domain.bbs.dto.CarInfo2Output.CarInfo2OutputBuilder respBuilder = 
          io.elasticore.blueprint.domain.bbs.dto.CarInfo2Output.builder();
      Mapper.of(response, respBuilder).execute();
      return respBuilder.build();
    }
}
