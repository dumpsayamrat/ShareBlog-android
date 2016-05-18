package th.ac.up.ict.se.dump.shareblog.domain.service;

import java.util.Iterator;
import java.util.List;

import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;

/**
 * Created by 56023 on 10/5/2559.
 */
public interface BlogService {

    public Iterator<Blog> getBlogs();
    public Iterator<Comment> getComments(Blog blog);
    public Blog findById(Long id);
    public Blog save(Blog blog);
    public Blog update(Blog blog);
    public void delete(Blog blog);
    public void addStar(Blog blog);
}
