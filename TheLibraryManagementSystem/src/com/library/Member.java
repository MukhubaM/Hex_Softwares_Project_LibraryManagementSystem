package com.library;

public class Member {
    private String name;
    private String memberId;
    private Book issuedBook;

    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.issuedBook = null;
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    public Book getIssuedBook() {
        return issuedBook;
    }

    public void issueBook(Book book) {
        this.issuedBook = book;
    }

    public void returnBook() {
        this.issuedBook = null;
    }

    public boolean hasIssuedBook() {
        return issuedBook != null;
    }
}