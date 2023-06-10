package gr.athtech.backend.resource;
import gr.athtech.backend.dto.FeedingSessionListDTO;
import gr.athtech.backend.responses.FeedingSessionListResponse;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import gr.athtech.backend.config.RoleBasedAccessFilter;
//import gr.athtech.backend.config.UserStore;
import gr.athtech.backend.model.FeedingSession;
import gr.athtech.backend.repository.FeedingSessionRepository;
import gr.athtech.backend.responses.ErrorResponse;
import gr.athtech.backend.responses.SuccessResponse;
import gr.athtech.backend.service.FeedingSessionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Path("/feedingSession")
public class FeedingSessionResource{
    private static final Logger logger = (Logger) LogManager.getLogger(FeedingSessionResource.class);
    @Inject
    private FeedingSessionRepository feedingSessionRepository;
    @Inject
    private FeedingSessionService feedingSessionService;

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFeedingSessions() {
        logger.error("GET ALL FEEDING SESSIONS REQUUEST");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Optional<FeedingSessionListDTO> feedingSessions = feedingSessionService.getAll();
            if (feedingSessions.isPresent()) {
                List<FeedingSession> sessions = feedingSessions.get().getFeedingSessions();

                String responseJson = objectMapper.writeValueAsString(new FeedingSessionListResponse(sessions,feedingSessions.get().getAverageTime(),
                        feedingSessions.get().getAverageAmountConsumed()));
                return Response.status(Response.Status.OK)
                        .entity(responseJson)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No feeding sessions found").build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }

    @GET
    @Path("/filterByDates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDate(@QueryParam("startDate") Date startDateStr, @QueryParam("endDate") Date endDateStr) {
        logger.error(startDateStr);
        logger.error(endDateStr);
        logger.error("GET ALL FEEDING SESSIONS REQUUEST BY DATE");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Optional<FeedingSessionListDTO> feedingSessions = feedingSessionService.getByDates(startDateStr, endDateStr);
            if (feedingSessions.isPresent()) {
                List<FeedingSession> sessions = feedingSessions.get().getFeedingSessions();

                String responseJson = objectMapper.writeValueAsString(new FeedingSessionListResponse(sessions, feedingSessions.get().getAverageTime(),
                        feedingSessions.get().getAverageAmountConsumed()));
                return Response.status(Response.Status.OK)
                        .entity(responseJson)
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No feeding sessions found").build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred").build();
        }
    }
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeedingSession(@PathParam("id") int id) {
        Optional<FeedingSession> feedingSession = this.feedingSessionService.getFeedingSessionById(id);
        if (feedingSession.isPresent()) {
            FeedingSession session = feedingSession.get();
            return Response.ok(session).entity(session).build();
        } else {
            throw new NotFoundException("Feeding session not found");
        }

    }
    @Path("/delete/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFeedingSession(@PathParam("id") int id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            boolean deleted = this.feedingSessionService.deleteFeedingSession(id);
            if(deleted){
                String responseJson = objectMapper.writeValueAsString(new SuccessResponse("Resource Deleted successfully"));

                return Response.status(Response.Status.CREATED)
                        .entity(responseJson)
                        .build();
            }else{
                String responseJson = objectMapper.writeValueAsString(new SuccessResponse("Resource does not exist."));

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(responseJson)
                        .build();
            }
        }catch (Exception e){
            String responseJson = objectMapper.writeValueAsString(new SuccessResponse("Error occured when deleting resource."));
            throw new NotFoundException("Feeding session not found");

        }
    }
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed("admin")
    public Response createFeedingSession(FeedingSession feedingSessionJSON) throws JsonProcessingException {
        logger.error("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Your code to create the feeding session
            boolean created = this.feedingSessionService.createFeedingSession(feedingSessionJSON);

            if (created) {
                // Create a JSON object for the success response
                String responseJson = objectMapper.writeValueAsString(new SuccessResponse("Resource Created successfully"));

                return Response.status(Response.Status.CREATED)
                        .entity(responseJson)
                        .build();
            } else {
                // Create a JSON object for the error response
                String responseJson = objectMapper.writeValueAsString(new ErrorResponse("Failed to create the resource"));

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(responseJson)
                        .build();
            }
        }
        catch (Exception e) {
            e.printStackTrace();

            // Create a JSON object for the error response
            String responseJson = objectMapper.writeValueAsString(new ErrorResponse("Invalid JSON payload"));

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(responseJson)
                    .build();
        }
    }

    @POST
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(FeedingSession feedingSessionJSON) {
        try {
            logger.error("---------------------------------------------------");
            Optional<FeedingSession> feedingSession = this.feedingSessionService.updateFeedingSession(feedingSessionJSON);
            if (feedingSession.isPresent()) {
                return Response.status(Response.Status.OK)
                        .entity("Resource updated successfully")
                        .entity(feedingSession.get())
                        .build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Feeding session not found")
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid JSON payload")
                    .build();
        }
    }
//    @Override
//    public ResourceConfig configure() {
//        return new ResourceConfig(FeedingSessionResource.class)
//                .register(RoleBasedAccessFilter.class)
//                .register(RolesAllowedDynamicFeature.class)
//                .register(new AbstractBinder(){
//                    @Override
//                    protected void configure() {
//                        bind(new UserStore()).to(UserStore.class);
//                    }
//                });
//    }


}
