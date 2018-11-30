package myapp.SzakdolgozatBE.rating;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rating")
@ApplicationScoped
public class RatingResource {

    @EJB
    RatingService service;

    //not used
    /*@POST
    @Path("/movie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovieRating(byte rating, long movieId) {
        Rating tmp = service.addMovieRating(rating, movieId);
        return Response.ok().entity(tmp).build();
    }

    @POST
    @Path("/episode")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEpisodeRating(byte rating, long episodeId) {
        Rating tmp = service.addEpisodeRating(rating, episodeId);
        return Response.ok().entity(tmp).build();
    }*/

    /*@GET
    @Path("/movie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieRating(long movieId) {
        try {
            Rating tmp = service.getMovieRating(movieId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/episode")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisodeRating(long episodeId) {
        try {
            Rating tmp = service.getEpisodeRating(episodeId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }*/
}
