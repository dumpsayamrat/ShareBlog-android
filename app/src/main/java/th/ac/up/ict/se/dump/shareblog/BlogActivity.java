package th.ac.up.ict.se.dump.shareblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.common.collect.Lists;

import java.util.ArrayList;

import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;
import th.ac.up.ict.se.dump.shareblog.domain.repository.BlogServiceImpl;
import th.ac.up.ict.se.dump.shareblog.domain.service.BlogService;
import th.ac.up.ict.se.dump.shareblog.ui.BlogAdapter;

public class BlogActivity extends AppCompatActivity {

    BlogService blogService = BlogServiceImpl.instance();

    Button btnAddAc2;
    ListView lvBlogs;

    ArrayList<Blog> listOfBlogs;
    String[] menuItems = {"Edit", "Delete"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

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
        Blog blog = listOfBlogs.get(info.position);
        if (menuItemName.equals("Delete")) {
            blogService.delete(blog);
            updateDatabase();
        } else if (menuItemName.equals("Edit")) {

            /*Intent i = new Intent(SupplierActivity.this, SupplierEditActivity.class);
            i.putExtra("SUPPLIER_NAME", supplier.getName());
            i.putExtra("SUPPLIER_ADDRESS", supplier.getAddress());
            i.putExtra("SUPPLIER_ID", supplier.getId());
            startActivity(i);*/
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
