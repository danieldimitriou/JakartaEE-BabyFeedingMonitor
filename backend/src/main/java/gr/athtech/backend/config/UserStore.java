//package gr.athtech.backend.config;
//
//import gr.athtech.backend.model.User;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class UserStore {
//
//    public final Map<String, User> users = new ConcurrentHashMap<>();
//
//    public UserStore() {
//        User admin = new User("daniel","dimitriou","admin","email@email.com","pas");
//        User physician = new User("daniel","dimitriou","physician","email@emai1l.com", "pass");
//        users.put("admin", admin);
//        users.put("physician",physician);
//    }
//
//    public User getUser(String username) {
//        return users.get(username);
//    }
//}