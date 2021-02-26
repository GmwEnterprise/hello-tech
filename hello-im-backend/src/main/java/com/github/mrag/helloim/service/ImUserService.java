package com.github.mrag.helloim.service;

import com.github.mrag.helloim.common.enums.UserStatus;
import com.github.mrag.helloim.domain.ImUser;

public interface ImUserService {

    ImUser findUserById(Integer userId);

    String signInByUsername(String username, String password);

    String signOn(ImUser user);

    UserStatus getUserStatus(int userId, String username);
}
