create table t_role
(
    id        bigserial
        constraint idx_28588_primary
            primary key,
    role      varchar(30),
    role_name varchar(30)
);

comment on table t_role is '角色表';

alter table t_role
    owner to postgres;

INSERT INTO spring_security.t_role (id, role, role_name) VALUES (1, 'admin', '管理员');
INSERT INTO spring_security.t_role (id, role, role_name) VALUES (2, 'saler', '销售员');
INSERT INTO spring_security.t_role (id, role, role_name) VALUES (3, 'manager', '销售经理');
INSERT INTO spring_security.t_role (id, role, role_name) VALUES (4, 'marketing ', '市场营销');
INSERT INTO spring_security.t_role (id, role, role_name) VALUES (5, 'accountant', '会计');
