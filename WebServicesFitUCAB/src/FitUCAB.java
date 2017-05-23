/**
 * Created by root on 14/05/17.
 */

import WebServicesClasses.M01_ServicesUser;
import WebServicesClasses.M11_ServicesDiet;
import WebServicesClasses.M11_ServicesFood;
import WebServicesClasses.M11_ServicesMoment;

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
        h.add(M11_ServicesFood.class);
        h.add(M11_ServicesDiet.class);
        h.add(M11_ServicesMoment.class);
        return h;
    }
}
