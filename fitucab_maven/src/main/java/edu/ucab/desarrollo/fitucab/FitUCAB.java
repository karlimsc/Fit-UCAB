package edu.ucab.desarrollo.fitucab;

import edu.ucab.desarrollo.fitucab.webService.M06_ServicesTraining;
import edu.ucab.desarrollo.fitucab.webService.M07_ServicesPlanification;
import edu.ucab.desarrollo.fitucab.webService.M09_ServicesGamification;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/")
public class FitUCAB extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h= new HashSet<Class<?>>();
        h.add(M06_ServicesTraining.class);
        h.add(M09_ServicesGamification.class);
        h.add(M07_ServicesPlanification.class);
        return h;
    }
}
