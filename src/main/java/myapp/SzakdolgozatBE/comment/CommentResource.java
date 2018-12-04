package myapp.SzakdolgozatBE.comment;

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

@Path("/comment")
@ApplicationScoped
public class CommentResource {
    
    @Inject
    CommentService service;
    
    @POST
    @Path("/movie/{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovieComment(@PathParam("movieId") long movieId, Comment comment) {
        try {
            Comment tmp = service.addMovieComment(movieId, comment);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("/actor/{actorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addActorComment(@PathParam("actorId") long actorId, Comment comment) {
        try {
            Comment tmp = service.addActorComment(actorId, comment);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("/article/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArticleComment(@PathParam("articleId") long articleId, Comment comment) {
        try {
            Comment tmp = service.addArticleComment(articleId, comment);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("/episode/{episodeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEpisodeComment(@PathParam("episodeId") long episodeId, Comment comment) {
        try {
            Comment tmp = service.addEpisodeComment(episodeId, comment);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Path("/topic/{topicId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTopicComment(@PathParam("topicId") long topicId, Comment comment) {
        try {
            Comment tmp = service.addTopicComment(topicId, comment);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/public/movie/{page}/{size}/{movieId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieComments(@PathParam("page") int page, @PathParam("size") int size, @PathParam("movieId") long movieId) {
        try {
            Wrapper tmp = service.getMovieComments(page, size, movieId);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/public/episode/{page}/{size}/{episodeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEpisodeComments(@PathParam("page") int page, @PathParam("size") int size, @PathParam("episodeId") long episodeId) {
        try {
            Wrapper tmp = service.getEpisodeComments(page, size, episodeId);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/public/actor/{page}/{size}/{actorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorComments(@PathParam("page") int page, @PathParam("size") int size, @PathParam("actorId") long actorId) {
        try {
            Wrapper tmp = service.getActorComments(page, size, actorId);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/public/article/{page}/{size}/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticleComments(@PathParam("page") int page, @PathParam("size") int size, @PathParam("articleId") long articleId) {
        try {
            Wrapper tmp = service.getArticleComments(page, size, articleId);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/public/topic/{page}/{size}/{topicId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopicComments(@PathParam("page") int page, @PathParam("size") int size, @PathParam("topicId") long topicId) {
        try {
            Wrapper tmp = service.getTopicComments(page, size, topicId);
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
    public Response moderateComment(@PathParam("id") long id) {
        try {
            Comment tmp = service.moderateComment(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
