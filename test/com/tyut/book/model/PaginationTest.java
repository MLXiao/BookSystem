package com.tyut.book.model;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class PaginationTest {

    private Pagination pagination;

    @Before
    public void Setup() {
        pagination = new Pagination();
    }

    @Test
    public void TestgetPageList() {
        pagination.setTotalCount(1000);
        Scanner scanner = new Scanner(System.in);
        int currentPage;
        do {
            currentPage = scanner.nextInt();
            pagination.setCurrentPage(currentPage);
            for (int p : pagination.getPageList()) {
                System.out.print(p + "  ");
            }
        } while (currentPage > 0);
        scanner.close();

    }

}
