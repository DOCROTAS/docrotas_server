create table pessoa (
	id int not null auto_increment,
	nome varchar(60) not null,
	fantasia varchar(60),
	cpf_cnpj varchar(14) not null,
	ie varchar(14),
	tipo_pessoa int not null,
	conta_id int not null,
	dt_criacao timestamp,
	dt_alteracao timestamp,
	primary key (id),
	foreign key (conta_id) references conta(id)
);