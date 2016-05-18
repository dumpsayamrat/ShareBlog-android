package th.ac.up.ict.se.dump.shareblog.domain.repository;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.up.ict.se.dump.shareblog.domain.dao.BlogDao;
import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;
import th.ac.up.ict.se.dump.shareblog.domain.retrofit.BlogRetrofit;

/**
 * Created by 56023 on 10/5/2559.
 */
public class BlogDaoWebImpl implements BlogDao {

    private BlogRetrofit blogRetrofit;
    private static BlogDao instance;

    private BlogDaoWebImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.10.104:8000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        blogRetrofit = retrofit.create(BlogRetrofit.class);
    }

    public static BlogDao instance() {
        if (instance == null) {
            instance = new BlogDaoWebImpl();
        }
        return instance;
    }

    @Override
    public Iterator<Blog> getBlogs() {
        Call<List<Blog>> call = blogRetrofit.getAllBlog();
        try {
            return call.execute().body().iterator();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Blog>().iterator();
        }
    }

    @Override
    public Iterator<Comment> getComments(Blog blog) {
        Call<List<Comment>> call = blogRetrofit.getComments(blog.getId());
        try {
            return call.execute().body().iterator();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<Comment>().iterator();
        }
    }

    @Override
    public Blog findById(Long id) {
        Call<Blog> call = blogRetrofit.getBlog(id);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Blog save(Blog blog) {
        Call<Blog> call = blogRetrofit.createBlog(blog);
        try {
            return call.execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Blog update(Blog blog) {
        Call<Blog> call = blogRetrofit.updateBlog(blog);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Blog blog) {

        Call<Blog> call = blogRetrofit.deleteBlog(blog.getId());
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addStar(Blog blog) {
        Call<Blog> call = blogRetrofit.addStar(blog.getId());
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
