# Investment Tracker

Welcome to the **Investment Tracker** project! This Android application helps users track and manage their investments efficiently.

## Project Structure

The project is organized into several packages to promote clean code and adhere to best practices. Here’s a breakdown of the package structure and their responsibilities:

### App

- **MainActivity**: The entry point of the application. It sets up the initial configuration and displays the primary user interface.

### Core

- **common**
  - **models**: Contains data models shared across different features of the app.
  - **UiState**: Manages the state of the user interface (UI) using a sealed class to represent:
    - **Data**: When the app has successfully loaded and is displaying information.
    - **Error**: When an error has occurred and an error message needs to be displayed.
    - **Loading**: When data is still being fetched and a loading indicator is shown.

### Feature

#### Common

- **common**: Contains shared components and utilities used across different features.

#### Details

- **data**
  - **Repo**: Interface for data operations related to investment details.
  - **RepoImpl**: Implements the `Repo` interface, handling data operations with a mock data source.

- **presentation**
  - **ui**: Contains all screens related to investment details.
  - **ViewModel**: Manages UI-related data and business logic for the investment details screens.
  - **PageEvent**: Defines events within the investment details feature, such as `FetchDetailsEvent` to fetch details of a selected investment.

#### Home

- **data**
  - **Repo**: Interface for data operations related to the home screen.
  - **RepoImpl**: Implements the `Repo` interface, handling data operations with a mock data source.

- **presentation**
  - **ui**: Contains all screens related to the home feature.
  - **ViewModel**: Manages UI-related data and business logic for the home screens.
  - **PageEvent**: Defines events within the home feature.

## Key Features

1. **Unidirectional Workflow**:
   - The app uses a unidirectional data flow architecture with Jetpack Compose, Flow, Coroutines, and Dagger Hilt for dependency injection. This architecture uses data flow and state management.

2. **Architecture Layers**:
   - **UI Layer**: Built with Jetpack Compose, this layer contains all user interfaces.
   - **ViewModel Layer**: Manages UI-related data and business logic, interacting with the repository to fetch or update data.
   - **Repository Layer**: Acts as the data source, currently using a mock implementation to provide data to the ViewModel.

3. **Design Principles**:
   - **Single Responsibility Principle**: Each class and module has a single responsibility, ensuring a clean and manageable codebase (e.g., UI, ViewModel, Repo, Data Source).
   - **Interface Segregation Principle**: Interfaces are tailored to the needs of their implementers, avoiding large, monolithic interfaces (e.g., HomeRepo, DetailRepo).
   - **Dependency Inversion Principle**: High-level modules depend on abstractions rather than concrete implementations, managed by Dagger Hilt (e.g., HomeRepo, DetailsRepo, InvestmentApi).

## Setup and Installation

To get started with the Investment Tracker app, follow these steps:

### Prerequisites

1. **Android Studio**:
   - Ensure you have the latest version of Android Studio installed.
   - This project has been tested with **Android Studio Koala | 2024.1.1 Patch 1**.

2. **Gradle**:
   - Make sure you have Gradle version **8.7-bin** installed. The project is compatible with this Gradle version.

