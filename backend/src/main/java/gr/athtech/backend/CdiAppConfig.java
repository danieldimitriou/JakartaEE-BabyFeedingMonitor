//package gr.athtech.backend;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Disposes;
//import javax.enterprise.inject.Produces;
//import javax.inject.Inject;
//import javax.transaction.TransactionScoped;
//
//public class CdiAppConfig {
//
//    @Inject
//    private EntityManagerFactory emf;
//
//    @Produces
//    @ApplicationScoped
//    public EntityManagerFactory createEntityManagerFactory() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("generalPU");
//        return emf;
//    }
//
//    public void close(@Disposes EntityManagerFactory emf) {
//        emf.close();
//    }
//
//    @Produces
//    @TransactionScoped
//    // is a bit better than @RequestScoped because it won't allow perform injection outside transaction context
//    public EntityManager createEntityManager() {
//        return emf.createEntityManager();
//    }
//
//    public void close(@Disposes EntityManager em) {
//        if (em.isOpen()) {
//            em.close();
//        }
//    }
//}