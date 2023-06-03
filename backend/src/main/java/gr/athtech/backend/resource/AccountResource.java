package gr.athtech.backend.resource;

import gr.athtech.backend.model.Account;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/account")
public class AccountResource {
    @Inject
    private gr.athtech.backend.repository.AccountRepository accountRepository;
    private static final Logger logger = LogManager.getLogger(AccountResource.class);
    @Path("hello")
    @GET
    public String sayHello() {
        return "Hello account " ;
    }

    @POST
    @Path("createAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(String data) {
        try {
            logger.error("------------------------------------------------------------------------------------------------------");
            Jsonb jsonb = JsonbBuilder.create();
            Account account = jsonb.fromJson(data, Account.class);
            logger.error("------------------------------------------------------------------------------------------------------");
            logger.error(account.getFirstName());
            logger.error(account.toString());
            logger.error("------------------------------------------------------------------------------------------------------");
            this.accountRepository.createAccount(account);
            return Response.status(Response.Status.CREATED)
                    .entity(account)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid JSON payload")
                    .build();
        }
    }

    @GET
    @Path("createAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createAccount() {
        return "create account";
    }
}
