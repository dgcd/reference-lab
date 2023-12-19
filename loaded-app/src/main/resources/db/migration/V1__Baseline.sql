create table main.operation (
    id              uuid                    not null,
    description     varchar(100)            not null,
    amount          numeric(15, 2)          not null    default 0,

    constraint      operation_pkey               primary key (id)
);
