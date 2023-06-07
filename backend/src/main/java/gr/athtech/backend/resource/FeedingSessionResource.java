package gr.athtech.backend.resource;

import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.repository.FeedingSessionRepository;
import gr.athtech.backend.service.FeedingSessionService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;


@Path("/feedingSession")
public class FeedingSessionResource {
    private static final Logger logger = LogManager.getLogger(FeedingSessionResource.class);
    @Inject
    private FeedingSessionRepository feedingSessionRepository;
    @Inject
    private FeedingSessionService feedingSessionService;

    @Path("getAll")
    @GET
    public String sayHello() {
        System.out.println("------------");
        return "Hello World ";
    }
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFeedingSession(String data) {
        logger.error("data");
        try {
//            logger.error("---------------------------------------------------");
//            logger.error(data);
//            logger.error("---------------------------------------------------");
//            Jsonb jsonb = JsonbBuilder.create();
//            FeedingSession feedingSession = jsonb.fromJson(data, FeedingSession.class);
            Optional<FeedingSession> feedingSession = this.feedingSessionService.createFeedingSession(data);
                        logger.error("---------------------------------------------------");
            logger.error("FeedingSession:" + feedingSession);
                        logger.error("---------------------------------------------------");

            if(feedingSession.isPresent()){
                return Response.status(Response.Status.CREATED)
                        .entity(feedingSession)
                        .build();
            }else{
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error Commiting to Database")
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid JSON payload")
                    .build();
        }
    }
}
