//PRUEBA DE LA EXCEPTION- al meterle un valor null


        @Test(expected = basedatosException.class)  
            public void testinsertSetting()
            {
                // ejecutamos la código que debiera lanzar la excepción
                M04_ServicesNotificationSettings prueba = new M04_ServicesNotificationSettings();
                prueba.insertSetting(null,null,null,null,null,null,null,null,null,null,null);

            }

           @Test(expected = basedatosException.class)  
            public void testupdateSetting()
            {
                // ejecutamos la código que debiera lanzar la excepción
                M04_ServicesNotificationSettings prueba = new M04_ServicesNotificationSettings();
                prueba.insertSetting(null,null,null,null,null,null,null,null,null,null,null);

            }

            @Test(expected = basedatosException.class)  
            public void testgetSetting()
            {
                // ejecutamos la código que debiera lanzar la excepción
                M04_ServicesNotificationSettings prueba = new M04_ServicesNotificationSettings();
                prueba.insertSetting(null);

            }