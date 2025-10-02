create table t_tran_remark
(
    id           bigserial
        constraint idx_28618_primary
            primary key,
    tran_id      bigint
        constraint t_tran_remark_ibfk_1
            references t_tran
            on update cascade on delete restrict,
    note_way     bigint
        constraint t_tran_remark_ibfk_2
            references t_dic_value
            on update cascade on delete restrict,
    note_content varchar(255),
    create_time  timestamp with time zone,
    create_by    bigint
        constraint t_tran_remark_ibfk_3
            references t_user
            on update cascade on delete restrict,
    edit_time    timestamp with time zone,
    edit_by      bigint
        constraint t_tran_remark_ibfk_4
            references t_user
            on update cascade on delete restrict,
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

create index idx_28618_t_tran_remark_ibfk_3
    on t_tran_remark (create_by);

create index idx_28618_t_tran_remark_ibfk_2
    on t_tran_remark (note_way);

create index idx_28618_t_tran_remark_ibfk_4
    on t_tran_remark (edit_by);

