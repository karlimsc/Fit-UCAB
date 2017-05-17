select public.obtenerDeporte('YOGA' :: VARCHAR(200));
select public.insertarDeporte(1 :: INTEGER , 1 :: INTEGER) ;

select public.insertarActividad( horainicio :: TIME, horafinal :: TIME, fecha :: DATE, km :: NUMERIC, caloria :: NUMERIC, lugarinicio :: VARCHAR(200),lugarfinal :: VARCHAR(200) )