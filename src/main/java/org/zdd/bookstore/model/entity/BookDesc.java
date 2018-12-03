package org.zdd.bookstore.model.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "book_desc")
public class BookDesc {
    @Id
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "created")
    private Date created;

    @Column(name = "updated")
    private Date updated;

    @Column(name = "book_desc")
    private String bookDesc;

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
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * @return book_desc
     */
    public String getBookDesc() {
        return bookDesc;
    }

    /**
     * @param bookDesc
     */
    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc == null ? null : bookDesc.trim();
    }
}