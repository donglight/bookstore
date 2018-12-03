package org.zdd.bookstore.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "date")
    private Date date;

    @Column(name = "content")
    private String content;

    /**
     * 审核标志
     */
    @Column(name = "flag")
    private String flag;

    /**
     * @return comment_id
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * @param commentId
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * @return book_id
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取审核标志
     *
     * @return flag - 审核标志
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置审核标志
     *
     * @param flag 审核标志
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}