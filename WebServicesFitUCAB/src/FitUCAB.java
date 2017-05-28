import WebServicesClasses.*;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


/**
 * Se define la raiz de todo el proyecto
 * Clase que contiene todos los webservices del proyecto
 */
@ApplicationPath("/")
public class FitUCAB extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h= new HashSet<Class<?>>();
        h.add( M01_ServicesUser.class );
        h.add( M02_ServicesUser.class );
        h.add( M02_ServicesHome.class );
        h.add( M03_ServicesContacts.class );
        h.add( M03_ServicesFriends.class );
        h.add( M03_ServicesNearMe.class );
        h.add( M04_ServicesNotificationSettings.class );
        h.add( M05_ServicesActivity.class );
        h.add( M05_ServicesSport.class );
        return h;
    }

}
