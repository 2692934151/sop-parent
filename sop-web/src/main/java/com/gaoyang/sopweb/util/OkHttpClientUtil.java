package com.gaoyang.sopweb.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.util.Map;

public class OkHttpClientUtil {

    /**
     * post请求，请求参数json串，默认"application/json;charset=utf-8"，可变header
     * @param url
     * @param object
     * @return
     * @throws Exception
     */
    public static String sendJSONPostRequest(String url, Object object, Map<String,String> headers)throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder().sslSocketFactory(OkHttpsUtils.createSSLSocketFactory(), new TrustAllCerts())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .build();
        RequestBody requestBody = null;
        if (object instanceof JSONObject) {
            requestBody = FormBody.create(MediaType.get("application/json;charset=utf-8"), ((JSONObject) object).toJSONString());
        } else if (object instanceof FormBody.Builder) {
            requestBody = ((FormBody.Builder) object).build();
        } else {
            throw new RuntimeException("没有请求参数没有对应的解析类型");
        }
        Request.Builder builder = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json;charset=utf-8");
        for (String key:
                headers.keySet()) {
            builder.addHeader(key,headers.get(key));
        }
        Request request = builder.post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body() == null ? "" : response.body().string();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * get请求，请求参数json串，默认"application/json;charset=utf-8"，可变header
     * @param url
     * @param headers
     * @return
     * @throws Exception
     */
    public static String sendGetRequest(String url, Map<String,String> headers)throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder().sslSocketFactory(OkHttpsUtils.createSSLSocketFactory(), new TrustAllCerts())
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .build();
        Request.Builder builder = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json;charset=utf-8");
        for (String key:
                headers.keySet()) {
            builder.addHeader(key,headers.get(key));
        }
        Request request = builder.get().build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body() == null ? "" : response.body().string();

    }
}
