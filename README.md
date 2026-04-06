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
![Menu](screenshots/menu.png)

Add Book
![Add](screenshots/add.png)

View Books
![View](screenshots/view.png)

Issue Book
![Issue](screenshots/issue.png)

Return Book
![Return](screenshots/return.png)

