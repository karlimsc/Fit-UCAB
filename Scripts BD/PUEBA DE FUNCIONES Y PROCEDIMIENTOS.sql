
select M05_obtenerdatosdeporte(1);
select M05_obteneriddeporte('CAMINAR');
select M05_obtenermetdeporte('CAMINAR');
select M05_obtenerdeportes('CAMINAR');
select M05_obtenerdeportesusuario(1);
select M05_insertardeporte(1,6);
select M05_eliminardeporte(1,6); 


Select M05_insertarActividad('21/05/2017 11:41:00','21/05/2017 11:41:00','21/05/2017',1,1,' ',' ',1,4);
Select M05_insertarActividad('21/05/2017 11:00:00','21/05/2017 11:41:00','21/05/2017',1,1,' ',' ',1,2);
Select M05_insertarActividadentrenamiento('21/05/2017 11:00:00','21/05/2017 11:41:00','21/05/2017',1,1,' ',' ',1,2,1);
select * from M05_obteneractividades ('19/05/2017', '21/05/2017' ,1)
select * from M05_obtenercaloriasactividades ('19/05/2017', '21/05/2017' ,1)
select * from M05_obtenerkmactividades('19/05/2017', '20/05/2017' ,1);
SELECT * FROM M05_obteneridactividades('25/05/2017','25/05/2017 1:00:00',1) -- OJO ESTA FUNCION;
select m05_modificarkmActividad(15,20000000); -- RECUERDA Q AQUI PASAS LA ID DE LA ACTIVIDAD;
select M05_modificarcaloriaActividad(15,20000000);-- RECUERDA Q AQUI PASAS LA ID DE LA ACTIVIDAD;
SELECT M05_eliminaractividad(15 ) ;   -- ELIMINA A TRAVES DE LA ID DE LA ACTIVIDAD

