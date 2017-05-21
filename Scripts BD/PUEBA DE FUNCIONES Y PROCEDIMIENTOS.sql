
select M05_obtenerdatosdeporte(1);
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
select m05_modificarActividad(2,20000000);
