package myapp.SzakdolgozatBE.comment;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Path("comment")
@ApplicationScoped
public class CommentResource {
    
    @EJB CommentService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(@FormParam("content") String content, MyUser myUser) {
        Comment tmp =  service.addComment(content, myUser);
        return Response.ok().entity(tmp).build();
    }
    
    /*@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getxxxComments () {
        //todo
    }*/
}
