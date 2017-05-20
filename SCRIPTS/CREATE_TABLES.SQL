DROP TABLE PERSON; 
DROP TABLE REGISTRY;

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
REGISTRYWEIGHT	integer not null,
REGISTRYHEIGHT	varchar(20) not null,
REGISTRYPOINT	integer not null,
FK_PERSONID	integer not null
);
