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
END;
$$LANGUAGE plpgsql;

--Funcion para borrar entrenamiento
CREATE OR REPLACE FUNCTION  deleteTraining( idTraining INTEGER
) RETURNS void AS $$
BEGIN
	delete from training where trainingid= idTraining;
END;
$$LANGUAGE plpgsql;
--Fin Modulo 6--