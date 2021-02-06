package com.github.mrag.helloim.service;

import com.github.mrag.helloim.domain.ImUser;

public interface ImUserService {

    ImUser findUserById(Integer userId);

    String signInByUsername(String username, String password);
}
