
/**M02_Gestion_Perfil_Home**/

/**************************************************Consultar Perfil por Username**************************************************/

CREATE OR REPLACE FUNCTION M02_CONSULTARPERFILUSER(username varchar(20)) RETURNS TABLE (id int, usuario varchar(20), password varchar(8), email varchar(30), sex varchar(20), phone varchar(20), birthdate date, weight real, height real, points int) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT P.PERSONID, P.PERSONUSERNAME, P.PERSONPASSWORD, P.PERSONEMAIL, P.PERSONSEX, P.PERSONPHONE, P.PERSONBIRTHDATE, R.REGISTRYWEIGHT, R.REGISTRYHEIGHT, R.REGISTRYPOINT
		FROM PERSON P, REGISTRY R
		WHERE PERSONUSERNAME = username AND P.PERSONID = R.FK_PERSONID
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************Consultar Perfil por ID**************************************************/

CREATE OR REPLACE FUNCTION M02_CONSULTARPERFILID(codigo int) RETURNS TABLE (id int, usuario varchar(20), password varchar(8), email varchar(30), sex varchar(20), phone varchar(20), birthdate date, weight real, height real, points int) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT P.PERSONID, P.PERSONUSERNAME, P.PERSONPASSWORD, P.PERSONEMAIL, P.PERSONSEX, P.PERSONPHONE, P.PERSONBIRTHDATE, R.REGISTRYWEIGHT, R.REGISTRYHEIGHT, R.REGISTRYPOINT
		FROM PERSON P, REGISTRY R
		WHERE PERSONUSERNAME = codigo AND P.PERSONID = R.FK_PERSONID
	;
END;
$$ LANGUAGE plpgsql;


/**************************************************Modificar Perfil**************************************************/

CREATE OR REPLACE FUNCTION M02_MODIFICARPERFIL(id int, password varchar(8), email varchar(30), sex varchar(20), phone varchar(20), birthdate date) RETURNS integer AS $$
DECLARE


BEGIN
	UPDATE person SET PERSONPASSWORD = password, PERSONEMAIL = email, PERSONSEX = sex, PERSONPHONE = phone, PERSONBIRTHDATE = birthdate WHERE PERSONUSERNAME = id;

END;
$$ LANGUAGE plpgsql;

/**************************************************Modificar Perfil y Registry**************************************************/

CREATE OR REPLACE FUNCTION M02_MODIFICARPERFILR(id int, password varchar(8), email varchar(30), sex varchar(20), phone varchar(20), birthdate date, weight real, height real, points int) RETURNS integer AS $$
DECLARE


BEGIN
	UPDATE person SET PERSONPASSWORD = password, PERSONEMAIL = email, PERSONSEX = sex, PERSONPHONE = phone, PERSONBIRTHDATE = birthdate WHERE PERSONUSERNAME = id;
    UPDATE registry SET REGISTRYWEIGHT = weight, REGISTRYHEIGHT = height, REGISTRYPOINT = points WHERE FK_PERSONID = id;

END;
$$ LANGUAGE plpgsql;


