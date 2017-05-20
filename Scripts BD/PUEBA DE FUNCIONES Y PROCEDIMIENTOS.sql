
select M05_obtenerdatosdeporte(1);
select M05_obtenermetdeporte('CAMINAR');
select M05_obtenerdeportes('CAMINAR');
select M05_obtenerdeportesusuario(1);
select M05_insertardeporte(1,6);
select M05_eliminardeporte(1,6); 

select public.insertarActividad( horainicio :: TIME, horafinal :: TIME, fecha :: DATE, km :: NUMERIC, caloria :: NUMERIC, lugarinicio :: VARCHAR(200),lugarfinal :: VARCHAR(200) )