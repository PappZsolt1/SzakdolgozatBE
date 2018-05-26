package myapp.SzakdolgozatBE.comment;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comment")
@ApplicationScoped
public class CommentResource {
    
    @EJB CommentService service;
    
    @POST
    @Path("/movie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovieComment(String content, long movieId) {
        Comment tmp =  service.addMovieComment(content, movieId);
        return Response.ok().entity(tmp).build();
    }
    
    @POST
    @Path("/episode")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEpisodeComment(String content, long episodeId) {
        Comment tmp =  service.addEpisodeComment(content, episodeId);
        return Response.ok().entity(tmp).build();
    }
    
    @POST
    @Path("/actor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addActorComment(String content, long actorId) {
        Comment tmp =  service.addActorComment(content, actorId);
        return Response.ok().entity(tmp).build();
    }
    
    @POST
    @Path("/article")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArticleComment(String content, long articleId) {
        Comment tmp =  service.addArticleComment(content, articleId);
        return Response.ok().entity(tmp).build();
    }
    
    @POST
    @Path("/topic")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTopicComment(String content, long topicId) {
        Comment tmp =  service.addTopicComment(content, topicId);
        return Response.ok().entity(tmp).build();
    }
    
    /*@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getxxxComments () {
        //todo
    }*/
}
