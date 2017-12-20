## Database and User setup

Assuming postgresql is installed, login as root and configure db, user

* postgres=# CREATE DATABASE financedb;
* postgres=# \l
* postgres=# CREATE ROLE hadev WITH LOGIN PASSWORD 'h@dev123';
* postgres=# \du
* postgres=# GRANT ALL PRIVILEGES ON DATABASE "financedb" to hadev
