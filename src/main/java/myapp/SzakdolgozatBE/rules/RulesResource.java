package myapp.SzakdolgozatBE.rules;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rules")
@ApplicationScoped
public class RulesResource {
    
    @EJB
    RulesService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRules() {
        Rules tmp = service.getRules();
        return Response.ok().entity(tmp).build();
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyRules(String content) {
        try {
            Rules tmp = service.modifyRules(content);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
