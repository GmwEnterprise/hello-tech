package com.github.mrag.repository.dao;

import com.github.mrag.repository.persistence.InfoOrderItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfoOrderItemDOMapper {
    int deleteByPrimaryKey(Long orderItemId);

    int insert(InfoOrderItemDO record);

    int insertSelective(InfoOrderItemDO record);

    InfoOrderItemDO selectByPrimaryKey(Long orderItemId);

    int updateByPrimaryKeySelective(InfoOrderItemDO record);

    int updateByPrimaryKey(InfoOrderItemDO record);

    List<InfoOrderItemDO> selectByOrderId(Long id);
}