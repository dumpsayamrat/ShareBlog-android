package th.ac.up.ict.se.dump.shareblog.domain.dao;

import java.util.Iterator;

import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;

/**
 * Created by 56023 on 10/5/2559.
 */
public interface CommentDao {

    public Iterator<Comment> getComments();
    public Blog getBlog(Comment comment);
    public Comment findById(Long id);
    public Comment save(Comment comment);
    public Comment update(Comment comment);
    public void delete(Comment comment);
}
