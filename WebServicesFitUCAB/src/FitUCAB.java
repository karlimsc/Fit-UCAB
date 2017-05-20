/**
 * Created by root on 14/05/17.
 */

import WebServicesClasses.M01_ServicesUser;
import WebServicesClasses.M06_ServicesTraining;

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
        h.add(M06_ServicesTraining.class);
        return h;
    }
}
