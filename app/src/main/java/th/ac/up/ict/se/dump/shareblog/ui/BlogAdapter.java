package th.ac.up.ict.se.dump.shareblog.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import th.ac.up.ict.se.dump.shareblog.R;
import th.ac.up.ict.se.dump.shareblog.domain.model.Blog;

/**
 * Created by 56023 on 10/5/2559.
 */
public class BlogAdapter extends ArrayAdapter<Blog> {

    private Context context;
    private List<Blog> blogs;

    public BlogAdapter(Context context, List<Blog> blogs) {
        super(context, -1, blogs);
        this.context = context;
        this.blogs = blogs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_blog, parent, false);

        TextView txtTitleAdap = (TextView) rowView.findViewById(R.id.txtTitleAdap);
        TextView txtAuthorAdap = (TextView) rowView.findViewById(R.id.txtAuthorAdap);

        String title = this.blogs.get(position).getTitle().toString();
        String author = this.blogs.get(position).getAuthor().toString();

        txtTitleAdap.setText(title == null ? "no data" : title);
        txtAuthorAdap.setText(author == null ? "no data" : author);
        return rowView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
