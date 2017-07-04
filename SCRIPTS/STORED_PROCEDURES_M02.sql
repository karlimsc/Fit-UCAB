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
      WHERE DATE(t.GLASSTIME) = date_stamp and fk_person = fkp;
 end;
$$ LANGUAGE 'plpgsql';

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
   fecha_inicio := fecha_fin - Interval '6 days';
   FOR var_r IN (SELECT SUM(DIETCALORIE) AS suma
           FROM PERSON, DIET
           WHERE PERSONID = FK_PERSONID AND DIETDATETIME  BETWEEN fecha_inicio AND fecha_fin AND PERSONUSERNAME = usuario)
   LOOP
          calorias := var_r.suma;
      RETURN NEXT;
   END LOOP;
END; $$
  LANGUAGE plpgsql;
