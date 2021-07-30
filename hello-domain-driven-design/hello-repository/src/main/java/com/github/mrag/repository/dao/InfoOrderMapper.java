package com.github.mrag.repository.dao;

import com.github.mrag.repository.persistence.InfoOrderDO;

public interface InfoOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(InfoOrderDO record);

    int insertSelective(InfoOrderDO record);

    InfoOrderDO selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(InfoOrderDO record);

    int updateByPrimaryKey(InfoOrderDO record);
}