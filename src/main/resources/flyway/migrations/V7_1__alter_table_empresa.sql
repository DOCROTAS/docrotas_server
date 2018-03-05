alter table empresa add column fantasia varchar(70) not null;
alter table empresa add column ie varchar(14) not null;
alter table empresa add column logradouro varchar(70) not null;
alter table empresa add column nro int not null;
alter table empresa add column complemento varchar(60);
alter table empresa add column bairro varchar(60) not null;
alter table empresa add column cidade_id int not null;
alter table empresa add foreign key (cidade_id) references cidade(id);
alter table empresa add column email varchar(60);