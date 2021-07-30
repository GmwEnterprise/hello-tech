create table info_order
(
    order_id    bigint auto_increment comment '订单号'
        primary key,
    total_price decimal(8, 2)                       not null comment '订单总价',
    create_time timestamp default CURRENT_TIMESTAMP not null
);

create table info_order_item
(
    order_item_id bigint auto_increment comment '子单号'
        primary key,
    order_id      bigint        not null comment '主单号',
    item_id       int           not null comment '商品编号',
    price         decimal(8, 2) not null comment '单价'
);

