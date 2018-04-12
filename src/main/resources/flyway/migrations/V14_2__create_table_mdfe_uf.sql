create table mdfe_uf (
	sequencia int(2) not null,
	id_mdfe int not null,
	id_uf int(2) not null,
	dt_criacao timestamp,
	dt_alteracao timestamp,	
	constraint pk_mdfe_uf primary key (sequencia, id_mdfe, id_uf),
	foreign key (id_mdfe) references mdfe(id),
	foreign key (id_uf) references uf(cod_ibge)
);