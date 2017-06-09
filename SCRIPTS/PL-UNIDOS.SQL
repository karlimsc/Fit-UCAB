

------------------------------------------------------STORE PROCEDURES------------------------------------------------------------ 
-- MODULO 1


/**************************************************INICIO DE SESION**************************************************/

CREATE OR REPLACE FUNCTION M01_INICIARSESION(varchar(20), varchar(80)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	IF ((SELECT COUNT(*) FROM person WHERE personusername = $1 AND personpassword = $2) = 0) THEN

		result := 0;
  	
  	ELSE
	
		SELECT PERSONID INTO result FROM PERSON WHERE PERSONUSERNAME = $1;
	
	END IF;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************REGISTRO DE USUARIO**************************************************/

CREATE OR REPLACE FUNCTION M01_REGISTRAR(varchar(20), varchar(80), varchar(50), varchar(1), varchar(20), date, real, real) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	INSERT INTO person(PERSONUSERNAME, PERSONPASSWORD, PERSONEMAIL, PERSONSEX, PERSONPHONE, PERSONBIRTHDATE) VALUES ($1, $2, $3, $4, $5, $6);
	INSERT INTO registry(REGISTRYWEIGHT, REGISTRYHEIGHT, REGISTRYPOINT, FK_PERSONID) VALUES ($7, $8, 0, (SELECT PERSONID FROM PERSON WHERE PERSONUSERNAME = $1));
	SELECT PERSONID INTO result FROM PERSON WHERE PERSONUSERNAME = $1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************RECUPERAR PASSWORD**************************************************/

CREATE OR REPLACE FUNCTION M01_RECUPERARPWD(varchar(50)) RETURNS TABLE (usuario varchar(20), password varchar(80)) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT PERSONUSERNAME, PERSONPASSWORD
		FROM PERSON
		WHERE PERSONEMAIL = $1
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************CONSULTAR USUARIO**************************************************/

CREATE OR REPLACE FUNCTION M01_INFORMACIONUSER(varchar(20)) RETURNS TABLE (id int, usuario varchar(20), pwd varchar(80), mail varchar(50), sex varchar(1), phone varchar(20), birthdate date, weight real, height real, points int) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT P.PERSONID, P.PERSONUSERNAME, P.PERSONPASSWORD, P.PERSONEMAIL, P.PERSONSEX, P.PERSONPHONE, P.PERSONBIRTHDATE, R.REGISTRYWEIGHT, R.REGISTRYHEIGHT, R.REGISTRYPOINT
		FROM PERSON P, REGISTRY R
		WHERE PERSONUSERNAME = $1 AND P.PERSONID = R.FK_PERSONID
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************BORRAR USUARIO**************************************************/

CREATE OR REPLACE FUNCTION M01_ELIMINARUSER(varchar(20)) RETURNS integer AS $$

DECLARE
 result integer;

BEGIN
	DELETE FROM PERSON WHERE PERSONUSERNAME = $1;
	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************ACTUALIZAR USUARIO**************************************************/

CREATE OR REPLACE FUNCTION M01_MODIFICARUSER(varchar(20), varchar(80), varchar(50), varchar(1), varchar(20)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	UPDATE person SET PERSONPASSWORD = $2, PERSONEMAIL = $3, PERSONSEX = $4, PERSONPHONE = $5 WHERE PERSONUSERNAME = $1;
	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;



--MODULO 2


/**************************************************Consultar Perfil por Username**************************************************/

CREATE OR REPLACE FUNCTION M02_CONSULTARPERFILUSER(username varchar(20)) RETURNS TABLE (id int, usuario varchar(20),
  password varchar(8), email varchar(30), sex varchar(20), phone varchar(20), birthdate date, weight real, height real,
  points int) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT P.PERSONID, P.PERSONUSERNAME, P.PERSONPASSWORD, P.PERSONEMAIL, P.PERSONSEX, P.PERSONPHONE, P.PERSONBIRTHDATE,
      R.REGISTRYWEIGHT, R.REGISTRYHEIGHT, R.REGISTRYPOINT
		FROM PERSON P, REGISTRY R
		WHERE PERSONUSERNAME = username AND P.PERSONID = R.FK_PERSONID
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************Consultar Perfil por ID**************************************************/

CREATE OR REPLACE FUNCTION M02_CONSULTARPERFILID(codigo int)
	RETURNS TABLE (id int,
		usuario varchar(20),
		email varchar(30),
		sex varchar(20),
		phone varchar(20),
		birthdate date,
		weight real,
		height real)
AS $$

BEGIN
 	RETURN QUERY
	SELECT P.PERSONID,
		P.PERSONUSERNAME,
		P.PERSONEMAIL,
		P.PERSONSEX,
		P.PERSONPHONE,
		P.PERSONBIRTHDATE,
		R.REGISTRYWEIGHT,
		R.REGISTRYHEIGHT
	FROM PERSON P, REGISTRY R
	WHERE PERSONID = codigo AND P.PERSONID = R.FK_PERSONID;
END;
$$ LANGUAGE plpgsql;

/**************************************************Modificar Perfil**************************************************/

CREATE OR REPLACE FUNCTION M02_MODIFICARPERFIL(id int, password varchar(8), email varchar(30), sex varchar(20),
  phone varchar(20), birthdate date) RETURNS integer AS $$
DECLARE

BEGIN
	UPDATE person SET PERSONPASSWORD = password, PERSONEMAIL = email, PERSONSEX = sex, PERSONPHONE = phone,
    PERSONBIRTHDATE = birthdate WHERE PERSONID = id;
END;
$$ LANGUAGE plpgsql;

/**************************************************Modificar Perfil y Registry**************************************************/

CREATE OR REPLACE FUNCTION M02_MODIFICARPERFILR(id int,
	password varchar(8),
	email varchar(30),
	sex varchar(20),
  phone varchar(20),
	birthdate date,
	weight real,
	height real)
	RETURNS VOID AS $$
DECLARE

BEGIN
	UPDATE person
	SET PERSONPASSWORD = password,
		PERSONEMAIL = email,
		PERSONSEX = sex,
		PERSONPHONE = phone,
		PERSONBIRTHDATE = birthdate
	WHERE PERSONID = id;

	UPDATE registry
	SET REGISTRYWEIGHT = weight,
		REGISTRYHEIGHT = height
	WHERE FK_PERSONID = id;
END;
$$ LANGUAGE plpgsql;

/**************************************************Comparar Perfil**************************************************/

CREATE OR REPLACE FUNCTION M02_COMPARARPERFILID(codigo int)
	RETURNS TABLE (id int,
		password varchar(8),
		email varchar(30),
		sex varchar(20),
		phone varchar(20),
		birthdate date,
		weight real,
		height real)
AS $$

BEGIN
	RETURN QUERY
	SELECT P.PERSONID,
		P.PERSONPASSWORD,
		P.PERSONEMAIL,
		P.PERSONSEX,
		P.PERSONPHONE,
		P.PERSONBIRTHDATE,
		R.REGISTRYWEIGHT,
		R.REGISTRYHEIGHT
	FROM PERSON P, REGISTRY R
	WHERE PERSONID = codigo AND P.PERSONID = R.FK_PERSONID;
END;
$$ LANGUAGE plpgsql;


/***************************************ACTUALIZACION PERSONALIZADA*************************************/
CREATE OR REPLACE FUNCTION M02_MODPERFILPASS(id int, password varchar(8))
  RETURNS VOID AS $$
DECLARE
BEGIN
  UPDATE person
  SET PERSONPASSWORD = password
  WHERE PERSONID = id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION M02_MODPERFILMAIL(id int, email varchar(30))
  RETURNS VOID AS $$
DECLARE
BEGIN
  UPDATE person
  SET PERSONEMAIL = email
  WHERE PERSONID = id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION M02_MODPERFILSEX(id int, sex varchar(20))
  RETURNS VOID AS $$
DECLARE
BEGIN
  UPDATE person
  SET PERSONSEX = sex
  WHERE PERSONID = id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION M02_MODPERFILPHONE(id int, phone varchar(20))
  RETURNS VOID AS $$
DECLARE
BEGIN
  UPDATE person
  SET PERSONPHONE = phone
  WHERE PERSONID = id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION M02_PERSONEXISTE(id int)
  RETURNS INTEGER AS $$
DECLARE
  result integer;
BEGIN
  IF ((SELECT COUNT(*) FROM person WHERE personid = id) = 0) THEN
    result := 0;
  ELSE
    result := 1;
  END IF;
  RETURN result;
END;
$$ LANGUAGE plpgsql;

------MODULO 5


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
CREATE OR REPLACE FUNCTION public.m05_obtenerdatosdeporte(nombre Varchar(200))
  RETURNS TABLE(iddeporte numeric, nombredeporte character varying, metdeporte numeric) AS
$BODY$ 
DECLARE
var_r record;
BEGIN 
for var_r in(SELECT * 
				 FROM  SPORT
				 WHERE SPORTNAME=nombre)
loop
		iddeporte:=var_r.SPORTID;
		nombredeporte := var_r.SPORTNAME;
		metdeporte:=var_r.SPORTMET;
end loop;		
return next;

  
END; 
$BODY$
  LANGUAGE plpgsql STABLE;

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
CREATE OR REPLACE FUNCTION M05_obtenerdeportesusuario (usuario INTEGER) RETURNS TABLE (id integer, nombredeporte VARCHAR(200),met float) AS 
$$ 
DECLARE
 var_r record;
BEGIN 

for var_r in  (SELECT SPORTNAME,SPORTID,SPORTMET 
		FROM   EXERCISE,SPORT,PERSON
		WHERE  FK_PERSON = usuario AND 
		FK_SPORT = SPORTID)
loop
		nombredeporte:= var_r.SPORTNAME;
		id:= var_r.SPORTID;
		met:=var_r.SPORTMET;
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

CREATE OR REPLACE FUNCTION M05_obteneractividades (fechamenor TIMESTAMP, fechamayor TIMESTAMP,usuario INTEGER) RETURNS TABLE (id integer,horainicio TIME, horafinal TIME, fecha DATE, km NUMERIC, caloria NUMERIC, lugarinicio VARCHAR(200),lugarfinal VARCHAR(200),nombredeporte VARCHAR(200)) AS $$
DECLARE
 var_r record;

BEGIN 

for var_r in  (SELECT ACTIVITYID,ACTIVITYSTARTTIME, ACTIVITYENDTIME,ACTIVITYDATE,ACTIVITYKM ,ACTIVITYCALOR,ACTIVITYSTARTSITE,ACTIVITYENDSITE,SPORTNAME
			   FROM   ACTIVITY,SPORT,REGISTRY
			   WHERE  FK_PERSONID=usuario  and FK_REGISTRY=REGISTRYID and
					  FK_SPORT=SPORTID and ACTIVITYDATE>=fechamenor and ACTIVITYDATE<=fechamayor)
loop
		id	      :=var_r.ACTIVITYID;
		horainicio    :=var_r.ACTIVITYSTARTTIME;
		horafinal     :=var_r.ACTIVITYENDTIME;
		fecha         :=var_r.ACTIVITYDATE;
		km            :=var_r.ACTIVITYKM;
		caloria       :=var_r.ACTIVITYCALOR;
		lugarinicio   :=var_r.ACTIVITYSTARTSITE;
		lugarfinal    :=var_r.ACTIVITYENDSITE;
		nombredeporte :=var_r.SPORTNAME;
		
		return next;
end loop;
END
$$
LANGUAGE 'plpgsql' VOLATILE; 

-- Actividades id usuario

CREATE OR REPLACE FUNCTION public.m05_obtenertodasactividades(IN usuario integer)
  RETURNS TABLE(id integer, horainicio time without time zone, horafinal time without time zone, fecha date, km numeric, caloria numeric, lugarinicio character varying, lugarfinal character varying, nombredeporte character varying) AS
$BODY$
DECLARE
 var_r record;

BEGIN 

for var_r in  (SELECT ACTIVITYID,ACTIVITYSTARTTIME, ACTIVITYENDTIME,ACTIVITYDATE,ACTIVITYKM ,ACTIVITYCALOR,ACTIVITYSTARTSITE,ACTIVITYENDSITE,SPORTNAME
               FROM   ACTIVITY,SPORT,REGISTRY
               WHERE  FK_PERSONID=usuario and REGISTRYID=FK_REGISTRY and FK_SPORT=SPORTID)
                    
loop
        id            :=var_r.ACTIVITYID;
        horainicio    :=var_r.ACTIVITYSTARTTIME;
        horafinal     :=var_r.ACTIVITYENDTIME;
        fecha         :=var_r.ACTIVITYDATE;
        km            :=var_r.ACTIVITYKM;
        caloria       :=var_r.ACTIVITYCALOR;
        lugarinicio   :=var_r.ACTIVITYSTARTSITE;
        lugarfinal    :=var_r.ACTIVITYENDSITE;
        nombredeporte :=var_r.SPORTNAME;
        
        return next;
end loop;
END
$BODY$
  LANGUAGE plpgsql VOLATILE;

-- Carga actividades por id usuario

CREATE OR REPLACE FUNCTION public.m05_obtenertodasactividades(IN usuario integer)
  RETURNS TABLE(id integer, horainicio time without time zone, horafinal time without time zone, fecha date, km numeric, caloria numeric, lugarinicio character varying, lugarfinal character varying, nombredeporte character varying) AS
$BODY$
DECLARE
 var_r record;

BEGIN 

for var_r in  (SELECT ACTIVITYID,ACTIVITYSTARTTIME, ACTIVITYENDTIME,ACTIVITYDATE,ACTIVITYKM ,ACTIVITYCALOR,ACTIVITYSTARTSITE,ACTIVITYENDSITE,SPORTNAME
               FROM   ACTIVITY,SPORT,REGISTRY
               WHERE  FK_PERSONID=usuario and REGISTRYID=FK_REGISTRY and FK_SPORT=SPORTID)
                    
loop
        id          :=var_r.ACTIVITYID;
        horainicio    :=var_r.ACTIVITYSTARTTIME;
        horafinal     :=var_r.ACTIVITYENDTIME;
        fecha         :=var_r.ACTIVITYDATE;
        km            :=var_r.ACTIVITYKM;
        caloria       :=var_r.ACTIVITYCALOR;
        lugarinicio   :=var_r.ACTIVITYSTARTSITE;
        lugarfinal    :=var_r.ACTIVITYENDSITE;
        nombredeporte :=var_r.SPORTNAME;
        
        return next;
end loop;
END
$BODY$
  LANGUAGE plpgsql VOLATILE;

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

-- Eliminar Actividad 

CREATE OR REPLACE FUNCTION M05_eliminaractividad(idactividad INTEGER )  RETURNS void AS
$$

BEGIN

	 DELETE FROM ACTIVITY WHERE ACTIVITYID=idactividad;

END
$$
LANGUAGE 'plpgsql' VOLATILE;

---Modulo 10

CREATE OR REPLACE  FUNCTION M10_AddWater( tm timestamp, gt integer, fkP integer,
                                 OUT res integer)
AS $$
DECLARE  
max integer;
BEGIN
		
	INSERT INTO glass_historic(
	 GLASSTIME, GLASSTYPE, fk_person)
	VALUES ( tm, gt, fkp);

    

    res = (SELECT Count(t.glasshistoricid) FROM glass_historic as t WHERE DATE(t.GLASSTIME) = DATE(tm) and fk_person = fkp);

END;
$$ LANGUAGE plpgsql VOLATILE;


INSERT INTO public.glass_historic(
            glasshistoricid, glasstime, glasstype, fk_person)
    VALUES (0, '11/11/2011', 0, 100);

CREATE OR REPLACE FUNCTION M10_DeletWaterTm (tm timestamp , fkP integer  , out res integer)
AS $$
DECLARE  


BEGIN
 	res :=(SELECT Count(t.glasshistoricid) FROM glass_historic as t WHERE DATE(t.GLASSTIME) = DATE(tm) and fk_person = fkp);
 	if res > 0 then
	delete from glass_historic where GLASSTIME=tm and fk_person=fkP ;
	res :=(SELECT Count(t.glasshistoricid) FROM glass_historic as t WHERE DATE(t.GLASSTIME) = DATE(tm) and fk_person = fkp);
	else 
	res :=0;
	end if;
END;
$$ LANGUAGE plpgsql VOLATILE;



CREATE OR REPLACE  FUNCTION M10_DeletWaterLast(tm timestamp, fkp integer , out res integer) 
AS $$
DECLARE  

BEGIN
 

	delete from glass_historic where glasshistoricid =
	( Select max(glasshistoricid) from glass_historic where fk_person = fkp and Date(GLASSTIME) = Date (tm));



    res :=(SELECT Count(t.glasshistoricid) FROM glass_historic as t WHERE DATE(t.GLASSTIME) = DATE(tm) and fk_person = fkp);


END;
$$ LANGUAGE plpgsql VOLATILE;







CREATE OR REPLACE  FUNCTION M10_GetWaterGlass( fkp int, date_stamp date,
                                 OUT sumG integer, OUT countG integer)
AS $$
DECLARE  

BEGIN	
sumG =  (SELECT Sum(t.GLASSTYPE) FROM glass_historic as t
       WHERE DATE(t.GLASSTIME) = date_stamp and fk_person = fkp);

countg =  (SELECT Count(t.glasshistoricid) FROM glass_historic as t
       WHERE DATE(t.GLASSTIME) = date_stamp and fk_person = fkp);
END;
$$ LANGUAGE plpgsql VOLATILE;


CREATE OR REPLACE FUNCTION M10_GetListFecha(fkp int, date_stamp date)
RETURNS TABLE(GLASSTIME timestamp, GLASSTYPE int) AS $$
BEGIN
  RETURN QUERY
    SELECT t.GLASSTIME,t.GLASSTYPE FROM glass_historic as t
      WHERE DATE(t.GLASSTIME) = DAte(date_stamp) and fk_person = fkp;
 end;
$$ LANGUAGE 'plpgsql';



CREATE OR REPLACE FUNCTION M10_Fechainter(fkp int, date_stamp date)
RETURNS TABLE(glasstime date, count bigint , sumg bigint) AS $$

DECLARE  
date2 date  ;
BEGIN
	date2 := DATE(date_stamp)+7;
  RETURN QUERY
  	SELECT DATE(t.glasstime) fecha  ,Count(t.glasshistoricid) ,  Sum(t.GLASSTYPE)  
	FROM glass_historic as t WHERE DATE(t.glasstime) >= DATE(date_stamp) and   t.glasstime < date2 
 	and fk_person = fkp group by t.GLASSTIME;

 end;
$$ LANGUAGE 'plpgsql';

-- Modulo 11


-- ELIMINAR UN ALIMENTO DE LA DIETA CONSUMIDA EN EL MISMO DIA
CREATE OR REPLACE FUNCTION m11_elimina_alimento_dieta(momento VARCHAR, usuario VARCHAR)
 RETURNS void AS $$
    DECLARE
        fecha_actual DATE;
    BEGIN
  fecha_actual := current_date;
        DELETE FROM DIET
        WHERE (select momentdescription from moment where momentdescription = momento) = momento and DIETDATETIME = fecha_actual;
    END; $$
LANGUAGE plpgsql;

--Este solo devuelve los alimentos personalizados del dia, al final esta el que devuelve todos.
CREATE OR REPLACE FUNCTION m11_get_alimentos_person(usuario VARCHAR)
  RETURNS TABLE(nombre_comida VARCHAR, peso_comida INT, calorias_comida INT, id_alimento INT)
   AS $$
DECLARE
   var_r    record;
   fecha_actual DATE;
BEGIN
   fecha_actual := (SELECT current_date);
   FOR var_r IN(SELECT  FOODNAME, FOODWEIGHT, FOODCALORIE, FOODID
        FROM PERSON inner join  DIET on personid = fk_personid inner join FOOD on fk_foodid = foodid
        WHERE FOODPERSONALIZED = TRUE AND personusername = usuario AND DIETDATETIME = fecha_actual)
   LOOP
    nombre_comida := var_r.FOODNAME;
    peso_comida := var_r.FOODWEIGHT;
    calorias_comida := var_r.FOODCALORIE;
  id_alimento := var_r.FOODID;
    RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;

-- OBTIENE LAS CALORIAS CONSUMIDAS DEL DIA
CREATE OR REPLACE FUNCTION m11_get_calorias_fecha(
    fecha DATE,
    usuario VARCHAR)
  RETURNS TABLE(calorias integer)
  AS $$
DECLARE
   var_r record;
BEGIN
   FOR var_r IN (SELECT SUM(DIETCALORIE) AS suma
           FROM PERSON, DIET
           WHERE PERSONID = FK_PERSONID AND DIETDATETIME = fecha AND PERSONUSERNAME = usuario)
   LOOP
          calorias := var_r.suma;
      RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;

--OBTIENE LOS ALIMENTOS QUE HA COMIDO EN CADA MOMENTO DEL DIA
CREATE OR REPLACE FUNCTION m11_get_comida_momento(
     momento VARCHAR,
     fecha DATE,
     usuario VARCHAR)
  RETURNS TABLE(nombre character varying, calorias integer, id_dieta integer)
  AS $$
DECLARE
  var_r record;
BEGIN
   FOR var_r IN(SELECT FOODNAME, DIETCALORIE, DIETID
             FROM moment, PERSON inner join  DIET on personid = fk_personid inner join FOOD on fk_foodid = foodid
         WHERE fk_momentid = momentid and momentdescription = momento and DIETDATETIME = fecha and
         PERSONUSERNAME = usuario)
   LOOP
    nombre := var_r.FOODNAME;
    calorias := var_r.DIETCALORIE;
  id_dieta := var_r.DIETID;
    RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;

-- INSERTA UN ALIMENTO PERSONALIZADO
CREATE OR REPLACE FUNCTION m11_inserta_alim_person(
    nombre VARCHAR,
    peso INT,
    calorias INT)
  RETURNS void
  AS $$
    BEGIN
        INSERT INTO FOOD (FOODNAME, FOODWEIGHT, FOODCALORIE, FOODPERSONALIZED) VALUES (nombre, peso, calorias, true);
    END; $$
  LANGUAGE plpgsql;

-- INSERTA UN ALIMENTO A LA DIETA
CREATE OR REPLACE FUNCTION m11_inserta_dieta(
    caloria INT,
    nombre_alimento VARCHAR,
    momento VARCHAR,
    ususario VARCHAR)
  RETURNS void
   AS $$
    DECLARE
        fecha_actual DATE;
    BEGIN
    fecha_actual  := current_date;
        INSERT INTO DIET (DIETCALORIE, DIETDATETIME, fk_foodid, fk_momentid, fk_personid) VALUES
        (caloria, fecha_actual, (SELECT FOODID FROM FOOD WHERE FOODNAME = nombre_alimento),
        (SELECT MOMENTID FROM MOMENT WHERE MOMENTDESCRIPTION = momento),
        (SELECT PERSONID FROM PERSON WHERE personusername = usuario) );
    END; $$
  LANGUAGE plpgsql;

-- NO SE USA POR LOS MOMENTOS
CREATE OR REPLACE FUNCTION m11_get_calorias_dia(
usuario VARCHAR)
  RETURNS TABLE(calorias INT)
  AS $$
DECLARE
   fecha_inicio date;
   fecha_fin    date;
   var_r record;
BEGIN
   fecha_fin := current_date;
   fecha_inicio := fecha_fin - 6;
   FOR var_r IN (SELECT SUM(DIETCALORIE) AS suma
           FROM PERSON, DIET
           WHERE PERSONID = FK_PERSONID AND DIETDATETIME  BETWEEN fecha_inicio AND fecha_fin AND PERSONUSERNAME = usuario)
   LOOP
          calorias := var_r.suma;
      RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;

-- SE USA PARA OBTENER LAS CALORIAS CONSUMIDAS POR EL USUARIO EN LOS ULTIMOS
-- 12 MESES, 4 SEMANAS Y 7 DIAS.
CREATE OR REPLACE FUNCTION m11_get_calorias_mes(
    IN usuario character varying,
    IN fecha_inicio date,
    IN fecha_fin date)
  RETURNS TABLE(calorias integer) AS
$BODY$
DECLARE
   var_r record;
BEGIN
   FOR var_r IN (SELECT SUM(DIETCALORIE) AS suma
           FROM PERSON as persona, DIET as dieta
           WHERE persona.PERSONID = dieta.FK_PERSONID AND
           dieta.DIETDATETIME BETWEEN fecha_inicio AND fecha_fin AND persona.PERSONUSERNAME = usuario)
   LOOP
          calorias := var_r.suma;
      RETURN NEXT;
   END LOOP;
END; $BODY$
  LANGUAGE plpgsql;

-- NO SE USA POR LOS MOMENTOS
CREATE OR REPLACE FUNCTION m11_get_calorias_semana(
    usuario VARCHAR)
  RETURNS TABLE(calorias integer)
  AS $$
DECLARE
   fecha_actual date;
   semana int;
   semana_atras int;
   var_r record;
BEGIN
   fecha_actual := current_date;
   semana := extract(week from fecha_actual);
   semana_atras := semana -4;
   FOR var_r IN (SELECT SUM(DIETCALORIE) AS suma
           FROM PERSON, DIET
           WHERE extract(week from DIET.DIETDATETIME) BETWEEN semana_atras AND semana AND
            PERSONID = FK_PERSONID AND PERSONUSERNAME = usuario)
   LOOP
          calorias := var_r.suma;
      RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;

-- NO SE USA POR LOS MOMENTOS
CREATE OR REPLACE FUNCTION m11_get_todos_alimentos(usuario VARCHAR)
  RETURNS TABLE(nombre_comida VARCHAR, peso_comida INT, calorias_comida INT, id_alimento INT)
   AS $$
DECLARE
   var_r    record;
   fecha_actual DATE;
BEGIN
   FOR var_r IN(SELECT  FOODNAME, FOODWEIGHT, FOODCALORIE, FOODID
        FROM PERSON inner join  DIET on personid = fk_personid inner join FOOD on fk_foodid = foodid
        WHERE personusername = usuario OR FOODPERSONALIZED = FALSE)
   LOOP
    nombre_comida := var_r.FOODNAME;
    peso_comida := var_r.FOODWEIGHT;
    calorias_comida := var_r.FOODCALORIE;
  id_alimento := var_r.FOODID;
    RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;

-- ACTUALIZA UN ALIMENTO PERSONALIZADO
CREATE OR REPLACE FUNCTION m11_act_alimento_person(nombre_alimento VARCHAR, peso_alimento VARCHAR, caloria_alimento INT, id_usuario INT)
  RETURNS void
   AS $$
DECLARE
    id_alimento int;
BEGIN
    id_alimento := (SELECT FOODID FROM FOOD, DIET, PERSON WHERE FOODNAME = nombre_alimento AND FK_PERSONID = id_usuario
                  AND FK_FOODID = FOODID);
    UPDATE FOOD SET FOODNAME = nombre_alimento , FOODWEIGHT = peso_alimento , FOODCALORIE = caloria_alimento
    WHERE FOODID = id_alimento;
END; $$
  LANGUAGE plpgsql;

-- ELIMINA ALIMENTO PERSONALIZADO
CREATE OR REPLACE FUNCTION m11_elimina_alimento_person(nombre_alimento VARCHAR, id_usuario INT)
  RETURNS void
   AS $$
DECLARE
  id_alimento int;
BEGIN
  id_alimento := (SELECT FOODID FROM FOOD WHERE FOODNAME = nombre_alimento);
  DELETE FROM DIET
  WHERE FK_FOODID = id_alimento and (select personid from person where personid = id_usuario) = id_usuario
  and FK_PERSONID = id_usuario;
  DELETE FROM FOOD
  WHERE FOODID = id_alimento;
END; $$
  LANGUAGE plpgsql;

-- OBTIENE LOS ALIMENTOS QUE SE SUGUEREN PARA LA CENA, EN BASE A LA CALORIAS RECOMENDADAS EN EL DIA
  CREATE OR REPLACE FUNCTION m11_get_alimentos_sugerencia(usuario VARCHAR, calorias INT)
  RETURNS TABLE(nombre_comida VARCHAR, peso_comida INT, calorias_comida INT, id_alimento INT)
   AS $$
DECLARE
   var_r  record;
BEGIN
   FOR var_r IN(SELECT  FOODNAME, FOODWEIGHT, FOODCALORIE, FOODID
    FROM PERSON inner join  DIET on personid = fk_personid inner join FOOD on fk_foodid = foodid
    WHERE personusername = usuario AND FOODDINNER = TRUE AND FOODCALORIE <= calorias)
   LOOP
  nombre_comida := var_r.FOODNAME;
  peso_comida := var_r.FOODWEIGHT;
  calorias_comida := var_r.FOODCALORIE;
  id_alimento := var_r.FOODID;
  RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;

-- OBTIENE TODOS LOS MOMENTOS DISPONIBLES EN LA APP
CREATE OR REPLACE FUNCTION m11_get_momentos()
  RETURNS TABLE(momento VARCHAR, momento_id INT)
   AS $$
DECLARE
   var_r  record;
BEGIN
   FOR var_r IN(SELECT  MOMENTDESCRIPTION, MOMENTID
    FROM MOMENT)
   LOOP
  momento := var_r.MOMENTDESCRIPTION;
  momento_id := var_r.MOMENTID;
  RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;
-- OBTIENE LOS ALIMENTOS DE INVESTIGACION Y PERSONALIZADOS POR EL USUARIO
CREATE OR REPLACE FUNCTION m11_get_todos_alimentos_autocompletar(usuario VARCHAR)
  RETURNS TABLE(nombre_comida VARCHAR, peso_comida INT, calorias_comida INT, id_alimento INT)
   AS $$
DECLARE
   var_r  record;
   fecha_actual DATE;
BEGIN
   FOR var_r IN(SELECT  FOODNAME, FOODWEIGHT, FOODCALORIE, FOODID
    FROM PERSON inner join  DIET on personid = fk_personid inner join FOOD on fk_foodid = foodid
    WHERE personusername = usuario OR FOODPERSONALIZED = false
    UNION
    SELECT  FOODNAME, FOODWEIGHT, FOODCALORIE, FOODID
    FROM FOOD
    WHERE FOODPERSONALIZED = false)
   LOOP
  nombre_comida := var_r.FOODNAME;
  peso_comida := var_r.FOODWEIGHT;
  calorias_comida := var_r.FOODCALORIE;
  id_alimento := var_r.FOODID;
  RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;

--Este devuelve todos los alimentos personalizados de la persona (TODOS, no solo los del dia)
  CREATE OR REPLACE FUNCTION m11_get_alimentos_person_lista(usuario VARCHAR)
  RETURNS TABLE(nombre_comida VARCHAR, peso_comida INT, calorias_comida INT, id_alimento INT)
   AS $$
DECLARE
   var_r  record;
   fecha_actual DATE;
BEGIN
   FOR var_r IN(SELECT  FOODNAME, FOODWEIGHT, FOODCALORIE, FOODID
    FROM PERSON inner join  DIET on personid = fk_personid inner join FOOD on fk_foodid = foodid
    WHERE FOODPERSONALIZED = TRUE AND personusername = usuario)
   LOOP
  nombre_comida := var_r.FOODNAME;
  peso_comida := var_r.FOODWEIGHT;
  calorias_comida := var_r.FOODCALORIE;
  id_alimento := var_r.FOODID;
  RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;
