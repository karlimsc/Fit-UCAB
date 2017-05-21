
select M05_obtenerdatosdeporte(1);
select M05_obtenermetdeporte('CAMINAR');
select M05_obtenerdeportes('CAMINAR');
select M05_obtenerdeportesusuario(1);
select M05_insertardeporte(1,6);
select M05_eliminardeporte(1,6); 


Select M05_insertarActividad('21/05/2017 11:41:00','21/05/2017 11:41:00','21/05/2017',1,1,' ',' ',1,4);
select * from M05_obteneractividades ('19/05/2017', '21/05/2017' ,1)
select * from M05_obtenercaloriasactividades ('19/05/2017', '21/05/2017' ,1)