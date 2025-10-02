create table t_user_role
(
    id      bigserial
        constraint idx_28628_primary
            primary key,
    user_id bigint
        constraint t_user_role_ibfk_1
            references t_user
            on update cascade on delete restrict,
    role_id bigint
        constraint t_user_role_ibfk_2
            references t_role
            on update cascade on delete restrict
);

comment on table t_user_role is '用户角色关系表';

alter table t_user_role
    owner to postgres;

create index idx_28628_t_user_role_ibfk_2
    on t_user_role (role_id);

create index idx_28628_t_user_role_ibfk_1
    on t_user_role (user_id);

INSERT INTO spring_security.t_user_role (id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO spring_security.t_user_role (id, user_id, role_id) VALUES (2, 2, 2);
INSERT INTO spring_security.t_user_role (id, user_id, role_id) VALUES (3, 3, 2);
INSERT INTO spring_security.t_user_role (id, user_id, role_id) VALUES (4, 4, 3);
INSERT INTO spring_security.t_user_role (id, user_id, role_id) VALUES (5, 5, 4);
INSERT INTO spring_security.t_user_role (id, user_id, role_id) VALUES (6, 6, 5);
