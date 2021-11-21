DROP TABLE IF EXISTS public.empresa CASCADE;
DROP TABLE IF EXISTS public.equipe CASCADE;
DROP TABLE IF EXISTS public.status CASCADE;
DROP TABLE IF EXISTS public.areaimpactada CASCADE;
DROP TABLE IF EXISTS public.etapaimpactada CASCADE;
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

insert into  usuario (id, nome, email, pass) values (1,'TESTE','a@a.com','a');
  
insert into  STATUS (id, nome) values (1,'PENDENTE');
insert into  STATUS (id, nome) values (2,'CONCLUÍDO');

insert into  EMPRESA (id, nome) values (1,'SANTANDER');
insert into  EMPRESA (id, nome) values (2,'SBK');
insert into  EMPRESA (id, nome) values (3,'OLÉ');
insert into  EMPRESA (id, nome) values (4,'ZURICH');

insert into  EQUIPE (id, nome) values (1,'AREAS EXTERNAS');
insert into  EQUIPE (id, nome) values (2,'ATIVOS');
insert into  EQUIPE (id, nome) values (3,'CARTÕES');
insert into  EQUIPE (id, nome) values (4,'CONTA CONRRENTE');
insert into  EQUIPE (id, nome) values (5,'DEFESA');
insert into  EQUIPE (id, nome) values (6,'DNRJ');
insert into  EQUIPE (id, nome) values (7,'FINANCEIRA');
insert into  EQUIPE (id, nome) values (8,'FRAUDE');
insert into  EQUIPE (id, nome) values (9,'INTIMAÇÕES');
insert into  EQUIPE (id, nome) values (10,'MOTOR');
insert into  EQUIPE (id, nome) values (11,'OFICIOS');
insert into  EQUIPE (id, nome) values (12,'OLÉ CONSIGNADO');
insert into  EQUIPE (id, nome) values (13,'ORGÃO REGULADORES');
insert into  EQUIPE (id, nome) values (14,'OUTROS');
insert into  EQUIPE (id, nome) values (15,'PROCURA DOCUMENTO');
insert into  EQUIPE (id, nome) values (16,'RECEPÇÃO');
insert into  EQUIPE (id, nome) values (17,'TRIAG CADASTRO');
insert into  EQUIPE (id, nome) values (18,'ZURICH');
insert into  EQUIPE (id, nome) values (19,'FINANCEIRA/OUTROS');

insert into  TIPOOCORRENCIA (id, nome) values (1,'ERRO');
insert into  TIPOOCORRENCIA (id, nome) values (2,'FALTA DE REDE');
insert into  TIPOOCORRENCIA (id, nome) values (3,'FRETADO');
insert into  TIPOOCORRENCIA (id, nome) values (4,'INFRAESTRUTURA');
insert into  TIPOOCORRENCIA (id, nome) values (5,'LENTIDÃO');
insert into  TIPOOCORRENCIA (id, nome) values (6,'PERCA DE ACESSO');
insert into  TIPOOCORRENCIA (id, nome) values (7,'QUEDA DE ENERGIA');
insert into  TIPOOCORRENCIA (id, nome) values (8,'QUEDA SISTICA');
insert into  TIPOOCORRENCIA (id, nome) values (9,'SEM ACESSO');

insert into  SISTEMAS (id, nome) values (1,'BENNER');
insert into  SISTEMAS (id, nome) values (2,'BOXBOT');
insert into  SISTEMAS (id, nome) values (3,'CICA');
insert into  SISTEMAS (id, nome) values (4,'COLDWEB');
insert into  SISTEMAS (id, nome) values (5,'CRM');
insert into  SISTEMAS (id, nome) values (6,'CSG');
insert into  SISTEMAS (id, nome) values (7,'EXYON');
insert into  SISTEMAS (id, nome) values (8,'GUARDA FISICA');
insert into  SISTEMAS (id, nome) values (9,'HARDWARE');
insert into  SISTEMAS (id, nome) values (10,'LINK SANTANDER');
insert into  SISTEMAS (id, nome) values (11,'LOMBARD');
insert into  SISTEMAS (id, nome) values (12,'LY');
insert into  SISTEMAS (id, nome) values (13,'LYNX');
insert into  SISTEMAS (id, nome) values (14,'MANAGER');
insert into  SISTEMAS (id, nome) values (15,'NEW SPACE');
insert into  SISTEMAS (id, nome) values (16,'OUTROS SISTEMAS');
insert into  SISTEMAS (id, nome) values (17,'PASTA DIGITAL');
insert into  SISTEMAS (id, nome) values (18,'PEOPLE SOFT');
insert into  SISTEMAS (id, nome) values (19,'PIM/PIR');
insert into  SISTEMAS (id, nome) values (20,'PIPEFY');
insert into  SISTEMAS (id, nome) values (21,'PORTAIS CONSIGNADO');
insert into  SISTEMAS (id, nome) values (22,'PORTAL AYMORÉ');
insert into  SISTEMAS (id, nome) values (23,'PORTAL DO CONTRATO');
insert into  SISTEMAS (id, nome) values (24,'RECTOR');
insert into  SISTEMAS (id, nome) values (25,'RPA SBK');
insert into  SISTEMAS (id, nome) values (26,'SERASA/SPC');
insert into  SISTEMAS (id, nome) values (27,'SINJUD');
insert into  SISTEMAS (id, nome) values (28,'SISCARD');
insert into  SISTEMAS (id, nome) values (29,'STOQUE');
insert into  SISTEMAS (id, nome) values (30,'TFC');
insert into  SISTEMAS (id, nome) values (31,'TMA');
insert into  SISTEMAS (id, nome) values (32,'VENTANA');
insert into  SISTEMAS (id, nome) values (33,'VPN');
insert into  SISTEMAS (id, nome) values (34,'YK');

insert into  AREAIMPACTADA (id, nome) values (1,'DEFESA');
insert into  AREAIMPACTADA (id, nome) values (2,'MOTOR');
insert into  AREAIMPACTADA (id, nome) values (3,'SOJ');

insert into  ETAPAIMPACTADA (id, nome) values (1,'CADASTRO');
insert into  ETAPAIMPACTADA (id, nome) values (2,'COMPLEMENTO');
insert into  ETAPAIMPACTADA (id, nome) values (3,'CONCLUSÃO');
insert into  ETAPAIMPACTADA (id, nome) values (4,'RECEPÇÃO');
insert into  ETAPAIMPACTADA (id, nome) values (5,'TRIAGEM');