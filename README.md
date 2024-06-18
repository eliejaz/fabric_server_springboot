# Fabric Server Spring Boot Application

## Overview

This project is a Spring Boot application designed to interact with Hyperledger Fabric in the context of medical facilities. It provides RESTful APIs to facilitate blockchain operations. The server is secured via JWT authentication and role-based APIs.
## Features

- **Spring Boot Framework**: Utilizes Spring Boot for easy setup and rapid development.
- **Hyperledger Fabric Integration**: Connects with Hyperledger Fabric to perform blockchain operations.
- **RESTful APIs**: Exposes endpoints to interact with the blockchain network. Available APIs can be seen in the swagger interface.

## Prerequisites

- Java 11 or higher
- Maven
## Setup

1. **Clone the repository**
   ```sh
   git clone https://github.com/eliejaz/fabric_server_springboot.git
   cd fabric_server_springboot
   ```

2. **Install dependencies**
   ```sh
   mvn clean install
   ```

3. **Configure Hyperledger Fabric**
   - Ensure Hyperledger Fabric is running.
   - Update the `application.properties` file with your Fabric network details.

4. **Run the application**
   ```sh
   mvn spring-boot:run
   ```

A swagger interface is available to check all the available APIs.

## Contact

For questions or issues, please open an issue in the repository.
