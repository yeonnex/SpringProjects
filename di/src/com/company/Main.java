package com.company;

import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url = "www.naver.com/books/it?10&size=20&name=spring-boot";

        // Base 64 encoding

        // url encoding
        IEncoder encoder = new Encoder(new Base64Encoder());
        String result = encoder.encode(url);

	    System.out.println(result);

    }
}
