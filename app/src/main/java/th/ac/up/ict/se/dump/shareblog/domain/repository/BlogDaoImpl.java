package th.ac.up.ict.se.dump.shareblog.domain.repository;

import java.util.Iterator;
import java.util.List;

import th.ac.up.ict.se.dump.shareblog.domain.dao.BlogDao;
import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;

/**
 * Created by 56023 on 10/5/2559.
 */
public class BlogDaoImpl implements BlogDao {

    private static BlogDao instance;

    private BlogDaoImpl(){}

    public static BlogDao instance() {
        if (instance == null) {
            instance = new BlogDaoImpl();
        }
        return instance;
    }

    @Override
    public Iterator<Blog> getBlogs() {
        return Blog.findAll(Blog.class);
    }

    @Override
    public Iterator<Comment> getComments(Blog blog) {
        return Comment.find(Comment.class, "author = ?", blog.getId()+"" ).iterator();
    }

    @Override
    public Blog findById(Long id) {
        return Blog.findById(Blog.class, id);
    }

    @Override
    public Blog save(Blog blog) {
        Long id = Blog.save(blog);
        return Blog.findById(Blog.class, id);
    }

    @Override
    public Blog update(Blog blog) {
        Long id = Blog.save(blog);
        return Blog.findById(Blog.class, id);
    }

    @Override
    public void delete(Blog blog) {
        Blog.delete(blog);
    }

    @Override
    public void addStar(Blog blog) {

    }
}
