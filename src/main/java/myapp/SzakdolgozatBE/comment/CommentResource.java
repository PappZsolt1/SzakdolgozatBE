package myapp.SzakdolgozatBE.comment;

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

@Path("/comment")
@ApplicationScoped
public class CommentResource {
    
    @EJB CommentService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(Comment comment) {
        Comment tmp =  service.addComment(comment);
        return Response.ok().entity(tmp).build();
    }
    
    @GET
    @Path("/movie/{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getMovieComments(@PathParam("movieId") long movieId) {
        return service.getMovieComments(movieId);
    }
    
    @GET
    @Path("/episode/{episodeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getEpisodeComments(@PathParam("episodeId") long episodeId) {
        return service.getEpisodeComments(episodeId);
    }
    
    @GET
    @Path("/actor/{actorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getActorComments(@PathParam("actorId") long actorId) {
        return service.getActorComments(actorId);
    }
    
    @GET
    @Path("/article/articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getArticleComments(@PathParam("articleId") long articleId) {
        return service.getArticleComments(articleId);
    }
    
    @GET
    @Path("/topic/{topicId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getTopicComments(@PathParam("topicId") long topicId) {
        return service.getTopicComments(topicId);
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response moderateComment(@PathParam("id") long id) {
        try {
            Comment tmp = service.moderateComment(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
