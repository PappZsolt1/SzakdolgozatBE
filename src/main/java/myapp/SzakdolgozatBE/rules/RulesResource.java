package myapp.SzakdolgozatBE.rules;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.MyValidationException;

@Path("/rules")
@ApplicationScoped
@PermitAll
public class RulesResource {
    
    @Inject
    RulesService service;
    
    @GET
    @Path("/public")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRules() {
        try {
            Rules tmp = service.getRules();
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response modifyRules(String content) {
        try {
            Rules tmp = service.modifyRules(content);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
