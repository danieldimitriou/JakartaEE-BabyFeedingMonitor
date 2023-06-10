package gr.athtech.backend;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application {
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
////        classes.add(RoleBasedAccessFilter.class); // Register the security filter
//        // Add your resource classes
//        classes.add(UserResource.class);
//        return classes;
//    }
}