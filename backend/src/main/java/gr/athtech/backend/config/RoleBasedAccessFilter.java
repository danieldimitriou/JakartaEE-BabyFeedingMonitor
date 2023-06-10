//package gr.athtech.backend.config;
//
//import gr.athtech.backend.resource.FeedingSessionResource;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;
//
//import javax.annotation.Priority;
//import javax.annotation.security.RolesAllowed;
//import javax.ws.rs.Priorities;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.container.PreMatching;
//import javax.ws.rs.container.ResourceInfo;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//import java.util.*;
//
//import static gr.athtech.backend.JWTGenerator.signingKey;
//
//@PreMatching
//@Provider
//@Priority(Priorities.AUTHORIZATION + 1)
//public class RoleBasedAccessFilter implements ContainerRequestFilter {
//
//    @Context
//    private ResourceInfo resourceInfo;
//    private static final Logger logger = (Logger) LogManager.getLogger(RoleBasedAccessFilter.class);
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) {
//        logger.error(requestContext.getUriInfo().getPath());
//        if ("user/login".equals(requestContext.getUriInfo().getPath())) {
//            return;
//        }
//        // Extract the user's roles from the JWT or session
//        Set<String> userRoles = extractUserRoles(requestContext);
//
//        // Get the required roles for the requested resource
//        Set<String> requiredRoles = getRequiredRoles(resourceInfo);
//        logger.error(requiredRoles);
//        logger.error(userRoles);
//        // Check if the user has at least one of the required roles
//        if (!hasRequiredRole(userRoles, requiredRoles)) {
//            requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//        }
//    }
//
//    private Set<String> extractUserRoles(ContainerRequestContext requestContext) {
//        logger.error(requestContext.getHeaderString("Authorization"));
//        // Extract the JWT token from the request headers
//        String authorizationHeader = requestContext.getHeaderString("Authorization");
//        String token = null;
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            token = authorizationHeader.substring(7); // Remove "Bearer " prefix
//        }
//
//        // Extract the user roles from the JWT token
//        if (token != null) {
//            try {
//                Claims claims = Jwts.parserBuilder()
//                        .setSigningKey(signingKey)
//                        .build()
//                        .parseClaimsJws(token)
//                        .getBody();
//                String role = claims.get("role", String.class); // Extract the role claim
//                if (role != null) {
//                    Set<String> roles = new HashSet<>();
//                    roles.add(role);
//                    return roles;
//                }
//            } catch (JwtException e) {
//                // Invalid token or token verification failed
//                // Handle the exception appropriately (e.g., log, return empty roles)
//                logger.error("Invalid JWT token: " + e.getMessage());
//            }
//        }
//
//        return Collections.emptySet();
//    }
//
//
//
//    private Set<String> getRequiredRoles(ResourceInfo resourceInfo) {
//        Set<String> requiredRoles = new HashSet<>();
//
//        // Check if the resource method has the @RolesAllowed annotation
//        if (resourceInfo.getResourceMethod().isAnnotationPresent(RolesAllowed.class)) {
//            RolesAllowed rolesAllowed = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
//            requiredRoles.addAll(Arrays.asList(rolesAllowed.value()));
//        }
//
//        // Check if the resource class has the @RolesAllowed annotation
//        if (resourceInfo.getResourceClass().isAnnotationPresent(RolesAllowed.class)) {
//            RolesAllowed rolesAllowed = resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);
//            requiredRoles.addAll(Arrays.asList(rolesAllowed.value()));
//        }
//
//        return requiredRoles;
//    }
//
//    private boolean hasRequiredRole(Set<String> userRoles, Set<String> requiredRoles) {
//        for (String role : requiredRoles) {
//            if (role.equals("admin") && userRoles.contains("admin")) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}