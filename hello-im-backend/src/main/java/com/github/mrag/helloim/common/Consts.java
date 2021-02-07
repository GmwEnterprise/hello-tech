package com.github.mrag.helloim.common;

public final class Consts {

    public interface RegExp {
        String USERNAME = "^\\w{1,18}$";
        String PASSWORD = "^\\w{4,18}$";
        String PHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
        String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String URL = "[a-zA-z]+://[^\\s]*";
    }
}
