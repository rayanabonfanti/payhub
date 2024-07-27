
# Foodify API Backend Documentation

## Overview
Foodify is an online food ordering system designed to deliver a seamless experience for users to browse, order, and receive food from various establishments. The backend is developed using Java 21 and Spring Boot 3.3.1, leveraging both Rest API and gRPC for robust and scalable communication between microservices.

## Microservices

### 1. Orchestrator Service
#### Description
Main entry point for the application, handling primary application logic and coordinating between different services.

#### Endpoints
- **Get Menu Items by Establishments**
    - **Endpoint**: `/foodify/v1/orchestrator/menu-by-establishments`
    - **Method**: `GET`
    - **Description**: Returns all menu items from all establishments.
    - **Authorization**: Requires `ROLE_ADMIN` or `ROLE_USER`.
    - **Response**:
      ```json
      [
        {
          "id": "string",
          "name": "string",
          "price": "number",
          "description": "string",
          "establishmentId": "string"
        }
      ]
      ```

### 2. IAM (Identity and Access Management) Service
#### Description
Manages user authentication.

#### Endpoints
- **Login**
    - **Endpoint**: `/foodify/v1/authorization/login`
    - **Method**: `POST`
    - **Description**: Authenticates the user and generates a JWT token.
    - **Request Body**:
      ```json
      {
        "username": "string",
        "password": "string"
      }
      ```
    - **Response**:
      ```json
      {
        "token": "string",
        "type": "Bearer"
      }
      ```

### 3. User-Customer Service
#### Description
Handles user information and customer-specific functionalities.

#### Endpoints
- **Register User Customer**
    - **Endpoint**: `/foodify/v1/user-customer/register`
    - **Method**: `POST`
    - **Description**: Registers a new user customer.
    - **Request Body**:
      ```json
      {
        "username": "string",
        "password": "string",
        "email": "string"
      }
      ```
    - **Response**:
      ```json
      {
        "message": "User Customer saved successfully!"
      }
      ```

- **Get User Customer by Login**
    - **Endpoint**: `/foodify/v1/user-customer/get-login`
    - **Method**: `GET`
    - **Description**: Retrieves user customer information by login.
    - **Request Param**: `login` (String)
    - **Response**:
      ```json
      {
        "id": "string",
        "username": "string",
        "email": "string",
        "password": "string"
      }
      ```

### 4. Establishment and Menus Service
#### Description
Manages information related to food establishments.

#### Endpoints
- **Get All Establishments**
    - **Endpoint**: `/foodify/v1/establishments-menu/all-establishments`
    - **Method**: `GET`
    - **Description**: Retrieves all establishments.
    - **Response**:
      ```json
      [
        {
          "id": "string",
          "name": "string",
          "address": "string",
          "phone": "string"
        }
      ]
      ```

- **Get Establishment by ID**
    - **Endpoint**: `/foodify/v1/establishments-menu/one-establishment`
    - **Method**: `GET`
    - **Description**: Retrieves establishment information by ID.
    - **Request Param**: `id` (String)
    - **Response**:
      ```json
      {
        "id": "string",
        "name": "string",
        "address": "string",
        "phone": "string"
      }
      ```

- **Create Establishment**
    - **Endpoint**: `/foodify/v1/establishments-menu/create-establishment`
    - **Method**: `POST`
    - **Description**: Creates a new establishment.
    - **Request Body**:
      ```json
      {
        "name": "string",
        "address": "string",
        "phone": "string"
      }
      ```
    - **Response**:
      ```json
      {
        "id": "string",
        "name": "string",
        "address": "string",
        "phone": "string"
      }
      ```

- **Update Establishment**
    - **Endpoint**: `/foodify/v1/establishments-menu/update-establishment`
    - **Method**: `PATCH`
    - **Description**: Updates establishment information.
    - **Request Param**: `id` (String)
    - **Request Body**:
      ```json
      {
        "name": "string",
        "address": "string",
        "phone": "string"
      }
      ```
    - **Response**:
      ```json
      {
        "id": "string",
        "name": "string",
        "address": "string",
        "phone": "string"
      }
      ```

- **Delete Establishment**
    - **Endpoint**: `/foodify/v1/establishments-menu/delete-establishment`
    - **Method**: `DELETE`
    - **Description**: Deletes an establishment.
    - **Request Param**: `id` (String)
    - **Response**:
      ```json
      {
        "deleted": true
      }
      ```

- **Get Menu Items**
    - **Endpoint**: `/foodify/v1/establishments-menu/all-menu`
    - **Method**: `GET`
    - **Description**: Retrieves all menu items for an establishment.
    - **Request Param**: `id` (String)
    - **Response**:
      ```json
      [
        {
          "id": "string",
          "name": "string",
          "price": "number",
          "description": "string",
          "establishmentId": "string"
        }
      ]
      ```

- **Add Menu Item**
    - **Endpoint**: `/foodify/v1/establishments-menu/create-menu`
    - **Method**: `POST`
    - **Description**: Adds a new menu item to an establishment.
    - **Request Param**: `id` (String)
    - **Request Body**:
      ```json
      {
        "name": "string",
        "price": "number",
        "description": "string"
      }
      ```
    - **Response**:
      ```json
      {
        "id": "string",
        "name": "string",
        "price": "number",
        "description": "string",
        "establishmentId": "string"
      }
      ```

- **Update Menu Item**
    - **Endpoint**: `/foodify/v1/establishments-menu/update-menu`
    - **Method**: `PATCH`
    - **Description**: Updates a menu item in an establishment.
    - **Request Param**: `id` (String), `itemId` (String)
    - **Request Body**:
      ```json
      {
        "name": "string",
        "price": "number",
        "description": "string"
      }
      ```
    - **Response**:
      ```json
      {
        "id": "string",
        "name": "string",
        "price": "number",
        "description": "string",
        "establishmentId": "string"
      }
      ```

- **Delete Menu Item**
    - **Endpoint**: `/foodify/v1/establishments-menu/delete-menu`
    - **Method**: `DELETE`
    - **Description**: Deletes a menu item from an establishment.
    - **Request Param**: `id` (String), `itemId` (String)
    - **Response**:
      ```json
      {
        "deleted": true
      }
      ```

## Technologies
- **Java 21**: Core programming language for developing the backend.
- **Spring Boot 3.3.1**: Framework for building and deploying the application.
- **Rest API**: For standard HTTP-based communication between microservices.
- **gRPC**: For high-performance, language-neutral remote procedure calls.
- **JWT**: For secure token-based authentication.
- **Database**: Typically NoSQL using MongoDB for storing persistent data.
- **Docker**: Containerization for deploying microservices.
- **Kubernetes**: Orchestration for managing containerized applications.

## Installation and Setup

1. **Clone the repository**:
   ```bash
   git clone <repository_url>
   cd foodify
   ```

2. **Build the project**:
   ```bash
   ./mvnw clean install
   ```

3. **Run the microservices**:
   Each microservice can be started individually using:
   ```bash
   ./mvnw spring-boot:run -pl <service_name>
   ```
   Replace `<service_name>` with `orchestrator`, `iam`, `user-customer`, or `establishment`.

## Sample API Requests

- **Placing an Order**:
  ```http
  POST /order
  Content-Type: application/json
  {
    "userId": "12345",
    "establishmentId": "67890",
    "items": [
      {"itemId": "111", "quantity": 2},
      {"itemId": "222", "quantity": 1}
    ]
  }
  ```

- **User Login**:
  ```http
  POST /login
  Content-Type: application/json
  {
    "username": "user@example.com",
    "password": "securepassword"
  }
  ```

- **Retrieving Establishment Details**:
  ```http
  GET /establishment/67890
  ```

## Security

- **Authentication**: Managed via the IAM service, using JWT tokens for secure and stateless authentication.
- **Authorization**: Ensured through role-based access controls (RBAC), where different roles (e.g., admin, user) have different levels of access to the endpoints.

## Error Handling

- Consistent error response structure across all services:
  ```json
  {
    "timestamp": "2024-07-27T14:34:00Z",
    "status": 404,
    "error": "Not Found",
    "message": "The resource you are looking for does not exist.",
    "path": "/order/99999"
  }
  ```

## Future Enhancements

- **Integration with Payment Gateways**: To support online payments directly through the platform.

## Contribution

To contribute to the Foodify project, please fork the repository, make your changes, and submit a pull request. Ensure all new code is well-documented and includes appropriate tests.

## Contact

For any questions or support, please contact the project maintainers at [rayanabonfanti@gmail.com](rayanabonfanti@gmail.com).
