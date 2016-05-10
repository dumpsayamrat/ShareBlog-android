package th.ac.up.ict.se.dump.shareblog.domain.repository;

import java.util.Iterator;

import th.ac.up.ict.se.dump.shareblog.domain.dao.BlogDao;
import th.ac.up.ict.se.dump.shareblog.domain.dao.CommentDao;
import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;

/**
 * Created by 56023 on 10/5/2559.
 */
public class CommentDaoImpl implements CommentDao {

    private static CommentDao instance;

    private CommentDaoImpl(){}

    public static CommentDao instance() {
        if (instance == null) {
            instance = new CommentDaoImpl();
        }
        return instance;
    }

    @Override
    public Iterator<Comment> getComments() {
        return Comment.findAll(Comment.class);
    }

    @Override
    public Blog getBlog(Comment comment) {
        return Blog.findById(Blog.class, (Long)comment.getBlog_id());
    }

    @Override
    public Comment findById(Long id) {
        return Comment.findById(Comment.class, id);
    }

    @Override
    public Comment save(Comment comment) {
        Long id = Comment.save(comment);
        return Comment.findById(Comment.class, id);
    }

    @Override
    public Comment update(Comment comment) {
        Long id = Comment.save(comment);
        return Comment.findById(Comment.class, id);
    }

    @Override
    public void delete(Comment comment) {
        Comment.delete(comment);
    }
}
