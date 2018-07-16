package io.fabric8.quickstarts.cxf.jaxrs;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.builder.SimpleBuilder.simple;


@Component
public class SayHelloRoute extends RouteBuilder {


    String topicName = "topic=";
    String kafkaServer = "kafka:second_topic";
    //tring zooKeeperHost = "zookeeperHost=localhost&zookeeperPort=2181";
    String producerType = "producerType=sync";

    String brokersOption = "brokers=localhost:9092";
    String serializerClass = "serializerClass=org.apache.kafka.common.serialization.StringSerializer";

    String kafkaConfig = new StringBuilder()
            .append(kafkaServer).append("?")
            //.append(topicName)
            //.append("&")
            .append(brokersOption)
            .append("&").append(serializerClass).toString();





    @Override
    public void configure() throws Exception{

        from("direct:sayHelloRoute").routeId("sayHelloRoute")
                .log("sayHelloRoute started...")
                .transform(simple("Hello ${body}, welcome to kafka world!!!"))
                .log("sayHelloRoute ended...")


                //.to("kafka:127.0.0.1:9092?topic=zoli_topic&requestRequiredAcks=1")

                .to(kafkaConfig)

                .log("sayHelloRoute ended...");



        from(kafkaConfig)
                .log("this is my Kafka message: ")
                .log("${body}");

        }












}
