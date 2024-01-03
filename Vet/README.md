# Veterinary Application For Database

    This application facilitates the addition and organization of information about veterinary patients, their owners, and veterinarians for your veterinary practice.

## Features

### Animals Database
-Capture and organize animal records.
-Associate animals with their owners.
-Include details such as the animal's name, species, gender, date of birth, and color.

### Animal Vaccines Database
-Manages vaccination data through animal IDs.
-Includes information about when the vaccination was administered.
-Captures the duration or validity period of the vaccination.

## Customer Database
-Allows for the addition, editing, and observation of customer information, including name, phone, address, state, and email.
-Connected to the animal table.

### Vaccines Database
-Capture and organize vaccine records.
-It carries the name and code of the vaccine based on the vaccine ID.

### Appointment Database
-Manage appointments and relationships between doctors and animals.
-Allows for easy addition and removal of appointments.

### Doctor Database
-It holds information including the name, email, state, and address for doctors, providing the capability to add and edit these details.

### Doctor Availability Database
-Associated with doctors' IDs.
-Displays the available date range for scheduling appointments.
-Offers the flexibility to be edited.

## Technologies Used
- Language: Java
- Framework: Spring Boot Rest API
- Database: PostgreSQL
- Tools: Lombok, Hibernate
## Database Structure
For more information about the database schema and detailed structure of our project, you can take a look at the (database_structure.md) file.

## Endpoints
To check endpoints of the project, you can take a look at the (endpoints.md) file.
## Installation

1. Clone the project.
2. Set up PostgreSQL database and configure connection details.
3. Install the project in your local environment and install the necessary dependencies.
4. Run the application.


