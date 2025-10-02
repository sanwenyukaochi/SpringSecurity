create table t_role_permission
(
    id            bigserial
        constraint idx_28593_primary
            primary key,
    role_id       bigint
        constraint t_role_permission_ibfk_1
            references t_role
            on update cascade on delete restrict,
    permission_id bigint
        constraint t_role_permission_ibfk_2
            references t_permission
            on update cascade on delete restrict
);

comment on table t_role_permission is '角色权限关系表';

alter table t_role_permission
    owner to postgres;

create index idx_28593_t_role_permission_ibfk_1
    on t_role_permission (role_id);

create index idx_28593_t_role_permission_ibfk_2
    on t_role_permission (permission_id);

INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (1, 1, 1);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (2, 1, 2);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (3, 1, 3);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (4, 1, 4);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (5, 1, 5);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (6, 1, 6);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (7, 1, 7);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (8, 1, 9);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (9, 1, 10);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (10, 1, 12);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (11, 1, 13);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (12, 1, 14);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (13, 1, 15);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (14, 1, 16);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (15, 1, 17);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (16, 1, 18);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (17, 1, 19);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (18, 1, 20);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (19, 1, 21);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (20, 1, 22);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (21, 1, 23);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (22, 1, 24);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (23, 1, 25);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (24, 1, 26);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (25, 1, 27);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (26, 1, 28);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (27, 1, 29);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (28, 1, 30);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (29, 1, 31);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (30, 1, 32);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (31, 1, 33);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (32, 1, 34);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (33, 1, 35);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (34, 1, 36);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (35, 1, 37);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (36, 1, 38);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (37, 1, 39);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (38, 1, 40);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (39, 1, 41);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (40, 1, 42);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (41, 1, 43);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (42, 1, 44);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (43, 1, 45);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (44, 1, 46);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (45, 1, 47);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (47, 1, 49);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (48, 1, 50);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (49, 1, 51);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (50, 1, 52);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (51, 1, 53);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (52, 1, 54);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (53, 1, 55);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (54, 1, 56);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (55, 1, 57);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (56, 1, 58);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (57, 1, 59);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (58, 1, 60);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (59, 1, 61);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (60, 2, 10);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (61, 2, 12);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (62, 2, 13);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (63, 2, 14);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (64, 2, 15);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (65, 2, 16);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (66, 2, 57);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (67, 2, 18);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (68, 2, 19);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (69, 2, 20);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (70, 2, 21);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (71, 2, 22);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (72, 2, 23);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (73, 2, 24);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (74, 2, 25);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (75, 2, 26);
INSERT INTO spring_security.t_role_permission (id, role_id, permission_id) VALUES (76, 2, 27);
