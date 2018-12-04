package myapp.SzakdolgozatBE.genre;

import java.util.List;
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

@Path("/genre")
@ApplicationScoped
@RolesAllowed("Admin")
public class GenreResource {

    @Inject
    GenreService service;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGenre(@PathParam("id") long id) {
        try {
            Genre tmp = service.getGenre(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGenres() {
        try {
            List<Genre> tmp = service.getAllGenres();
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGenre(String name) {
        try {
            Genre tmp = service.addGenre(name);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGenre(@PathParam("id") long id) {
        try {
            service.deleteGenre(id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyGenre(@PathParam("id") long id, String name) {
        try {
            Genre tmp = service.modifyGenre(id, name);
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
