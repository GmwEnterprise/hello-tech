package com.github.mrag.helloim.service.impl;

import com.github.mrag.helloim.common.Enums;
import com.github.mrag.helloim.common.Exceptions;
import com.github.mrag.helloim.common.HttpToken;
import com.github.mrag.helloim.common.HttpTokenUtils;
import com.github.mrag.helloim.dao.ImUserMapper;
import com.github.mrag.helloim.domain.ImUser;
import com.github.mrag.helloim.service.ImUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class ImUserServiceImpl implements ImUserService {
    @Resource
    ImUserMapper imUserMapper;
    @Resource
    HttpTokenUtils httpTokenUtils;

    @Override
    public ImUser findUserById(Integer userId) {
        return imUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public String signInByUsername(String username, String password) {
        Integer userId = Optional.ofNullable(imUserMapper.selectIdByUsername(username, password))
                                 .orElseThrow(() -> Exceptions.signInError("账户不存在或密码错误"));
        return httpTokenUtils.tokenSerialize(new HttpToken(userId, username));
    }

    @Override
    public String signOn(ImUser user) {
        imUserMapper.insertSelective(user);
        return httpTokenUtils.tokenSerialize(new HttpToken(user.getId(), user.getUsername()));
    }

    @Override
    public Enums.UserStatus getUserStatus(int userId, String username) {
        // todo 待扩展
        return Enums.UserStatus.NORMAL;
    }
}
