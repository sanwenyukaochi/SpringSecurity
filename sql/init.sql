create table t_activity
(
    id          bigserial,
    owner_id    bigint,
    name        varchar(128),
    start_time  timestamp with time zone,
    end_time    timestamp with time zone,
    cost        numeric(11, 2),
    description varchar(255),
    create_time timestamp with time zone,
    create_by   bigint,
    edit_time   timestamp with time zone,
    edit_by     bigint
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

alter table t_activity
    add constraint idx_28536_primary
        primary key (id);

create table t_activity_remark
(
    id           bigserial,
    activity_id  bigint,
    note_content varchar(255),
    create_time  timestamp with time zone,
    create_by    bigint,
    edit_time    timestamp with time zone,
    edit_by      bigint,
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

create index idx_28541_t_activity_remark_ibfk_2
    on t_activity_remark (create_by);

create index idx_28541_t_activity_remark_ibfk_3
    on t_activity_remark (edit_by);

alter table t_activity_remark
    add constraint idx_28541_primary
        primary key (id);

alter table t_activity_remark
    add constraint t_activity_remark_ibfk_1
        foreign key (activity_id) references t_activity
            on update cascade on delete restrict;

create table t_clue
(
    id                bigserial,
    owner_id          bigint,
    activity_id       bigint,
    full_name         varchar(64),
    appellation       bigint,
    phone             varchar(18),
    weixin            varchar(128),
    qq                varchar(20),
    email             varchar(128),
    age               bigint,
    job               varchar(64),
    year_income       numeric(10, 2),
    address           varchar(128),
    need_loan         bigint,
    intention_state   bigint,
    intention_product bigint,
    state             bigint,
    source            bigint,
    description       varchar(255),
    next_contact_time timestamp with time zone,
    create_time       timestamp with time zone,
    create_by         bigint,
    edit_time         timestamp with time zone,
    edit_by           bigint
);

comment on table t_clue is '线索表';

comment on column t_clue.id is '主键，自动增长，线索ID';

comment on column t_clue.owner_id is '线索所属人ID';

comment on column t_clue.activity_id is '活动ID';

comment on column t_clue.full_name is '姓名';

comment on column t_clue.appellation is '称呼';

comment on column t_clue.phone is '手机号';

comment on column t_clue.weixin is '微信号';

comment on column t_clue.qq is 'QQ号';

comment on column t_clue.email is '邮箱';

comment on column t_clue.age is '年龄';

comment on column t_clue.job is '职业';

comment on column t_clue.year_income is '年收入';

comment on column t_clue.address is '地址';

comment on column t_clue.need_loan is '是否需要贷款（0不需要，1需要）';

comment on column t_clue.intention_state is '意向状态';

comment on column t_clue.intention_product is '意向产品';

comment on column t_clue.state is '线索状态';

comment on column t_clue.source is '线索来源';

comment on column t_clue.description is '线索描述';

comment on column t_clue.next_contact_time is '下次联系时间';

comment on column t_clue.create_time is '创建时间';

comment on column t_clue.create_by is '创建人';

comment on column t_clue.edit_time is '编辑时间';

comment on column t_clue.edit_by is '编辑人';

alter table t_clue
    owner to postgres;

create index idx_28546_appellation
    on t_clue (appellation);

create index idx_28546_create_by
    on t_clue (create_by);

create index idx_28546_edit_by
    on t_clue (edit_by);

create index idx_28546_owner
    on t_clue (owner_id);

create index idx_28546_source
    on t_clue (source);

create index idx_28546_state
    on t_clue (state);

create index idx_28546_t_clue_ibfk_10
    on t_clue (intention_product);

create index idx_28546_t_clue_ibfk_7
    on t_clue (activity_id);

create index idx_28546_t_clue_ibfk_8
    on t_clue (need_loan);

create index idx_28546_t_clue_ibfk_9
    on t_clue (intention_state);

alter table t_clue
    add constraint idx_28546_primary
        primary key (id);

alter table t_clue
    add constraint t_clue_ibfk_7
        foreign key (activity_id) references t_activity
            on update cascade on delete restrict;

create table t_clue_remark
(
    id           bigserial,
    clue_id      bigint,
    note_way     bigint,
    note_content varchar(255),
    create_time  timestamp with time zone,
    create_by    bigint,
    edit_time    timestamp with time zone,
    edit_by      bigint,
    deleted      bigint
);

comment on table t_clue_remark is '线索跟踪记录表';

comment on column t_clue_remark.id is '主键，自动增长，线索备注ID';

comment on column t_clue_remark.clue_id is '线索ID';

comment on column t_clue_remark.note_way is '跟踪方式';

comment on column t_clue_remark.note_content is '跟踪内容';

comment on column t_clue_remark.create_time is '跟踪时间';

comment on column t_clue_remark.create_by is '跟踪人';

comment on column t_clue_remark.edit_time is '编辑时间';

comment on column t_clue_remark.edit_by is '编辑人';

comment on column t_clue_remark.deleted is '删除状态（0正常，1删除）';

alter table t_clue_remark
    owner to postgres;

create index idx_28553_clue_id
    on t_clue_remark (clue_id);

create index idx_28553_create_by
    on t_clue_remark (create_by);

create index idx_28553_edit_by
    on t_clue_remark (edit_by);

create index idx_28553_t_clue_remark_ibfk_4
    on t_clue_remark (note_way);

alter table t_clue_remark
    add constraint idx_28553_primary
        primary key (id);

alter table t_clue_remark
    add constraint t_clue_remark_ibfk_3
        foreign key (clue_id) references t_clue
            on update cascade on delete restrict;

create table t_customer
(
    id                bigserial,
    clue_id           bigint,
    product           bigint,
    description       varchar(255),
    next_contact_time timestamp with time zone,
    create_time       timestamp with time zone,
    create_by         bigint,
    edit_time         timestamp with time zone,
    edit_by           bigint
);

comment on table t_customer is '客户表';

comment on column t_customer.id is '主键，自动增长，客户ID';

comment on column t_customer.clue_id is '线索ID';

comment on column t_customer.product is '选购产品';

comment on column t_customer.description is '客户描述';

comment on column t_customer.next_contact_time is '下次联系时间';

comment on column t_customer.create_time is '创建时间';

comment on column t_customer.create_by is '创建人';

comment on column t_customer.edit_time is '编辑时间';

comment on column t_customer.edit_by is '编辑人';

alter table t_customer
    owner to postgres;

create index idx_28558_t_customer_ibfk_1
    on t_customer (clue_id);

create index idx_28558_t_customer_ibfk_2
    on t_customer (product);

create index idx_28558_t_customer_ibfk_3
    on t_customer (create_by);

create index idx_28558_t_customer_ibfk_4
    on t_customer (edit_by);

alter table t_customer
    add constraint idx_28558_primary
        primary key (id);

alter table t_customer
    add constraint t_customer_ibfk_1
        foreign key (clue_id) references t_clue
            on update cascade on delete restrict;

create table t_customer_remark
(
    id           bigserial,
    customer_id  bigint,
    note_way     bigint,
    note_content varchar(255),
    create_by    bigint,
    create_time  timestamp with time zone,
    edit_time    timestamp with time zone,
    edit_by      bigint,
    deleted      bigint
);

comment on table t_customer_remark is '客户跟踪记录表';

comment on column t_customer_remark.id is '主键，自动增长，客户备注ID';

comment on column t_customer_remark.customer_id is '客户ID';

comment on column t_customer_remark.note_way is '跟踪方式';

comment on column t_customer_remark.note_content is '跟踪内容';

comment on column t_customer_remark.create_by is '跟踪人';

comment on column t_customer_remark.create_time is '跟踪时间';

comment on column t_customer_remark.edit_time is '编辑时间';

comment on column t_customer_remark.edit_by is '编辑人';

comment on column t_customer_remark.deleted is '删除状态（0正常，1删除）';

alter table t_customer_remark
    owner to postgres;

create index idx_28563_t_customer_remark_ibfk_1
    on t_customer_remark (customer_id);

create index idx_28563_t_customer_remark_ibfk_2
    on t_customer_remark (note_way);

create index idx_28563_t_customer_remark_ibfk_3
    on t_customer_remark (create_by);

create index idx_28563_t_customer_remark_ibfk_4
    on t_customer_remark (edit_by);

alter table t_customer_remark
    add constraint idx_28563_primary
        primary key (id);

alter table t_customer_remark
    add constraint t_customer_remark_ibfk_1
        foreign key (customer_id) references t_customer
            on update cascade on delete restrict;

create table t_dic_type
(
    id        bigserial,
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

alter table t_dic_type
    add constraint idx_28568_primary
        primary key (id);

create table t_dic_value
(
    id         bigserial,
    type_code  varchar(64),
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

alter table t_dic_value
    add constraint idx_28573_primary
        primary key (id);

alter table t_clue
    add constraint t_clue_ibfk_1
        foreign key (appellation) references t_dic_value
            on update cascade on delete restrict;

alter table t_clue
    add constraint t_clue_ibfk_2
        foreign key (state) references t_dic_value
            on update cascade on delete restrict;

alter table t_clue
    add constraint t_clue_ibfk_3
        foreign key (source) references t_dic_value
            on update cascade on delete restrict;

alter table t_clue
    add constraint t_clue_ibfk_8
        foreign key (need_loan) references t_dic_value
            on update cascade on delete restrict;

alter table t_clue
    add constraint t_clue_ibfk_9
        foreign key (intention_state) references t_dic_value
            on update cascade on delete restrict;

alter table t_clue_remark
    add constraint t_clue_remark_ibfk_4
        foreign key (note_way) references t_dic_value
            on update cascade on delete restrict;

alter table t_customer_remark
    add constraint t_customer_remark_ibfk_2
        foreign key (note_way) references t_dic_value
            on update cascade on delete restrict;

alter table t_dic_value
    add constraint t_dic_value_ibfk_1
        foreign key (type_code) references t_dic_type (type_code)
            on update cascade on delete restrict;

create table t_permission
(
    id        bigserial,
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

alter table t_permission
    add constraint idx_28578_primary
        primary key (id);

create table t_product
(
    id            bigserial,
    name          varchar(128),
    guide_price_s numeric(10, 2),
    guide_price_e numeric(10, 2),
    quotation     numeric(10, 2),
    state         bigint,
    create_time   timestamp with time zone,
    create_by     bigint,
    edit_time     timestamp with time zone,
    edit_by       bigint
);

comment on table t_product is '产品表';

comment on column t_product.id is '主键，自动增长，线索ID';

comment on column t_product.name is '产品名称';

comment on column t_product.guide_price_s is '官方指导起始价';

comment on column t_product.guide_price_e is '官方指导最高价';

comment on column t_product.quotation is '经销商报价';

comment on column t_product.state is '状态 0在售 1售罄';

comment on column t_product.create_time is '创建时间';

comment on column t_product.create_by is '创建人';

comment on column t_product.edit_time is '编辑时间';

comment on column t_product.edit_by is '编辑人';

alter table t_product
    owner to postgres;

create index idx_28583_t_product_ibfk_1
    on t_product (create_by);

create index idx_28583_t_product_ibfk_2
    on t_product (edit_by);

alter table t_product
    add constraint idx_28583_primary
        primary key (id);

alter table t_clue
    add constraint t_clue_ibfk_10
        foreign key (intention_product) references t_product
            on update cascade on delete restrict;

alter table t_customer
    add constraint t_customer_ibfk_2
        foreign key (product) references t_product
            on update cascade on delete restrict;

create table t_role
(
    id        bigserial,
    role      varchar(30),
    role_name varchar(30)
);

comment on table t_role is '角色表';

alter table t_role
    owner to postgres;

alter table t_role
    add constraint idx_28588_primary
        primary key (id);

create table t_role_permission
(
    id            bigserial,
    role_id       bigint,
    permission_id bigint
);

comment on table t_role_permission is '角色权限关系表';

alter table t_role_permission
    owner to postgres;

create index idx_28593_t_role_permission_ibfk_1
    on t_role_permission (role_id);

create index idx_28593_t_role_permission_ibfk_2
    on t_role_permission (permission_id);

alter table t_role_permission
    add constraint idx_28593_primary
        primary key (id);

alter table t_role_permission
    add constraint t_role_permission_ibfk_1
        foreign key (role_id) references t_role
            on update cascade on delete restrict;

alter table t_role_permission
    add constraint t_role_permission_ibfk_2
        foreign key (permission_id) references t_permission
            on update cascade on delete restrict;

create table t_system_info
(
    id           bigserial,
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
    create_by    bigint,
    edit_time    timestamp with time zone,
    edit_by      bigint
);

comment on table t_system_info is '系统信息表';

alter table t_system_info
    owner to postgres;

create index idx_28598_t_system_info_ibfk_1
    on t_system_info (create_by);

create index idx_28598_t_system_info_ibfk_2
    on t_system_info (edit_by);

alter table t_system_info
    add constraint idx_28598_primary
        primary key (id);

create table t_tran
(
    id                bigserial,
    tran_no           varchar(255),
    customer_id       bigint,
    money             numeric(10, 2),
    expected_date     timestamp with time zone,
    stage             bigint,
    description       varchar(255),
    next_contact_time timestamp with time zone,
    create_time       timestamp with time zone,
    create_by         bigint,
    edit_time         timestamp with time zone,
    edit_by           bigint
);

comment on table t_tran is '交易表';

comment on column t_tran.id is '主键，自动增长，交易ID';

comment on column t_tran.tran_no is '交易流水号';

comment on column t_tran.customer_id is '客户ID';

comment on column t_tran.money is '交易金额';

comment on column t_tran.expected_date is '预计成交日期';

comment on column t_tran.stage is '交易所处阶段';

comment on column t_tran.description is '交易描述';

comment on column t_tran.next_contact_time is '下次联系时间';

comment on column t_tran.create_time is '创建时间';

comment on column t_tran.create_by is '创建人';

comment on column t_tran.edit_time is '编辑时间';

comment on column t_tran.edit_by is '编辑人';

alter table t_tran
    owner to postgres;

create index idx_28606_t_tran_ibfk_1
    on t_tran (customer_id);

create index idx_28606_t_tran_ibfk_2
    on t_tran (stage);

create index idx_28606_t_tran_ibfk_3
    on t_tran (create_by);

create index idx_28606_t_tran_ibfk_4
    on t_tran (edit_by);

alter table t_tran
    add constraint idx_28606_primary
        primary key (id);

alter table t_tran
    add constraint t_tran_ibfk_1
        foreign key (customer_id) references t_customer
            on update cascade on delete restrict;

alter table t_tran
    add constraint t_tran_ibfk_2
        foreign key (stage) references t_dic_value
            on update cascade on delete restrict;

create table t_tran_history
(
    id            bigserial,
    tran_id       bigint,
    stage         bigint,
    money         numeric(10, 2),
    expected_date timestamp with time zone,
    create_time   timestamp with time zone,
    create_by     bigint
);

comment on table t_tran_history is '交易历史记录表';

comment on column t_tran_history.id is '主键，自动增长，交易记录ID';

comment on column t_tran_history.tran_id is '交易ID';

comment on column t_tran_history.stage is '交易阶段';

comment on column t_tran_history.money is '交易金额';

comment on column t_tran_history.expected_date is '交易预计成交时间';

comment on column t_tran_history.create_time is '创建时间';

comment on column t_tran_history.create_by is '创建人';

alter table t_tran_history
    owner to postgres;

create index idx_28613_t_tran_history_ibfk_1
    on t_tran_history (tran_id);

create index idx_28613_t_tran_history_ibfk_2
    on t_tran_history (stage);

create index idx_28613_t_tran_history_ibfk_3
    on t_tran_history (create_by);

alter table t_tran_history
    add constraint idx_28613_primary
        primary key (id);

alter table t_tran_history
    add constraint t_tran_history_ibfk_1
        foreign key (tran_id) references t_tran
            on update cascade on delete restrict;

alter table t_tran_history
    add constraint t_tran_history_ibfk_2
        foreign key (stage) references t_dic_value
            on update cascade on delete restrict;

create table t_tran_remark
(
    id           bigserial,
    tran_id      bigint,
    note_way     bigint,
    note_content varchar(255),
    create_time  timestamp with time zone,
    create_by    bigint,
    edit_time    timestamp with time zone,
    edit_by      bigint,
    deleted      bigint
);

comment on table t_tran_remark is '交易跟踪记录表';

comment on column t_tran_remark.id is '主键，自动增长，交易备注ID';

comment on column t_tran_remark.tran_id is '交易ID';

comment on column t_tran_remark.note_way is '跟踪方式';

comment on column t_tran_remark.note_content is '跟踪内容';

comment on column t_tran_remark.create_time is '跟踪时间';

comment on column t_tran_remark.create_by is '跟踪人';

comment on column t_tran_remark.edit_time is '编辑时间';

comment on column t_tran_remark.edit_by is '编辑人';

comment on column t_tran_remark.deleted is '删除状态（0正常，1删除）';

alter table t_tran_remark
    owner to postgres;

create index idx_28618_t_tran_remark_ibfk_1
    on t_tran_remark (tran_id);

create index idx_28618_t_tran_remark_ibfk_2
    on t_tran_remark (note_way);

create index idx_28618_t_tran_remark_ibfk_3
    on t_tran_remark (create_by);

create index idx_28618_t_tran_remark_ibfk_4
    on t_tran_remark (edit_by);

alter table t_tran_remark
    add constraint idx_28618_primary
        primary key (id);

alter table t_tran_remark
    add constraint t_tran_remark_ibfk_1
        foreign key (tran_id) references t_tran
            on update cascade on delete restrict;

alter table t_tran_remark
    add constraint t_tran_remark_ibfk_2
        foreign key (note_way) references t_dic_value
            on update cascade on delete restrict;

create table t_user
(
    id                     bigserial,
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

create unique index idx_28623_login_act
    on t_user (login_act);

create unique index idx_28623_phone
    on t_user (phone);

alter table t_user
    add constraint idx_28623_primary
        primary key (id);

alter table t_activity
    add constraint t_activity_ibfk_1
        foreign key (owner_id) references t_user
            on update cascade on delete restrict;

alter table t_activity
    add constraint t_activity_ibfk_2
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_activity
    add constraint t_activity_ibfk_3
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

alter table t_activity_remark
    add constraint t_activity_remark_ibfk_2
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_activity_remark
    add constraint t_activity_remark_ibfk_3
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

alter table t_clue
    add constraint t_clue_ibfk_4
        foreign key (owner_id) references t_user
            on update cascade on delete restrict;

alter table t_clue
    add constraint t_clue_ibfk_5
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_clue
    add constraint t_clue_ibfk_6
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

alter table t_clue_remark
    add constraint t_clue_remark_ibfk_1
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_clue_remark
    add constraint t_clue_remark_ibfk_2
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

alter table t_customer
    add constraint t_customer_ibfk_3
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_customer
    add constraint t_customer_ibfk_4
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

alter table t_customer_remark
    add constraint t_customer_remark_ibfk_3
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_customer_remark
    add constraint t_customer_remark_ibfk_4
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

alter table t_product
    add constraint t_product_ibfk_1
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_product
    add constraint t_product_ibfk_2
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

alter table t_system_info
    add constraint t_system_info_ibfk_1
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_system_info
    add constraint t_system_info_ibfk_2
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

alter table t_tran
    add constraint t_tran_ibfk_3
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_tran
    add constraint t_tran_ibfk_4
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

alter table t_tran_history
    add constraint t_tran_history_ibfk_3
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_tran_remark
    add constraint t_tran_remark_ibfk_3
        foreign key (create_by) references t_user
            on update cascade on delete restrict;

alter table t_tran_remark
    add constraint t_tran_remark_ibfk_4
        foreign key (edit_by) references t_user
            on update cascade on delete restrict;

create table t_user_role
(
    id      bigserial,
    user_id bigint,
    role_id bigint
);

comment on table t_user_role is '用户角色关系表';

alter table t_user_role
    owner to postgres;

create index idx_28628_t_user_role_ibfk_1
    on t_user_role (user_id);

create index idx_28628_t_user_role_ibfk_2
    on t_user_role (role_id);

alter table t_user_role
    add constraint idx_28628_primary
        primary key (id);

alter table t_user_role
    add constraint t_user_role_ibfk_1
        foreign key (user_id) references t_user
            on update cascade on delete restrict;

alter table t_user_role
    add constraint t_user_role_ibfk_2
        foreign key (role_id) references t_role
            on update cascade on delete restrict;

