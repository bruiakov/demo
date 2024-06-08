create table if not exists CURRENCY (
id bigint GENERATED ALWAYS AS IDENTITY primary key,
code varchar(3) not null,
description varchar(100)
);

create table if not exists CURRENCY_DATE (
id bigint GENERATED ALWAYS AS IDENTITY primary key,
currency_date date not null
);

create table if not exists CURRENCY_VALUE (
id bigint GENERATED ALWAYS AS IDENTITY primary key,
currency_date_id bigint references CURRENCY_DATE(id),
currency_id bigint references CURRENCY(id),
rate double not null
);

create user if not exists robot password 'N1JwJXZ2oYFRgoD';
ALTER USER robot ADMIN TRUE;