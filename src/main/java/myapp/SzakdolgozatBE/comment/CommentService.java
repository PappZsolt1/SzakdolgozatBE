package myapp.SzakdolgozatBE.comment;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.myUser.MyUser;

@Stateless
public class CommentService {
    
    @Inject CommentDAO dao;
    
    public Comment addComment(String content, MyUser myUser) {
        Comment tmp = new Comment();
        tmp.setContent(content);
        tmp.setMyUser(myUser);
        return dao.addComment(tmp);
    }
    
    public List<Comment> getxxxComments () {
        //todo
    }
}
