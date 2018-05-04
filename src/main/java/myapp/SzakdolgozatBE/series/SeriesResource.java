package myapp.SzakdolgozatBE.series;

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
import myapp.SzakdolgozatBE.enums.AgeClassification;
import myapp.SzakdolgozatBE.enums.Genre;

@Path("series")
@ApplicationScoped
public class SeriesResource {
    
    @EJB SeriesService service;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSeries(@FormParam("title") String title,
            @FormParam("releaseYear") int releaseYear,
            @FormParam("coverPicture") byte[] coverPicture,
            @FormParam("ageClassification") AgeClassification ageClassification,
            @FormParam("genre") Genre genre) {
        Series tmp = service.addSeries(title, releaseYear, coverPicture, ageClassification, genre);
        return Response.ok().entity(tmp).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSeries(@PathParam("id") long id) {
        try {
            Series tmp = service.getSeries(id);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Series> getAllSeries() {
        return service.getAllSeries();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSeries(@PathParam("id") long id) {
        try {
            service.deleteSeries(id);
            return Response.ok().build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifySeries(@PathParam("id") long id,
            @FormParam("title") String title,
            @FormParam("releaseYear") int releaseYear,
            @FormParam("coverPicture") byte[] coverPicture,
            @FormParam("ageClassification") AgeClassification ageClassification,
            @FormParam("genre") Genre genre) {
        try {
            Series tmp = service.modifySeries(id, title, releaseYear, coverPicture, ageClassification, genre);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    //todo
    public void changeRating(long id, double rating) {
        service.changeRating(id, rating);
    }
}
