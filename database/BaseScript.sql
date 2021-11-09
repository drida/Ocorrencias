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

CREATE TABLE public.areaimpactada (
	id serial NOT NULL,
	nome varchar(60),
	CONSTRAINT areaimpactada_pk PRIMARY KEY (id)
);

CREATE TABLE public.etapaimpactada (
	id serial NOT NULL,
	nome varchar(60),
	CONSTRAINT etapaimpactada_pk PRIMARY KEY (id)
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
	idareaimpactada int NOT NULL,
	idetapaimpactada int NOT NULL,
	idusuario int NOT NULL,
	idtipoocorrencia int NOT NULL,
	observacoes varchar(255),
	idsistemas int NOT NULL,
	funcionariosafetados int,
	casosimpactados int,
	canalsuporte varchar(60),
	protocolo varchar(60),
	datahoraocorrencia timestamp without time zone,
	datahoraconclusao timestamp without time zone,
	tempoparaconclusao double precision,
	CONSTRAINT ocorrencia_pk PRIMARY KEY (id)
);

ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_empresa FOREIGN KEY (idempresa) REFERENCES public.empresa(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_equipe FOREIGN KEY (idequipe) REFERENCES public.equipe(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_status FOREIGN KEY (idstatus) REFERENCES public.status(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_usuario FOREIGN KEY (idusuario) REFERENCES public.usuario(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_tipoocorrencia FOREIGN KEY (idtipoocorrencia) REFERENCES public.tipoocorrencia(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_sistemas FOREIGN KEY (idsistemas) REFERENCES public.sistemas(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_areaimpactada FOREIGN KEY (idareaimpactada) REFERENCES public.areaimpactada(id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE public.ocorrencia ADD CONSTRAINT ocorrencia_fk_etapaimpactada FOREIGN KEY (idetapaimpactada) REFERENCES public.etapaimpactada(id) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE OR REPLACE VIEW public.ocorrencias
AS SELECT o.id,
    emp.nome AS empresa,
    eqp.nome AS equipe,
    sts.nome AS status,
    ari.nome AS area,
    eti.nome AS etapa,
    tpo.nome AS tipo,
    o.observacoes,
    sis.nome AS sistema,
    o.funcionariosafetados,
    o.casosimpactados,
    o.canalsuporte,
    o.protocolo,
    o.datahoraocorrencia,
    o.datahoraconclusao,
    o.tempoparaconclusao,
    o.idusuario
   FROM ocorrencia o
     JOIN empresa emp ON emp.id = o.idempresa
     JOIN equipe eqp ON eqp.id = o.idequipe
     JOIN status sts ON sts.id = o.idstatus
     JOIN areaimpactada ari ON ari.id = o.idareaimpactada
     JOIN etapaimpactada eti ON eti.id = o.idetapaimpactada
     JOIN tipoocorrencia tpo ON tpo.id = o.idtipoocorrencia
     JOIN sistemas sis ON sis.id = o.idsistemas;
