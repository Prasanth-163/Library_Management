Overview

This project is a simple console-based Library Management System developed using Java and MySQL. It helps in managing books, users, and transactions in a structured way.

Features
Add, remove, and update books
View all books with their availability status
Register users
Issue books to users
Return books with fine calculation
Search books by title or author


Technologies Used
Java (Object-Oriented Programming concepts)
JDBC (for database connection)
MySQL (for storing data)

Database Tables
Books (id, title, author, is_issued)
Users (user_id, name)
Transactions (book_id, user_id, issue_date, return_date)


How to Run
Create a database named library in MySQL
Create required tables
Place MySQL connector jar inside the lib folder
Compile the program:
javac -cp ".;lib/mysql-connector-j-9.6.0.jar" *.java
Run the program:
java -cp ".;lib/mysql-connector-j-9.6.0.jar" Main


## Screenshots

Menu
![Menu](library-management/Screenshots/menu.png)

View Books
![View](library-management/Screenshots/view_books.png)

Issue Book
![Issue](library-management/Screenshots/issue.png)

After Issue + View
![After Issue](library-management/Screenshots/after_issue_view_books.png)

Return Book
![Return](library-management/Screenshots/return.png)

