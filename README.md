# 📚 Library Management System

A console-based Library Management System built entirely in **Core Java** — Designed as a course project to demonstrate OOP, collections, file I/O, and clean code structure.

---

## 📖 Project Overview

This application simulates a basic library workflow where a librarian can:

- Manage a catalog of books
- Register library members
- Issue books to members
- Accept returned books
- Persist all data to disk so it survives application restarts

The project focuses on **clean separation of concerns**: data models, business logic, persistence, and UI (console menu) are each handled by a dedicated class.

---

## ✨ Features

### Book Management
- ➕ Add new books (with duplicate ID protection)
- 📋 View all books
- 🔍 Search books by title (case-insensitive, partial match)
- 🗑️ Remove books (only if not currently issued)

### Member Management
- ➕ Register new members (with duplicate ID protection)
- 📋 View all registered members
- 📊 Track how many books each member currently holds

### Issue & Return
- 📤 Issue a book to a member
- 📥 Return a book from a member
- 🚫 Prevent issuing an already-issued book
- 🚫 Enforce business rule: **max 3 books per member**
- 🚫 Prevent deleting an issued book

### Data Persistence
- 💾 Save books and members to `.txt` files on exit
- 🔄 Auto-load saved data on startup
- 🧷 Simple `|`-delimited format, easy to read and debug

### Input Validation
- 🛡️ Rejects non-numeric input without crashing
- 🛡️ Rejects empty strings
- 🛡️ Prevents duplicate IDs

---

## 🛠️ Tech Stack

| Category | Technology |
|---|---|
| Language | Java (JDK 8+) |
| UI | Console (Scanner) |
| Data Structures | `ArrayList` |
| File I/O | `BufferedWriter`, `BufferedReader`, `FileReader`, `FileWriter` |
| Exception Handling | `try-with-resources`, custom user messages |
| Version Control | Git + GitHub |
| Build Tool | None (plain `javac`) |

> **No external libraries, no Maven, no Gradle, no database.** Runs with just the JDK installed.

---

## 🚀 Setup and Run

### Prerequisites
- **Java JDK 8 or higher** installed
- Verify with:
  ```bash
  java -version
  javac -version
  ```

### Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git
cd YOUR_REPO_NAME
```

### Compile and run
```bash
javac *.java
java Main
```

### First-time run
- No configuration needed
- `books.txt` and `members.txt` are auto-created in the project folder on first save
- The program will be empty on first launch

---

## 🌍 Environment Variables

**None required.** ✅

The application uses only built-in Java APIs and relative file paths (`books.txt`, `members.txt`). No API keys, no `.env` files, no secrets.

---

## 🗄️ Database / API Notes

**No database or API is used in this project.**

Data is persisted using **plain text files**:

| File | Format | Example Line |
|---|---|---|
| `books.txt` | `id\|title\|author\|isIssued` | `1\|Clean Code\|Robert Martin\|false` |
| `members.txt` | `id\|name\|booksIssued` | `1\|Alice\|0` |

### Why text files instead of a database?
- Keeps the project within **Core Java** scope
- Demonstrates real **file I/O** concepts (read, write, parse)
- No external setup, so the project is easy to clone and run anywhere
- A database (e.g. SQLite/MySQL) would be the natural next step for a larger version

### File handling details
- **Load:** `FileManager.loadBooks()` / `loadMembers()` reads each line, splits by `|`, reconstructs objects
- **Save:** `FileManager.saveBooks()` / `saveMembers()` writes each object via its `toFileString()` method
- **Safety:** `try-with-resources` guarantees files are closed even if an exception occurs
- **First-run safe:** if files don't exist yet, `load*()` returns an empty list instead of crashing

---

## 📁 Project Structure

```
library-management-system/
├── Book.java           # Book model (id, title, author, isIssued)
├── Member.java         # Member model (id, name, booksIssued)
├── Library.java        # All business logic (add, issue, return, search)
├── FileManager.java    # File I/O — save and load to .txt
├── Main.java           # Entry point + console menu
├── .gitignore          # Ignores .class files and runtime data
└── README.md
```

### Class responsibilities

| Class | Responsibility |
|---|---|
| `Book` | Data model — fields, getters/setters, `toString()`, `toFileString()`, `fromFileString()` |
| `Member` | Data model — fields, getters/setters, `toString()`, `toFileString()`, `fromFileString()` |
| `Library` | Business logic — in-memory `ArrayList`s, issue/return rules, search, validation |
| `FileManager` | Persistence layer — save/load books and members to/from `.txt` |
| `Main` | Console menu, input reading, calls into `Library` and `FileManager` |

---

## 👥 Team Member Contributions

> **Note:** This project was completed **individually**.

| Name | Roll Number | Contributions |
|---|---|---|
| YOUR FULL NAME | YOUR ROLL NO | Full project design and development — data models (`Book`, `Member`), business logic (`Library`), persistence layer (`FileManager`), console UI (`Main`), testing, documentation |

---

## 🧪 Sample Test Flow

Try this after running the program:

1. Add book: ID `1`, title `Clean Code`, author `Robert Martin`
2. Add book: ID `2`, title `Effective Java`, author `Joshua Bloch`
3. Register member: ID `1`, name `Alice`
4. View all books → both show **Available**
5. Issue book → book ID `1`, member ID `1` → **success**
6. Issue the same book again → **rejected** ("Book is already issued")
7. View all books → book `1` now shows **Issued**
8. Return book → book ID `1`, member ID `1` → **success**
9. Exit with option `9`
10. Re-run `java Main` → **data is still there** ✅

---

## 🔮 Future Improvements

- Add a graphical UI (JavaFX or Swing)
- Add user login for librarian vs. member roles
- Add due dates and fine calculation
- Migrate to SQLite for structured storage
- Add unit tests with JUnit

---

## 📄 License

This project is for educational purposes as part of a course submission.
