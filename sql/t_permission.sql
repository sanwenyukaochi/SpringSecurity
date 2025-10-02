create table t_permission
(
    id        bigserial
        constraint idx_28578_primary
            primary key,
    name      varchar(30),
    code      varchar(30),
    url       varchar(30),
    type      varchar(30),
    parent_id bigint,
    order_no  bigint,
    icon      varchar(100),
    component varchar(50)
);

comment on table t_permission is '权限表';

comment on column t_permission.icon is '菜单图标';

comment on column t_permission.component is '菜单对应要渲染的Vue组件名称';

alter table t_permission
    owner to postgres;

INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (1, '市场活动', null, null, 'menu', 0, 1, 'OfficeBuilding', null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (2, '市场活动', null, '/dashboard/activity', 'menu', 1, 1, 'CreditCard', 'ActivityView');
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (3, '市场活动-列表', 'activity:list', null, 'button', 2, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (4, '市场活动-录入', 'activity:add', null, 'button', 2, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (5, '市场活动-编辑', 'activity:edit', null, 'button', 2, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (6, '市场活动-查看', 'activity:view', null, 'button', 2, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (7, '市场活动-删除', 'activity:delete', null, 'button', 2, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (9, '市场活动-搜索', 'activity:search', null, 'button', 2, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (10, '线索管理', null, null, 'menu', 0, 2, 'Magnet', null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (12, '线索管理', null, '/dashboard/clue', 'menu', 10, 1, 'Paperclip', 'ClueView');
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (13, '线索管理-列表', 'clue:list', null, 'button', 12, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (14, '线索管理-录入', 'clue:add', null, 'button', 12, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (15, '线索管理-编辑', 'clue:edit', null, 'button', 12, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (16, '线索管理-查看', 'clue:view', null, 'button', 12, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (17, '线索管理-删除', 'clue:delete', null, 'button', 12, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (18, '线索管理-导入', 'clue:import', null, 'button', 12, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (19, '客户管理', null, null, 'menu', 0, 3, 'User', null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (20, '客户管理', null, '/dashboard/customer', 'menu', 19, 1, 'UserFilled', 'CustomerView');
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (21, '客户管理-列表', 'customer:list', null, 'button', 20, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (22, '客户管理-查看', 'customer:view', null, 'button', 20, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (23, '客户管理-导出', 'customer:export', null, 'button', 20, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (24, '交易管理', null, null, 'menu', 0, 4, 'Wallet', null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (25, '交易管理', null, '/dashboard/tran', 'menu', 24, 1, 'Coin', 'TranView');
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (26, '交易管理-列表', 'tran:list', null, 'button', 25, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (27, '交易管理-查看', 'tran:view', null, 'button', 25, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (28, '产品管理', null, null, 'menu', 0, 5, 'Memo', null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (29, '产品管理', null, '/dashboard/product', 'menu', 28, 1, 'SetUp', 'ProductView');
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (30, '产品管理-列表', 'product:list', null, 'button', 29, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (31, '产品管理-录入', 'product:add', null, 'button', 29, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (32, '产品管理-编辑', 'product:edit', null, 'button', 29, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (33, '产品管理-查看', 'product:view', null, 'button', 29, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (34, '产品管理-删除', 'product:delete', null, 'button', 29, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (35, '字典管理', null, null, 'menu', 0, 6, 'Grid', null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (36, '字典类型', null, '/dashboard/dictype', 'menu', 35, 1, 'Postcard', 'DictypeView');
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (37, '字典类型-列表', 'dictype:list', null, 'button', 36, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (38, '字典类型-录入', 'dictype:add', null, 'button', 36, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (39, '字典类型-编辑', 'dictype:edit', null, 'button', 36, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (40, '字典类型-查看', 'dictype:view', null, 'button', 36, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (41, '字典类型-删除', 'dictype:delete', null, 'button', 36, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (42, '字典数据', '', '/dashboard/dicvalue', 'menu', 35, 2, 'DataAnalysis', 'DicvalueView');
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (43, '字典数据-列表', 'dicvalue:list', null, 'button', 42, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (44, '字典数据-录入', 'dicvalue:add', null, 'button', 42, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (45, '字典数据-编辑', 'dicvalue:edit', null, 'button', 42, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (46, '字典数据-查看', 'dicvalue:view', null, 'button', 42, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (47, '字典数据-删除', 'dicvalue:delete', null, 'button', 42, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (49, '用户管理', null, '/dashboard/user', 'menu', 55, 1, 'User', 'UserView');
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (50, '用户管理-列表', 'user:list', null, 'button', 49, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (51, '用户管理-录入', 'user:add', null, 'button', 49, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (52, '用户管理-编辑', 'user:edit', null, 'button', 49, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (53, '用户管理-查看', 'user:view', null, 'button', 49, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (54, '用户管理-删除', 'user:delete', null, 'button', 49, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (55, '系统管理', null, null, 'menu', 0, 8, 'Setting', null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (56, '系统管理', null, '/dashboard/system', 'menu', 55, 3, 'Tools', 'SystemView');
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (57, '系统管理-列表', 'system:list', null, 'button', 56, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (58, '系统管理-录入', 'system:add', null, 'button', 56, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (59, '系统管理-编辑', 'system:edit', null, 'button', 56, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (60, '系统管理-查看', 'system:view', null, 'button', 56, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (61, '系统管理-删除', 'system:delete', null, 'button', 56, null, null, null);
INSERT INTO spring_security.t_permission (id, name, code, url, type, parent_id, order_no, icon, component) VALUES (1112, '角色管理', null, '/dashboard/role', 'menu', 55, 2, 'UserFilled', 'RoleView');
