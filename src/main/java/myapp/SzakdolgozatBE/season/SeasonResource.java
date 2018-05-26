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

@Path("season")
@ApplicationScoped
public class SeasonResource {

    @EJB
    SeasonSerivce service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSeason(int number, long seriesId) {
        Season tmp = service.addSeason(number, seriesId);
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
    public List<Season> getSeriesSeasons(long seriesId) {
        return service.getSeriesSeasons(seriesId);
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
    public Response modifySeason(@PathParam("id") long id, int number, long seriesId) {
        try {
            Season tmp = service.modifySeason(id, number, seriesId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
