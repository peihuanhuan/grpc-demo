package com.example.grpcclient.client

import io.grpc.Grpc
import io.grpc.InsecureChannelCredentials
import io.grpc.ManagedChannel
import net.devh.boot.grpc.client.inject.GrpcClient
import net.devh.boot.grpc.examples.lib.HelloRequest
import net.devh.boot.grpc.examples.lib.MyServiceGrpc
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class MyServiceClient2 : CommandLineRunner {

    private lateinit var myServiceStub: MyServiceGrpc.MyServiceBlockingStub

    init {
        val channel: ManagedChannel =
            Grpc.newChannelBuilder("localhost:9090", InsecureChannelCredentials.create())
                .build()
        myServiceStub = MyServiceGrpc.newBlockingStub(channel)

    }

    fun receiveGreeting(name: String?): String? {
        val request: HelloRequest = HelloRequest.newBuilder()
            .setName(name)
            .build()
        return myServiceStub.sayHello(request).getMessage()
    }


    override fun run(vararg args: String?) {
        val x = receiveGreeting("xxxxx")
        println(x)
    }
}