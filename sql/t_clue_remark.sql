create table t_clue_remark
(
    id           bigserial
        constraint idx_28553_primary
            primary key,
    clue_id      bigint
        constraint t_clue_remark_ibfk_3
            references t_clue
            on update cascade on delete restrict,
    note_way     bigint
        constraint t_clue_remark_ibfk_4
            references t_dic_value
            on update cascade on delete restrict,
    note_content varchar(255),
    create_time  timestamp with time zone,
    create_by    bigint
        constraint t_clue_remark_ibfk_1
            references t_user
            on update cascade on delete restrict,
    edit_time    timestamp with time zone,
    edit_by      bigint
        constraint t_clue_remark_ibfk_2
            references t_user
            on update cascade on delete restrict,
    deleted      bigint
);

comment on table t_clue_remark is '线索跟踪记录表';

comment on column t_clue_remark.id is '主键，自动增长，线索备注ID';

comment on column t_clue_remark.clue_id is '线索ID';

comment on column t_clue_remark.note_way is '跟踪方式';

comment on column t_clue_remark.note_content is '跟踪内容';

comment on column t_clue_remark.create_time is '跟踪时间';

comment on column t_clue_remark.create_by is '跟踪人';

comment on column t_clue_remark.edit_time is '编辑时间';

comment on column t_clue_remark.edit_by is '编辑人';

comment on column t_clue_remark.deleted is '删除状态（0正常，1删除）';

alter table t_clue_remark
    owner to postgres;

create index idx_28553_edit_by
    on t_clue_remark (edit_by);

create index idx_28553_create_by
    on t_clue_remark (create_by);

create index idx_28553_clue_id
    on t_clue_remark (clue_id);

create index idx_28553_t_clue_remark_ibfk_4
    on t_clue_remark (note_way);

INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (5, 8, 65, '2143242354', '2023-04-28 14:24:27.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (6, 10, 64, '123412312312', '2023-04-28 14:29:41.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (7, 10, 63, '二位绕弯儿翁人', '2023-04-28 14:30:16.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (8, 21, 61, '12213123123', '2023-05-21 23:43:48.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (9, 21, 62, '3ewrwerewr', '2023-05-21 23:43:52.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (10, 21, 65, 'ewrwerewrewr', '2023-05-21 23:43:55.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (11, 16, 64, '123213123', '2023-05-21 23:54:57.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (12, 16, 63, '23123123', '2023-05-21 23:54:59.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (13, 16, 61, '2312313', '2023-05-21 23:55:02.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (14, 1, 62, null, '2023-06-27 22:47:49.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (15, 1, 65, '111111111111111', '2023-06-27 22:47:56.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_clue_remark (id, clue_id, note_way, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (16, 1, 64, '1111111111111111111111222222222222222222222222', '2023-06-27 22:48:01.000000 +00:00', 1, null, null, null);
