package myapp.SzakdolgozatBE.episode;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
import myapp.SzakdolgozatBE.actor.Actor;

@Path("/episode")
@ApplicationScoped
@PermitAll
public class EpisodeResource {

    @Inject
    EpisodeService service;

    @POST
    @Path("/{seasonId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
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
    @Path("/public/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisode(@PathParam("id") long id) {
        try {
            Episode tmp = service.getEpisode(id);
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
    @RolesAllowed("Admin")
    public Response checkIfExists(@PathParam("id") long id) {
        try {
            boolean tmp = service.checkIfExists(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/season/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response getEpisodeSeasonId(@PathParam("id") long id) {
        try {
            long tmp = service.getEpisodeSeasonId(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/public/actors/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisodeActors(@PathParam("id") long id) {
        try {
            List<Actor> tmp = service.getEpisodeActors(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("/add/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response addActorToEpisode(@PathParam("id") long id, long actorId) {
        try {
            Actor tmp = service.addActorToEpisode(id, actorId);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("/remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response removeActorFromEpisode(@PathParam("id") long id, long actorId) {
        try {
            Actor tmp = service.removeActorFromEpisode(id, actorId);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{seasonId}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response deleteEpisode(@PathParam("seasonId") long seasonId, @PathParam("id") long id) {
        try {
            service.deleteEpisode(seasonId, id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{seasonId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
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
    @Path("/rating/{id}/{rating}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"RegisteredUser", "Moderator"})
    public Response changeRating(@PathParam("id") long id, @PathParam("rating") byte rating, String username) {
        try {
            service.changeRating(id, rating, username);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
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
