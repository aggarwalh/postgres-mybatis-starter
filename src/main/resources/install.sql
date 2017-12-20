DROP TABLE IF EXISTS flat_table;

CREATE TABLE flat_table(
    id BIGSERIAL not null,
    str_attr1 varchar(100) not null,
    str_attr2 varchar(100) not null,
    int_attr1 int not null,
    int_attr2 int not null,
    long_attr1 bigint not null,
    long_attr2 bigint not null,
    double_attr1 double precision not null,
    double_attr2 double precision not null,
    bigdecimal_attr1 numeric(18,8) not null,
    bigdecimal_attr2 numeric(18,8) not null,
    date_attr1 date not null,
    date_attr2 timestamp not null
);