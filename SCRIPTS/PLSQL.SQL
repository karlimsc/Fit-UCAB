
select res from m10_addwater('03/04/2014',300,1);



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

    

    res = (SELECT Count(t.glasshistoricid) FROM glass_historic as t WHERE DATE(t.GLASSTIME) = DATE(tm) and fk_person = fkp);

END;
$$ LANGUAGE plpgsql VOLATILE;




CREATE OR REPLACE FUNCTION M10_DeletWaterTm (tm timestamp , fkP integer  , out res integer)
AS $$
DECLARE  
res integer ;

BEGIN
 

	delete from glass_historic where GLASSTIME=tm and fk_person=fkP ;

    res :=(SELECT Count(t.glasshistoricid) FROM glass_historic as t WHERE DATE(t.GLASSTIME) = DATE(tm) and fk_person = fkp);


END;
$$ LANGUAGE plpgsql VOLATILE;



CREATE OR REPLACE  FUNCTION M10_DeletWaterLast(tm timestamp, fkp integer , out res integer) 
AS $$
DECLARE  

BEGIN
 

	delete from glass_historic where glasshistoricid =
	( select glasshistoricid from  glass_historic where
	 DATE(GLASSTIME) = DATE(tm) and fk_person = fkp order by GLASSTIME desc limit 1);



    res :=(SELECT Count(t.glasshistoricid) FROM glass_historic as t WHERE DATE(t.GLASSTIME) = DATE(tm) and fk_person = fkp);


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
RETURNS TABLE(GLASSTIME timestamp, count int , sumg) AS $$

DECLARE  
date2 timestamp ;
BEGIN
	date2 := DATE(date_stamp)+7;
  RETURN QUERY
  	SELECT DATE(t.glasstime) fecha  ,Count(t.glasshistoricid) ,  Sum(t.GLASSTYPE)  
	FROM glass_historic as t WHERE DATE(t.glasstime) >= DATE(date_stamp) and   t.glasstime < date2 
 	and fk_person = 1 group by t.GLASSTIME;

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





