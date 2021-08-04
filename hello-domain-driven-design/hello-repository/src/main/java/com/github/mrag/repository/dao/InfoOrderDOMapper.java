package com.github.mrag.repository.dao;

import com.github.mrag.repository.persistence.InfoOrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfoOrderDOMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(InfoOrderDO record);

    int insertSelective(InfoOrderDO record);

    InfoOrderDO selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(InfoOrderDO record);

    int updateByPrimaryKey(InfoOrderDO record);
}