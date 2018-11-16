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
import myapp.SzakdolgozatBE.MyValidationException;
import myapp.SzakdolgozatBE.rating.RatingService;

@Path("/movie")
@ApplicationScoped
public class MovieResource {

    @EJB
    MovieService service;

    @EJB
    RatingService ratingService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        try {
            Movie tmp = service.addMovie(movie);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovie(@PathParam("id") long id) {
        try {
            Movie tmp = service.getMovie(id);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMovies() {
        try {
            List<Movie> tmp = service.getAllMovies();
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @Path("/search/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResultMovies(@PathParam("title") String title) {
        try {
            List<Movie> tmp = service.getResultMovies(title);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMovie(@PathParam("id") long id) {
        try {
            service.deleteMovie(id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyMovie(Movie movie) {
        try {
            Movie tmp = service.modifyMovie(movie);
            return Response.ok().entity(tmp).build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/rating/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeRating(@PathParam("id") long id, int rating) {
        try {
            service.changeRating(id, rating);
            ratingService.addMovieRating((byte) rating, id);
            return Response.ok().build();
        } catch (MyValidationException m) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
