-- Planificar una actividad de actividad--
CREATE OR REPLACE function m7_inserta_actividad(fecha_inicio DATE, fecha_fin DATE, hora_inicio TIME, duracion TIME
	, lunes BOOLEAN, martes BOOLEAN, miercoles BOOLEAN, jueves BOOLEAN, viernes BOOLEAN, sabado BOOLEAN, domingo BOOLEAN, usuario INT
	, deporte INT)
	RETURNS void AS $$
	BEGIN
		INSERT INTO PLANIFICATION ( PLANIFICATIONSTARTDATE, PLANIFICATIONENDDATE, PLANIFICATIONSTARTTIME, PLANIFICATIONDURATIONTIME
		,MONDAY , TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, FK_PERSONID, FK_SPORTID) VALUES (
		fecha_inicio, fecha_fin, hora_inicio, duracion, lunes, martes, miercoles, jueves, viernes, sabado, domingo, usuario, deporte);
	END; $$
LANGUAGE plpgsql;
--Probar que funciona aquui abajo
--select m7_inserta_actividad('12-12-2010' , '12-12-2017', '04:00:00', '06:00:00', true, true, true, true, true, true
--, true, 1, 1);

--Editar una activdad planificada
CREATE OR REPLACE function m7_edita_actividad(idPlanificacion INT, fecha_inicio DATE, fecha_fin DATE, hora_inicio TIME, duracion TIME
	, lunes BOOLEAN, martes BOOLEAN, miercoles BOOLEAN, jueves BOOLEAN, viernes BOOLEAN, sabado BOOLEAN, domingo BOOLEAN, usuario INT
	, deporte INT)
	RETURNS void AS $$
	BEGIN
		UPDATE PLANIFICATION  SET  PLANIFICATIONSTARTDATE =fecha_inicio, PLANIFICATIONENDDATE =fecha_fin, PLANIFICATIONSTARTTIME =hora_inicio
		, PLANIFICATIONDURATIONTIME = duracion	,MONDAY =lunes , TUESDAY =martes, WEDNESDAY =miercoles, THURSDAY =jueves, FRIDAY =viernes
		, SATURDAY =sabado, SUNDAY =domingo, FK_SPORTID = deporte
		WHERE FK_PERSONID = usuario and PLANIFICATIONID = idPlanificacion;
	END; $$
LANGUAGE plpgsql;

--Para probar que funcione....
--select m7_edita_actividad('12-12-2010' , '12-12-2017', '04:00:00', '06:00:00', true, true, false, true, true, true
--, true, 1, 1);


--Eliminar una planificacion
CREATE OR REPLACE function m7_elimina_actividad(id_planificacion int, usuario INT)
	RETURNS void AS $$
	BEGIN
		DELETE  FROM PLANIFICATION
		WHERE FK_PERSONID = usuario AND PLANIFICATIONID = id_planificacion;
	END; $$
LANGUAGE plpgsql;
--Ejemplo
--select m7_elimina_actividad(1,1);


--Leer todas las planificaciones del usuario (actividades)
CREATE OR REPLACE function m7_get_actividad(usuario INT)
	RETURNS TABLE ( id_planificacion INT ,fecha_inicio DATE, fecha_fin DATE, hora_inicio TIME, duracion TIME
		, lunes BOOLEAN, martes BOOLEAN, miercoles BOOLEAN, jueves BOOLEAN, viernes BOOLEAN, sabado BOOLEAN
		, domingo BOOLEAN, deporte INT)
	 AS $$
	 DECLARE
	 	var_r record;
		BEGIN
			FOR var_r IN ( SELECT PLANIFICATIONID, PLANIFICATIONSTARTDATE, PLANIFICATIONENDDATE, PLANIFICATIONENDDATE, PLANIFICATIONSTARTTIME, PLANIFICATIONDURATIONTIME,
							MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, FK_SPORTID
						   FROM PLANIFICATION
						   WHERE FK_PERSONID = usuario)
			LOOP
			id_planificacion := var_r.PLANIFICATIONID;
			fecha_inicio := var_r.PLANIFICATIONSTARTDATE;
			fecha_fin := var_r.PLANIFICATIONENDDATE;
			hora_inicio := var_r.PLANIFICATIONSTARTTIME;
			duracion := var_r.PLANIFICATIONDURATIONTIME;
			lunes := var_r.MONDAY;
			martes := var_r.TUESDAY;
			miercoles := var_r.WEDNESDAY;
			jueves := var_r.THURSDAY;
			viernes := var_r.FRIDAY;
			sabado := var_r.SATURDAY;
			domingo := var_r.SUNDAY;
			deporte := var_r.FK_SPORTID;
			RETURN NEXT;
			END LOOP;
		END; $$
LANGUAGE plpgsql;
--ejemplo
-- SELECT * FROM m7_get_actividad(1);

--Verificar si existe una planificacion especifica de un usuario (actividades)
CREATE OR REPLACE function m7_get_actividad_por_id(planificacion INT, usuario INT)
	RETURNS TABLE ( id_planificacion INT ,fecha_inicio DATE, fecha_fin DATE, hora_inicio TIME, duracion TIME
		, lunes BOOLEAN, martes BOOLEAN, miercoles BOOLEAN, jueves BOOLEAN, viernes BOOLEAN, sabado BOOLEAN
		, domingo BOOLEAN, deporte INT)
	 AS $$
	 DECLARE
	 	var_r record;
		BEGIN
			FOR var_r IN ( SELECT PLANIFICATIONID, PLANIFICATIONSTARTDATE, PLANIFICATIONENDDATE, PLANIFICATIONENDDATE, PLANIFICATIONSTARTTIME, PLANIFICATIONDURATIONTIME,
							MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, FK_SPORTID
						   FROM PLANIFICATION
						   WHERE FK_PERSONID = usuario AND PLANIFICATIONID = planificacion)
			LOOP
			id_planificacion := var_r.PLANIFICATIONID;
			fecha_inicio := var_r.PLANIFICATIONSTARTDATE;
			fecha_fin := var_r.PLANIFICATIONENDDATE;
			hora_inicio := var_r.PLANIFICATIONSTARTTIME;
			duracion := var_r.PLANIFICATIONDURATIONTIME;
			lunes := var_r.MONDAY;
			martes := var_r.TUESDAY;
			miercoles := var_r.WEDNESDAY;
			jueves := var_r.THURSDAY;
			viernes := var_r.FRIDAY;
			sabado := var_r.SATURDAY;
			domingo := var_r.SUNDAY;
			deporte := var_r.FK_SPORTID;
			RETURN NEXT;
			END LOOP;
		END; $$
LANGUAGE plpgsql;
--ejemplo
-- SELECT * FROM m7_get_actividad_por_id(1,1);


