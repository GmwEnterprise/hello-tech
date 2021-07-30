package com.github.mrag.repository.dao;

import com.github.mrag.repository.persistence.InfoOrderItemDO;

public interface InfoOrderItemMapper {
    int deleteByPrimaryKey(Long orderItemId);

    int insert(InfoOrderItemDO record);

    int insertSelective(InfoOrderItemDO record);

    InfoOrderItemDO selectByPrimaryKey(Long orderItemId);

    int updateByPrimaryKeySelective(InfoOrderItemDO record);

    int updateByPrimaryKey(InfoOrderItemDO record);
}