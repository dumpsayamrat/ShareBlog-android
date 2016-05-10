package th.ac.up.ict.se.dump.shareblog.domain.repository;

import java.util.Iterator;

import th.ac.up.ict.se.dump.shareblog.domain.dao.CommentDao;
import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;
import th.ac.up.ict.se.dump.shareblog.domain.service.CommentService;

/**
 * Created by 56023 on 10/5/2559.
 */
public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao = CommentDaoImpl.instance();

    private static CommentService instance;

    private CommentServiceImpl(){}

    public static CommentService instance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
        }
        return instance;
    }

    @Override
    public Iterator<Comment> getComments() {
        return commentDao.getComments();
    }

    @Override
    public Blog getBlog(Comment comment) {
        return commentDao.getBlog(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        return commentDao.update(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }
}
