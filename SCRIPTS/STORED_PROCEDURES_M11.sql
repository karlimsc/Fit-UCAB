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
CREATE OR REPLACE FUNCTION m11_inserta_alim_person(nombre character varying, peso integer, calorias integer, cena boolean, id_usuario integer)
  RETURNS void AS
$BODY$
  DECLARE
  id_alimento integer;
  id_user    integer;
    BEGIN
        INSERT INTO FOOD (FOODNAME, FOODWEIGHT, FOODCALORIE, FOODPERSONALIZED, FOODDINNER) VALUES (nombre, peso, calorias, true, cena);
        id_alimento = (SELECT distinct FOODID FROM FOOD WHERE FOODNAME = nombre and FOODWEIGHT = peso and FOODCALORIE = calorias and
        FOODDINNER = cena);
        INSERT INTO DIET (DIETCALORIE, DIETDATETIME, FK_FOODID, FK_MOMENTID, FK_PERSONID) VALUES (0, '01-01-1990', id_alimento, 1, id_usuario);
    END; $BODY$
  LANGUAGE plpgsql

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
