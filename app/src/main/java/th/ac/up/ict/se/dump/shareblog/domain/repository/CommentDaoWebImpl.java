package th.ac.up.ict.se.dump.shareblog.domain.repository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import th.ac.up.ict.se.dump.shareblog.domain.dao.CommentDao;
import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;
import th.ac.up.ict.se.dump.shareblog.domain.retrofit.BlogRetrofit;
import th.ac.up.ict.se.dump.shareblog.domain.retrofit.CommentRetrofit;

/**
 * Created by 56023 on 10/5/2559.
 */
public class CommentDaoWebImpl implements CommentDao {

    private CommentRetrofit commentRetrofit;

    private static CommentDao instance;

    private CommentDaoWebImpl(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.10.114:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        commentRetrofit = retrofit.create(CommentRetrofit.class);
    }

    public static CommentDao instance() {
        if (instance == null) {
            instance = new CommentDaoWebImpl();
        }
        return instance;
    }

    @Override
    public Iterator<Comment> getComments() {
        Call<List<Comment>> call = commentRetrofit.getAllComment();
        try {
            return call.execute().body().iterator();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Blog getBlog(Comment comment) {
        return null;
    }

    @Override
    public Comment findById(Long id) {
        Call<Comment> call = commentRetrofit.getComment(id);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Comment save(Comment comment) {
        Call<Comment> call = commentRetrofit.createComment(comment);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Comment update(Comment comment) {
        Call<Comment> call = commentRetrofit.updateComment(comment);
        try {
            return call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Comment comment) {
        Call<Comment> call = commentRetrofit.deleteComment(comment);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
