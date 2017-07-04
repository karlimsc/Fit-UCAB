/**************************************************CREATE TRAINING**************************************************/

CREATE OR REPLACE FUNCTION M06_CREATETRAINING(varchar(50), integer) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	INSERT INTO TRAINING(TRAININGID, TRAININGNAME, FK_PERSONID) VALUES ((SELECT MAX(TRAININGID) FROM TRAINING) + 1, $1,$2);
	SELECT MAX(TRAININGID) INTO result FROM TRAINING;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************ADDTRAINING_ACTIVITY**************************************************/
CREATE OR REPLACE FUNCTION M06_ADDTRAINING_ACTIVITY(numeric, integer, integer) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	INSERT INTO TRAINING_ACTIVITY(TRAINING_ACTIVITYID, TRAINING_ACTIVITYHOURS, FK_TRAININGID, FK_ACTIVITYID) VALUES ((SELECT MAX(TRAINING_ACTIVITYID) FROM TRAINING_ACTIVITY) + 1, $1, $2, $3);
	SELECT MAX(TRAINING_ACTIVITYID) INTO result FROM TRAINING_ACTIVITY;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************UPDATE_TRAINING**************************************************/
--------MODIFICA EL NOMBRE---------
CREATE OR REPLACE FUNCTION M06_MODIFYNAMETRAINING(integer, varchar(50)) RETURNS integer AS $$
DECLARE
 result integer;

BEGIN
	UPDATE TRAINING SET TRAININGNAME = $2 WHERE TRAININGID = $1;
	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

-------ELIMINA UNA ACTIVIDAD-------
CREATE OR REPLACE FUNCTION M06_DELETE_ACTIVITY(integer, integer) RETURNS integer AS $$

DECLARE
 result integer;

BEGIN
	DELETE FROM TRAINING_ACTIVITY WHERE FK_TRAININGID = $1;
	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************DELETE_TRAINING**************************************************/
CREATE OR REPLACE FUNCTION M06_DELETE_TRAINING(integer) RETURNS integer AS $$

DECLARE
 result integer;

BEGIN
	DELETE FROM TRAINING_ACTIVITY WHERE FK_TRAININGID = $1;
	DELETE FROM TRAINING WHERE TRAININGID = $1;
	result := 1;
 	RETURN result;
END;
$$ LANGUAGE plpgsql;

/**************************************************GET_EVERY_TRAINING**************************************************/
CREATE OR REPLACE FUNCTION M06_GET_TRAININGS(integer) RETURNS TABLE (id integer, training_name varchar(50)) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT TRAININGID, TRAININGNAME
		FROM TRAINING
		WHERE FK_PERSONID = $1
	;
END;
$$ LANGUAGE plpgsql;

/**************************************************GET_TRAINING_DETAILS**************************************************/
CREATE OR REPLACE FUNCTION M06_GET_TRAINING_DETAILS(integer) RETURNS TABLE (id integer, activityhours integer, activityname varchar(100)) AS $$

BEGIN
 	RETURN QUERY
	 	SELECT TA.TRAINING_ACTIVITYID, TA.TRAINING_ACTIVITYHOURS, A.ACTIVITYNAME
		FROM TRAINING T, ACTIVITY A, TRAINING_ACTIVITY TA  
		WHERE T.TRAININGID = $1 AND TA.FK_TRAININGID = T.TRAININGID AND TA.FK_ACTIVITYID = A.ACTIVITYID
	;
END;
$$ LANGUAGE plpgsql;