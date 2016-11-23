package com.shan.mvp_rxjava_retrofit.net;

import com.shan.mypubliclibrary.utils.LogUtils;
import com.shan.mypubliclibrary.utils.TimeUtils;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpEngine;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by chenjunshan on 2016/8/19.
 */

public class HttpInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();

        if (!HttpEngine.hasBody(response) || bodyEncoded(response.headers())) {
            return response;
        }

        try {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            if (!isPlaintext(buffer)) {
                LogUtils.i("<-- END HTTP (binary " + buffer.size() + "-byte body omitted)");
                return response;
            }

            if (contentLength != 0) {
                LogUtils.i("==================================" + TimeUtils.getTime("yyyy-MM-dd HH:mm:ss") + "==================================");
                //如果result的长度大于1000则分段打印输出
                String result = buffer.clone().readString(charset);
                int length = result.length();
                int printNum = 3000;//每次打印的字数
                if (length > printNum) {
                    int number = length / printNum;
                    int beyond = length % printNum;
                    int index = 0;
                    for (int i = 0; i < number; i++) {
                        LogUtils.i(result.substring(index, index + printNum));
                        index = index + printNum;
                    }
                    LogUtils.i(result.substring(index, index + beyond));
                } else {
                    LogUtils.i(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

}
