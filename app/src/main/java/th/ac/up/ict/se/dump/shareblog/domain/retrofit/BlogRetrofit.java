package th.ac.up.ict.se.dump.shareblog.domain.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;

/**
 * Created by 56023 on 10/5/2559.
 */
public interface BlogRetrofit {

    @GET("/api/blog")
    Call<List<Blog>> getAllBlog();

    @GET("/api/blog/{id}")
    Call<Blog> getBlog(@Path("id") Long id);

    @GET("/api/blog/comment/{blog_id}")
    Call<List<Comment>> getComments(@Path("blog_id") Long id);

    @POST("/api/blog")
    Call<Blog> createBlog(@Body Blog blog);

    @PUT("/api/blog")
    Call<Blog> updateBlog(@Body Blog blog);

    @GET("/api/blog/{id}/delete")
    Call<Blog> deleteBlog(@Path("id") Long id);

    @GET("/api/blog/{id}/star")
    Call<Blog> addStar(@Path("id") Long id);

}
