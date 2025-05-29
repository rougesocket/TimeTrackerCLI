# ⏱️ TimeTrackerCLI

**TimeTrackerCLI** is a lightweight, Java-based command-line tool for tracking time spent on tasks and projects. Organize tasks into categories, generate detailed reports, and persist your data using CSV files — all through a simple terminal interface.

---

## 🚀 Features

- ✅ Start and stop timers for tasks
- 🗂️ Categorize tasks for structured time tracking
- 📊 Generate time usage reports by task or category
- 📁 Export logs as CSV files
- 💾 Persistent storage using CSV — no database required

---

## 📦 Installation

### Prerequisites

- Java JDK 8 or higher
- Maven 3+

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/rougesocket/TimeTrackerCLI.git
   cd TimeTrackerCLI
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the CLI**
   ```bash
   java -jar target/TimeTrackerCLI.jar
   ```

---

## 🛠️ Usage

### Start tracking time
```bash
timetracker start <task-name>
timetracker start <task-name> <category-name>
```

### Stop tracking time
```bash
timetracker stop <task-name>
```

### View reports
- Task report:
  ```bash
  timetracker report task
  ```
- Category report:
  ```bash
  timetracker report category
  ```

### Export data
```bash
timetracker export <output-file-path.csv>
```

📌 **Note**: Data is stored in a local CSV file. Make sure you have write permissions in the target directory.

---

## 💾 Data Storage

TimeTrackerCLI uses a simple CSV file as its storage backend. Each log entry is stored with:

- Task Name
- Category (if provided)
- Start Time
- Stop Time
- Duration (calculated)

The file is read and written automatically by the application — no external database or configuration needed.

---