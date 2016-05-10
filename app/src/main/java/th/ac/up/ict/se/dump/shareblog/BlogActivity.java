package th.ac.up.ict.se.dump.shareblog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.common.collect.Lists;

import java.util.ArrayList;

import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.repository.BlogServiceImpl;
import th.ac.up.ict.se.dump.shareblog.domain.repository.BlogServiceWebImpl;
import th.ac.up.ict.se.dump.shareblog.domain.service.BlogService;
import th.ac.up.ict.se.dump.shareblog.ui.BlogAdapter;

public class BlogActivity extends AppCompatActivity {

   // BlogService blogService = BlogServiceImpl.instance();
    BlogService blogService = BlogServiceWebImpl.instance();
    Button btnAddAc2;
    ListView lvBlogs;

    ArrayList<Blog> listOfBlogs;
    String[] menuItems = {"Edit", "Delete"};

    private String passwordEdit = "";
    private String passwordDelete = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btnAddAc2 = (Button) findViewById(R.id.btnAddAc2);
        lvBlogs = (ListView) findViewById(R.id.lvBlogs);
        updateDatabase();

        registerForContextMenu(lvBlogs);

        btnAddAc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BlogActivity.this, BlogCreateActivity.class);
                startActivity(i);
            }
        });

        lvBlogs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Blog blog = listOfBlogs.get(position);
                Intent i = new Intent(BlogActivity.this, BlogShowActivity.class);
                i.putExtra("BLOG_ID", blog.getId());
                startActivity(i);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if (v.getId() == R.id.lvBlogs) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            Blog blog = listOfBlogs.get(info.position);
            menu.setHeaderTitle(blog.getTitle()+" | "+ blog.getAuthor());
            for (int i = 0; i < menuItems.length; i++) {
                menu.add(ContextMenu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String menuItemName = menuItems[menuItemIndex];
        final Blog blog = listOfBlogs.get(info.position);
        if (menuItemName.equals("Delete")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter your password.");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    passwordDelete = input.getText().toString();
                    if (passwordDelete.equals(blog.getPassword())) {
                        blogService.delete(blog);
                        updateDatabase();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        } else if (menuItemName.equals("Edit")) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enter your password.");

            // Set up the input
            final EditText input = new EditText(this);
            // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    passwordEdit = input.getText().toString();
                    if (passwordEdit.equals(blog.getPassword())) {
                        Intent i = new Intent(BlogActivity.this, BlogEditActivity.class);
                        i.putExtra("BLOG_ID", blog.getId());
                        startActivity(i);
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDatabase();

    }



    private void updateDatabase() {

        listOfBlogs = Lists.newArrayList(blogService.getBlogs());
        BlogAdapter adapter = new BlogAdapter(
                this, listOfBlogs

        );
        lvBlogs.setAdapter(adapter);
    }
}
