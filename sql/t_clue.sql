create table t_clue
(
    id                bigserial
        constraint idx_28546_primary
            primary key,
    owner_id          bigint
        constraint t_clue_ibfk_4
            references t_user
            on update cascade on delete restrict,
    activity_id       bigint
        constraint t_clue_ibfk_7
            references t_activity
            on update cascade on delete restrict,
    full_name         varchar(64),
    appellation       bigint
        constraint t_clue_ibfk_1
            references t_dic_value
            on update cascade on delete restrict,
    phone             varchar(18),
    weixin            varchar(128),
    qq                varchar(20),
    email             varchar(128),
    age               bigint,
    job               varchar(64),
    year_income       numeric(10, 2),
    address           varchar(128),
    need_loan         bigint
        constraint t_clue_ibfk_8
            references t_dic_value
            on update cascade on delete restrict,
    intention_state   bigint
        constraint t_clue_ibfk_9
            references t_dic_value
            on update cascade on delete restrict,
    intention_product bigint
        constraint t_clue_ibfk_10
            references t_product
            on update cascade on delete restrict,
    state             bigint
        constraint t_clue_ibfk_2
            references t_dic_value
            on update cascade on delete restrict,
    source            bigint
        constraint t_clue_ibfk_3
            references t_dic_value
            on update cascade on delete restrict,
    description       varchar(255),
    next_contact_time timestamp with time zone,
    create_time       timestamp with time zone,
    create_by         bigint
        constraint t_clue_ibfk_5
            references t_user
            on update cascade on delete restrict,
    edit_time         timestamp with time zone,
    edit_by           bigint
        constraint t_clue_ibfk_6
            references t_user
            on update cascade on delete restrict
);

comment on table t_clue is '线索表';

comment on column t_clue.id is '主键，自动增长，线索ID';

comment on column t_clue.owner_id is '线索所属人ID';

comment on column t_clue.activity_id is '活动ID';

comment on column t_clue.full_name is '姓名';

comment on column t_clue.appellation is '称呼';

comment on column t_clue.phone is '手机号';

comment on column t_clue.weixin is '微信号';

comment on column t_clue.qq is 'QQ号';

comment on column t_clue.email is '邮箱';

comment on column t_clue.age is '年龄';

comment on column t_clue.job is '职业';

comment on column t_clue.year_income is '年收入';

comment on column t_clue.address is '地址';

comment on column t_clue.need_loan is '是否需要贷款（0不需要，1需要）';

comment on column t_clue.intention_state is '意向状态';

comment on column t_clue.intention_product is '意向产品';

comment on column t_clue.state is '线索状态';

comment on column t_clue.source is '线索来源';

comment on column t_clue.description is '线索描述';

comment on column t_clue.next_contact_time is '下次联系时间';

comment on column t_clue.create_time is '创建时间';

comment on column t_clue.create_by is '创建人';

comment on column t_clue.edit_time is '编辑时间';

comment on column t_clue.edit_by is '编辑人';

alter table t_clue
    owner to postgres;

create index idx_28546_state
    on t_clue (state);

create index idx_28546_create_by
    on t_clue (create_by);

create index idx_28546_edit_by
    on t_clue (edit_by);

create index idx_28546_appellation
    on t_clue (appellation);

create index idx_28546_t_clue_ibfk_10
    on t_clue (intention_product);

create index idx_28546_source
    on t_clue (source);

create index idx_28546_owner
    on t_clue (owner_id);

create index idx_28546_t_clue_ibfk_9
    on t_clue (intention_state);

create index idx_28546_t_clue_ibfk_7
    on t_clue (activity_id);

create index idx_28546_t_clue_ibfk_8
    on t_clue (need_loan);

INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 50, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-07-17 15:17:52.000000 +00:00', null, '2023-12-18 18:56:18.000000 +00:00', 1);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (2, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 50, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:13.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (3, 2, 7, '张翔', 18, '13876903226', '13876903226', '123456', null, 26, null, 9.00, '天津和平', 50, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00.000000 +00:00', '2023-06-10 01:01:17.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (4, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 50, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-04-12 15:17:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (5, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 50, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:19.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (6, 2, 7, '张翔', 18, '13876903226', '13876903226', null, null, 26, null, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00.000000 +00:00', '2023-06-10 01:01:23.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (7, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-04-12 15:17:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (8, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 49, 47, 8, 24, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:25.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (9, 2, 7, '张翔', 18, '13876903226', '13876903226', null, null, 26, null, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-28 13:24:10.000000 +00:00', '2023-06-10 01:01:28.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (10, 1, 46, '王杰89890890', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '', 10.00, '北京亦庄', 49, 46, 5, -1, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-04-12 15:17:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (11, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 49, 47, 8, 24, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:30.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (12, 2, 7, '张翔', 18, '13876903226', '13876903226', null, null, 26, null, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00.000000 +00:00', '2023-06-10 01:01:33.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (13, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, -1, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-04-12 15:17:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (14, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 49, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:36.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (15, 2, 7, '张翔', 18, '13876903226', '13876903226', null, null, 26, null, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00.000000 +00:00', '2023-06-10 01:01:38.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (16, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-04-12 15:17:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (17, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 49, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:41.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (18, 2, 7, '张翔', 18, '13876903226', '13876903226', null, null, 26, null, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00.000000 +00:00', '2023-06-10 01:01:44.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (19, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, -1, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-06-12 15:17:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (20, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 49, 47, 8, 24, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:46.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (21, 2, 7, '张翔', 18, '13876903226', '13876903226', null, null, 26, null, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00.000000 +00:00', '2023-06-10 01:01:46.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (22, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-04-12 15:17:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (23, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 49, 47, 8, -1, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:46.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (24, 2, 7, '张翔', 18, '13876903226', '13876903226', null, null, 26, null, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00.000000 +00:00', '2023-06-10 01:01:46.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (25, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-04-12 15:17:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (26, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 49, 47, 8, 24, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:46.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (27, 2, 7, '张翔', 18, '13876903226', '13876903226', null, null, 26, null, 9.00, '天津和平', 49, 48, 10, 30, 44, '有购车意向，需要跟踪', '2023-04-15 00:00:00.000000 +00:00', '2023-06-10 01:01:46.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (28, 1, 46, '王杰', 18, '13700000000', '13700000000', '13700000000', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', 49, 46, 5, 24, 3, '近期在看车', '2023-04-27 10:33:47.000000 +00:00', '2023-06-12 15:17:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (29, 1, 47, '张峰', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', 49, 47, 8, 24, 33, '通过打电话获取的线索', '2023-04-30 10:33:51.000000 +00:00', '2023-06-10 01:01:46.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (31, 3, 2, '213213', 20, '12312312', '12312312', '2312312', '12312312', 12, '341231', 2131231.00, '12312312', 50, 46, 7, 24, 44, 'asfeefsdewrewr', '2023-04-27 16:48:30.000000 +00:00', '2023-06-10 01:01:46.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1109, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 14, '近期在看车', '2023-11-27 20:33:25.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1110, 1, 47, '张怡然', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 3, '通过打电话获取的线索', '2023-11-30 10:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1111, 2, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', null, 26, null, 9.00, '天津和平', null, null, null, null, 16, '有购车意向，需要跟踪', '2023-11-15 10:30:00.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1112, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 17, '近期在看车', '2023-12-27 09:20:21.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1113, 1, 47, '张珊珊', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 2, '通过打电话获取的线索', '2023-11-30 13:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1114, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 22, '近期在看车', '2023-11-27 20:33:25.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1115, 1, 47, '张怡然', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 39, '通过打电话获取的线索', '2023-11-30 10:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1116, 2, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', null, 26, null, 9.00, '天津和平', null, null, null, null, 33, '有购车意向，需要跟踪', '2023-11-15 10:30:00.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1117, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 36, '近期在看车', '2023-12-27 09:20:21.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1118, 1, 47, '张珊珊', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 23, '通过打电话获取的线索', '2023-11-30 13:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1119, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 43, '近期在看车', '2023-11-27 20:33:25.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1120, 1, 47, '张怡然', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 44, '通过打电话获取的线索', '2023-11-30 10:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1121, 2, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', null, 26, null, 9.00, '天津和平', null, null, null, null, 45, '有购车意向，需要跟踪', '2023-11-15 10:30:00.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1122, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 25, '近期在看车', '2023-12-27 09:20:21.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1123, 1, 47, '张珊珊', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 14, '通过打电话获取的线索', '2023-11-30 13:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1124, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 3, '近期在看车', '2023-11-27 20:33:25.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1125, 1, 47, '张怡然', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 16, '通过打电话获取的线索', '2023-11-30 10:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1126, 2, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', null, 26, null, 9.00, '天津和平', null, null, null, null, 17, '有购车意向，需要跟踪', '2023-11-15 10:30:00.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1127, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 2, '近期在看车', '2023-12-27 09:20:21.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1128, 1, 47, '张珊珊', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 22, '通过打电话获取的线索', '2023-11-30 13:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1129, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 39, '近期在看车', '2023-11-27 20:33:25.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1130, 1, 47, '张怡然', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 33, '通过打电话获取的线索', '2023-11-30 10:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1131, 2, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', null, 26, null, 9.00, '天津和平', null, null, null, null, 36, '有购车意向，需要跟踪', '2023-11-15 10:30:00.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1132, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 23, '近期在看车', '2023-12-27 09:20:21.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1133, 1, 47, '张珊珊', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 43, '通过打电话获取的线索', '2023-11-30 13:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1134, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 44, '近期在看车', '2023-11-27 20:33:25.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1135, 1, 47, '张怡然', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 45, '通过打电话获取的线索', '2023-11-30 10:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1136, 2, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', null, 26, null, 9.00, '天津和平', null, null, null, null, 25, '有购车意向，需要跟踪', '2023-11-15 10:30:00.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1137, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 14, '近期在看车', '2023-12-27 09:20:21.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1138, 1, 47, '张珊珊', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 3, '通过打电话获取的线索', '2023-11-30 13:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1139, 1, 46, '王杰', 18, '13700000000', '13700000000', '230989432', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 16, '近期在看车', '2023-11-27 20:33:25.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1140, 1, 47, '张怡然', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 17, '通过打电话获取的线索', '2023-11-30 10:33:51.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1141, 2, 7, '张翔宇', 18, '13876903226', '13876903226', '1298094321', null, 26, null, 9.00, '天津和平', null, null, null, null, 2, '有购车意向，需要跟踪', '2023-11-15 10:30:00.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1142, 1, 46, '王世坤', 18, '13700000000', '13700000000', '209836613', 'wangjie@163.com', 32, '工程师', 10.00, '北京亦庄', null, null, null, null, 22, '近期在看车', '2023-12-27 09:20:21.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_clue (id, owner_id, activity_id, full_name, appellation, phone, weixin, qq, email, age, job, year_income, address, need_loan, intention_state, intention_product, state, source, description, next_contact_time, create_time, create_by, edit_time, edit_by) VALUES (1143, 1, 47, '张珊珊', 41, '13700000001', '13700000001', null, null, 28, null, 8.00, '河北廊坊', null, null, null, null, 39, '通过打电话获取的线索', '2023-11-30 13:33:51.000000 +00:00', null, null, null, null);
