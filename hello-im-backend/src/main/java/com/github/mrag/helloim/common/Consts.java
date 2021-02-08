package com.github.mrag.helloim.common;

public final class Consts {

    public interface RegExp {
        /**
         * 中文、字母、数字，长度为2-18
         */
        String USERNAME = "^[\\u4e00-\\u9fa5|\\w]{2,18}$";
        /**
         * 字母、数字、特殊符号，长度为4-20
         */
        String PASSWORD = "^[\\w,.\\\\\\[\\]/!@#$%^&*()_\\-+]{4,20}$";
        /**
         * 常规手机号码
         */
        String PHONE = "^(13[0-9]|14[57]|15[0-35-9]|18[0-35-9])\\d{8}$";
        String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String URL = "[a-zA-z]+://[^\\s]*";
    }
}
