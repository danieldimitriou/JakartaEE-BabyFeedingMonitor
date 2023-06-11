package gr.athtech.backend.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.athtech.backend.model.LoginData;
import gr.athtech.backend.model.LoginResponseData;
import gr.athtech.backend.responses.ErrorResponse;
import gr.athtech.backend.responses.LoginSuccessResponse;
import gr.athtech.backend.responses.SuccessResponse;
import gr.athtech.backend.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.eclipse.persistence.sessions.Login;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Path("/user")
public class UserResource {
    @Inject
    private UserService userService;
    private static final Logger logger = (Logger) LogManager.getLogger(UserResource.class);


//    @POST
//    @Path("createAccount")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createAccount(String data) {
//        try {
//            Jsonb jsonb = JsonbBuilder.create();
//            User user = jsonb.fromJson(data, User.class);
//            this.userRepository.createUser(user);
//            return Response.status(Response.Status.CREATED)
//                    .entity(user)
//                    .build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity("Invalid JSON payload")
//                    .build();
//        }
//    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response login(LoginData loginData) throws SQLException, JsonProcessingException, LoginException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LoginResponseData loginResponseData= this.userService.login(loginData);
            String responseJson = objectMapper.writeValueAsString(loginResponseData);
            return Response.status(Response.Status.OK)
                    .entity(responseJson)
                    .build();
        }catch (Exception e ){
            logger.error(e);
            String responseJson = objectMapper.writeValueAsString(new ErrorResponse("There was an error."));
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(responseJson)
                    .build();
        }
    }
}
