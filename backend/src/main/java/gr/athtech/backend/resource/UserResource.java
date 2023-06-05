package gr.athtech.backend.resource;

import gr.athtech.backend.model.User;
import gr.athtech.backend.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.sql.SQLException;
import java.util.Optional;

@Path("/user")
public class UserResource {
    @Inject
    private UserRepository userRepository;
    @Path("hello")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public String sayHello() throws SQLException {
//        Account a = accountRepository.getAccountById(1L);
        return "Hello account ";
    }

    @POST
    @Path("createAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(String data) {
        try {
            Jsonb jsonb = JsonbBuilder.create();
            User user = jsonb.fromJson(data, User.class);
            this.userRepository.createUser(user);
            return Response.status(Response.Status.CREATED)
                    .entity(user)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid JSON payload")
                    .build();
        }
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") Long id) throws SQLException {
        Optional<User> optionalUser = userRepository.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return Response.ok(user).build(); // Return 200 OK with the user entity as the response body
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Return 404 Not Found
        }
    }
}
