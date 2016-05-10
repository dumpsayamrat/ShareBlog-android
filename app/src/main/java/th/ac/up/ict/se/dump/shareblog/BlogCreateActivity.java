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
import th.ac.up.ict.se.dump.shareblog.domain.service.BlogService;

public class BlogCreateActivity extends AppCompatActivity {

    BlogService blogService = BlogServiceImpl.instance();

    EditText edtCreateTitle;
    EditText edtCreateAuthor;
    EditText edtCreatePassword;
    EditText edtCreateContent;
    Button btnBlogAddAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_create);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        edtCreateTitle = (EditText) findViewById(R.id.edtCreateTitle);
        edtCreateAuthor = (EditText) findViewById(R.id.edtCreateAuthor);
        edtCreatePassword = (EditText) findViewById(R.id.edtCreatePassword);
        edtCreateContent = (EditText) findViewById(R.id.edtCreateContent);
        btnBlogAddAction = (Button) findViewById(R.id.btnBlogCreateAction);

        btnBlogAddAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtCreateTitle.getText().toString();
                String author = edtCreateAuthor.getText().toString();
                String password = edtCreatePassword.getText().toString();
                String content = edtCreateContent.getText().toString();

                Blog blog = new Blog(title, author, password, content);

                blogService.save(blog);

                Intent i = new Intent(BlogCreateActivity.this, BlogActivity.class);
                startActivity(i);

            }
        });
    }
}
