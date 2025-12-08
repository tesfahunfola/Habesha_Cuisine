# Habesha Cuisine

## Table of Contents
- [Description](#description)
- [Features](#features)
- [Screenshots](#screenshots)
- [Installation and Running](#installation-and-running)
  - [Prerequisites](#prerequisites)
  - [Steps to Run](#steps-to-run)
  - [Building with IntelliJ IDEA](#building-with-intellij-idea)
- [Project Structure](#project-structure)
- [UML Diagram](#uml-diagram)
- [Technologies Used](#technologies-used)
- [Testing](#testing)
  - [Prerequisites](#prerequisites-1)
  - [Running Tests](#running-tests)
  - [Test Coverage](#test-coverage)
- [Usage](#usage)
- [Contributing](#contributing)
- [Credits](#credits)
- [Contact](#contact)

## Description
Habesha Cuisine is a Java console-based application designed for an Ethiopian-inspired restaurant. It allows customers to place orders interactively through a text-based interface, including custom cuisine platters (injera-based dishes), drinks, appetizers, and signature platters. The system calculates subtotals, taxes (10%), tips, and generates receipts saved to a local folder.

This project simulates a point-of-sale system for a restaurant, focusing on Ethiopian cuisine elements like injera, traditional meats, vegetables, and sauces.

## Features
- **Custom Cuisine Platters**: Build personalized platters with choices of injera type (Teff, White, Gluten-Free, Wheat, Regular), size (Half, Full, Combo), toppings (Meats, Sides, Toppings, Sauces), and options for extra portions or fresh injera.
- **Drinks**: Select from beverages like Perrier, Tej (Honey Wine), Ethiopian Spiced Tea, and Ethiopian Beer in small, medium, or large sizes.
- **Appetizers**: Choose from options like Sambusa, Chechebsa, Veggie Rolls, Foull, and Tomato Fitfit.
- **Signature Platters**: Pre-defined combos such as Veggie Combo, Meat Combo, and Habesha Special.
- **Order Management**: Add items to an order, view summaries, calculate totals with tax and tip options (10%, 15%, 20%, custom, or none).
- **Receipt Generation**: Automatically save detailed receipts to the `receipts/` folder with timestamps.
- **Order Types**: Support for Dine-in or Takeout orders.
- **User-Friendly Interface**: Interactive menu-driven console interface with emojis and clear prompts.

## Screenshots
![Screenshot 1]![1Screenshot 2025-11-14 033736.png](Screenshot/1Screenshot%202025-11-14%20033736.png)
![Screenshot 2]![2Screenshot 2025-11-14 034022.png](Screenshot/2Screenshot%202025-11-14%20034022.png)
![Screenshot 3]![3Screenshot 2025-11-14 034122.png](Screenshot/3Screenshot%202025-11-14%20034122.png)
![Screenshot 4]![4Screenshot 2025-11-14 034231.png](Screenshot/4Screenshot%202025-11-14%20034231.png)
![Screenshot 5]![5Screenshot 2025-11-14 034336.png](Screenshot/5Screenshot%202025-11-14%20034336.png)
![Screenshot 6]![6Screenshot 2025-11-14 034432.png](Screenshot/6Screenshot%202025-11-14%20034432.png)
![Screenshot 7]![7Screenshot 2025-11-14 034530.png](Screenshot/7Screenshot%202025-11-14%20034530.png)
![Screenshot 8]![8Screenshot 2025-11-14 034626.png](Screenshot/8Screenshot%202025-11-14%20034626.png)
![Screenshot 9]![9Screenshot 2025-11-14 034712.png](Screenshot/9Screenshot%202025-11-14%20034712.png)

## Installation and Running
### Prerequisites
- Java Development Kit (JDK) 8 or higher installed on your system.
- An IDE like IntelliJ IDEA (recommended, as the project includes `.iml` file) or any Java-compatible editor.

### Steps to Run
1. Clone or download the project to your local machine.
2. Open the project in your IDE (e.g., IntelliJ IDEA).
3. Ensure the source folder is set to `src/`.
4. Compile and run the `Main.java` file located in `src/main/com/tesfahun/ui/Main.java`.
   - In IntelliJ IDEA: Right-click `Main.java` > Run 'Main.main()'.
   - Alternatively, from the command line:
     ```
     cd path/to/Habesha_Cuisine
     javac -d out src/main/com/tesfahun/**/*.java
     java -cp out main.com.tesfahun.ui.Main
     ```
5. Follow the on-screen prompts to place an order.

### Building with IntelliJ IDEA
- The project is configured with `Habesha_Cuisine.iml` for IntelliJ IDEA.
- Output directory is set to `out/`, which is excluded from version control.

## Project Structure
```
Habesha_Cuisine/
├── .gitignore                 # Git ignore file for IDEs and build outputs
├── Habesha_Cuisine.iml        # IntelliJ IDEA module file
├── README.md                  # This file
├── lib/                       # Library dependencies (e.g., JUnit jars)
│   └── junit-platform-console-standalone-1.10.1.jar
├── receipts/                  # Folder for saved order receipts (auto-generated)
│   └── receipt-YYYYMMDD-HHMMSS.txt  # Example receipt files
├── Screenshot/                # Screenshots of the application
│   ├── 1Screenshot 2025-11-14 033736.png
│   ├── 2Screenshot 2025-11-14 034022.png
│   ├── 3Screenshot 2025-11-14 034122.png
│   ├── 4Screenshot 2025-11-14 034231.png
│   ├── 5Screenshot 2025-11-14 034336.png
│   ├── 6Screenshot 2025-11-14 034432.png
│   ├── 7Screenshot 2025-11-14 034530.png
│   ├── 8Screenshot 2025-11-14 034626.png
│   └── 9Screenshot 2025-11-14 034712.png
├── UML diagram/               # UML diagram files
│   └── Habesha_Cuisine UML.drawio.png
├── src/
│   ├── main/
│   │   └── com/
│   │       └── tesfahun/
│   │           ├── models/    # Data models for menu items and orders
│   │           │   ├── Appetizers.java
│   │           │   ├── CuisinePlatter.java
│   │           │   ├── Drink.java
│   │           │   ├── Injera.java
│   │           │   ├── Order.java
│   │           │   ├── OrderItem.java
│   │           │   ├── SignaturePlatter.java
│   │           │   └── Topping.java
│   │           ├── ui/        # User interface components
│   │           │   ├── Main.java          # Entry point of the application
│   │           │   ├── MenuProduct.java   # Abstract base for menu items
│   │           │   └── UserInterface.java # Handles user interactions and menus
│   │           └── util/     # Utility classes
│   │               ├── Orderable.java     # Interface for orderable items
│   │               └── PlatterBuilder.java # Builder for custom platters
│   └── test/
│       └── java/
│           └── main/
│               └── com/
│                   └── tesfahun/
│                       ├── models/    # Unit tests for models
│                       │   ├── CuisinePlatterTest.java
│                       │   ├── DrinkTest.java
│                       │   ├── InjeraTest.java
│                       │   ├── OrderTest.java
│                       │   └── ToppingTest.java
│                       └── util/     # Unit tests for utilities
└── out/                      # Compiled classes (excluded from git)
```

## UML Diagram
![Habesha Cuisine UML Diagram]![Habesha_Cuisine UML.drawio.png](UML%20diagram/Habesha_Cuisine%20UML.drawio.png)

## Technologies Used
- **Java**: Core programming language for the application.
- **Standard Libraries**: Utilizes Java's built-in classes like `Scanner` for input, `LocalDateTime` for timestamps, and file I/O for receipts.
- **Design Patterns**: Implements Builder pattern in `PlatterBuilder` for constructing custom platters.
- **Testing**: JUnit 5 (Jupiter) for unit tests.

## Testing
Unit tests are provided for key classes using JUnit 5. To run the tests:

### Prerequisites
- Download JUnit 5 jars: `junit-jupiter-api-5.x.x.jar` and `junit-jupiter-engine-5.x.x.jar` (replace x.x.x with the latest version).
- Place the jars in a `lib/` directory in the project root.

### Running Tests
1. Compile the test classes:
   ```
   javac -cp "lib/*" -d out src/main/com/tesfahun/**/*.java src/test/java/main/com/tesfahun/models/*.java
   ```
2. Run the tests using JUnit Console Launcher:
   ```
   java -jar lib/junit-platform-console-standalone-1.10.1.jar --scan-classpath --cp out
   ```
   Or run specific test classes:
   ```
   java -jar lib/junit-platform-console-standalone-1.10.1.jar --select-class main.com.tesfahun.models.OrderTest --cp out
   ```

### Test Coverage
- **OrderTest**: Tests order management, subtotal, tax, total calculations, and tip setting.
- **DrinkTest**: Tests drink pricing based on size.
- **CuisinePlatterTest**: Tests custom cuisine platter creation and pricing.
- **InjeraTest**: Tests injera types and pricing.
- **ToppingTest**: Tests topping options and pricing.

## Usage
1. Run the application as described above.
2. Select "New Order" from the main menu.
3. Choose order type: Dine-in or Takeout.
4. Add items:
   - Custom Cuisine Platter: Select size, injera, toppings.
   - Drink: Choose size and flavor.
   - Appetizers: Pick from available options.
   - Signature Platter: Select from presets.
5. View order summary, add tip, and checkout.
6. Receipt is automatically saved.

   ## Credits


👨‍💻 **Developed by:** *Tesfahun Fola*  
🎓 *Pluralsight Java Developer Academy*  

🧑‍🏫 **Instructor:** ***Maaike*** — thank you again for the guidance, encouragement, and support throughout this capstone journey! 🙏✨  

💡 *Capstone Project 2 — Habesha Cuisine CLI Application*  

## Contributing
Feel free to fork the repository and submit pull requests for improvements or bug fixes.


## Contact
For questions or feedback, please reach out to the project maintainer.
