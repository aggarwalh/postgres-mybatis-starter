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

CREATE TABLE json_table (
  jdoc jsonb not null
);

-- MSSQL developers gotchas:
    -- Postgres is SQL compliant. Thus most cases where Postgres and MSSQL Server don't match are cases where the latter is not compliant
    -- for dropping tables IF EXISTS () kinda check doesn't work in postgres, use to above strategy to drop
    -- using ';' is mandatory for multiline executions. Line delimiter doesn't work.
    -- data types and constraints
       -- SERIAL instead of INTEGER IDENTITY and BIGSERIAL -> LONG IDENTITY for auto-generated unit wise incremented id.
       -- DATE is same but DATETIME is TIMESTAMP
       -- DOUBLE PRECISION instead of just DOUBLE. Or use it's alias FLOAT8. (8 bit floating point number)
       -- BIGINT instead of LONG. Or it's alias INT8. (8 bit signed integer)
       -- NUMERIC type's rounding strategy is same as sql server (HALF_UP or "away from zero")
    -- Locking and Transaction Isolation
       -- In SQL Server a read without a transaction level set would involve read locks. To get away with this we add
       -- "with (nolock)" in sql query after table name or query within a transaction with Isolation level Read Uncommitted
       -- But in PostGreSQL (which uses Multi-Version Concurrency Control model), read not done is a transaction does not
       -- lock. Thus Read Uncommitted is meaningless in PostgreSQL. Rather pg maps transactions initiated with Read Uncommitted
       -- to Read Committed (which doesn't allow dirty reads but allows non-repeatable & phantom reads). Repeatable Read
       -- Isolation level is mapped to more strict serializable ( no dirty, non-repeatable and phantom reads). Serializable is
       -- mapped to