package com.library;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryApp extends JFrame {
    private Library library = new Library();

    public LibraryApp() {
        // Window Setup
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel Setup
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Header with Title
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel header = new JLabel("Library Management System", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(header, BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Tabbed Pane for Navigation
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        // Tab 1: Book Management
        JPanel bookPanel = createBookManagementPanel();
        tabbedPane.addTab("Manage Books", bookPanel);

        // Tab 2: Member Management
        JPanel memberPanel = createMemberManagementPanel();
        tabbedPane.addTab("Manage Members", memberPanel);

        // Tab 3: Issue and Return
        JPanel issueReturnPanel = createIssueReturnPanel();
        tabbedPane.addTab("Issue/Return Books", issueReturnPanel);

        // Adding tabs to main panel
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Status Bar
        JLabel statusBar = new JLabel("Ready");
        statusBar.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Book Management Panel
    private JPanel createBookManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField bookTitleField = new JTextField();
        JTextField bookAuthorField = new JTextField();
        JTextField bookIsbnField = new JTextField();

        formPanel.add(new JLabel("Title:"));
        formPanel.add(bookTitleField);
        formPanel.add(new JLabel("Author:"));
        formPanel.add(bookAuthorField);
        formPanel.add(new JLabel("ISBN:"));
        formPanel.add(bookIsbnField);

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addBookButton.addActionListener(e -> {
            String title = bookTitleField.getText();
            String author = bookAuthorField.getText();
            String isbn = bookIsbnField.getText();

            if (!title.isEmpty() && !author.isEmpty() && !isbn.isEmpty()) {
                library.addBook(new Book(title, author, isbn));
                JOptionPane.showMessageDialog(this, "Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                bookTitleField.setText("");
                bookAuthorField.setText("");
                bookIsbnField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(addBookButton, BorderLayout.SOUTH);
        return panel;
    }

    // Member Management Panel
    private JPanel createMemberManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField memberNameField = new JTextField();
        JTextField memberIdField = new JTextField();

        formPanel.add(new JLabel("Member Name:"));
        formPanel.add(memberNameField);
        formPanel.add(new JLabel("Member ID:"));
        formPanel.add(memberIdField);

        JButton addMemberButton = new JButton("Add Member");
        addMemberButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addMemberButton.addActionListener(e -> {
            String name = memberNameField.getText();
            String id = memberIdField.getText();

            if (!name.isEmpty() && !id.isEmpty()) {
                library.addMember(new Member(name, id));
                JOptionPane.showMessageDialog(this, "Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                memberNameField.setText("");
                memberIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(addMemberButton, BorderLayout.SOUTH);
        return panel;
    }

    // Issue/Return Management Panel
    private JPanel createIssueReturnPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JTextField isbnField = new JTextField();
        JTextField memberIdField = new JTextField();

        formPanel.add(new JLabel("Book ISBN:"));
        formPanel.add(isbnField);
        formPanel.add(new JLabel("Member ID:"));
        formPanel.add(memberIdField);

        JButton issueButton = new JButton("Issue Book");
        JButton returnButton = new JButton("Return Book");

        issueButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        returnButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        issueButton.addActionListener(e -> {
            String isbn = isbnField.getText();
            String memberId = memberIdField.getText();
            if (library.issueBook(isbn, memberId)) {
                JOptionPane.showMessageDialog(this, "Book issued successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Issue failed. Check availability and member status.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        returnButton.addActionListener(e -> {
            String memberId = memberIdField.getText();
            if (library.returnBook(memberId)) {
                JOptionPane.showMessageDialog(this, "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Return failed. Check member status.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(issueButton);
        buttonPanel.add(returnButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryApp app = new LibraryApp();
            app.setVisible(true);
        });
    }
}