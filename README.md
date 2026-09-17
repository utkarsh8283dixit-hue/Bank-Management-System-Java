# Bank Management System

A console-based Bank Management System developed using **Java, JDBC, and MySQL**.

## 📌 About the Project

The Bank Management System is an academic project designed to computerize basic banking operations such as account creation, secure login, balance inquiry, deposits, withdrawals, transaction history, and administrative monitoring.

The project demonstrates practical integration of **Java application logic with a MySQL database using JDBC**.

## 🚀 Features

### User Module

* Create a new bank account
* Automatic account number generation
* User login using account number and PIN
* Check account balance
* Deposit money
* Withdraw money
* View transaction history
* Change PIN
* Logout

### Admin Module

* Admin authentication
* View all accounts
* Search accounts
* Monitor transactions
* View total bank balance
* Delete accounts

### Database

* MySQL database
* Account information storage
* Transaction history
* JDBC connectivity
* PreparedStatement for SQL queries

## 🛠️ Technology Stack

* **Java 21**
* **JDBC**
* **MySQL**
* **Maven**
* **IntelliJ IDEA**
* **MySQL Workbench**

## 🗄️ Database Structure

### Accounts

| Field      | Type         | Key         |
| ---------- | ------------ | ----------- |
| account_no | INT          | Primary Key |
| name       | VARCHAR(100) |             |
| phone      | VARCHAR(15)  |             |
| pin        | VARCHAR(10)  |             |
| balance    | DOUBLE       |             |

### Transactions

| Field            | Type        | Key         |
| ---------------- | ----------- | ----------- |
| transaction_id   | INT         | Primary Key |
| account_no       | INT         | Foreign Key |
| type             | VARCHAR(20) |             |
| amount           | DOUBLE      |             |
| transaction_date | TIMESTAMP   |             |

## ⚙️ Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/YOUR-USERNAME/Bank-Management-System-Java.git
```

### 2. Create the MySQL Database

Open MySQL Workbench and create the database:

```sql
CREATE DATABASE bankdb;
```

Then execute the SQL file available inside:

```text
database/bankdb.sql
```

### 3. Configure Database Connection

Update the database credentials in:

```text
DBConnection.java
```

Example:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/bankdb";

private static final String USER = "root";

private static final String PASSWORD =
        "YOUR_PASSWORD";
```

### 4. Run the Project

Open the project in IntelliJ IDEA and run:

```text
Main.java
```

## 🔐 Security

The project uses `PreparedStatement` for parameterized SQL queries to reduce SQL injection risks.

**Note:** This is an academic project. PINs are currently stored as plain text and should be hashed in a production application.

## 🧪 Testing

The following functionality has been tested:

* Account creation
* User login
* Deposit and withdrawal
* Insufficient balance validation
* Admin monitoring
* PIN change

## 🔮 Future Enhancements

* JavaFX/Swing GUI
* Secure PIN hashing using BCrypt
* Inter-account fund transfer
* OTP and email notifications
* Cloud database deployment
* Improved transaction handling

## 👨‍💻 Author

**Your Name**

B.Tech Computer Science & Engineering

## 📄 Project Documentation

The complete project report is available in the `docs` folder.
