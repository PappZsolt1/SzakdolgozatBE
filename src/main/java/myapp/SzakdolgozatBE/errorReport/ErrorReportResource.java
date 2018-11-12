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
import myapp.SzakdolgozatBE.MyValidationException;

@Path("/errorreport")
@ApplicationScoped
public class ErrorReportResource {

    @EJB
    ErrorReportService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addErrorReport(String content) {
        try {
            ErrorReport tmp = service.add(content);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllErrorReports() {
        try {
            List<ErrorReport> tmp = service.getAllErrorReports();
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/resolved")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResolvedErrorReports() {
        try {
            List<ErrorReport> tmp = service.getResolvedErrorReports();
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/notresolved")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotResolvedErrorReports() {
        try {
            List<ErrorReport> tmp = service.getNotResolvedErrorReports();
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeResolved(@PathParam("id") long id) {
        try {
            service.makeResolved(id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
