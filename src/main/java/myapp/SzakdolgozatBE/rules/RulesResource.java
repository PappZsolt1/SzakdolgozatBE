package myapp.SzakdolgozatBE.rules;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rules")
@ApplicationScoped
public class RulesResource {
    
    @EJB
    RulesService service;
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRules(@PathParam("id") long id) {
        try {
            Rules tmp = service.getRules(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    public Response modifyRules(@PathParam("id") long id, String content) {
        try {
            Rules tmp = service.modifyRules(id, content);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
