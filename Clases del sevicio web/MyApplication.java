<<<<<<< HEAD:Clases del sevicio web/MyApplication.java

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by estefania on 14/05/2017.
 */
//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class MyApplication extends Application{
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override

    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();

        h.add(HelloWorld.class);
        h.add(SportController.class);
        h.add(ActivityController.class);
        return h;
    }
}
=======
/**
 * Created by root on 14/05/17.
 */

import WebServicesClasses.*;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/* Se define la raiz de todo el proyecto*/
@ApplicationPath("/")

/* Clase que contiene todos los ws del proyecto */
public class FitUCAB extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h= new HashSet<Class<?>>();
        h.add(M01_ServicesUser.class);
        h.add(M05_ServicesActivity.class);
        h.add(M05_ServicesSport.class);
        return h;
    }
}
>>>>>>> M05_RespaldoWs:WebServicesFitUCAB/src/FitUCAB.java
