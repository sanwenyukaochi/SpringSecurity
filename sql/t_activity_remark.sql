create table t_activity_remark
(
    id           bigserial
        constraint idx_28541_primary
            primary key,
    activity_id  bigint
        constraint t_activity_remark_ibfk_1
            references t_activity
            on update cascade on delete restrict,
    note_content varchar(255),
    create_time  timestamp with time zone,
    create_by    bigint
        constraint t_activity_remark_ibfk_2
            references t_user
            on update cascade on delete restrict,
    edit_time    timestamp with time zone,
    edit_by      bigint
        constraint t_activity_remark_ibfk_3
            references t_user
            on update cascade on delete restrict,
    deleted      bigint
);

comment on table t_activity_remark is '市场活动备注表';

comment on column t_activity_remark.id is '主键，自动增长，活动备注ID';

comment on column t_activity_remark.activity_id is '活动ID';

comment on column t_activity_remark.note_content is '备注内容';

comment on column t_activity_remark.create_time is '备注创建时间';

comment on column t_activity_remark.create_by is '备注创建人';

comment on column t_activity_remark.edit_time is '备注编辑时间';

comment on column t_activity_remark.edit_by is '备注编辑人';

comment on column t_activity_remark.deleted is '删除状态（0或者null正常，1删除）';

alter table t_activity_remark
    owner to postgres;

create index idx_28541_activity_id
    on t_activity_remark (activity_id);

create index idx_28541_t_activity_remark_ibfk_3
    on t_activity_remark (edit_by);

create index idx_28541_t_activity_remark_ibfk_2
    on t_activity_remark (create_by);

INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (1, 46, '1111111111111', '2023-05-17 14:07:48.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (2, 1, '市场活动效果需要进一步改进。', '2023-05-17 14:25:30.000000 +00:00', 1, '2024-06-25 14:44:01.000000 +00:00', 1, 1);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (3, 2, '1231231', '2023-06-07 23:08:32.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (4, 2, '23123', '2023-06-07 23:08:57.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (5, 10, '恶趣味群二无群二群无', '2023-06-07 23:10:20.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (6, 1, '上帝粉红色的匡扶汉室登记卡放哪的桑拿房科技大收纳发卡机打撒', '2023-08-04 22:30:47.000000 +00:00', 1, '2024-06-25 14:43:51.000000 +00:00', 1, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (7, 1, '啥地方跟你说快递费牛会计三大步伐年卡大撒把饭卡大撒把开发受打击开发你受打击考拉放哪受打击可燃放哪凯撒', '2024-06-25 11:11:00.000000 +00:00', 1, '2024-06-25 14:44:53.000000 +00:00', 1, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (8, 1, '首付款是的呢防溺水迪菲娜手打 ', '2024-06-25 11:11:30.000000 +00:00', 1, '2024-06-25 14:44:49.000000 +00:00', 1, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (9, 1, '活动效果还可以，每天可以拿到2000个真实的意向客户的手机号，但是后续电话联系的时候，大部分电话不接听，联系不上。', '2024-06-25 11:13:30.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (10, 1, '附件看过的发给你了地方你噶漏打卡个', '2024-06-25 11:40:11.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (11, 1, '隧道粉红色款到发货', '2024-06-25 12:01:09.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (12, 1, '冻死佛山分你收到了菲尼萨打卡漏发了双打卡发撒。', '2024-06-25 12:01:21.000000 +00:00', 1, '2024-06-25 14:44:14.000000 +00:00', 1, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (13, 1, '咨询费重振雄风产生的', '2024-06-25 12:01:39.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (14, 1, '水电费水电费是的', '2024-06-25 12:01:41.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (15, 1, '水电费水电费是的', '2024-06-25 12:01:43.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (16, 1, '水电费水电费', '2024-06-25 12:01:44.000000 +00:00', 1, '2024-06-25 14:44:25.000000 +00:00', 1, 1);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (17, 1, '水电费水电费是的', '2024-06-25 12:01:46.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (18, 1, '收到放虎归山东方红郡双打卡饭卡打撒', '2024-06-25 12:12:07.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (19, 1, 'USD会阿迪斯', '2024-06-25 14:45:37.000000 +00:00', 1, null, null, 1);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (20, 1, '瓦尔特瑞特热二位大范甘迪公对公', '2024-06-25 15:01:27.000000 +00:00', 1, '2024-06-25 15:01:35.000000 +00:00', 1, 1);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (21, 48, '二哥让他人', '2024-06-25 16:59:50.000000 +00:00', 1, null, null, null);
INSERT INTO spring_security.t_activity_remark (id, activity_id, note_content, create_time, create_by, edit_time, edit_by, deleted) VALUES (22, 48, '如图和液体与东风股份大概', '2024-06-25 16:59:54.000000 +00:00', 1, '2024-06-25 17:00:08.000000 +00:00', 1, 1);
