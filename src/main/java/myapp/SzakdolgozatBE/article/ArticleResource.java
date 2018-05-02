package myapp.SzakdolgozatBE.article;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Path("/article")
@ApplicationScoped
public class ArticleResource {
    
    @EJB ArticleService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArticle(@FormParam("title") String title, @FormParam("content") String content) {
        try {
            Article tmp = service.addArticle(title, content);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getAllArticles() {
        return service.getAllArticles();
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
