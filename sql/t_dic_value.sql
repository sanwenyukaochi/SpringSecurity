create table t_dic_value
(
    id         bigserial
        constraint idx_28573_primary
            primary key,
    type_code  varchar(64)
        constraint t_dic_value_ibfk_1
            references t_dic_type ()
            on update cascade on delete restrict,
    type_value varchar(64),
    "order"    bigint,
    remark     varchar(64)
);

comment on table t_dic_value is '字典值表';

comment on column t_dic_value.id is '主键，自动增长，字典值ID';

comment on column t_dic_value.type_code is '字典类型代码';

comment on column t_dic_value.type_value is '字典值';

comment on column t_dic_value."order" is '字典值排序';

comment on column t_dic_value.remark is '备注';

alter table t_dic_value
    owner to postgres;

create index idx_28573_t_dic_value_ibfk_1
    on t_dic_value (type_code);

INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (-1, 'clueState', '已转客户', 0, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (1, 'clueState', '虚假线索', 4, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (2, 'source', '知乎', 8, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (3, 'source', '车展会', 11, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (4, 'returnPriority', '最高', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (5, 'appellation', '教授', 5, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (6, 'clueState', '将来联系', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (7, 'clueState', '丢失线索', 5, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (8, 'returnState', '未启动', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (10, 'clueState', '试图联系', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (11, 'appellation', '博士', 4, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (12, 'stage', '01创建交易', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (14, 'source', '汽车之家', 14, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (15, 'returnPriority', '低', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (16, 'source', '网络广告', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (17, 'source', '视频直播', 9, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (18, 'appellation', '先生', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (19, 'returnPriority', '高', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (20, 'appellation', '夫人', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (21, 'stage', '06丢失关闭', 7, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (22, 'source', '地图', 13, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (23, 'source', '合作伙伴', 6, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (24, 'clueState', '未联系', 6, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (25, 'source', '朋友圈', 10, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (26, 'returnState', '进行中', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (27, 'clueState', '已联系', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (28, 'returnState', '推迟', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (29, 'returnState', '完成', 4, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (30, 'clueState', '需要条件', 7, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (32, 'returnState', '等待某人', 5, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (33, 'source', '懂车帝', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (34, 'returnPriority', '常规', 5, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (35, 'stage', '04产品检验', 5, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (36, 'source', '易车网', 12, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (37, 'stage', '02确认清单', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (38, 'returnPriority', '最低', 4, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (39, 'source', '员工介绍', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (40, 'stage', '03交付定金', 4, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (41, 'appellation', '女士', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (42, 'stage', '05付款成交', 6, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (43, 'source', '官方网站', 5, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (44, 'source', '公众号', 7, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (45, 'source', '门店参观', 4, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (46, 'intentionState', '有意向', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (47, 'intentionState', '无意向', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (48, 'intentionState', '意向不明', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (49, 'needLoan', '需要', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (50, 'needLoan', '不需要', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (51, 'sex', '男', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (52, 'sex', '女', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (53, 'educational', '小学', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (54, 'educational', '初中', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (55, 'educational', '高中', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (56, 'educational', '大学', 4, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (57, 'educational', '研究生', 5, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (58, 'userState', '正常', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (59, 'userState', '锁定', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (60, 'userState', '禁用', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (61, 'noteWay', '电话', 1, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (62, 'noteWay', '微信', 2, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (63, 'noteWay', 'QQ', 3, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (64, 'noteWay', '面聊', 4, null);
INSERT INTO spring_security.t_dic_value (id, type_code, type_value, "order", remark) VALUES (65, 'noteWay', '其他', 5, null);
