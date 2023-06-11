package gr.athtech.backend;
//import gr.athtech.backend.config.SecurityFilter;
import gr.athtech.backend.resource.FeedingSessionResource;
import gr.athtech.backend.resource.UserResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class HelloApplication extends Application {
        @Override
        public Set<Class<?>> getClasses() {
            Set<Class<?>> classes = new HashSet<>();
            classes.add(FeedingSessionResource.class);
            classes.add(UserResource.class);
//            classes.add(SecurityFilter.class);
//            classes.add(CorsFilter.class); // Add CorsFilter to the classes
            return classes;
        }

}