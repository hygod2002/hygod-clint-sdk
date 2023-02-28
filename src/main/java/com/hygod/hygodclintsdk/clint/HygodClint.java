package com.hygod.hygodclintsdk.clint;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.hygod.hygodclintsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.hygod.hygodclintsdk.utils.SignUtils.getSign;

/**
 * @Author: hygod
 * @Date: 2023/2/24
 * @Time: 19:20
 * @Description:
 */
public class HygodClint {

    private String accessKey;
    private String secretKey;
    private static final String GATEWAY_HOST = "http://localhost:8090";
    public HygodClint(String accessKey, String secretKey){
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST+"/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    public String getNameByPost( String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post(GATEWAY_HOST+"/api/name/", paramMap);
        System.out.println(result);
        return result;
    }


    private Map<String, String> getHeaders(String body){
        Map<String, String> headers = new HashMap<>();
        headers.put("accessKey",accessKey);
//        headers.put("secretKey",secretKey);
        headers.put("nonce", RandomUtil.randomNumbers(4));
        headers.put("body",body);
        headers.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        headers.put("sign",getSign(body,secretKey));
        return headers;
    }
    public String getUsernameByPost( User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST+"/api/name/user")
                .addHeaders(getHeaders(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }
}
