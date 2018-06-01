package myapp.SzakdolgozatBE.movie;

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

@Path("movie")
@ApplicationScoped
public class MovieResource {

    @EJB
    MovieService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        Movie tmp = service.addMovie(movie);
        return Response.ok().entity(tmp).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovie(@PathParam("id") long id) {
        try {
            Movie tmp = service.getMovie(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAllMovies() {
        return service.getAllMovies();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") long id) {
        try {
            service.deleteMovie(id);
            return Response.ok().build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyMovie(@PathParam("id") long id, Movie movie) {
        try {
            Movie tmp = service.modifyMovie(id, movie);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/rating/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void changeRating(@PathParam("id") long id, int rating) {
        service.changeRating(id, rating);
    }
}
