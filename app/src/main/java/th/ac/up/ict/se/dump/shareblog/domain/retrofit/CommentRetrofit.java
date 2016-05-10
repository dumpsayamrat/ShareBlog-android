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
public interface CommentRetrofit {

    @GET("/api/comment")
    Call<List<Comment>> getAllComment();

    @GET("/api/comment/{id}")
    Call<Comment> getComment(@Path("id") Long id);

    @POST("/api/comment")
    Call<Comment> createComment(@Body Comment comment);

    @PUT("/api/comment")
    Call<Comment> updateComment(@Body Comment comment);

    @DELETE("/api/comment")
    Call<Comment> deleteComment(@Body Comment comment);
}
