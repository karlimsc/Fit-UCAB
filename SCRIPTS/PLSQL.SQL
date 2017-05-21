

CREATE OR REPLACE  FUNCTION M10_AddWater( tm timestamp, gt integer, fkP integer,
                                 OUT res integer)
AS $$
DECLARE  
max integer;
BEGIN
	
max = (Select max(glasshistoricid)+1 from glass_historic) ;
	
	INSERT INTO glass_historic(
	glasshistoricid, GLASSTIME, GLASSTYPE, fk_person)
	VALUES (max, tm, gt, fkp);

    

    res :=100;

END;
$$ LANGUAGE plpgsql VOLATILE;

CREATE OR REPLACE  FUNCTION M10_DeletWater(id integer, fkp integer , out res integer) 
AS $$
DECLARE  
res integer ;

BEGIN

	DELETE FROM public.glass_historic
	WHERE glasshistoricid = id and fk_person = fkp;

    res :=100;


END;
$$ LANGUAGE plpgsql VOLATILE;




CREATE OR REPLACE FUNCTION M10_GetListFecha(fkp int, date_stamp date)
RETURNS TABLE(GLASSTIME int, GLASSTYPE timestamp) AS $$
BEGIN
  RETURN QUERY
    SELECT t.GLASSTYPE,t.GLASSTIME FROM glass_historic as t
      WHERE DATE(t.GLASSTIME) = date_stamp and fk_person = fkp;
 end;
$$ LANGUAGE 'plpgsql';




CREATE OR REPLACE  FUNCTION M10_GetWaterGlass( fkp int, date_stamp date,
                                 OUT max integer, OUT max1 integer)
AS $$
DECLARE  

BEGIN	
max =  (SELECT Sum(t.GLASSTYPE) FROM glass_historic as t
       WHERE DATE(t.GLASSTIME) = date_stamp and fk_person = fkp);

max1 =  (SELECT Count(t.glasshistoricid) FROM glass_historic as t
       WHERE DATE(t.GLASSTIME) = date_stamp and fk_person = fkp);
END;
$$ LANGUAGE plpgsql VOLATILE;

