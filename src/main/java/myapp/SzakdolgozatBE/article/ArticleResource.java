package myapp.SzakdolgozatBE.article;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/article")
@ApplicationScoped
public class ArticleResource {

    @EJB
    ArticleService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveArticle(String title, String content) {
        Article tmp = service.saveArticle(title, content);
        return Response.ok().entity(tmp).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response publishNewArticle(String title, String content) {
        Article tmp = service.publishNewArticle(title, content);
        return Response.ok().entity(tmp).build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response publishSavedArticle(@PathParam("id") long id, String title, String content) {
        Article tmp = service.publishSavedArticle(id, title, content);
        return Response.ok().entity(tmp).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getSavedArticles() {
        return service.getSavedArticles();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getPublishedArticles() {
        return service.getPublishedArticles();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteArticle(@PathParam("id") long id) {
        service.deleteArticle(id);
        return Response.ok().build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticle(@PathParam("id") long id) {
        try {
            Article tmp = service.getArticle(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
