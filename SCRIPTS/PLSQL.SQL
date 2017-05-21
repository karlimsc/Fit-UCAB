

CREATE OR REPLACE  FUNCTION M10_AddWater( tm timestamp, gt integer, fkP integer,
                                 OUT res integer)
AS $$
DECLARE  
res integer ;
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







SELECT glasshistoricid, glasstime, glasstype, fk_person
  FROM public.glass_historic where  Extract(day from glasstime ) = '03' and Extract(month from glasstime ) = '03' 
  and Extract(year from glasstime ) = '2014' and fk_person = 2;
 