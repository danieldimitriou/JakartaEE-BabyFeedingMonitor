package gr.athtech.backend.resource;

import gr.athtech.backend.model.Account;
import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.repository.AccountRepository;
import gr.athtech.backend.repository.FeedingSessionRepository;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/session")
public class FeedingSessionResource {
    @Inject
    private FeedingSessionRepository feedingSessionRepository;
private static final Logger logger = LogManager.getLogger(AccountResource.class);

    @Path("hello")
    @GET
    public String sayHello() {
        System.out.println("------------");
        return "Hello World ";
    }
    @POST
    @Path("createAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(String data) {
        try {
            Jsonb jsonb = JsonbBuilder.create();
            FeedingSession feedingSession = jsonb.fromJson(data, FeedingSession.class);
            logger.error("------------------------------------------------------------------------------------------------------");
            logger.error(feedingSession.toString());
            logger.error("------------------------------------------------------------------------------------------------------");
            this.feedingSessionRepository.createFeedingSession(feedingSession);
            return Response.status(Response.Status.CREATED)
                    .entity(feedingSession)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid JSON payload")
                    .build();
        }
    }
}
