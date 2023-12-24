# Employee Collaboration Tracker

## Project Overview

This Spring Boot application identifies pairs of employees who have worked together on common projects for the longest period. It calculates the duration of their collaboration on each project using data provided via a CSV file. The application follows the MVC pattern, uses Hibernate for ORM, and adheres to RESTful principles.

## Key Features

- **Data Parsing**: Loads employee assignment data from a CSV file, supporting multiple date formats.
- **Duration Calculation**: Calculates the number of days employees have worked together on a project.
- **CRUD Operations**: Supports CRUD operations for employees, projects, and assignments via HTTP requests.
- **Report Generation**: Generates reports of employee collaborations with duration.
- **Exception Handling**: Includes global exception handling for robust error management.

## Technologies Used

- Spring Boot
- Hibernate
- JPA Repositories
- Validation Annotations
- Lombok
- ModelMapper
- Maven
- Java

## CSV File and Data Loading

The application automatically loads assignment data from a CSV file at startup.
The CSV file should be placed in the `src/main/resources/inputData` directory.
It supports multiple date formats for the `DateFrom` and `DateTo` fields.
The format for each record in the CSV file is as follows:
employeeEmail, Project Title, DateFrom, DateTo

## Approach and Algorithm

Upon startup, the application performs the following operations:

1. **Loading Assignments**: Reads assignment data from a CSV file located in `src/main/resources/inputData`.
2. **Employee and Project Verification**: Checks if each employee and project from the assignments exist in the database. If not, it adds them.

## To add or modify employees, projects, or assignments, HTTP requests should be sent to the respective API endpoints.
## API Endpoints

Detailed API endpoint descriptions for managing employees, projects, and assignments.
### EmployeeController

- **Add Employee**: `POST /employee/add`
- **Get All Employees**: `GET /employee/findAll`
- **Get Employee by ID**: `GET /employee/get/{id}`
- **Update Employee**: `PUT /employee/update/{id}`
- **Delete Employee**: `DELETE /employee/delete/{id}`

### ProjectAssignmentController

- **Add Assignment**: `POST /assignment/add`
- **Get All Assignments**: `GET /assignment/findAll`
- **Get Assignment by ID**: `GET /assignment/get/{id}`
- **Update Assignment**: `PUT /assignment/update/{id}`
- **Delete Assignment**: `DELETE /assignment/delete/{id}`
- **Get Overlap Report**: `GET /assignment/report`

### ProjectController

- **Add Project**: `POST /project/add`
- **Get All Projects**: `GET /project/findAll`
- **Get Project by ID**: `GET /project/get/{id}`
- **Update Project**: `PUT /project/update/{id}`
- **Delete Project**: `DELETE /project/delete/{id}`

### ReportDTO

- **Fields**: `employeeId1`, `employeeId2`, `projectId`, `togetherWorkingDaysCount`
- **Description**: Represents data about pairs of employees who have worked together, including the project and duration of collaboration.

## Getting Started

### Prerequisites

- Java JDK 11 or later
- Maven
- MySQL database

### Setup and Installation

1. Clone the repository:
2. Navigate to the project directory:
3. Configure `application.properties` with your database settings.
4. Build the project using Maven:
5. Run the application:

### Using the Application

Place your CSV file in `src/main/resources/inputData` and copy-paste the path to your file in DataLoaderService.class.
The application will automatically load and parse the data on startup.
The REST API endpoints are available for managing employees, projects, assignments, and generating reports.


## Contributions

Feel free to fork this project, submit pull requests, or open issues for bugs or feature suggestions.

## **Author**

Mohamed Karaahmed



