create table t_user
(
    id                     bigserial
        constraint idx_28623_primary
            primary key,
    login_act              varchar(32),
    login_pwd              varchar(64),
    name                   varchar(32),
    phone                  varchar(18),
    email                  varchar(64),
    account_no_expired     bigint,
    credentials_no_expired bigint,
    account_no_locked      bigint,
    account_enabled        bigint,
    create_time            timestamp with time zone,
    create_by              bigint,
    edit_time              timestamp with time zone,
    edit_by                bigint,
    last_login_time        timestamp with time zone
);

comment on table t_user is '用户表';

comment on column t_user.id is '主键，自动增长，用户ID';

comment on column t_user.login_act is '登录账号';

comment on column t_user.login_pwd is '登录密码';

comment on column t_user.name is '用户姓名';

comment on column t_user.phone is '用户手机';

comment on column t_user.email is '用户邮箱';

comment on column t_user.account_no_expired is '账户是否没有过期，0已过期 1正常';

comment on column t_user.credentials_no_expired is '密码是否没有过期，0已过期 1正常';

comment on column t_user.account_no_locked is '账号是否没有锁定，0已锁定 1正常';

comment on column t_user.account_enabled is '账号是否启用，0禁用 1启用';

comment on column t_user.create_time is '创建时间';

comment on column t_user.create_by is '创建人';

comment on column t_user.edit_time is '编辑时间';

comment on column t_user.edit_by is '编辑人';

comment on column t_user.last_login_time is '最近登录时间';

alter table t_user
    owner to postgres;

create unique index idx_28623_email
    on t_user (email);

create unique index idx_28623_phone
    on t_user (phone);

create unique index idx_28623_login_act
    on t_user (login_act);

INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (1, 'admin', '$2a$10$M/Cj/AMCXXHbf3nlRhivoO81o5n9ETel0Mo8v5QAjYMKrzJP6w1Ei', '管理员', '13700000000', 'admin@qq.com', 1, 1, 1, 1, '2023-02-22 09:37:12.000000 +00:00', null, '2024-06-24 10:48:48.000000 +00:00', 1, '2024-01-11 11:20:17.000000 +00:00');
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (2, 'yuyan', '$2a$10$WEkWUvuJOV/q7lRXzmdvgulskx8EhLPU8N.hdnfNyk.AYqYSPwYHa', '于嫣', '13809090908', 'yuyan@163.com', 1, 1, 1, 1, '2023-02-28 12:11:40.000000 +00:00', 1, '2024-06-22 16:31:32.000000 +00:00', 2, '2023-12-11 21:07:07.000000 +00:00');
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (3, 'zhangqi', '$2a$10$Q0qTW6QqkabTzFyoilViw..YdrVzZkSKe5RvLmjgPgW/IrcPkBoF.', '张琪', '1362362323', 'zhangqi@qq.com', 1, 1, 1, 1, '2023-03-02 11:37:34.000000 +00:00', null, '2023-05-23 00:21:02.000000 +00:00', null, '2023-12-11 21:07:28.000000 +00:00');
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (4, 'suwanting', '$2a$10$3bambNLTCAKtQn2OXPiHb.f0SzH.MucTiLi6GPT6nQrYpsxsdxaFi', '苏婉婷', null, 'suwanting@qq.com', 1, 1, 1, 1, '2023-04-03 15:04:54.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (5, 'wuxiaoxiao', '$2a$10$Lmk5wXYkZzQMFJEcXVZAZegIQhnAm6ONHpz09X/.gbOh5ze5fU6MW', '吴潇潇', null, 'wuxiaoxiao@qq.com', 1, 1, 1, 1, '2023-01-27 12:15:26.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (6, 'mengyan', '$2a$10$6zGT7CfeuJ/6jZPk1pAqcuiMYDnCJstrceThGD5DVVOA5XvOP/sQq', '孟岩', null, 'mengyan@163.com', 1, 1, 1, 1, '2023-03-19 10:17:28.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (7, 'yuanhuimin', '$2a$10$mbsloGtPV7cDwfAVYxuvLemQRWumZKrDxVZxg4fnbfaocnfZFlYuu', '袁慧敏', null, 'yuanhuimin@11.com', 1, 1, 1, 1, '2023-04-11 20:18:50.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (8, 'qinxuwen', '$2a$10$ir8uLlBrPMHRtGiu5Ajkv.UKcRacXWRen7zxelp9iUaco3WhGkJ36', '秦旭文', '13820000000', 'qinxuwen@163.com', 1, 1, 1, 1, '2023-03-19 21:11:37.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (9, 'chengjie', '$2a$10$jQR8yyF/ailGP/zW6G4JOOffzWOXhe02Rgw7MZLfxL.IGFdM3cjM2', '程杰', '13500000000', 'chengjie@qq.com', 1, 1, 1, 1, '2023-04-16 07:16:19.000000 +00:00', null, '2023-04-20 21:42:21.000000 +00:00', null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (10, 'zhouliang', '$2a$10$0yOGdkAcG8JLEcoEmmCnfO8Vp6rcqBnn30k6pGor5Z0.eLMyLEd7.', '周亮', '13800000008', 'zhouliang@163.com', 1, 1, 1, 1, '2023-03-18 13:13:45.000000 +00:00', null, '2024-06-22 16:49:57.000000 +00:00', 2, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (11, 'zhangwei', '$2a$10$BfOgsdSAZ9VYBOzv692BM.oWGPLktcqhhjU3AaWESkGNRcW484N7O', '张伟', null, 'zhangwei@qq.com', 1, 1, 1, 1, '2023-03-06 09:18:23.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (12, 'dengping', '$2a$10$hpN8orfqUFXb.WWbIoZBkOZrr6D8rdSbl/SWXsMQ0zEuqkldlkpW2', '邓萍', null, 'dengping@qq.com', 1, 1, 1, 1, '2023-02-19 20:10:58.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (13, 'zhangxing', '$2a$10$uBVDcCCJQvTfoFCjbjwrf.MhyczNNJfCn76jD61CsAgsUlXjXhxzG', '张欣', null, 'zhangxing@qq.com', 1, 1, 1, 1, '2023-03-17 12:12:11.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (14, 'zhangmeng', '$2a$10$MMHG2cQh4H4YFbdf48SnyO9IZ78F110x3.7IWGNExrgk2rFmhrd/u', '张萌', null, 'zhangmeng@qq.com', 1, 1, 1, 1, '2023-01-13 08:16:02.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (15, 'shixixiang', '$2a$10$zYwq/QfevFPAZxw4b2DkCeQvjVQ52AUU9c4aC0uS0wTJaRr75G74y', '石喜祥', null, 'shixixiang@qq.com', 1, 1, 1, 1, '2023-03-10 15:19:49.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (16, 'chengjiuming', '$2a$10$yNN5TcFkM4OqRsKGNM8CNeqAJhRYKQgXVFqbre5lQPicnIXT7THTu', '陈久明', null, 'chengjiuming@163.com', 1, 1, 1, 1, '2023-04-09 23:17:37.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (17, 'genghao', '$2a$10$rWHo.vUpJCbqWLGMkPj95O5FlhaQLzro.LY7pVQ/UnVVAdvjEAy0K', '耿浩', null, 'genghao@qq.com', 1, 1, 1, 1, '2023-03-19 12:10:22.000000 +00:00', null, '2023-04-10 21:42:21.000000 +00:00', null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (18, 'hanmingyang', '$2a$10$PRMdG7a8nFIN1A3TD584Xe2BZI7Y0mktDL7Wp5lF88E1D1iPijFc6', '韩明洋', null, 'hanmingyang@163.com', 1, 1, 1, 1, '2023-02-12 18:13:01.000000 +00:00', null, '2023-04-13 23:43:25.000000 +00:00', null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (19, 'xuyan', '$2a$10$S7MF2dOqFcoOJPqpEH2nu.Muhn2XC0BlBTZ5gAoL3axrQxdJEJNnK', '徐燕', null, 'xuyan@qq.com', 1, 1, 1, 1, '2023-03-29 13:16:15.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (20, 'chengjuan', '$2a$10$m1g5cxikApV05pR7Cx4cy.d4sT3efOl6UvDLvH27WzMjtpymQ5ANi', '程娟', null, 'chengjuan@qq.com', 1, 1, 1, 1, '2023-02-19 15:12:22.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (21, 'huangxiao', '$2a$10$R/RwQd5.3OxYpSZBLIn8DeeYYNF0vgWCrCR4tcyL.c/HtnuIfBRIK', '黄潇', null, 'huangxiao@qq.com', 1, 1, 1, 1, '2023-03-26 22:11:37.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (22, 'yangyuxin', '$2a$10$ucE/By6NLBb4tN5H3CUimOQ2eAtbjXFf2v77SJUPbHXRI9lTF97Ka', '杨雨欣', null, 'yangyuxin@163.com', 1, 1, 1, 1, '2023-04-13 18:14:59.000000 +00:00', null, null, null, null);
INSERT INTO spring_security.t_user (id, login_act, login_pwd, name, phone, email, account_no_expired, credentials_no_expired, account_no_locked, account_enabled, create_time, create_by, edit_time, edit_by, last_login_time) VALUES (23, 'xiaojie', '$2a$10$A215.iFSp7/d99X5M6KE.eu5YvA7nJ5vNEJraxmpA8EUYJN6lx9rW', '肖捷', null, 'xiaojie@163.com', 1, 1, 1, 1, '2023-02-18 09:19:02.000000 +00:00', null, null, null, null);
