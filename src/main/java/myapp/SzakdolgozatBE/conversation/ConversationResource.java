package myapp.SzakdolgozatBE.conversation;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/conversation")
@ApplicationScoped
public class ConversationResource {
    
    @EJB
    ConversationService service;
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConversation(@PathParam("id") long id) {
        try {
            Conversation tmp = service.getConversation(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addConversation(Conversation conversation) {
        Conversation tmp = service.addConversation(conversation);
        return Response.ok().entity(tmp).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conversation> getUserConversations() {
        return service.getUserConversations();
    }
}
