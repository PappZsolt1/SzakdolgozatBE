package myapp.SzakdolgozatBE.actor;

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

@Path("/actor")
@ApplicationScoped
public class ActorResource {
    
    @EJB
    ActorService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addActor(Actor actor) {
        try {
            Actor tmp = service.addActor(actor);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        }        
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyActor(Actor actor) {
        try {
            Actor tmp = service.modifyActor(actor);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
