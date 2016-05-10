package th.ac.up.ict.se.dump.shareblog.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import th.ac.up.ict.se.dump.shareblog.R;
import th.ac.up.ict.se.dump.shareblog.domain.model.Comment;

/**
 * Created by 56023 on 10/5/2559.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {

    private Context context;
    private List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> comments) {
        super(context, -1, comments);
        this.context = context;
        this.comments = comments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_comment, parent, false);

        TextView txtAuthorCommentAdap = (TextView) rowView.findViewById(R.id.txtAuthorCommentAdap);
        TextView txtCommentAdap = (TextView) rowView.findViewById(R.id.txtCommentAdap);

        String author = this.comments.get(position).getAuthor().toString();
        String comment = this.comments.get(position).getComment().toString();

        txtAuthorCommentAdap.setText(author == null ? "no data" : author);
        txtCommentAdap.setText(comment == null ? "no data" : comment);
        return rowView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
