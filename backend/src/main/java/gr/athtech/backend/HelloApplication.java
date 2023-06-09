package gr.athtech.backend;

//import gr.athtech.backend.config.RoleBasedAccessFilter;
import gr.athtech.backend.resource.UserResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class HelloApplication extends Application {
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
//        classes.add(RoleBasedAccessFilter.class); // Register the security filter
//        // Add your resource classes
//        classes.add(UserResource.class);
//        return classes;
//    }
}