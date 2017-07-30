/***********************ESCOGE EL ULTIMO USUARIO REGISTRADO************************NUEVO*/
CREATE OR REPLACE FUNCTION M01_LASTUSER() RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	SELECT PERSONID INTO result FROM PERSON
	ORDER BY PERSONID DESC LIMIT 1;
	
 	RETURN result;
END;
$$ LANGUAGE plpgsql;


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
