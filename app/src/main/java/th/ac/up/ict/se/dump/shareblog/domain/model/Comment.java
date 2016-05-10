package th.ac.up.ict.se.dump.shareblog.domain.model;

import com.orm.SugarRecord;

/**
 * Created by 56023 on 10/5/2559.
 */
public class Comment extends SugarRecord {
    
    private String author;
    private String comment;
    private Long blog_id;
    private String create_at;
    private String update_at;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(Long blog_id) {
        this.blog_id = blog_id;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
