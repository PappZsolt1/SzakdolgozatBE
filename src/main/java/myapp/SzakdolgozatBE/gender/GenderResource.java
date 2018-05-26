package myapp.SzakdolgozatBE.gender;

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

@Path("/gender")
@ApplicationScoped
public class GenderResource {

    @EJB
    GenderService service;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGender(@PathParam("id") long id) {
        try {
            Gender tmp = service.getGender(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gender> getAllGenders() {
        return service.getAllGenders();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGender(String name) {
        Gender tmp = service.addGender(name);
        return Response.ok().entity(tmp).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGender(@PathParam("id") long id) {
        service.deleteGender(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyGender(@PathParam("id") long id, String name) {
        try {
            Gender tmp = service.modifyGender(id, name);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
