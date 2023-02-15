package com.example.grpcserver.service

import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.examples.lib.HelloReply
import net.devh.boot.grpc.examples.lib.HelloRequest
import net.devh.boot.grpc.examples.lib.MyServiceGrpc

import net.devh.boot.grpc.server.service.GrpcService

@GrpcService
class MyServiceImpl : MyServiceGrpc.MyServiceImplBase() {

    override fun sayHello(request: HelloRequest?, responseObserver: StreamObserver<HelloReply>?) {
        val reply = HelloReply.newBuilder()
            .setMessage("Hello ==> " + request!!.name)
            .build()
        responseObserver!!.onNext(reply)
        responseObserver.onCompleted()
    }
}