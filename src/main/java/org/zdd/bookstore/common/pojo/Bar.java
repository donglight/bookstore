package org.zdd.bookstore.common.pojo;

import java.util.List;

public class Bar {


    private List<String> bookNames;

    private List<Integer> sales;

    public List<String> getBookNames() {
        return bookNames;
    }

    public void setBookNames(List<String> bookNames) {
        this.bookNames = bookNames;
    }

    public List<Integer> getSales() {
        return sales;
    }

    public void setSales(List<Integer> sales) {
        this.sales = sales;
    }
}
