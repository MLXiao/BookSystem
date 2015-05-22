package com.tyut.book.model;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    private static final int DEFOULT_PAGE_SIZE = 10;
    private static final int DEFOULT_CURRENT_PAGE = 1;
    private static final int DEFOULT_LIST_SIZE = 5;

    private int totalCount;
    private int pageSize;
    private int currentPage;
    private int totalPage;
    private List<Integer> pageList;
    private int listSize;
    private int offSet;

    public Pagination() {
        pageSize = DEFOULT_PAGE_SIZE;
        currentPage = DEFOULT_CURRENT_PAGE;
        listSize = DEFOULT_LIST_SIZE;
        pageList = new ArrayList<Integer>();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        totalPage = (totalCount - 1) / pageSize + 1;
        return totalPage;
    }

    public List<Integer> getPageList() {
        pageList.clear();
        int n = (currentPage - 1) / listSize + 1;
        for (int i = (n - 1) * listSize + 1; i <= n * listSize && i <= getTotalPage(); i++) {
            pageList.add(i);
        }
        return pageList;
    }

    public int getListSize() {
        return listSize;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount < 0) {
            totalCount = 0;
        }
        this.totalCount = totalCount;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            pageSize = 1;
        }
        this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > getTotalPage()) {
            currentPage = getTotalPage();
        }
        this.currentPage = currentPage;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public int getOffSet() {
        offSet = (currentPage - 1) * pageSize;
        return offSet;
    }

}
