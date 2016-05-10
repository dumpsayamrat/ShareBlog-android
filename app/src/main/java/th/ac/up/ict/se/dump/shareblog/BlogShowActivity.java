package th.ac.up.ict.se.dump.shareblog;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.common.collect.Lists;

import java.util.ArrayList;

import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;
import th.ac.up.ict.se.dump.shareblog.domain.repository.BlogServiceImpl;
import th.ac.up.ict.se.dump.shareblog.domain.repository.CommentServiceImpl;
import th.ac.up.ict.se.dump.shareblog.domain.service.BlogService;
import th.ac.up.ict.se.dump.shareblog.domain.service.CommentService;
import th.ac.up.ict.se.dump.shareblog.ui.BlogAdapter;
import th.ac.up.ict.se.dump.shareblog.ui.CommentAdapter;

public class BlogShowActivity extends AppCompatActivity {

    BlogService blogService = BlogServiceImpl.instance();
    CommentService commentService = CommentServiceImpl.instance();

    TextView txtTitle;
    TextView txtAuthor;
    TextView txtContent;

    ListView lvComments;
    ArrayList<Comment> listOfComments;

    EditText edtCommentAuthor;
    EditText edtComment;
    Button btnCommentAction;

    Blog blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_show);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtAuthor = (TextView) findViewById(R.id.txtAuthor);
        txtContent = (TextView) findViewById(R.id.txtContent);

        lvComments = (ListView) findViewById(R.id.lvComments);

        edtCommentAuthor = (EditText) findViewById(R.id.edtCommetAuthor);
        edtComment = (EditText) findViewById(R.id.edtComment);
        btnCommentAction = (Button) findViewById(R.id.btnCommentAction);

        Intent i = getIntent();
        Long id = i.getLongExtra("BLOG_ID", 0);

        blog = blogService.findById(id);
        txtTitle.setText("Title : "+blog.getTitle());
        txtAuthor.setText("Author : "+blog.getAuthor());
        txtContent.setText(blog.getContent());

        updateDatabase();

        btnCommentAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtCommentAuthor.getText().toString();
                String comment = edtComment.getText().toString();

                Comment c = new Comment();
                c.setAuthor(name);
                c.setComment(comment);
                c.setBlog_id(blog.getId());
                commentService.save(c);
                /*Comment cc = commentService.findById(c.getId());
                txtTitle.setText(cc.getBlog_id() + "");*/
                updateDatabase();
                edtCommentAuthor.setText("");
                edtComment.setText("");
            }
        });
    }

    private void updateDatabase() {
        //listOfComments = Lists.newArrayList(blogService.getComments(blog));
        listOfComments = Lists.newArrayList(commentService.getComments());
        CommentAdapter adapter = new CommentAdapter(
                this, listOfComments
        );
        lvComments.setAdapter(adapter);
    }
}
