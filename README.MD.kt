# Investment Tracker

Welcome to the **Investment Tracker** project! This Android application helps users track and manage their investments.

## Project Structure

The project is organized into several packages to separate concerns and follow best practices. Here’s a breakdown of the package structure and their responsibilities:

### App

- **MainActivity**: The main entry point of the application. It sets up the initial configuration and displays the primary user interface.

### Core

- **common**
- **models**: Contains data models shared across different features of the app.
- **UiState**: Defines various states that the user interface can be in, helping manage UI changes reactively.

### Feature

#### Common

- **common**: Contains shared components and utilities used across different features.

#### Details

- **data**
- **Repo**: Defines the interface for data operations related to investment details.
- **RepoImpl**: Implements the `Repo` interface, handling data operations and providing the data source (mock data for now).

- **presentation**
- **ui**: Contains all the screens related to investment details.
- **ViewModel**: Manages UI-related data for the investment details screens and handles business logic.
- **PageEvent**: Defines events that occur within the investment details feature.

#### Home

- **data**
- **Repo**: Defines the interface for data operations related to the home screen.
- **RepoImpl**: Implements the `Repo` interface, handling data operations for the home screen (mock data for now).

- **presentation**
- **ui**: Contains all the screens related to the home feature.
- **ViewModel**: Manages UI-related data for the home screens and handles business logic.
- **PageEvent**: Defines events that occur within the home feature.

## Key Features

1. **Unidirectional Workflow**: The app uses a unidirectional data flow architecture with Jetpack Compose, Flow, Coroutines, and Dagger Hilt for dependency injection. This architecture simplifies state management and ensures a predictable flow of data.

2. **Architecture Layers**:
- **UI Layer**: Built with Jetpack Compose, this layer contains all the user interfaces.
- **ViewModel Layer**: Manages UI-related data and business logic. It interacts with the repository to fetch or update data.
- **Repository Layer**: Acts as the data source, which is currently a mock implementation. It abstracts the data operations and provides data to the ViewModel.

3. **Design Principles**:
- **Single Responsibility Principle**: Each class and module has a single responsibility, ensuring that the codebase remains clean and manageable.
- **Interface Segregation Principle**: Interfaces are designed to be specific to the needs of the implementers, avoiding large, monolithic interfaces.
- **Dependency Inversion Principle**: High-level modules are not dependent on low-level modules. Both depend on abstractions, which are managed by Dagger Hilt.

## Setup and Installation

To get started with the Investment Tracker app, follow these steps:

1. **Clone the Repository**:
```bash
git clone https://github.com/yourusername/investment-tracker.git
