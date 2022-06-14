drop schema if exists easyshop;

create database easyshop;

use easyshop;

create table goods
(
    goods_id           int primary key auto_increment comment '商品id',
    goods_name         varchar(500) comment '商品名称',
    goods_price        decimal(10, 2) comment '商品售价',
    goods_introduction varchar(4000) comment '商品简介',
    goods_picture      varchar(500) comment '商品图片路径，多张图片用,隔开',
    goods_stock        int comment '商品库存',
    remark             varchar(4000) comment '备注'
);
alter table goods
    comment '商品表';

create table user_info
(
    user_id     int primary key auto_increment comment '用户id',
    username    varchar(200) comment '用户名',
    password    varchar(200) comment '密码',
    name        varchar(200) comment '姓名',
    user_type   int comment '用户类别，1：管理员，2：普通用户',
    balance     decimal(10, 2) comment '用户余额',
    create_time bigint comment '创建时间',
    remark      varchar(4000) comment '备注'
);
alter table user_info
    comment '用户表';

create table shopping_cart
(
    cart_id     int primary key auto_increment comment '购物车id',
    user_id     int comment '用户id',
    goods_id    int comment '商品id',
    status      int comment '状态,1：正常，0：已删除',
    create_time bigint comment '添加时间',
    remark      varchar(4000) comment '备注'
);
alter table shopping_cart
    comment '购物车表';

create table `order`
(
    order_id     int primary key auto_increment comment '订单id',
    user_id      int comment '用户id',
    goods_ids    varchar(4000) comment '商品id，多件商品用,号隔开',
    status       int comment '订单状态',
    create_time  bigint comment '创建时间',
    settle_time  bigint comment '结算时间',
    payment_time bigint comment '付款时间',
    remark       varchar(4000) comment '备注'
);
alter table `order` comment '订单表';