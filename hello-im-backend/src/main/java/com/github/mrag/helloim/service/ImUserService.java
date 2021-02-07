package com.github.mrag.helloim.service;

import com.github.mrag.helloim.common.Enums;
import com.github.mrag.helloim.domain.ImUser;

public interface ImUserService {

    ImUser findUserById(Integer userId);

    String signInByUsername(String username, String password);

    String signOn(ImUser user);

    Enums.UserStatus getUserStatus(int userId, String username);
}
