package th.ac.up.ict.se.dump.shareblog;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.repository.BlogServiceImpl;
import th.ac.up.ict.se.dump.shareblog.domain.repository.BlogServiceWebImpl;
import th.ac.up.ict.se.dump.shareblog.domain.service.BlogService;

public class BlogEditActivity extends AppCompatActivity {

    //private BlogService blogService = BlogServiceImpl.instance();
    BlogService blogService = BlogServiceWebImpl.instance();

    Button btnBlogEditAction;
    EditText edtEditTitle;
    EditText edtEditAuthor;
    EditText edtEditPassword;
    EditText edtEditContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_edit);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        edtEditTitle = (EditText) findViewById(R.id.edtEditTitle);
        edtEditAuthor = (EditText) findViewById(R.id.edtEditAuthor);
        edtEditPassword = (EditText) findViewById(R.id.edtEditPassword);
        edtEditContent = (EditText) findViewById(R.id.edtEditContent);
        btnBlogEditAction = (Button) findViewById(R.id.btnBlogEditAction);

        Intent i = getIntent();
        final Long id = i.getLongExtra("BLOG_ID", 0);

        Blog blog = blogService.findById(id);

        edtEditTitle.setText(blog.getTitle());
        edtEditAuthor.setText(blog.getAuthor());
        edtEditPassword.setText(blog.getPassword());
        edtEditContent.setText(blog.getContent());

        btnBlogEditAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtEditTitle.getText().toString();
                String author = edtEditAuthor.getText().toString();
                String password = edtEditPassword.getText().toString();
                String content = edtEditContent.getText().toString();

                Blog blogEdit = blogService.findById(id);
                blogEdit.setTitle(title);
                blogEdit.setAuthor(author);
                blogEdit.setPassword(password);
                blogEdit.setContent(content);
                blogService.update(blogEdit);

                finish();
            }
        });
    }
}
