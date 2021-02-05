package com.github.mrag.helloim.dao;

import com.github.mrag.helloim.domain.ImUserMessage;

public interface ImUserMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImUserMessage record);

    int insertSelective(ImUserMessage record);

    ImUserMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImUserMessage record);

    int updateByPrimaryKey(ImUserMessage record);
}