package com.zhonghcc.cloud.common.rpc;

import com.zhonghcc.cloud.common.model.CoolMessage;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Chenzhi on 2019/8/29.
 */

@Slf4j
public class ProtostuffHttpMessageConverter extends AbstractGenericHttpMessageConverter<Object> {

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
        log.debug("readInternal content type={}",inputMessage.getHeaders().getContentType());
        if (MEDIA_TYPE.isCompatibleWith(inputMessage.getHeaders().getContentType())) {

//            final Schema<?> schema = RuntimeSchema.getSchema(clazz);
            final Schema<?> schema = RuntimeSchema.getSchema(CoolMessage.class);
            final Object message = schema.newMessage();

            try (final InputStream stream = inputMessage.getBody()) {
                ProtobufIOUtil.mergeFrom(stream, message, (Schema<Object>) schema);

                if(message instanceof CoolMessage){
                    CoolMessage msg = (CoolMessage) message;
                    return msg.getData();
                }

            }
        }

        throw new HttpMessageNotReadableException(
                "Unrecognized HTTP media type " + inputMessage.getHeaders().getContentType().getType() + ".");
    }


    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        MediaType contentType = outputMessage.getHeaders().getContentType();
        log.debug("current type:{}",contentType);

        OutputStream stream = null;
        long start = System.currentTimeMillis();
        
        try {

            CoolMessage coolMessage = new CoolMessage();
            coolMessage.setData(object);
            coolMessage.setUuid(UUID.randomUUID().toString());
            stream = outputMessage.getBody();

            ProtobufIOUtil.writeTo(stream, coolMessage, RuntimeSchema.getSchema(CoolMessage.class),
                                       LinkedBuffer.allocate());
            stream.flush();
        } finally {
            IOUtils.closeQuietly(stream);
        }

        log.debug("Output spend {}", System.currentTimeMillis()-start);


    }

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        writeInternal(o,httpOutputMessage);
    }

    @Override
    public Object read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return read(aClass,httpInputMessage);
    }
}