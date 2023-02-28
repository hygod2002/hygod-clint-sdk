package com.hygod.hygodclintsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @Author: hygod
 * @Date: 2023/2/24
 * @Time: 21:05
 * @Description:
 */
public class SignUtils {
    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body+'.'+secretKey;
        return md5.digestHex(content);
    }
}
