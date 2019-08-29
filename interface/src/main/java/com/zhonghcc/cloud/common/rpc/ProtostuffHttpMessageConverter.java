package com.zhonghcc.cloud.common.rpc;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Chenzhi on 2019/8/29.
 */

@Slf4j
public class ProtostuffHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    public static final MediaType MEDIA_TYPE = new MediaType("application", "x-protobuf", DEFAULT_CHARSET);

    public ProtostuffHttpMessageConverter() {
        super(new MediaType[]{MEDIA_TYPE});
    }


    @Override
    protected boolean supports(Class<?> aClass) {
        return Object.class.isAssignableFrom(aClass);
    }

    @Override
    protected Object readInternal(final Class<?> clazz, final HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        log.info("readInternal content type={}",inputMessage.getHeaders().getContentType());
        if (MEDIA_TYPE.isCompatibleWith(inputMessage.getHeaders().getContentType())) {
            final Schema<?> schema = RuntimeSchema.getSchema(clazz);

            final Object value = schema.newMessage();

            try (final InputStream stream = inputMessage.getBody()) {
                ProtobufIOUtil.mergeFrom(stream, value, (Schema<Object>) schema);

                return value;
            }
        }

        throw new HttpMessageNotReadableException(
                "Unrecognized HTTP media type " + inputMessage.getHeaders().getContentType().getType() + ".");
    }


    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        MediaType contentType = outputMessage.getHeaders().getContentType();
        log.info("current type:{}",contentType);

        OutputStream stream = null;
        long start = System.currentTimeMillis();
        
        try {
            stream = outputMessage.getBody();

            ProtobufIOUtil.writeTo(stream, object, RuntimeSchema.getSchema((Class<Object>) object.getClass()),
                                       LinkedBuffer.allocate());
            stream.flush();
        } finally {
            IOUtils.closeQuietly(stream);
        }

        log.info("Output spend {}", System.currentTimeMillis()-start);


    }
}