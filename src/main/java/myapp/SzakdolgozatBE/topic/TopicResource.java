package myapp.SzakdolgozatBE.topic;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Path("topic")
@ApplicationScoped
public class TopicResource {
    
    @EJB TopicService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTopic(@FormParam("title") String title,
            @FormParam("description") String description,
            @FormParam("myUser") MyUser myUser) {
        Topic tmp = service.addTopic(title, description, myUser);
        return Response.ok().entity(tmp).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Topic> getAllTopics() {
        return service.getAllTopics();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopic(@PathParam("id") long id) {
        try {
            Topic tmp = service.getTopic(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTopic(@PathParam("id") long id) {
        try {
            service.deleteTopic(id);
            return Response.ok().build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
