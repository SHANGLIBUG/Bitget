package me.jing.kecheng.utils.bitget;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckSign {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String generate(String timestamp, String method, String requestPath,
                                  String queryString, String body, String secretKey)
            throws CloneNotSupportedException, InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {


        method = method.toUpperCase();
        body = StringUtils.defaultIfBlank(body, StringUtils.EMPTY);
        queryString = StringUtils.isBlank(queryString) ? StringUtils.EMPTY : "?" + queryString;
        String preHash = timestamp + method + requestPath + queryString + body;
        //log.info("preHash:{}", preHash);
        Mac mac = Mac.getInstance("HmacSHA256");
        byte[] secretKeyBytes = secretKey.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, "HmacSHA256");
        mac.init(secretKeySpec);

            return Base64.getEncoder().encodeToString(mac.doFinal(preHash.getBytes("UTF-8")));



    }
}
