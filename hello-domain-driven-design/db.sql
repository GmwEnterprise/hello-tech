drop table if exists info_order;
create table info_order
(
    order_id        bigint auto_increment comment '订单号'
        primary key,
    total_price     decimal(8, 2)                       not null comment '订单总价',
    customer_name   varchar(32)                         not null comment '客户姓名',
    sub_order_count int                                 not null comment '子单条数',
    create_time     timestamp default CURRENT_TIMESTAMP not null comment '订单创建时间',
    finish_time     timestamp comment '订单完成时间'
);
drop table if exists info_order_item;
create table info_order_item
(
    order_item_id bigint auto_increment comment '子单号'
        primary key,
    order_id      bigint        not null comment '主单号',
    item_id       int           not null comment '商品编号',
    price         decimal(8, 2) not null comment '单价'
);

