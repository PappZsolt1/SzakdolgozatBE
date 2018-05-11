package myapp.SzakdolgozatBE.genre;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("genre")
@ApplicationScoped
public class GenreResource {
    
    @EJB GenreService service;
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGenre(@PathParam("id") long id) {
        try{
            Genre tmp = service.getGenre(id);            
            return Response.ok().entity(tmp).build();
        }
        catch(Throwable t){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Genre> getAllGenres() {
        return service.getAllGenres();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGenre(@FormParam("name") String name) {
        Genre tmp = service.addGenre(name);
        return Response.ok().entity(tmp).build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGenre(@PathParam("id") long id) {
        service.deleteGenre(id);
        return Response.ok().build();
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyGenre(@PathParam("id") long id, @PathParam("name") String name) {
        try{
            Genre tmp = service.modifyGenre(id, name);            
            return Response.ok().entity(tmp).build();
        }
        catch(Throwable t){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
