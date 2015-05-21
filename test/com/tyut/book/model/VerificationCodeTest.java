package com.tyut.book.model;

import org.junit.Before;
import org.junit.Test;

public class VerificationCodeTest {

    private VerificationCode vCode;

    @Before
    public void Setup() {
        vCode = new VerificationCode();
    }

    @Test
    public void TestgetImgBase64String() {
        System.out.println(vCode.getImgBase64String());
    }

}
