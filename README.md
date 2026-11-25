# ChatApp

A Java-based chatroom application organized into separate client and server modules. The project provides a foundation for exploring client-server communication and chat application development using Java 17 and Maven.

## Tech Stack

- Java 17
- Apache Maven
- Client-server architecture

## Project Structure

```text
ChatApp/
├── chatroom-app/          # Server module
│   └── pom.xml
├── chatroom-app-client/   # Client module
│   └── pom.xml
└── README.md
```

The two modules are built independently. Both use the Maven group ID `com.fouresia.chatroom`.

## Getting Started

### Prerequisites

- JDK 17
- Apache Maven
- Git

### 1. Clone the repository

```bash
git clone https://github.com/saltindag22/ChatApp.git
cd ChatApp
```

### 2. Build the server

```bash
cd chatroom-app
mvn clean package
```

### 3. Build the client

From the repository root:

```bash
cd chatroom-app-client
mvn clean package
```

Maven places the build outputs in each module's `target/` directory.

### 4. Run the application

Open both Maven modules in an IDE such as IntelliJ IDEA. Locate their respective Java entry points (`public static void main(String[] args)`). Start the server first, then the client. Ensure the client's connection settings match the server's configuration, if applicable.

The Maven descriptors do not specify an executable JAR entry point, so the commands above build the modules rather than launch them.

## Architecture

```text
Chatroom Client  <---->  Chatroom Server
```

The project separates the client-side application from the server-side application, allowing each component to be developed and built independently.

## Development

To compile either module without packaging it, run the following command inside that module:

```bash
mvn clean compile
```

## Future Improvements

Possible extensions include:

- User authentication and authorization
- Private messaging and multiple chatrooms
- Persistent message history
- Improved connection and error handling
- Automated integration tests

## Repository

[github.com/saltindag22/ChatApp](https://github.com/saltindag22/ChatApp)
