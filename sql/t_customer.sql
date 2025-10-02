create table t_customer
(
    id                bigserial
        constraint idx_28558_primary
            primary key,
    clue_id           bigint
        constraint t_customer_ibfk_1
            references t_clue
            on update cascade on delete restrict,
    product           bigint
        constraint t_customer_ibfk_2
            references t_product
            on update cascade on delete restrict,
    description       varchar(255),
    next_contact_time timestamp with time zone,
    create_time       timestamp with time zone,
    create_by         bigint
        constraint t_customer_ibfk_3
            references t_user
            on update cascade on delete restrict,
    edit_time         timestamp with time zone,
    edit_by           bigint
        constraint t_customer_ibfk_4
            references t_user
            on update cascade on delete restrict
);

comment on table t_customer is '客户表';

comment on column t_customer.id is '主键，自动增长，客户ID';

comment on column t_customer.clue_id is '线索ID';

comment on column t_customer.product is '选购产品';

comment on column t_customer.description is '客户描述';

comment on column t_customer.next_contact_time is '下次联系时间';

comment on column t_customer.create_time is '创建时间';

comment on column t_customer.create_by is '创建人';

comment on column t_customer.edit_time is '编辑时间';

comment on column t_customer.edit_by is '编辑人';

alter table t_customer
    owner to postgres;

create index idx_28558_t_customer_ibfk_1
    on t_customer (clue_id);

create index idx_28558_t_customer_ibfk_2
    on t_customer (product);

create index idx_28558_t_customer_ibfk_4
    on t_customer (edit_by);

create index idx_28558_t_customer_ibfk_3
    on t_customer (create_by);

INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1, 10, 3, '2131231', '2023-05-06 15:36:59.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (2, 8, 6, '124气味儿群', '2023-05-05 00:00:00.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (3, 6, 6, '1232强21312', '2023-05-06 00:00:00.000000 +00:00', '2023-04-28 15:42:15.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (4, 2, 3, '阿萨的人', '2023-05-05 00:00:00.000000 +00:00', '2023-04-28 15:54:04.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (5, 10, 1, '234234', '2023-05-05 00:00:00.000000 +00:00', '2023-04-28 15:56:44.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (6, 17, 2, '是的啊所大', '2023-05-05 00:00:00.000000 +00:00', '2023-04-28 15:59:33.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (7, 5, 2, '12312312', '2023-05-12 00:00:00.000000 +00:00', '2023-05-04 10:03:05.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (8, 19, 2, '气味儿群翁', '2023-05-04 10:03:18.000000 +00:00', '2023-05-04 10:03:20.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (9, 14, 4, '沃尔沃二', '2023-06-09 00:00:00.000000 +00:00', '2023-05-04 10:03:39.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (10, 23, 4, '沃尔沃二翁', '2023-05-23 00:00:00.000000 +00:00', '2023-05-04 10:03:53.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (11, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (12, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (13, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (14, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (15, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (17, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (18, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (19, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (20, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (21, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (22, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (23, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (24, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (25, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (26, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (27, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (28, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (29, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (30, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (31, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (32, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (33, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (34, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (35, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (36, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (37, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (38, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_customer (id, clue_id, product, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (39, 13, 2, '沃尔沃二', '2023-05-13 00:00:00.000000 +00:00', '2023-05-04 10:04:03.000000 +00:00', 1, null, null);
