# TODO List (Spring Boot and Vue.js)

This repository contains a web application built using Vue.js for the frontend and Spring Boot for the backend. The application is designed for managing todo items and includes features for adding, updating, and viewing todo items.

## Application functions
The web application provides several key functions to manage and display todo items efficiently. Here's an overview of its main functions:
- View todo list
- Add new todo item
- Update existing todo item
- Responsive UI design

## Backend Functions
The backend provides a RESTful API to handle todo data management using Spring Boot. Key functions include:
- GET /todo: Retrieve the list of todos.
- POST /todo: Add a new todo item.
- PUT /todo/{id}: Update an existing todo item by ID.
- DELETE /todo/{id}: Delete a todo item by ID.

## Technologies Used
### Frontend
- Vue.js: Framework for building user interfaces with reactive components.
- Vite: Build tool for fast development and bundling.
- Vue Router: Router for managing navigation and routing within the application.
- Bootstrap: Front-end framework for responsive and mobile-first web development.
- CSS: Custom styling for application layout and design.
### Backend
- Spring Boot: Framework for building production-ready applications quickly and efficiently.
- Spring Web: Provides support for creating RESTful web services.
- Spring Data JPA: Simplifies database access using Java Persistence API (JPA).
- MySQL: Database management system used for storing todo items.
- Maven: Build tool for managing dependencies and building the project.

## Running the Application
### Backend
Configure your Spring Boot application to connect to the MySQL database by updating the application.properties file. Sql script is part of the directory structure. To run the backend application locally:
- Make sure you have MySQL installed and running.
- Update application.properties with your MySQL database URL, username, and password.
- Use Maven or your preferred IDE to build and run the project.
- The backend server will be running on http://localhost:8080.
### Frontend
To run the frontend application locally: 
