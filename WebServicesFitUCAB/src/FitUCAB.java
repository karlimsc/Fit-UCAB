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
        h.add(M04_ServicesNotificationSettings.class);
        h.add( M02_ServiceUser.class );
        h.add( M02_ServiceHome.class );
        h.add(M05_ServicesActivity.class);
        h.add(M05_ServicesSport.class);
        return h;
    }

}
