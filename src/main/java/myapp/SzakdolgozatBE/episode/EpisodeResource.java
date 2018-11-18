package myapp.SzakdolgozatBE.episode;

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
    RatingService ratingService; //todo
    
    @EJB
    SeriesService seriesService; //todo

    @POST
    @Path("/{seasonId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEpisode(@PathParam("seasonId") long seasonId, Episode episode) {
        try {
            Episode tmp = service.addEpisode(seasonId, episode);
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
    @Path("/season/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisodeSeasonId(@PathParam("id") long id) {
        try {
            long tmp = service.getEpisodeSeasonId(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //not used
    /*@GET
    @Path("/season/{seasonId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeasonEpisodes(@PathParam("seasonId") long seasonId) {
        try {
            List<Episode> tmp = service.getSeasonEpisodes(seasonId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }*/

    @DELETE
    @Path("/{seasonId}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEpisode(@PathParam("seasonId") long seasonId, @PathParam("id") long id) {
        try {
            service.deleteEpisode(seasonId, id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{seasonId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyEpisode(@PathParam("seasonId") long seasonId, Episode episode) {
        try {
            Episode tmp = service.modifyEpisode(seasonId, episode);
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
    
    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response canBeDeleted(@PathParam("id") long id) {
        try {
            boolean tmp = service.canBeDeleted(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
