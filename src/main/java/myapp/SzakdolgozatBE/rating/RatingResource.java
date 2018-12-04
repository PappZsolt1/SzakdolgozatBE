package myapp.SzakdolgozatBE.rating;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.MyValidationException;

@Path("/rating")
@ApplicationScoped
public class RatingResource {

    @Inject
    RatingService service;

    @GET
    @Path("/movie/{movieId}/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response canRateThisMovie(@PathParam("movieId") long movieId, @PathParam("username") String username) {
        try {
            boolean tmp = service.canRateThisMovie(movieId, username);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/episode/{episodeId}/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response canRateThisEpisode(@PathParam("episodeId") long episodeId, @PathParam("username") String username) {
        try {
            boolean tmp = service.canRateThisEpisode(episodeId, username);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
