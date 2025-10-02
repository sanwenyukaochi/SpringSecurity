create table t_tran
(
    id                bigserial
        constraint idx_28606_primary
            primary key,
    tran_no           varchar(255),
    customer_id       bigint
        constraint t_tran_ibfk_1
            references t_customer
            on update cascade on delete restrict,
    money             numeric(10, 2),
    expected_date     timestamp with time zone,
    stage             bigint
        constraint t_tran_ibfk_2
            references t_dic_value
            on update cascade on delete restrict,
    description       varchar(255),
    next_contact_time timestamp with time zone,
    create_time       timestamp with time zone,
    create_by         bigint
        constraint t_tran_ibfk_3
            references t_user
            on update cascade on delete restrict,
    edit_time         timestamp with time zone,
    edit_by           bigint
        constraint t_tran_ibfk_4
            references t_user
            on update cascade on delete restrict
);

comment on table t_tran is '交易表';

comment on column t_tran.id is '主键，自动增长，交易ID';

comment on column t_tran.tran_no is '交易流水号';

comment on column t_tran.customer_id is '客户ID';

comment on column t_tran.money is '交易金额';

comment on column t_tran.expected_date is '预计成交日期';

comment on column t_tran.stage is '交易所处阶段';

comment on column t_tran.description is '交易描述';

comment on column t_tran.next_contact_time is '下次联系时间';

comment on column t_tran.create_time is '创建时间';

comment on column t_tran.create_by is '创建人';

comment on column t_tran.edit_time is '编辑时间';

comment on column t_tran.edit_by is '编辑人';

alter table t_tran
    owner to postgres;

create index idx_28606_t_tran_ibfk_1
    on t_tran (customer_id);

create index idx_28606_t_tran_ibfk_2
    on t_tran (stage);

create index idx_28606_t_tran_ibfk_3
    on t_tran (create_by);

create index idx_28606_t_tran_ibfk_4
    on t_tran (edit_by);

INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1, '202311121932019432', 7, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (5, '202311121932019431', 1, 120000.00, '2023-11-14 00:00:00.000000 +00:00', 42, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (6, '202311121932019432', 2, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 42, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (7, '202311121932019432', 3, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 42, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (8, '202311121932019432', 4, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 42, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (9, '202311121932019432', 6, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 42, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (10, '202311121932019432', 5, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (11, '202311121932019432', 8, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (12, '202311121932019432', 10, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (13, '202311121932019432', 11, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (14, '202311121932019432', 12, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (15, '202311121932019432', 13, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (16, '202311121932019432', 14, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (17, '202311121932019432', 15, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_tran (id, tran_no, customer_id, money, expected_date, stage, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (612, '202311121932019432', 9, 93000.00, '2023-11-12 00:00:00.000000 +00:00', 12, '123131', '2023-11-29 00:00:00.000000 +00:00', '2023-11-12 19:32:02.000000 +00:00', 1, null, null);
