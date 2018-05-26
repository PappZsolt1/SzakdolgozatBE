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

    public Actor addActor(String name, Date birthDate, String birthPlace, String bio, long genderId) {
        Actor tmp = new Actor();
        tmp.setName(name);
        tmp.setBirthDate(sdf.format(birthDate));
        tmp.setBirthPlace(birthPlace);
        tmp.setBio(bio);
        tmp.setGender(genderDao.getGender(genderId));
        return dao.addActor(tmp);
    }

    public Actor getActor(long id) throws NullPointerException {
        return dao.getActor(id);
    }

    /*public List<Actor> getxxxActors() {
        //todo
    }*/
    
    public void deleteActor(long id) throws NullPointerException {
        dao.deleteActor(id);
    }

    public Actor modifyActor(long id, String name, Date birthDate, String birthPlace, String bio, long genderId) throws NullPointerException {
        Actor tmp = new Actor();
        tmp.setName(name);
        tmp.setBirthDate(sdf.format(birthDate));
        tmp.setBirthPlace(birthPlace);
        tmp.setBio(bio);
        tmp.setGender(genderDao.getGender(genderId));
        return dao.modifyActor(id, tmp);
    }
}
