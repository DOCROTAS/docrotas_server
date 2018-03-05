create table certificado (
	id int not null auto_increment,
	conta_id int not null,
	dt_inclusao timestamp,
	dt_vencimento timestamp,
	nome_arquivo varchar(80),
	senha varchar(80),
	observacao varchar(255),
	arquivo blob,
	primary key (id),
	foreign key (conta_id) references conta(id)
);