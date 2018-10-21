package myapp.SzakdolgozatBE.actor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.gender.GenderDAO;

@Stateless
public class ActorService {

    @Inject
    ActorDAO dao;
    
    @Inject
    GenderDAO genderDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. HH:mm:ss");

    public Actor addActor(Actor actor) {
        return dao.addActor(actor);
    }

    public Actor getActor(long id) throws NullPointerException {
        Actor tmp = dao.getActor(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            return tmp;
        }
    }

    /*public List<Actor> getxxxActors() {
        //todo
    }*/
    
    public void deleteActor(long id) throws NullPointerException {
        Actor tmp = dao.getActor(id);
        if (tmp == null) {
            throw new NullPointerException();
        } else {
            dao.deleteActor(id);
        }
    }

    public Actor modifyActor(Actor actor) throws NullPointerException {
        Actor tmp = dao.getActor(actor.getId());
        if (tmp != null) {
            tmp.setBio(actor.getBio());
            tmp.setBirthDate(actor.getBirthDate());
            tmp.setBirthPlace(actor.getBirthPlace());
            tmp.setName(actor.getName());
            tmp.setGender(actor.getGender());
            return dao.modifyActor(tmp);
        } else {
            throw new NullPointerException();
        }
    }
}
