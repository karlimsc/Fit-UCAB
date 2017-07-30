CREATE OR REPLACE FUNCTION M09_GetAchieveChallengeById(code int)
  RETURNS TABLE (id int,
                 name varchar(20),
                 description varchar(30),
                 score int)
AS $$
BEGIN
  RETURN QUERY
  SELECT C.challengeid,
         C.challengename,
         C.challengedescription,
         C.challengescore
    FROM CHALLENGE C, DETAIL D
   WHERE D.FK_CHALLENGEID = C.CHALLENGEID
     AND D.FK_USERID = code
     AND D.DETAILACTIVE = TRUE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION M09_GetAchieveAndUanchieveChallengeById(code int)
  RETURNS TABLE (achieve bigint,
                 unachieve bigint)
AS $$
BEGIN
  RETURN QUERY
  SELECT COUNT(D.*) achieve,
         (select COUNT(*)
          FROM  DETAIL D
          WHERE D.FK_USERID = code) unachieve
  FROM CHALLENGE C, DETAIL D
  WHERE D.FK_CHALLENGEID = C.CHALLENGEID
        AND D.FK_USERID = code
        AND D.DETAILACTIVE = TRUE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION M09_GetScoreChallengeById(code int)
  RETURNS bigint
AS $$
DECLARE
  sum bigint;
BEGIN
  SELECT SUM(C.CHALLENGESCORE)
  INTO sum
  FROM CHALLENGE C, DETAIL D
  WHERE D.FK_CHALLENGEID = C.CHALLENGEID
        AND D.FK_USERID = code
        AND D.DETAILACTIVE = TRUE;
  RETURN sum;
END;
$$ LANGUAGE plpgsql;