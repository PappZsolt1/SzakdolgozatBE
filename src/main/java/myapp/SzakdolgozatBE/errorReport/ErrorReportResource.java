package myapp.SzakdolgozatBE.errorReport;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/errorreport")
@ApplicationScoped
public class ErrorReportResource {

    @EJB
    ErrorReportService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addErrorReport(String content) {
        ErrorReport tmp = service.add(content);
        return Response.ok().entity(tmp).build();
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ErrorReport> getAllErrorReports() {
        return service.getAllErrorReports();
    }

    @Path("/resolved")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ErrorReport> getResolvedErrorReports() {
        return service.getResolvedErrorReports();
    }

    @Path("/notresolved")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ErrorReport> getNotResolvedErrorReports() {
        return service.getNotResolvedErrorReports();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeResolved(@PathParam("id") long id) {
        service.makeResolved(id);
        return Response.ok().build();
    }
}
