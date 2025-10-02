create table t_dic_type
(
    id        bigserial
        constraint idx_28568_primary
            primary key,
    type_code varchar(64) not null,
    type_name varchar(64),
    remark    varchar(128)
);

comment on table t_dic_type is '字典类型表';

comment on column t_dic_type.id is '主键，自动增长，字典类型ID';

comment on column t_dic_type.type_code is '字典类型代码';

comment on column t_dic_type.type_name is '字典类型名称';

comment on column t_dic_type.remark is '备注';

alter table t_dic_type
    owner to postgres;

create unique index idx_28568_uk_type_code
    on t_dic_type (type_code);

INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (1, 'sex', '性别', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (2, 'appellation', '称呼', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (3, 'clueState', '线索状态', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (4, 'returnPriority', '回访优先级', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (5, 'returnState', '回访状态', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (6, 'source', '来源', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (7, 'stage', '阶段', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (8, 'transactionType', '交易类型', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (9, 'intentionState', '意向状态', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (10, 'needLoan', '是否贷款', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (11, 'educational', '学历', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (12, 'userState', '用户状态', null);
INSERT INTO spring_security.t_dic_type (id, type_code, type_name, remark) VALUES (13, 'noteWay', '跟踪方式', null);
