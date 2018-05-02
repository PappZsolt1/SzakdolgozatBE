package myapp.SzakdolgozatBE.season;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Path;

@Path("season")
@ApplicationScoped
public class SeasonResource {
    
    @EJB SeasonSerivce service;
    
}
