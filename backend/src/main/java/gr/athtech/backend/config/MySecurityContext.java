//package gr.athtech.backend.config;
//
//import gr.athtech.backend.model.User;
//
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.SecurityContext;
//import java.security.Principal;
//
//class MySecurityContext implements SecurityContext {
//
//    private final User user;
//
//    public MySecurityContext(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Principal getUserPrincipal() {
//        return new Principal() {
//            @Override
//            public String getName() {
//                return user.getFirstName();
//            }
//        };
//    }
//
//    @Override
//    public boolean isUserInRole(String role) {
//        return role.equals(user.getRole());
//    }
//
//    @Override
//    public boolean isSecure() { return true; }
//
//    @Override
//    public String getAuthenticationScheme() {
//        return "Basic";
//    }
//
//}
