
-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++DEPORTES++++++++++++++++++++++++++++++++++++++++++++

-- INSERTA EL ID DEL USUARIO Y EL ID DEL DEPORTE PARA HABILITARLO 

CREATE OR REPLACE FUNCTION M05_insertardeporte(usuario INTEGER ,deporte INTEGER)  RETURNS void AS
$$

BEGIN

 INSERT INTO EXERCISE (EXERCISEID,FK_PERSON,FK_SPORT) VALUES (nextval('EXERCISEID'),usuario,deporte);

END
$$
LANGUAGE 'plpgsql' VOLATILE; 

--BUSCAR DATOS DE LOS DEPPORTES.
CREATE OR REPLACE FUNCTION M05_obtenerdatosdeporte (ide INTEGER) RETURNS TABLE (iddeporte NUMERIC, nombredeporte VARCHAR(200),metdeporte NUMERIC) AS 
$$ 
DECLARE
var_r record;
BEGIN 
for var_r in(SELECT * 
				 FROM  SPORT
				 WHERE SPORTID=ide)
loop
		iddeporte:=var_r.SPORTID;
		nombredeporte := var_r.SPORTNAME;
		metdeporte:=var_r.SPORTMET;
end loop;		
return next;

  
END; 
$$
LANGUAGE 'plpgsql' STABLE;

-- BUSCA EL MET DEL DEPORTE QUE EL USUARIO QUIERE AÑADIR A SU LISTA  A TRAVES DE SU NOMBRE
-- FUE CREADO PARA OBTENER EL MET DEL DEPORTE ANTES DE INSERTAR

CREATE OR REPLACE FUNCTION M05_obtenermetdeporte (nombre VARCHAR(200))  RETURNS TABLE (metdeporte FLOAT) AS 
$$ 
DECLARE
 var_r record;
BEGIN 

for var_r in  (SELECT SPORTMET
				FROM  SPORT
				WHERE SPORTNAME=nombre)
loop
		metdeporte := var_r.SPORTMET;
end loop;
return next;
END; 
$$
LANGUAGE 'plpgsql' STABLE;

-- OBTIENE EL ID A TRAVES DEL NOMBRE DEL DEPORTE

CREATE OR REPLACE FUNCTION M05_obteneriddeporte (nombre VARCHAR(200))  RETURNS TABLE (iddeporte INTEGER) AS 
$$ 
DECLARE
 var_r record;
BEGIN 

for var_r in  (SELECT SPORTID
				FROM  SPORT
				WHERE SPORTNAME=nombre)
loop
		iddeporte := var_r.SPORTID;
end loop;
return next;
END; 
$$
LANGUAGE 'plpgsql' STABLE;

-- CARGA TODOS LOS DEPORTES DE UN USUARIO A TRAVES DEL ID del usuario
CREATE OR REPLACE FUNCTION M05_obtenerdeportesusuario (usuario INTEGER) RETURNS TABLE (nombredeporte VARCHAR(200)) AS 
$$ 
DECLARE
 var_r record;
BEGIN 

for var_r in  (SELECT SPORTNAME 
		FROM   EXERCISE,SPORT,PERSON
		WHERE  FK_PERSON = usuario AND 
		FK_SPORT = SPORTID)
loop
		nombredeporte:= var_r.SPORTNAME;
		return next;
end loop;

END; 
$$
LANGUAGE 'plpgsql' STABLE;


-- ELIMINA DEPORTES REGISTRADOS


CREATE OR REPLACE FUNCTION M05_eliminardeporte(usuario INTEGER ,deporte INTEGER)  RETURNS void AS
$$

BEGIN

	 DELETE FROM EXERCISE WHERE fk_person=usuario and fk_sport=deporte;

END
$$
LANGUAGE 'plpgsql' VOLATILE; 



-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ACTIVIDADES+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

-- INSERTA LOS DATOS PARA EL REGISTROS DE LAS ACTIVIDADES de entrenamientos

CREATE OR REPLACE FUNCTION M05_insertaractividadentrenamiento (horainicio TIMESTAMP, horafinal TIMESTAMP, fecha TIMESTAMP, km NUMERIC, caloria NUMERIC, lugarinicio VARCHAR(200),lugarfinal VARCHAR(200),idregistry INTEGER,idsport INTEGER ,identrenamiento integer)
RETURNS void AS $$
BEGIN
	 INSERT INTO  ACTIVITY (ACTIVITYID,ACTIVITYSTARTTIME, ACTIVITYENDTIME,ACTIVITYDATE,ACTIVITYKM ,ACTIVITYCALOR,ACTIVITYSTARTSITE,ACTIVITYENDSITE,FK_REGISTRY,FK_SPORT,FK_TRAINING) VALUES (nextval('ACTIVITYID'),
                    horainicio,horafinal,fecha,km,caloria,lugarinicio,lugarfinal,idregistry,idsport,identrenamiento) ;

END
$$
LANGUAGE 'plpgsql' VOLATILE; 

-- INSERTA LOS DATOS PARA EL REGISTROS DE LAS ACTIVIDADES 

CREATE OR REPLACE FUNCTION M05_insertaractividad (horainicio TIMESTAMP, horafinal TIMESTAMP, fecha TIMESTAMP, km NUMERIC, caloria NUMERIC, lugarinicio VARCHAR(200),lugarfinal VARCHAR(200),idregistry INTEGER,idsport INTEGER )
RETURNS void AS $$
BEGIN
	 INSERT INTO  ACTIVITY (ACTIVITYID,ACTIVITYSTARTTIME, ACTIVITYENDTIME,ACTIVITYDATE,ACTIVITYKM ,ACTIVITYCALOR,ACTIVITYSTARTSITE,ACTIVITYENDSITE,FK_REGISTRY,FK_SPORT) VALUES (nextval('ACTIVITYID'),
                    horainicio,horafinal,fecha,km,caloria,lugarinicio,lugarfinal,idregistry,idsport) ;

END
$$
LANGUAGE 'plpgsql' VOLATILE; 

-- CARGA ACTIVIDADES DADAS DOS FECHAS + EL ID DE USUARIO

CREATE OR REPLACE FUNCTION M05_obteneractividades (fechamenor TIMESTAMP, fechamayor TIMESTAMP,usuario INTEGER) RETURNS TABLE (horainicio TIME, horafinal TIME, fecha DATE, km NUMERIC, caloria NUMERIC, lugarinicio VARCHAR(200),lugarfinal VARCHAR(200),nombredeporte VARCHAR(200)) AS $$
DECLARE
 var_r record;

BEGIN 

for var_r in  (SELECT ACTIVITYSTARTTIME, ACTIVITYENDTIME,ACTIVITYDATE,ACTIVITYKM ,ACTIVITYCALOR,ACTIVITYSTARTSITE,ACTIVITYENDSITE,SPORTNAME
			   FROM   ACTIVITY,SPORT,REGISTRY
			   WHERE  FK_PERSONID=usuario  and FK_REGISTRY=REGISTRYID and
					  FK_SPORT=SPORTID and ACTIVITYDATE>=fechamenor and ACTIVITYDATE<=fechamayor)
loop
	
		horainicio    :=var_r.ACTIVITYSTARTTIME;
		horafinal     :=var_r.ACTIVITYENDTIME;
		fecha         :=var_r.ACTIVITYDATE;
		km            :=var_r.ACTIVITYKM;
		caloria       :=var_r.ACTIVITYCALOR;
		lugarinicio   :=var_r ACTIVITYSTARTSITE;
		lugarfinal    :=var_r.ACTIVITYENDSITE;
		nombredeporte :=var_r.SPORTNAME;
		
		return next;
end loop;
END
$$
LANGUAGE 'plpgsql' VOLATILE; 

-- CARGA LAS CALORIAS QUEMADAS POR ACTIVIDAD---

CREATE OR REPLACE FUNCTION M05_obtenercaloriasactividades (fechamenor TIMESTAMP, fechamayor TIMESTAMP,usuario INTEGER) RETURNS TABLE (dia DATE ,caloria NUMERIC) AS $$
DECLARE
 var_r record;

BEGIN 

for var_r in  (SELECT ACTIVITYCALOR,ACTIVITYDATE
			   FROM   ACTIVITY,SPORT,REGISTRY
			   WHERE  FK_PERSONID=usuario  and FK_REGISTRY=REGISTRYID and
					  FK_SPORT=SPORTID and ACTIVITYDATE>=fechamenor and ACTIVITYDATE<=fechamayor)
loop
	
		caloria       :=var_r.ACTIVITYCALOR;
		dia           :=var_r.ACTIVITYDATE;

		return next;
end loop;
END
$$
LANGUAGE 'plpgsql' VOLATILE; 

-- CARGA LOS KILOMETROS RECORRIDOS POR DIA

CREATE OR REPLACE FUNCTION M05_obtenerkmactividades (fechamenor TIMESTAMP, fechamayor TIMESTAMP,usuario INTEGER) RETURNS TABLE (dia DATE ,km NUMERIC) AS $$
DECLARE
 var_r record;

BEGIN 

for var_r in  (SELECT ACTIVITYKM,ACTIVITYDATE
			   FROM   ACTIVITY,SPORT,REGISTRY
			   WHERE  FK_PERSONID=usuario  and FK_REGISTRY=REGISTRYID and
					  FK_SPORT=SPORTID and ACTIVITYDATE>=fechamenor and ACTIVITYDATE<=fechamayor)
loop
	
		km      	  :=var_r.ACTIVITYKM;
		dia           :=var_r.ACTIVITYDATE;

		return next;
end loop;
END
$$
LANGUAGE 'plpgsql' VOLATILE; 

-- FUNCIONA PERO CARGA 2 VECES EL VALOR DEL ID DE LA ACTIVIDAD;
-- CARGAR ID DE ACTIVIDAD---
CREATE OR REPLACE FUNCTION M05_obtenerIDactividades (fechamenor TIMESTAMP ,horainicio TIMESTAMP ,idregistro INTEGER) RETURNS TABLE (id INTEGER) AS $$
DECLARE
 var_r record;

BEGIN 

for var_r in  (SELECT ACTIVITYID
	       FROM   ACTIVITY,REGISTRY,PERSON
	       WHERE  FK_PERSONID=PERSONID  and FK_REGISTRY=idregistro and
       	              ACTIVITYDATE=fechamenor and ACTIVITYSTARTTIME=CAST(horainicio AS TIME))
loop
	
		id :=var_r.ACTIVITYID;


		return next;
end loop;
END
$$
LANGUAGE 'plpgsql' VOLATILE; 



-- Modificar ACTIVIDAD--->KM

CREATE OR REPLACE FUNCTION M05_modificarKmActividad (idactividad integer,km NUMERIC) RETURNS VOID AS
 $$
BEGIN
	 UPDATE ACTIVITY SET ACTIVITYKM=km
	 WHERE  ACTIVITYID =idactividad ;
	 
END
$$
LANGUAGE 'plpgsql' VOLATILE; 


-- Modificar Actividad---> CALORIAS
CREATE OR REPLACE FUNCTION M05_modificarcaloriaActividad (idactividad integer,calor NUMERIC) RETURNS VOID AS
 $$
BEGIN
	 UPDATE ACTIVITY SET ACTIVITYCALOR=calor
	 WHERE  ACTIVITYID =idactividad ;
	 
END
$$
LANGUAGE 'plpgsql' VOLATILE; 

-- DUDA : CUANDO MODIFICAS LOS KM DEBERIAS MODIFICAR TAMBIEN LAS CALORIAS NO? OSEA DEBERIAN RECALCULARSE

-- Eliminar Actividad 

CREATE OR REPLACE FUNCTION M05_eliminaractividad(idactividad INTEGER )  RETURNS void AS
$$

BEGIN

	 DELETE FROM ACTIVITY WHERE ACTIVITYID=idactividad;

END
$$
LANGUAGE 'plpgsql' VOLATILE; 
