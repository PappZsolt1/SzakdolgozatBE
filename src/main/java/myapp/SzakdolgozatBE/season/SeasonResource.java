package myapp.SzakdolgozatBE.season;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.episode.Episode;
import myapp.SzakdolgozatBE.series.Series;

@Path("season")
@ApplicationScoped
public class SeasonResource {
    
    @EJB SeasonSerivce service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSeason(@FormParam("number") int number,
            @FormParam("series") Series series) {
        Season tmp = service.addSeason(number, series);
        return Response.ok().entity(tmp).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeason(@PathParam("id") long id) {
        try {
            Season tmp = service.getSeason(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Season> getSeriesSeasons(Series series) {
        return service.getSeriesSeasons(series);
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSeason(@PathParam("id") long id) {
        try {
            service.deleteSeason(id);
            return Response.ok().build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifySeason(@PathParam("id") long id,
            @FormParam("number") int number,
            @FormParam("series") Series series) {
        try {
            Season tmp = service.modifySeason(id, number, series);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
