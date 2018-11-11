package myapp.SzakdolgozatBE.episode;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.rating.RatingService;
import myapp.SzakdolgozatBE.series.SeriesService;

@Path("/episode")
@ApplicationScoped
public class EpisodeResource {

    @EJB
    EpisodeService service;
    
    @EJB
    RatingService ratingService;
    
    @EJB
    SeriesService seriesService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEpisode(Episode episode) {
        try {
            Episode tmp = service.addEpisode(episode);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisode(@PathParam("id") long id) {
        try {
            Episode tmp = service.getEpisode(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/season/{seasonId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeasonEpisodes(@PathParam("seasonId") long seasonId) {
        try {
            List<Episode> tmp = service.getSeasonEpisodes(seasonId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEpisode(@PathParam("id") long id) {
        try {
            service.deleteEpisode(id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyEpisode(Episode episode) {
        try {
            Episode tmp = service.modifyEpisode(episode);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/rating/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeRating(@PathParam("id") long id, int rating) {
        try {
            service.changeRating(id, rating);
            ratingService.addMovieRating((byte) rating, id);
            long seriesId = service.getEpisode(id).getSeason().getSeries().getId();
            seriesService.changeRating(seriesId);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
