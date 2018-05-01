package myapp.SzakdolgozatBE.user;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
    
    @Inject UserDAO dao;
    
    public User add(String username, String password) {
        if (dao.isNotTaken(username)) {
            User tmp = new User();
            tmp.setUsername(username);
            tmp.setPassword(password);
            return dao.add(tmp);
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
