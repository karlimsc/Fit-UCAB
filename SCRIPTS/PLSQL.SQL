

CREATE OR REPLACE  FUNCTION M10_AddWater( tm timestamp, gt integer, fkP integer,
                                 OUT res integer)
AS $$
DECLARE  
res integer ;
max integer;
BEGIN
	
max = (Select max(glasshistoricid)+1 from glass_historic) ;
	
	INSERT INTO glass_historic(
	glasshistoricid, GLASSTYPE, fk_glass, fk_person)
	VALUES (max, tm, gt, fkp);
    res :=100;

END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE  FUNCTION M10_DeletWater(id integer , out res integer) 
AS $$
DECLARE  
res integer ;

BEGIN

	DELETE FROM public.glass_historic
	WHERE glasshistoricid = id;

    res :=100;


END;
$$ LANGUAGE plpgsql;
