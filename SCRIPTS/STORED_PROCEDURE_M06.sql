--Inicio Modulo 06--
-- Funcion para insertar un entrenamiento 
CREATE OR REPLACE FUNCTION insertTraining( idTraining INTEGER,
					   trainingName CHARACTER VARYING(80),
					   trainingPeriod INTEGER,
					   trainingCalories INTEGER,
					   fk_userId INTEGER
) RETURNS void AS $$
BEGIN
	insert into TRAINING values (idTraining, trainingName, trainingPeriod, trainingCalories, NULL , fk_userId);
	COMMIT;
END;
$$LANGUAGE plpgsql;

--Funcion para borrar entrenamiento
CREATE OR REPLACE FUNCTION  deleteTraining( idTraining INTEGER
) RETURNS void AS $$
BEGIN
	delete from training where trainingid= idTraining;
	COMMIT;
END;
$$LANGUAGE plpgsql;

--Funcion para agregar entrenamiendo predefinio por tiempo
CREATE OR REPLACE FUNCTION insertTrainingPredefinedTime(
					   trainingName CHARACTER VARYING(80),
					   trainingPeriod INTEGER,
					   trainingCalories INTEGER,
					   fk_userId INTEGER,
					   typeComplexity CHARACTER VARYING(80),
					   time_var INTEGER,
					   fk_sportid INTEGER
) RETURNS void AS $$
BEGIN
	INSERT INTO TRAINING values( nextval('TRAININGID') , trainingName , trainingPeriod , trainingCalories , NULL , fk_userId);
	INSERT INTO SPOR_TRAINING VALUES( nextval('SPOR_TRAININGID') , typeComplexity , time_var , NULL , fk_sportid , nextval('trainingid')-1 );
	COMMIT;
END;
$$LANGUAGE plpgsql;
--Fin Modulo 6--

