package com.alna.software.ci.utils;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

public final class JenkinsConvert {
    private JenkinsConvert() {
    }


    public static String toString(String base64) {
        JenkinsValidate.notEmpty(base64, "Base64 string is mandatory");

        return StringUtils.newStringUtf8(Base64.decodeBase64(base64));
    }
}
