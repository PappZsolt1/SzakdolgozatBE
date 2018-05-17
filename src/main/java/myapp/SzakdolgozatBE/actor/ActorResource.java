package myapp.SzakdolgozatBE.actor;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
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

@Path("/actor")
@ApplicationScoped
public class ActorResource {
    
    @EJB ActorService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addActor(String name, Date birthDate, String birthPlace, String bio, long genderId) {
        Actor tmp = service.addActor(name, birthDate, birthPlace, bio, genderId);
        System.out.println("name");
        System.out.println("birthDate");
        System.out.println("birthPlace");
        System.out.println("bio");
        System.out.println("genderId");
        return Response.ok().entity(tmp).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActor(@PathParam("id") long id) {
        try {
            Actor tmp = service.getActor(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getxxxActors() {
        //todo
    }*/
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteActor(@PathParam("id") long id) {
        try {
            service.deleteActor(id);
            return Response.ok().build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyActor(@PathParam("id") long id,
            @FormParam("name") String name,
            @FormParam("birthDate") Date birthDate,
            @FormParam("birthPlace") String birthPlace,
            @FormParam("bio") String bio,
            @FormParam("genderId") long genderId) {
        try {
            Actor tmp = service.modifyActor(id, name, birthDate, birthPlace, bio, genderId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
