package myapp.SzakdolgozatBE.season;

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

@Path("/season")
@ApplicationScoped
public class SeasonResource {

    @EJB
    SeasonSerivce service;

    @POST
    @Path("/{seriesId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSeason(@PathParam("seriesId") long seriesId, Season season) {
        try {
            Season tmp = service.addSeason(seriesId, season);
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
    public Response getSeason(@PathParam("id") long id) {
        try {
            Season tmp = service.getSeason(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/check/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkIfExists(@PathParam("id") long id) {
        try {
            boolean tmp = service.checkIfExists(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{seriesId}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSeason(@PathParam("seriesId") long seriesId, @PathParam("id") long id) {
        try {
            service.deleteSeason(seriesId, id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{seriesId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifySeason(@PathParam("seriesId") long seriesId, Season season) {
        try {
            Season tmp = service.modifySeason(seriesId, season);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
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
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
