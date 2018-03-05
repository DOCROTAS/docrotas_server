create table endereco_pessoa (
	id int not null auto_increment,
	logradouro varchar(70) not null,
	nro int not null,
	complemento varchar(60),
	bairro varchar(60) not null,
	cep varchar(8) not null,
	cidade_id int not null,
	pessoa_id int not null,
	dt_criacao timestamp,
	dt_alteracao timestamp,
	primary key (id),
	foreign key (cidade_id) references cidade(id),
	foreign key (pessoa_id) references pessoa(id)
);