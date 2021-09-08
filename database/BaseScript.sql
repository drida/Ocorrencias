DROP TABLE IF EXISTS public.empresa CASCADE;
DROP TABLE IF EXISTS public.equipe CASCADE;
DROP TABLE IF EXISTS public.status CASCADE;
DROP TABLE IF EXISTS public.usuario CASCADE;
DROP TABLE IF EXISTS public.tipoocorrencia CASCADE;
DROP TABLE IF EXISTS public.sistemas CASCADE ;
DROP TABLE IF EXISTS public.ocorrencia CASCADE;

CREATE TABLE public.empresa (
	id serial NOT NULL,
	nome varchar(60),
	CONSTRAINT empresa_pk PRIMARY KEY (id)
);

CREATE TABLE public.equipe (
	id serial NOT NULL,
	nome varchar(60),
	CONSTRAINT equipe_pk PRIMARY KEY (id)
);

CREATE TABLE public.status (
	id serial NOT NULL,
	nome varchar(60),
	CONSTRAINT status_pk PRIMARY KEY (id)
);

CREATE TABLE public.usuario (
	id serial NOT NULL,
	nome varchar(60),
	email varchar(255),
	pass varchar(20),
	CONSTRAINT usuario_pk PRIMARY KEY (id)
);

CREATE TABLE public.tipoocorrencia (
	id serial NOT NULL,
	nome varchar(60),
	CONSTRAINT tipoocorrencia_pk PRIMARY KEY (id)
);

CREATE TABLE public.sistemas (
	id serial NOT NULL,
	nome varchar(60),
	CONSTRAINT sistemas_pk PRIMARY KEY (id)
);

CREATE TABLE public.ocorrencia (
	id serial NOT NULL,
	idempresa int NOT NULL,
	idequipe int NOT NULL,
	idstatus int NOT NULL,
	idusuario int NOT NULL,
	idtipoocorrencia int NOT NULL,
	observacoes varchar(255),
	idsistemas int NOT NULL,
	funcionariosafetados int,
	canalsuporte varchar(60),
	protocolo varchar(60),
	datahoraocorrencia timestamp without time zone,
	datahoraconclusao timestamp without time zone,
	tempoparaconclusao timestamp without time zone,
	CONSTRAINT ocorrencia_pk PRIMARY KEY (id)
);

ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_empresa FOREIGN KEY (idempresa) REFERENCES public.empresa(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_equipe FOREIGN KEY (idequipe) REFERENCES public.equipe(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_status FOREIGN KEY (idstatus) REFERENCES public.status(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_usuario FOREIGN KEY (idusuario) REFERENCES public.usuario(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_tipoocorrencia FOREIGN KEY (idtipoocorrencia) REFERENCES public.tipoocorrencia(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_sistemas FOREIGN KEY (idsistemas) REFERENCES public.sistemas(id) ON DELETE CASCADE ON UPDATE CASCADE;
