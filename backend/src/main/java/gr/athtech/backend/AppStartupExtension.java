//package gr.athtech.backend;
//
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.event.Observes;
//import javax.enterprise.inject.spi.*;
//
//public class AppStartupExtension implements Extension {
//
//    void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
//        // This method is called before the CDI container starts the bean discovery process.
//        // You can perform any initialization tasks here.
//    }
//
//    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> pat) {
//        // This method is called for each annotated type discovered during the bean discovery process.
//        // You can inspect and modify the annotated types here.
//    }
//
//    <T, X> void processInjectionTarget(@Observes ProcessInjectionTarget<T> pit) {
//        // This method is called for each injection target (class or producer method) discovered during the bean discovery process.
//        // You can inspect and modify the injection targets here.
//    }
//
//    <T> void processProducer(@Observes ProcessProducer<T, EntityManagerFactory> pp) {
//        // This method is called for each producer method or field for EntityManagerFactory.
//        // You can inspect and modify the producer methods or fields here.
//    }
//
//    void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
//        // This method is called after the CDI container finishes the bean discovery process.
//        // You can perform any finalization tasks here.
//
//        // Create EntityManagerFactory and register it as a bean
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("generalPU");
//        abd.addBean()
//                .types(EntityManagerFactory.class)
//                .scope(ApplicationScoped.class)
//                .produceWith((i) -> emf)
//                .disposeWith((emfInstance, i) -> emfInstance.close());
//    }
//}
