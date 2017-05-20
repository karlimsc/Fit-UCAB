CREATE TABLE PERSON(

PERSONID		serial  not null,
PERSONUSERNAME	varchar(20) not null,
PERSONPASSWORD	varchar(8) not null,
PERSONEMAIL	varchar(30) not null,
PERSONSEX		varchar(20) not null,
PERSONPHONE	varchar(20) not null,
constraint PK_PERSONID primary key (PERSONID)
);

CREATE TABLE REGISTRY (

REGISTRYID 	integer not null,
REGISTRYWEIGHT	integer not null,
REGISTRYHEIGHT	varchar(20) not null,
REGISTRYPOINT	integer not null,
FK_PERSONID	integer not null,
constraint PK_REGISTRYID primary key (REGISTRYID)

);
