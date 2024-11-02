package com.library;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Member> members = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getIsbn(), book);
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public Book findBook(String isbn) {
        return books.get(isbn);
    }

    public Member findMember(String memberId) {
        return members.get(memberId);
    }

    public boolean issueBook(String isbn, String memberId) {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book != null && member != null && !book.isIssued() && !member.hasIssuedBook()) {
            book.issueBook();
            member.issueBook(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(String memberId) {
        Member member = findMember(memberId);
        if (member != null && member.hasIssuedBook()) {
            Book book = member.getIssuedBook();
            book.returnBook();
            member.returnBook();
            return true;
        }
        return false;
    }

    public Map<String, Book> getAllBooks() {
        return books;
    }

    public Map<String, Member> getAllMembers() {
        return members;
    }
}
