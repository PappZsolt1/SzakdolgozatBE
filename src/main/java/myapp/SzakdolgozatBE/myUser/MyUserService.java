package myapp.SzakdolgozatBE.myUser;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MyUserService {

    @Inject
    MyUserDAO dao;

    public MyUser addMyUser(String username, String password) {
        if (dao.isNotTaken(username)) {
            MyUser tmp = new MyUser();
            tmp.setUsername(username);
            tmp.setPassword(password);
            return dao.addMyUser(tmp);
        } else {
            throw new NullPointerException();
        }
    }

    public List<String> getUsernames() {
        return dao.getUsernames();
    }

    public boolean isNotTaken(String username) {
        return dao.isNotTaken(username);
    }

}
