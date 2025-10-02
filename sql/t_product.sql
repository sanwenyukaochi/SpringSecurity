create table t_product
(
    id            bigserial
        constraint idx_28583_primary
            primary key,
    name          varchar(128),
    guide_price_s numeric(10, 2),
    guide_price_e numeric(10, 2),
    quotation     numeric(10, 2),
    state         bigint,
    create_time   timestamp with time zone,
    create_by     bigint
        constraint t_product_ibfk_1
            references t_user
            on update cascade on delete restrict,
    edit_time     timestamp with time zone,
    edit_by       bigint
        constraint t_product_ibfk_2
            references t_user
            on update cascade on delete restrict
);

comment on table t_product is '产品表';

comment on column t_product.id is '主键，自动增长，线索ID';

comment on column t_product.name is '产品名称';

comment on column t_product.guide_price_s is '官方指导起始价';

comment on column t_product.guide_price_e is '官方指导最高价';

comment on column t_product.quotation is '经销商报价';

comment on column t_product.state is '状态 0在售 1售罄';

comment on column t_product.create_time is '创建时间';

comment on column t_product.create_by is '创建人';

comment on column t_product.edit_time is '编辑时间';

comment on column t_product.edit_by is '编辑人';

alter table t_product
    owner to postgres;

create index idx_28583_t_product_ibfk_1
    on t_product (create_by);

create index idx_28583_t_product_ibfk_2
    on t_product (edit_by);

INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (1, '海鸥', 10.18, 10.58, 9.28, 0, '2023-04-06 18:25:00.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (2, '比亚迪e2', 10.28, 10.98, 9.78, 0, '2023-04-03 15:26:12.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (3, '比亚迪e3', 15.48, 15.98, 14.38, 0, '2023-04-03 11:29:08.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (4, '海豚', 11.68, 13.68, 10.86, 0, '2023-04-09 10:27:47.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (5, '秦EV', 12.99, 16.98, 11.98, 0, '2023-04-08 15:28:23.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (6, '秦PLUS DM-i', 9.98, 16.58, 9.06, 0, '2023-04-10 19:29:53.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (7, '秦PLUS EV', 12.98, 18.08, 12.38, 0, '2023-04-05 09:30:31.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (8, '海豹', 21.28, 28.98, 20.18, 0, '2023-04-02 10:31:08.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (9, '汉DM', 21.78, 32.18, 19.88, 0, '2023-04-07 16:31:45.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_product (id, name, guide_price_s, guide_price_e, quotation, state, create_time, create_by, edit_time, edit_by) VALUES (10, '宋PLUS EV', 18.68, 20.38, 17.86, 0, '2023-03-18 21:33:08.000000 +00:00', 1, null, null);
