DROP TABLE PERSON; 
DROP TABLE REGISTRY;
DROP TABLE TRAINING;
DROP TABLE EXER_TRAINING;


CREATE TABLE PERSON(

PERSONID		serial  primary key,
PERSONUSERNAME	varchar(20) unique not null,
PERSONPASSWORD	varchar(8) not null,
PERSONEMAIL	varchar(30) not null,
PERSONSEX		varchar(20) not null,
PERSONPHONE	varchar(20) not null,
PERSONBIRTHDATE date not null
);

CREATE TABLE REGISTRY (

REGISTRYID 	serial primary key,
REGISTRYWEIGHT	real not null,
REGISTRYHEIGHT	real not null,
REGISTRYPOINT	integer not null,
FK_PERSONID	integer not null
);

CREATE TABLE TRAINING (

TRAININGID serial primary key
TRAININGNAME varchar (80) not null,
TRAININGPERIOD integer not null,
TRAININGCALORIES integer not null,
FK_FRIENDSHIPID integer,
FK_USERID integer 
);

CREATE TABLE EXER_TRAINING ( 
EXER_TRAININGID serial not null, 
TIPO_COMPLEJIDAD varchar (90) not null, 
TIEMPO integer not null,
DISTANCIA integer not null,
FK_EXERCISEID integer ,
FK_TRAININGID integer 
);


