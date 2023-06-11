//package gr.athtech.backend.config;//package gr.athtech.backend.config;
////
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
//public class SecurityFilter implements ContainerRequestFilter {
//
//    @Context
//    private ResourceInfo resourceInfo;
//    private static final Logger logger = (Logger) LogManager.getLogger(SecurityFilter.class);
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
//            if (role.equals("ADMIN") && userRoles.contains("ADMIN")) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}






//--------------------------------------------------------------------------------------------------------
//import java.lang.reflect.Method;
//import gr.athtech.backend.JWTGenerator;
//import gr.athtech.backend.model.Role;
//import gr.athtech.backend.service.AuthenticationService;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import lombok.NoArgsConstructor;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;
//
//import javax.annotation.security.PermitAll;
//import javax.inject.Inject;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//import java.io.IOException;
//import javax.ws.rs.container.ResourceInfo;
//@Provider
//@NoArgsConstructor
//public class SecurityFilter implements ContainerRequestFilter {
//    @Inject
//    private AuthenticationService authenticationService;
//    private static final Logger logger = (Logger) LogManager.getLogger(ContainerRequestFilter.class);
//    @Context
//    private ResourceInfo resourceInfo;
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        Method method = resourceInfo.getResourceMethod();
//        if (method.isAnnotationPresent(PermitAll.class)) {
//            return;
//        }
//        String path = requestContext.getUriInfo().getPath();
////        if(method.)
//        // Check if the URL is for login
//        if ("user/login".equals(path)) {
//            logger.error("LOGIN");
//            return;
//        }
//
//        // Get the Authorization header from the request
//        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
//        logger.error(authorizationHeader);
//
//        // Check if the Authorization header is present and contains a JWT
//        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
//            // Extract the JWT from the Authorization header
//            String jwt = authorizationHeader.substring("Basic ".length()).trim();
//
//            try {
//                // Parse the JWT and extract the claims
//                Claims claims = Jwts.parser()
//                        .setSigningKey(JWTGenerator.signingKey)
//                        .parseClaimsJws(jwt)
//                        .getBody();
//
//                // Extract the "role" claim from the JWT
//                String role = claims.get("role", String.class);
//
//                // Check authorization based on role and HTTP method
//                if (Role.ADMIN.toString().equals(role)) {
//                    // User is authorized as Admin
//                    return;
//                } else if (Role.PHYSICIAN.toString().equals(role) && "GET".equalsIgnoreCase(requestContext.getMethod())) {
//                    // User is authorized as Physician and the method is GET
//                    return;
//                }
//            } catch (Exception e) {
//                // Invalid JWT or claims, deny access
//                logger.error("Invalid JWT: " + e.getMessage());
//            }
//        }
//
//        // Deny access for unauthorized requests
//        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
//    }
//}