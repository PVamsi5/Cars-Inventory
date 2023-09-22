--creating a table for storing data of all cars.
create table if not exists cars(
    id bigint Auto_increment primary key,
    price bigint,
    model varchar(255) ,
    company varchar(255) ,
    release_year int
);