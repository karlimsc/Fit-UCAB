
CREATE OR REPLACE  FUNCTION M10_AddWater(id int2, tm timestamp, fkg int2, fkP int2,
                                 OUT res int2)
AS $$
DECLARE  
res int2;
BEGIN
	INSERT INTO glass_historic(
	glasshistoricid, glasstime, fk_glass, fk_person)
	VALUES (id, tm, fkg, fkp);
    res :=1;

END;
$$ LANGUAGE plpgsql;



CREATE OR REPLACE  FUNCTION M10_AddWater( tm timestamp, fkg integer, fkP integer,
                                 OUT res integer)
AS $$
DECLARE  
res integer ;
max integer;
BEGIN
	
max = (Select max(glasshistoricid)+1 from glass_historic) ;
	
	INSERT INTO glass_historic(
	glasshistoricid, glasstime, fk_glass, fk_person)
	VALUES (max, tm, fkg, fkp);
    res :=100;

END;
$$ LANGUAGE plpgsql;
