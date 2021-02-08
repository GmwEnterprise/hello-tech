package com.github.mrag.helloim.dao;

import com.github.mrag.helloim.domain.ImUserRelationship;

public interface ImUserRelationshipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImUserRelationship record);

    int insertSelective(ImUserRelationship record);

    ImUserRelationship selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImUserRelationship record);

    int updateByPrimaryKey(ImUserRelationship record);
}