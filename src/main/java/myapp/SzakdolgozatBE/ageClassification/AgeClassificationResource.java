package myapp.SzakdolgozatBE.ageClassification;

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

@Path("/ageclassification")
@ApplicationScoped
public class AgeClassificationResource {
    
    @EJB AgeClassificationService service;
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAgeClassification(@PathParam("id") long id) {
        try{
            AgeClassification tmp = service.getAgeClassification(id);            
            return Response.ok().entity(tmp).build();
        }
        catch(Throwable t){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AgeClassification> getAllAgeClassifications() {
        return service.getAllAgeClassifications();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAgeClassification(@FormParam("name") String name) {
        AgeClassification tmp = service.addAgeClassification(name);
        return Response.ok().entity(tmp).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAgeClassification(@PathParam("id") long id) {
        service.deleteAgeClassification(id);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyAgeClassification(@PathParam("id") long id, @PathParam("name") String name) {
        try{
            AgeClassification tmp = service.modifyAgeClassification(id, name);            
            return Response.ok().entity(tmp).build();
        }
        catch(Throwable t){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
