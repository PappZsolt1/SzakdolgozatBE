package myapp.SzakdolgozatBE.episode;

import java.util.Date;
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
import myapp.SzakdolgozatBE.season.Season;

@Path("episode")
@ApplicationScoped
public class EpisodeResource {
    
    @EJB EpisodeService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEpisode(String title, Date releaseDate, int length, Season season) {
        Episode tmp = service.addEpisode(title, releaseDate, length, season);
        return Response.ok().entity(tmp).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisode(@PathParam("id") long id) {
        try {
            Episode tmp = service.getEpisode(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Episode> getSeasonEpisodes(Season season) {
        return service.getSeasonEpisodes(season);
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEpisode(@PathParam("id") long id) {
        try {
            service.deleteEpisode(id);
            return Response.ok().build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyEpisode(@PathParam("id") long id, String title, Date releaseDate, int length, Season season) {
        try {
            Episode tmp = service.modifyEpisode(id, title, releaseDate, length, season);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    //todo
    public void changeRating(long id, double rating) {
        service.changeRating(id, rating);
    }
}
