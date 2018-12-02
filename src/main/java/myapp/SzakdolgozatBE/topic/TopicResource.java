package myapp.SzakdolgozatBE.topic;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.Wrapper;

@Path("/topic")
@ApplicationScoped
public class TopicResource {

    @EJB
    TopicService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTopic(Topic topic) {
        try {
            Topic tmp = service.addTopic(topic);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/public/{page}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTopics(@PathParam("page") int page, @PathParam("size") int size) {
        try {
            Wrapper tmp = service.getAllTopics(page, size);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/public/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopic(@PathParam("id") long id) {
        try {
            Topic tmp = service.getTopic(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTopic(@PathParam("id") long id) {
        try {
            service.deleteTopic(id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
