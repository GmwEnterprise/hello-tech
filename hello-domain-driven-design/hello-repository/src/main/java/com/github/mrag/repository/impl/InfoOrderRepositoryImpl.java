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
import com.github.mrag.types.OrderItemId;
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
    protected void onUpdate(InfoOrder aggregate, EntityDiff<InfoOrder, OrderId> diff) {
        // 主单实体是否有变化
        if (diff.getDiffType() == DiffType.Modified) {
            InfoOrderDO orderDO = orderConverter.entityToData(aggregate);
            orderMapper.updateByPrimaryKey(orderDO);
        }

        //
        Diff<InfoOrderItem, OrderItemId> itemsDiff = diff.getDiff("items");
        if (itemsDiff instanceof ListDiff) {
            List<Diff<InfoOrderItem, OrderItemId>> itemListDiff =
                    ((ListDiff<InfoOrderItem, OrderItemId>) itemsDiff).getList();
            for (Diff<InfoOrderItem, OrderItemId> itemDiff : itemListDiff) {
                EntityDiff<InfoOrderItem, OrderItemId> entityItemDiff =
                        (EntityDiff<InfoOrderItem, OrderItemId>) itemDiff;
                if (entityItemDiff.getDiffType() == DiffType.Removed) {
                    InfoOrderItem item = entityItemDiff.getOldValue();
                    InfoOrderItemDO itemDO = orderItemConverter.entityToData(item);
                    orderItemMapper.deleteByPrimaryKey(itemDO.getOrderItemId());
                }
                if (entityItemDiff.getDiffType() == DiffType.Added) {
                    InfoOrderItem item = entityItemDiff.getNewValue();
                    InfoOrderItemDO itemDO = orderItemConverter.entityToData(item);
                    orderItemMapper.insert(itemDO);
                }
                if (entityItemDiff.getDiffType() == DiffType.Modified) {
                    InfoOrderItem item = entityItemDiff.getNewValue();
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
