package myapp.SzakdolgozatBE.errorReport;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.Wrapper;

@Path("/errorreport")
@ApplicationScoped
@PermitAll
public class ErrorReportResource {

    @Inject
    ErrorReportService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"RegisteredUser", "Moderator", "ArticleWriter"})
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

    @GET
    @Path("/all/{page}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response getAllErrorReports(@PathParam("page") int page, @PathParam("size") int size) {
        try {
            Wrapper tmp = service.getAllErrorReports(page, size);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/resolved/{page}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response getResolvedErrorReports(@PathParam("page") int page, @PathParam("size") int size) {
        try {
            Wrapper tmp = service.getResolvedErrorReports(page, size);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/notresolved/{page}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response getNotResolvedErrorReports(@PathParam("page") int page, @PathParam("size") int size) {
        try {
            Wrapper tmp = service.getNotResolvedErrorReports(page, size);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response makeResolved(@PathParam("id") long id) {
        try {
            service.makeResolved(id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
