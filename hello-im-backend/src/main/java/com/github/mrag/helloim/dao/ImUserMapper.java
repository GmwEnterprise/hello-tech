package com.github.mrag.helloim.dao;

import com.github.mrag.helloim.domain.ImUser;

public interface ImUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImUser record);

    int insertSelective(ImUser record);

    ImUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImUser record);

    int updateByPrimaryKey(ImUser record);
}