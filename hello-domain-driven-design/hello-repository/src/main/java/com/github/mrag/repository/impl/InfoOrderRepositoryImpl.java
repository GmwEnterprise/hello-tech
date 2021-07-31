package com.github.mrag.repository.impl;

import com.github.mrag.common.utils.DbTransactionExecutor;
import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.domain.repository.InfoOrderRepository;
import com.github.mrag.repository.AbstractRepositorySupport;
import com.github.mrag.repository.converter.InfoOrderConverter;
import com.github.mrag.repository.converter.InfoOrderItemConverter;
import com.github.mrag.repository.dao.InfoOrderItemMapper;
import com.github.mrag.repository.dao.InfoOrderMapper;
import com.github.mrag.repository.diff.Diff;
import com.github.mrag.repository.diff.DiffType;
import com.github.mrag.repository.diff.EntityDiff;
import com.github.mrag.repository.diff.ListDiff;
import com.github.mrag.repository.persistence.InfoOrderDO;
import com.github.mrag.repository.persistence.InfoOrderItemDO;
import com.github.mrag.types.OrderId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InfoOrderRepositoryImpl
        extends AbstractRepositorySupport<InfoOrder, OrderId>
        implements InfoOrderRepository {

    private final InfoOrderMapper orderMapper;
    private final InfoOrderItemMapper orderItemMapper;
    private final InfoOrderConverter orderConverter;
    private final InfoOrderItemConverter orderItemConverter;

    private final DbTransactionExecutor transactionExecutor;

    public InfoOrderRepositoryImpl(InfoOrderMapper orderMapper,
                                   InfoOrderItemMapper orderItemMapper,
                                   InfoOrderConverter orderConverter,
                                   InfoOrderItemConverter orderItemConverter,
                                   DbTransactionExecutor transactionExecutor) {
        super(InfoOrder.class);
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.orderConverter = orderConverter;
        this.orderItemConverter = orderItemConverter;
        this.transactionExecutor = transactionExecutor;
    }

    @Override
    protected void onInsert(InfoOrder aggregate) {
        transactionExecutor.execute(() -> {
            InfoOrderDO infoOrderDO = orderConverter.entityToData(aggregate);
            orderMapper.insert(infoOrderDO);

            if (aggregate.getItems() != null) {
                aggregate.getItems().forEach(item -> {
                    InfoOrderItemDO itemDO = orderItemConverter.entityToData(item);
                    orderItemMapper.insert(itemDO);
                });
            }
        });
    }

    @Override
    protected InfoOrder onSelect(OrderId orderId) {
        InfoOrderDO infoOrderDO = orderMapper.selectByPrimaryKey(orderId.getId());
        List<InfoOrderItemDO> items = orderItemMapper.selectByOrderId(orderId.getId());
        InfoOrder order = orderConverter.dataToEntity(infoOrderDO);
        order.setItems(items.stream().map(orderItemConverter::dataToEntity).collect(Collectors.toList()));
        return order;
    }

    @Override
    protected void onUpdate(InfoOrder aggregate, EntityDiff diff) {
        // 主单实体是否有变化
        if (diff.isSelfModified()) {
            InfoOrderDO orderDO = orderConverter.entityToData(aggregate);
            orderMapper.updateByPrimaryKey(orderDO);
        }

        //
        Diff itemsDiff = diff.getDiff("items");
        if (itemsDiff instanceof ListDiff) {
            ListDiff itemListDiff = (ListDiff) itemsDiff;
            for (Diff itemDiff : itemListDiff) {
                if (itemDiff.getType() == DiffType.Removed) {
                    InfoOrderItem item = (InfoOrderItem) itemDiff.getOldValue();
                    InfoOrderItemDO itemDO = orderItemConverter.entityToData(item);
                    orderItemMapper.deleteByPrimaryKey(itemDO.getOrderItemId());
                }
                if (itemDiff.getType() == DiffType.Added) {
                    InfoOrderItem item = (InfoOrderItem) itemDiff.getNewValue();
                    InfoOrderItemDO itemDO = orderItemConverter.entityToData(item);
                    orderItemMapper.insert(itemDO);
                }
                if (itemDiff.getType() == DiffType.Modified) {
                    InfoOrderItem item = (InfoOrderItem) itemDiff.getNewValue();
                    InfoOrderItemDO itemDO = orderItemConverter.entityToData(item);
                    orderItemMapper.updateByPrimaryKey(itemDO);
                }
            }
        }
    }

    @Override
    protected void onDelete(InfoOrder aggregate) {

    }
}
