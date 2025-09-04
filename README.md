
# 📊 Expense Manager App

An **Android application** built with **Kotlin** and **Room Database**
to help users track their daily expenses and manage budgets effectively.

------------------------------------------------------------------------

## 🚀 Features

-   ➕ Add, edit, and delete expenses\
-   📂 Categorize expenses (e.g., Food, Travel, Shopping)\
-   📅 View expenses by date, month, or category\
-   📊 Dashboard with expense summary\
-   💾 Offline-first with **Room Database**\
-   🔍 Search and filter expenses\
-   🎨 Material UI design with RecyclerView

------------------------------------------------------------------------

## 🛠 Tech Stack

-   **Language**: Kotlin\
-   **Architecture**: MVVM (Model-View-ViewModel)\
-   **Database**: Room (SQLite)\
-   **UI Components**: RecyclerView, Material Components\
-   **Coroutines + LiveData** for async & reactive updates

------------------------------------------------------------------------

## 📂 Project Structure

``` plaintext
ExpenseManager/
│── app/
│   ├── data/
│   │   ├── dao/               # Room DAO interfaces
│   │   ├── entities/          # Database entities
│   │   ├── repository/        # Repository layer
│   ├── ui/
│   │   ├── activities/        # Activities
│   │   ├── fragments/         # Fragments
│   │   ├── viewmodel/         # ViewModels
│   ├── utils/                 # Helper classes
│   ├── res/                   # Layouts, drawables, values
│── build.gradle
│── settings.gradle
│── README.md
```

------------------------------------------------------------------------

## 📸 Screenshots

  -----------------------------------------------------------------------------------------------------------------------
Dashboard                                 Add Expense                           Expense List
  ----------------------------------------- ------------------------------------- ---------------------------------------
![Dashboard](screenshots/dashboard.png)   ![Add](screenshots/add_expense.png)   ![List](screenshots/expense_list.png)

  -----------------------------------------------------------------------------------------------------------------------

*(replace with your actual screenshots)*

------------------------------------------------------------------------

## ⚡ Installation

1.  Clone the repository

    ``` bash
    git clone https://github.com/your-username/expense-manager.git
    ```

2.  Open in **Android Studio**\

3.  Sync Gradle and run on an emulator/device

------------------------------------------------------------------------

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!\
Feel free to fork this repo and submit a Pull Request.

------------------------------------------------------------------------

## 📜 License

This project is licensed under the **MIT License** -- see the
[LICENSE](LICENSE) file for details.
