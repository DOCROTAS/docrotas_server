create table mdfe (
	id int not null auto_increment,
	numero numeric(9),
	serie numeric(3) default 0,
	chave varchar(44),
	dt_emissao timestamp,
	dt_iviagem timestamp,
	dt_fviagem timestamp,
	dt_criacao timestamp,
	dt_alteracao timestamp,
	apolice varchar(20),
	averbacao varchar(40),
	tipo_ambiente varchar(1),
	tipo_emissao varchar(1),
	situacao numeric(2) default 0,
	emitente_id int not null,
	seguradora_id int not null,
	motorista_id int not null,
	cidadeorigem_id int not null,
	cidadedestino_id int not null,
	veiculo_id int not null,
	primary key (id),
	foreign key (emitente_id) references empresa(id),
	foreign key (seguradora_id) references pessoa(id),
	foreign key (motorista_id) references pessoa(id),
	foreign key (cidadeorigem_id) references cidade(id),
	foreign key (cidadedestino_id) references cidade(id),
	foreign key (veiculo_id) references veiculo(id)
);