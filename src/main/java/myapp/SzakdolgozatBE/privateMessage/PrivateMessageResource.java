package myapp.SzakdolgozatBE.privateMessage;

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

@Path("/PrivateMessage")
@ApplicationScoped
public class PrivateMessageResource {
    
    @EJB
    PrivateMessageService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrivateMessage(String content) {
        PrivateMessage tmp = service.addPrivateMessage(content);
        return Response.ok().entity(tmp).build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeRead(@PathParam("id") long id) {
        try {
            service.makeRead(id);
            return Response.ok().build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/sent")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrivateMessage> getSentPrivateMessages() {
        return service.getSentPrivateMessages();
    }
    
    @GET
    @Path("/received")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrivateMessage> getReceivedPrivateMessages() {
        return service.getReceivedPrivateMessages();
    }
}
