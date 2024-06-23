create table operation (
    id              bigserial               not null,
    description     varchar(100)            not null,
    balance         numeric(15, 2)          not null    default 0,
    is_closed       bool                    not null    default false,

    constraint      operation_pkey               primary key (id),
    constraint      operation_description_key    unique (description)
);
