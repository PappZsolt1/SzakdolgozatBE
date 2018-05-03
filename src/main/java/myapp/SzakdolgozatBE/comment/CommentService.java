package myapp.SzakdolgozatBE.comment;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import myapp.SzakdolgozatBE.user.User;

@Stateless
public class CommentService {
    
    @Inject CommentDAO dao;
    
    public Comment addComment(String content, User myUser) {
        Comment tmp = new Comment();
        tmp.setContent(content);
        tmp.setMyUser(myUser);
        return dao.addComment(tmp);
    }
    
    public List<Comment> getxxxComments () {
        //todo
    }
}
