package org.zdd.bookstore.common.pojo;

import java.util.List;

/**
 * 分页bean
 */
public class PageBean<T> {

    //当前页
    private int page;

    //每页显示多少条
    private int pageSize;

    //总页数
    private int totalPage;

    //总记录数
    private int totalCount;

    //数据
    public List<T> rows;

    public PageBean(int page, int pageSize, int totalCount, List<T> rows) {

        //默认第1页
        if(page < 1){
            page = 1;
        }
        //默认每页显示5条
        if(pageSize < 1){
            pageSize = 5;
        }
        setTotalPage(totalCount, pageSize);
        if(page > this.totalPage){
            page = this.totalPage;
        }
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.rows = rows;
    }

    //获得起始记录的行号
    public int getStart(){
        return (int) Math.ceil((page-1)*pageSize);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalCount,int pageSize) {
        this.totalPage = (int) Math.ceil(totalCount / pageSize);
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
