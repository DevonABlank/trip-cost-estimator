# Trip Cost Estimator

A JavaFX desktop application for estimating total trip costs based on distance, fuel prices, vehicle mileage, and lodging expenses.

## Features

- **Multi-Factor Cost Calculation**: Compute trip costs using distance, fuel efficiency, gas prices, and lodging
- **Unit Conversion Support**: Handles both miles/kilometers and gallons/liters
- **Input Validation**: Robust error handling for invalid or out-of-range inputs
- **User-Friendly Interface**: Intuitive JavaFX GUI with clear input fields and immediate results
- **Real-Time Calculations**: Instant cost updates as parameters change

## Technical Highlights

- **Immutable Utility Classes**: Implements immutable design patterns for unit conversion calculations
- **Exception Handling Framework**: Comprehensive input validation with user-friendly error messaging
- **Object-Oriented Design**: Clean separation of concerns with dedicated classes for business logic
- **MVC Architecture**: Model-View-Controller pattern for maintainable code structure

## Technologies

- Java
- JavaFX
- Maven
- Object-Oriented Programming principles

## Project Structure
```
src/main/java/umgc/assignments/project3/
├── Project3.java    # Main application with JavaFX GUI
└── TripCost.java    # Cost calculation logic and utility methods
```

## Usage

This application allows users to:
1. Enter trip distance (miles or kilometers)
2. Input vehicle fuel efficiency (MPG or L/100km)
3. Specify current fuel price
4. Add lodging and other expenses
5. View instant total cost calculation

## Calculation Components

- **Fuel Cost**: Based on distance, fuel efficiency, and current gas prices
- **Lodging Cost**: Per-night accommodation expenses
- **Total Trip Cost**: Sum of all expenses with clear breakdown

## Academic Context

Developed as Project 3 for CMSC 215 - Intermediate Programming (Java) at UMGC, demonstrating GUI development, input validation, unit conversion logic, and practical application design.

## License

MIT License
