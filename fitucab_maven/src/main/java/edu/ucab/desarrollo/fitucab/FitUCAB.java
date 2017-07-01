package edu.ucab.desarrollo.fitucab;

import edu.ucab.desarrollo.fitucab.webService.M01_ServicesUser;
import edu.ucab.desarrollo.fitucab.webService.M01_ServicesUser1;
import edu.ucab.desarrollo.fitucab.webService.M06_ServicesTraining;
import edu.ucab.desarrollo.fitucab.webService.M09_ServicesGamification;
import edu.ucab.desarrollo.fitucab.webService.M10_ServicesHidration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/")
public class FitUCAB extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h= new HashSet<Class<?>>();
        h.add(M01_ServicesUser.class);
        h.add(M01_ServicesUser1.class);
        h.add(M06_ServicesTraining.class);
        h.add(M09_ServicesGamification.class);
        h.add(M10_ServicesHidration.class);
        return h;
    }
}
