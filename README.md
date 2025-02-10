---

# 🏦 Bank Management System  

## 🚀 Overview  
The **Bank Management System** is a Java-based application that provides a seamless and secure way to manage bank accounts, transactions, and users. What sets this system apart is:  
✅ **Dual Interface:** Users can choose between a **Graphical User Interface (GUI)** and a **Console-based Interface** for flexibility.  
✅ **Robust Database Integration:** All transactions, accounts, and users are stored in a **MySQL database** with timestamps for accurate tracking.  
✅ **Security Features:** Includes user authentication, role management, and account lock mechanisms for failed login attempts.  
✅ **Well-Documented Code:** The project is structured with clear documentation, making it easy to understand and extend.  

---

## 🏗️ Features  
- **User Authentication:** Secure login system with password hashing and account locking on multiple failed attempts.  
- **Multiple Account Types:** Supports different types of bank accounts (e.g., Savings, Checking).  
- **Deposit & Withdrawal:** Users can perform deposits and withdrawals securely.  
- **Transaction History:** Every transaction is logged with timestamps in the database.  
- **Database-Driven:** Uses **MySQL** to store all user and transaction data.  

---

## 🗃️ Database Schema  
The system uses a **MySQL database** named `bank` with three core tables:  

### 🔹 `accounts` Table  
Stores information about users' bank accounts.  
```sql
CREATE TABLE accounts (
    accountNumber INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    accountType VARCHAR(20),
    balance DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (userId) REFERENCES users(userId)
);
```

### 🔹 `users` Table  
Manages user credentials and access control.  
```sql
CREATE TABLE users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(10) NOT NULL, -- e.g., "admin" or "customer"
    isLocked TINYINT(1) DEFAULT 0,
    failedAttempts INT DEFAULT 0
);
```

### 🔹 `transactions` Table  
Logs all user transactions with timestamps.  
```sql
CREATE TABLE transactions (
    transactionId INT AUTO_INCREMENT PRIMARY KEY,
    accountNumber INT,
    transactionType VARCHAR(50),
    amount DECIMAL(10,2) NOT NULL,
    transactionDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (accountNumber) REFERENCES accounts(accountNumber)
);
```

---

## 🛠️ Technologies Used  
- **Java** (Core application logic)  
- **Swing** (GUI interface)  
- **MySQL** (Database management)  
- **JDBC** (Database connectivity)  
- **Git & GitHub** (Version control)  

---

## 📂 Project Structure  
```
📦 BankManagementSystem  
 ┣ 📂 src  
 ┃ ┣ 📜 Main.java (Application entry point)  
 ┃ ┣ 📜 DatabaseManager.java (Handles MySQL operations)  
 ┃ ┣ 📜 User.java (User class and authentication)  
 ┃ ┣ 📜 Account.java (Account management)  
 ┃ ┣ 📜 Transaction.java (Handles deposits/withdrawals)  
 ┃ ┗ 📜 GUI.java (Graphical User Interface)  
 ┣ 📂 resources (Icons, SQL scripts)  
 ┣ 📜 README.md  
 ┣ 📜 .gitignore  
 ┗ 📜 pom.xml  
```

---

## 🚀 How to Run  
### 🖥️ Running the Console Version  
1️⃣ Clone the repository:  
```sh
git clone https://github.com/2Ntlaks/Bank-Management-System.git
cd Bank-Management-System
```
2️⃣ Compile and run:  
```sh
javac Main.java  
java Main  
```

### 🖼️ Running the GUI Version  
1️⃣ Ensure Java is installed.  
2️⃣ Open `GUI.java` in your IDE and run the file.  

---

## 🌟 Why Choose This Project?  
Unlike most Java bank management systems, **this project**:  
✅ **Provides dual interaction methods (GUI + Console)**  
✅ **Uses a structured database to manage accounts & transactions**  
✅ **Implements security features such as user authentication & locking**  
✅ **Well-documented codebase for easy customization**  

🚀 **This makes it an excellent addition to any portfolio or real-world project implementation!**  

---

## 👨‍💻 Contributing  
Want to improve this project? Feel free to fork it and submit a pull request!  

📧 **Contact:** [ntlakaniphomgaguli210@gmail.com]  

---

