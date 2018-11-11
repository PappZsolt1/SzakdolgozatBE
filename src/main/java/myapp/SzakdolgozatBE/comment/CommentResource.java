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
import myapp.SzakdolgozatBE.MyValidationException;

@Path("/comment")
@ApplicationScoped
public class CommentResource {
    
    @EJB CommentService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(Comment comment) {
        try {
            Comment tmp =  service.addComment(comment);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/movie/{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieComments(@PathParam("movieId") long movieId) {
        try {
            List<Comment> tmp = service.getMovieComments(movieId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/episode/{episodeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisodeComments(@PathParam("episodeId") long episodeId) {
        try {
            List<Comment> tmp = service.getEpisodeComments(episodeId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/actor/{actorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorComments(@PathParam("actorId") long actorId) {
        try {
            List<Comment> tmp = service.getActorComments(actorId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/article/articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticleComments(@PathParam("articleId") long articleId) {
        try {
            List<Comment> tmp = service.getArticleComments(articleId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/topic/{topicId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopicComments(@PathParam("topicId") long topicId) {
        try {
            List<Comment> tmp = service.getTopicComments(topicId);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
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
