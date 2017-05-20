
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

-- INSERTA LOS DATOS PARA EL REGISTROS DE LAS ACTIVIDADES

CREATE OR REPLACE FUNCTION insertarActividad (horainicio TIME, horafinal TIME, fecha DATE, km NUMERIC, caloria NUMERIC, lugarinicio VARCHAR(200),lugarfinal VARCHAR(200) ) RETURNS void AS $$
BEGIN
	 INSERT INTO  ACTIVITY (ACTIVITYID,ACTIVITYSTARTTIME, ACTIVITYENDTIME,ACTIVITYDATE,ACTIVITYKM ,ACTIVITYCALOR,ACTIVITYSTARTSITE,ACTIVITYENDSITE) VALUES (nextval('ACTIVITYID'),
                    horainicio,horafinal,fecha,km,caloria,lugarinicio,lugarfinal) ;

END
$$
LANGUAGE 'plpgsql' VOLATILE; 

-- Modificar ACTIVIDAD--->KM

CREATE OR REPLACE FUNCTION modificarActividad (usuario INTEGER,km NUMERIC) RETURNS BOOLEAN AS $$
BEGIN
	 UPDATE ACTIVITY SET ACTIVITYKM=km
	 WHERE  FK_PERSON=usuario;
	 RETURN TRUE;
	 
END
$$
LANGUAGE 'plpgsql' VOLATILE; 
-- DUDA : CUANDO MODIFICAS LOS KM DEBERIAS MODIFICAR TAMBIEN LAS CALORIAS NO? OSEA DEBERIAN RECALCULARSE
