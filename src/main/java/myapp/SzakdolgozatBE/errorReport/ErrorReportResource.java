package myapp.SzakdolgozatBE.errorReport;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Path("/errorreport")
@ApplicationScoped
public class ErrorReportResource {
    
    @EJB ErrorReportService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addErrorReport(@FormParam("content") String content, MyUser myUser) {
        ErrorReport tmp = service.add(myUser, content);
        return Response.ok().entity(tmp).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ErrorReport> getAllErrorReports() {
        return service.getAll();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeResolved(long id) {
        service.makeResolved(id);
        return Response.ok().build();
    }
}
