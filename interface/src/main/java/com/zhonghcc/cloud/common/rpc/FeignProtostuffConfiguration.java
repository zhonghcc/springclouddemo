package com.zhonghcc.cloud.common.rpc;

import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

/**
 * Created by Chenzhi on 2019/8/29.
 */
@Configuration
@Slf4j
public class FeignProtostuffConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;


    @Bean
    public ProtostuffHttpMessageConverter protostuffHttpMessageConverter() {
        return new ProtostuffHttpMessageConverter();
    }

    //override the encoder
    @Bean
    public Encoder springEncoder(){
        StringBuilder names=new StringBuilder();
        messageConverters.getObject().getConverters().forEach(i->names.append(i.getClass().toString()));
        log.info("encoder converters={}",names.toString());
        return new SpringEncoder(this.messageConverters);
    }

    //override the encoder
    @Bean
    public Decoder springDecoder(){
        StringBuilder names=new StringBuilder();
        messageConverters.getObject().getConverters().forEach(i->names.append(i.getClass().toString()));
        log.info("decoder converters={}",names.toString());

        return new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
    }

}