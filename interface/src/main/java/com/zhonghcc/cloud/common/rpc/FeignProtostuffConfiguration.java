package com.zhonghcc.cloud.common.rpc;

import feign.codec.Decoder;
import feign.codec.Encoder;
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
public class FeignProtostuffConfiguration {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;


    @Bean
    ProtostuffHttpMessageConverter protostuffHttpMessageConverter() {
        return new ProtostuffHttpMessageConverter();
    }

    //override the encoder
    @Bean
    public Encoder springEncoder(){
        return new SpringEncoder(this.messageConverters);
    }

    //override the encoder
    @Bean
    public Decoder springDecoder(){
        return new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
    }

}