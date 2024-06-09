# Lucidity Assessment

## Overview
This project simulates a delivery route optimization system, aiming to efficiently manage the process of picking up and delivering orders from various restaurants to customers. 
The system includes functionalities for order management, route planning, and handling user interactions in a structured MVC framework.

## Architecture
The application is organized into several key packages:
- **common**: Contains models like `Location` and would contain other shared utility.
- **order**: Manages all order-related functionalities including creation, storage, and retrieval through services and repositories.
- **route**: Responsible for calculating optimized delivery routes using various strategies.
- **user**: Contains entities and services related to users such as `Consumer`, `DeliveryExecutive`, and `Restaurant`.

Every package has it's functionality segregated into `models`, `repositories`, `services` and `strategies` (if any). Ideally, every package would also have its own `controller` package that would handle API requests and responses. 
It would also have `requests` and `responses` packages inside each `model` package which would contain models that encapsulate requests and responses. For the sake of time, I have skipped adding `controllers` and instead tested 
functionality using driver method in `main()` method -> `LucidityApplication.kt`. 

### Design Principles and Patterns
The project is built adhering to SOLID principles and utilizes various design patterns to ensure robustness, scalability, and maintainability:

### SOLID Principles
- **Single Responsibility Principle**: Each class and module in the system has a single responsibility and reason to change, exemplified by the separation of services, repositories, models, strategies. Each class and function also has one and only one responsibility.
- **Open/Closed Principle**: New delivery routing strategies can be added without altering existing code, using the Strategy pattern.
- **Interface Segregation Principle**: Interfaces are specific to client requirements, ensuring that implementing classes do not have to depend on interfaces they do not use. 
- **Liskov Substitution Principle**: Achieved through the consistent use of the `RouteStrategy` interface, which enables different routing algorithms to be interchanged seamlessly within the RouteService.
- **Dependency Inversion Principle**: Is demonstrated by the use of field injection in the `RouteService` class, which relies on the OrderService abstraction.

#### Modularity
The system is highly modular, with clear boundaries defined by packages and services, facilitating easy maintenance and scalability.

#### Encapsulation
Data and methods are encapsulated within classes, exposing only what is necessary externally through carefully designed interfaces, protecting internal states and behaviors from unintended interference.

#### Readability
The codebase is structured to enhance readability through consistent coding standards, meaningful variable names, and comprehensive documentation of each component and operation.

#### Extensibility
Extensibility is achieved through the use of interfaces and abstract classes, allowing new functionality to be added with minimal changes to existing code. For example, new types of users, orders, or routing algorithms can be introduced seamlessly.

### Design Patterns

- **Strategy Pattern**: The Strategy pattern is utilized to define a family of algorithms, encapsulate each one, and make them interchangeable. For example, `RouteStrategy` interface allows the use of different routing algorithms, and new strategies
can be implemented and swapped in without altering the clients that use them.

- **Singleton Pattern**: Object creation are be handled by Spring Boot itself using the `@Component` annotation, and its various subtypes such as `@Service`, `@Repository` annotation used throughout the project. Using `@Autowired` maintains Singleton Design Pattern as 
Spring injects the singleton instance of the required bean.

- **Repository Pattern**: Used for abstracting the persistence mechanisms for accessing the data layer, promoting a more decoupled architecture.

## Functionality

### RouteService

`RouteService` utilizes a routing strategy to determine the optimal delivery path for a delivery executive.

#### Responsibilities:
- **Fetch Orders**: Retrieves all current orders using the `OrderService`.
- **Order Limit Check**: Ensures that the total number of orders does not exceed a predefined maximum (`MAX_ORDERS`). If this limit is exceeded, it throws an `UnsupportedStrategyException`.
- **Route Calculation**: Delegates the route calculation to the passed routing strategy, specifically `RestaurantPrecedenceRouteStrategy` in this case, which is designed to optimize the route based on restaurant preparation times and travel distances.
- **Error Handling**: Catches and logs exceptions, returning a default `RoutePlan` with no steps and zero total time in case of errors.

#### Method: `planRoute`
- **Parameters**:
    - `executive`: The `DeliveryExecutive` whose route is being planned.
    - `strategy`: The `RestaurantPrecedenceRouteStrategy` used to calculate the optimal route.
- **Returns**: `RoutePlan` which includes detailed route steps and the total time required for the executive to complete the deliveries.

### Explanation of `findOptimalRoute`

The `findOptimalRoute` method, a part of the `RestaurantPrecedenceRouteStrategy`, is crucial for calculating the most efficient route for a delivery executive based on current orders. 
It employs a priority queue to manage orders by the shortest combined time of travel and wait due to preparation.

- **Process**:
    1. **Initialize**: Start with the executive's location and prepare a priority queue based on time efficiency.
    2. **Queue Processing**: Extract orders from the priority queue and for each:
        - Calculate and log the travel time to the restaurant and any waiting time needed if the food is not ready.
        - Calculate the travel time from the restaurant to the consumer's location, updating the route plan with each step.
    3. **Total Time Calculation**: Sum up all the individual times of the route steps to compute the total delivery time.

- **Outcome**: Produces a `RoutePlan` that details each step of the delivery route and the total time required, ensuring optimal resource utilization and timely deliveries.
