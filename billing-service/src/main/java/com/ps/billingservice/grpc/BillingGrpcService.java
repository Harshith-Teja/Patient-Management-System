package com.ps.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService //tells Spring that this is grpc service, spring manages it as a bean
public class BillingGrpcService extends BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
        //StreamObserver helping in sending responses, even multiple responses when server is connected with multiple clients, and also in real-time communications like chat app/dashboard

        log.info("createBillingAccount request received : {}", billingRequest.toString());

        //Business logic - ex: save to db, perform calculations etc...

        BillingResponse billingResponse = BillingResponse.newBuilder()
                .setAccountId("1234")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(billingResponse); //starts sending responses, can send multiple responses here
        responseObserver.onCompleted(); //close the connection

    }
}
