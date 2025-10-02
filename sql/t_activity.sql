create table t_activity
(
    id          bigserial
        constraint idx_28536_primary
            primary key,
    owner_id    bigint
        constraint t_activity_ibfk_1
            references t_user
            on update cascade on delete restrict,
    name        varchar(128),
    start_time  timestamp with time zone,
    end_time    timestamp with time zone,
    cost        numeric(11, 2),
    description varchar(255),
    create_time timestamp with time zone,
    create_by   bigint
        constraint t_activity_ibfk_2
            references t_user
            on update cascade on delete restrict,
    edit_time   timestamp with time zone,
    edit_by     bigint
        constraint t_activity_ibfk_3
            references t_user
            on update cascade on delete restrict
);

comment on table t_activity is '市场活动表';

comment on column t_activity.id is '主键，自动增长，活动ID';

comment on column t_activity.owner_id is '活动所属人ID';

comment on column t_activity.name is '活动名称';

comment on column t_activity.start_time is '活动开始时间';

comment on column t_activity.end_time is '活动结束时间';

comment on column t_activity.cost is '活动预算';

comment on column t_activity.description is '活动描述';

comment on column t_activity.create_time is '活动创建时间';

comment on column t_activity.create_by is '活动创建人';

comment on column t_activity.edit_time is '活动编辑时间';

comment on column t_activity.edit_by is '活动编辑人';

alter table t_activity
    owner to postgres;

create index idx_28536_create_by
    on t_activity (create_by);

create index idx_28536_edit_by
    on t_activity (edit_by);

create index idx_28536_owner
    on t_activity (owner_id);

INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (1, 4, '百度推广', '2023-08-05 19:18:20.000000 +00:00', '2023-08-25 00:00:00.000000 +00:00', 1200000.00, '百度排名推广,活动非常非常贵,大家好好的做推广.由于推广活动的效果非常好,决定延期一个月.山东科技付货款打扫房间凯撒的补给卡时间跨度风寒咳嗽大部分就开吧打撒个发过火发给你厉害u7如果发生大叔大婶i我饿火绒额特容易骗人空调已汇入退役军人天涯茫然了啊浴室柜打不打卡是i速单号覅收到反馈上课的话费尽口舌大富科技是', '2024-06-25 10:33:33.000000 +00:00', 8, '2023-05-15 00:10:49.000000 +00:00', 12);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (2, 2, '充话费-送手机', '2023-10-28 17:48:49.000000 +00:00', '2023-10-30 17:48:54.000000 +00:00', 9000.00, '充话费,送手机,充满送Iphone14', '2023-03-28 17:49:28.000000 +00:00', 1, '2023-05-15 00:10:52.000000 +00:00', 1);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (7, 1, '抖音推广', '2023-12-01 12:03:09.000000 +00:00', '2023-12-30 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, 6, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (8, 1, '抖音推广', '2023-01-01 12:03:09.000000 +00:00', '2023-04-30 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, 7, '2023-04-28 14:28:53.000000 +00:00', null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (10, 2, '11', '2023-01-11 00:30:58.000000 +00:00', '2023-04-30 00:31:00.000000 +00:00', 131.00, '12312312', null, null, '2023-04-28 13:33:56.000000 +00:00', null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (11, 3, '1213', '2023-02-01 00:00:00.000000 +00:00', '2023-04-30 00:31:13.000000 +00:00', 123123.00, '23123123', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (12, 1, '抖音推广', '2023-12-01 12:03:09.000000 +00:00', '2023-12-11 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (13, 1, '抖音推广', '2023-01-01 12:03:09.000000 +00:00', '2023-04-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, '2023-04-28 13:27:43.000000 +00:00', null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (14, 1, '抖音推广', '2023-11-01 12:03:09.000000 +00:00', '2023-12-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (15, 1, '抖音推广', '2023-03-01 12:03:09.000000 +00:00', '2023-04-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动11111', null, null, '2023-04-26 17:09:49.000000 +00:00', null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (16, 1, '抖音推广', '2023-03-01 12:03:09.000000 +00:00', '2023-04-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (17, 1, '抖音推广', '2023-03-01 12:03:09.000000 +00:00', '2023-04-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (18, 1, '抖音推广', '2023-04-01 12:03:09.000000 +00:00', '2023-04-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (19, 1, '抖音推广', '2023-09-01 12:03:09.000000 +00:00', '2023-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (20, 1, '抖音推广', '2023-09-01 12:03:09.000000 +00:00', '2023-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (21, 1, '抖音推广', '2023-04-01 12:03:09.000000 +00:00', '2023-04-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (22, 1, '抖音推广', '2024-02-01 10:03:09.000000 +00:00', '2024-09-09 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (23, 1, '抖音推广', '2024-02-01 10:03:09.000000 +00:00', '2024-09-11 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (24, 1, '抖音推广', '2024-01-07 10:03:09.000000 +00:00', '2024-09-27 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (25, 1, '抖音推广', '2024-02-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (26, 1, '抖音推广', '2024-02-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (27, 1, '抖音推广', '2024-02-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (28, 1, '抖音推广', '2024-02-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (29, 1, '抖音推广', '2024-02-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (40, 1, '抖音推广11', '2024-05-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (41, 1, '抖音推广11', '2024-05-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (42, 1, '抖音推广11', '2024-05-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (43, 1, '抖音推广11', '2024-05-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (44, 1, '抖音推广11', '2024-06-01 12:03:09.000000 +00:00', '2024-09-01 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (45, 1, '抖音推广11', '2024-06-01 12:03:09.000000 +00:00', '2024-09-15 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动2', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (46, 3, '抖音短视频广告', '2024-07-05 00:00:00.000000 +00:00', '2024-11-30 00:00:00.000000 +00:00', 5000.00, '抖音短视频广告，宣传产品', null, null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (47, 3, '我去恶趣味', '2024-07-15 16:51:40.000000 +00:00', '2024-11-30 16:51:42.000000 +00:00', 231231.00, '色达所大所大所多', '2024-06-25 09:19:14.000000 +00:00', null, '2024-06-25 16:59:31.000000 +00:00', 1);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (48, 7, '可视角度焚枯食淡粉红色', '2024-11-01 00:44:19.000000 +00:00', '2024-12-30 00:44:19.000000 +00:00', 185323.00, '第三方水电费水电费水电费是非得失', '2024-06-25 22:27:15.000000 +00:00', null, '2024-06-25 17:00:02.000000 +00:00', 1);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (49, 1, '抖音推广11', '2024-12-01 12:03:09.000000 +00:00', '2024-12-12 12:03:09.000000 +00:00', 15800.00, '抖音直播推广活动1', '2024-06-25 09:19:23.000000 +00:00', null, null, null);
INSERT INTO spring_security.t_activity (id, owner_id, name, start_time, end_time, cost, description, create_time, create_by, edit_time, edit_by) VALUES (50, 2, '手动滑稽腹背受敌', '2024-06-25 16:58:52.000000 +00:00', '2024-06-29 00:00:00.000000 +00:00', 1280000.00, '第三方水电费水电费', '2024-06-25 16:12:00.000000 +00:00', 1, '2024-06-25 16:58:55.000000 +00:00', 1);
