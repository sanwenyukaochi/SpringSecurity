create table t_tran_history
(
    id            bigserial
        constraint idx_28613_primary
            primary key,
    tran_id       bigint
        constraint t_tran_history_ibfk_1
            references t_tran
            on update cascade on delete restrict,
    stage         bigint
        constraint t_tran_history_ibfk_2
            references t_dic_value
            on update cascade on delete restrict,
    money         numeric(10, 2),
    expected_date timestamp with time zone,
    create_time   timestamp with time zone,
    create_by     bigint
        constraint t_tran_history_ibfk_3
            references t_user
            on update cascade on delete restrict
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

create index idx_28613_t_tran_history_ibfk_3
    on t_tran_history (create_by);

create index idx_28613_t_tran_history_ibfk_1
    on t_tran_history (tran_id);

create index idx_28613_t_tran_history_ibfk_2
    on t_tran_history (stage);

