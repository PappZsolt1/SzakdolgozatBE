package myapp.SzakdolgozatBE.myUser;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@ApplicationScoped
public class MyUserResource {

    @EJB
    MyUserService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMyUser(String username, String password) {
        try {
            MyUser tmp = service.addMyUser(username, password);
            return Response.ok().entity(tmp).build();
        } catch (Throwable t) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getUsernames() {
        return service.getUsernames();
    }
}
