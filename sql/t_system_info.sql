create table t_system_info
(
    id           bigserial
        constraint idx_28598_primary
            primary key,
    system_code  varchar(45),
    name         varchar(100) not null,
    site         varchar(100) not null,
    logo         varchar(100),
    title        varchar(45)  not null,
    description  varchar(45)  not null,
    keywords     varchar(100) not null,
    shortcuticon varchar(100) not null,
    tel          varchar(100),
    weixin       varchar(25),
    email        varchar(45),
    address      varchar(100),
    version      varchar(145),
    closemsg     varchar(500),
    isopen       varchar(8) default 'y'::character varying,
    create_time  timestamp with time zone,
    create_by    bigint
        constraint t_system_info_ibfk_1
            references t_user
            on update cascade on delete restrict,
    edit_time    timestamp with time zone,
    edit_by      bigint
        constraint t_system_info_ibfk_2
            references t_user
            on update cascade on delete restrict
);

comment on table t_system_info is '系统信息表';

alter table t_system_info
    owner to postgres;

create index idx_28598_t_system_info_ibfk_2
    on t_system_info (edit_by);

create index idx_28598_t_system_info_ibfk_1
    on t_system_info (create_by);

INSERT INTO spring_security.t_system_info (id, system_code, name, site, logo, title, description, keywords, shortcuticon, tel, weixin, email, address, version, closemsg, isopen, create_time, create_by, edit_time, edit_by) VALUES (1, 'crm', '动力云客系统', 'http://www.bjpowernode.com', 'http://localhost:8080/image/logo.png', '动力云客系统', '动力CRM 企业客户智慧云管理', 'crm, 客户, 客户关系, 客户关系管理', 'http://www.bjpowernode.com/favicon.ico', '010-84846003', '123456789', '123456789@qq.com', '北京市大兴区大族企业湾10栋3层', '系统版本:1.1.0.bate', '网站维护中 动力云客系统 http://www.bjpowernode.com', 'true', '2023-11-08 13:28:18.000000 +00:00', 1, null, null);
INSERT INTO spring_security.t_system_info (id, system_code, name, site, logo, title, description, keywords, shortcuticon, tel, weixin, email, address, version, closemsg, isopen, create_time, create_by, edit_time, edit_by) VALUES (2, 'call', '动力呼叫系统', 'http://www.bjpowernode.com', 'http://localhost:8080/image/logo.png', '动力呼叫系统', '动力CRM 企业客户智慧云管理', 'crm, 客户, 客户关系, 客户关系管理', 'http://www.bjpowernode.com/favicon.ico', '010-84846003', '123456789', '123456789@qq.com', '北京市大兴区大族企业湾10栋3层', '系统版本:1.1.0.bate', '网站维护中 动力呼叫系统 http://www.bjpowernode.com', 'true', '2023-11-08 13:28:21.000000 +00:00', 1, null, null);
