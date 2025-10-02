create table t_customer_remark
(
    id           bigserial
        constraint idx_28563_primary
            primary key,
    customer_id  bigint
        constraint t_customer_remark_ibfk_1
            references t_customer
            on update cascade on delete restrict,
    note_way     bigint
        constraint t_customer_remark_ibfk_2
            references t_dic_value
            on update cascade on delete restrict,
    note_content varchar(255),
    create_by    bigint
        constraint t_customer_remark_ibfk_3
            references t_user
            on update cascade on delete restrict,
    create_time  timestamp with time zone,
    edit_time    timestamp with time zone,
    edit_by      bigint
        constraint t_customer_remark_ibfk_4
            references t_user
            on update cascade on delete restrict,
    deleted      bigint
);

comment on table t_customer_remark is '客户跟踪记录表';

comment on column t_customer_remark.id is '主键，自动增长，客户备注ID';

comment on column t_customer_remark.customer_id is '客户ID';

comment on column t_customer_remark.note_way is '跟踪方式';

comment on column t_customer_remark.note_content is '跟踪内容';

comment on column t_customer_remark.create_by is '跟踪人';

comment on column t_customer_remark.create_time is '跟踪时间';

comment on column t_customer_remark.edit_time is '编辑时间';

comment on column t_customer_remark.edit_by is '编辑人';

comment on column t_customer_remark.deleted is '删除状态（0正常，1删除）';

alter table t_customer_remark
    owner to postgres;

create index idx_28563_t_customer_remark_ibfk_2
    on t_customer_remark (note_way);

create index idx_28563_t_customer_remark_ibfk_1
    on t_customer_remark (customer_id);

create index idx_28563_t_customer_remark_ibfk_3
    on t_customer_remark (create_by);

create index idx_28563_t_customer_remark_ibfk_4
    on t_customer_remark (edit_by);

INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (1, 10, 65, '1111111111111111', 1, '2023-05-04 15:25:51.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (2, 10, 64, '2222222222222', 1, '2023-05-04 15:28:13.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (3, 10, 63, 'EREWREWRWRWR', 1, '2023-05-04 16:21:03.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (4, 7, 61, '13213123', 1, '2023-05-17 17:36:16.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (5, 10, 62, '2342423423423', 1, '2023-05-17 17:36:33.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (6, 1, 65, '3212321321', 1, '2023-05-21 23:50:42.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (7, 1, 64, 'eqwewqeqwe', 1, '2023-05-21 23:50:46.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (8, 1, 63, 'wqeqwewqeqw', 1, '2023-05-21 23:50:48.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (9, 8, 61, 'ewqeqweq', 1, '2023-05-21 23:50:59.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (10, 5, 62, 'weqweqweqweq', 1, '2023-05-21 23:51:05.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (11, 5, 65, 'weqwewqeqw', 1, '2023-05-21 23:51:08.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (12, 5, 64, '23213213123', 1, '2023-05-21 23:51:13.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (13, 5, 65, '23123213213', 1, '2023-05-21 23:51:16.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (14, 5, 64, '23123213213', 1, '2023-05-21 23:51:19.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (15, 5, 63, '3232323123', 1, '2023-05-21 23:51:23.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (16, 1, 61, '12312321321', 1, '2023-05-21 23:56:55.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (17, 2, 62, '1232131231', 1, '2023-05-21 23:57:03.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (18, 10, 65, null, 1, '2023-05-22 22:12:52.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (19, 10, 64, '123213214124', 1, '2023-05-22 22:12:58.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (20, 10, 63, '13241242432432', 1, '2023-05-22 22:13:03.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (21, 10, 61, '3423423423', 1, '2023-05-22 22:13:06.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_customer_remark (id, customer_id, note_way, note_content, create_by, create_time, edit_time, edit_by, deleted) VALUES (22, 2, 62, '1242412141', 1, '2023-05-31 20:14:08.000000 +00:00', null, null, null);
