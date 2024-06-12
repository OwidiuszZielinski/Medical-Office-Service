### Application Configuration

### Key Features of the Application

- **Queue Management:** Add patients to the queue, assign them to specific offices and doctors at designated times. New doctors can also be added.
- **Web Service:** Accessible functionalities across platforms as a web service.
- **Technology Stack:** Implemented with Spring Boot and managed with Maven.
- **Database:** Data stored in a separate PostgreSQL database.
- **Docker Integration:** Ensures environment isolation and consistency using Docker.
- **API Visualization:** Utilizes Swagger UI for easy API visualization and usage.
- **Logging:** Uses Log4j2 for event logging in both console and file.
- **Unit Testing:** Important methods are unit tested to ensure correct functionality.

**The application has been tested using the following software:**
- IntelliJ IDE
- Docker
**Launching the Application**

To launch the application, follow these steps in the CMD command line:

1. Ensure you have the latest version of Java (21) installed:
    - Enter the command:
      ```bash
      java --version
      ```
    - If Java is installed, proceed to step 3.

2. Install JDK 21 if not already installed:
    - Download the JDK package:
      ```bash
      wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.deb
      ```
    - Install the package:
      ```bash
      sudo apt install ./jdk-21_linux-x64_bin.deb
      ```

3. Navigate to the application folder and build it using Maven:
    - Run:
      ```bash
      mvn clean install
      ```

4. Navigate to the directory at `src/main/resources/docker`:
    - Check if Docker is installed:
      ```bash
      docker --version
      ```
    - If Docker is not installed, install it:
      ```bash
      sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
      ```
    - For Windows, Docker Desktop is required.

5. Set up the PostgreSQL database using Docker Compose:
    - Build the Docker containers:
      ```bash
      docker-compose build
      ```
    - Start the Docker containers:
      ```bash
      docker-compose up
      ```

6. Everything is ready. You can now run the application:
    - Run:
      ```bash
      ./mvnw spring-boot:run
      ```

      ## Endpoints

      <h1 align="center">
  <img src="https://drive.google.com/uc?id=1_EMzUJu2YNLQW71l9Qpa1zcAm0MC6D3f&export=download" width="1000"/>
  <br>
</h1> 

## Waiting room

<h1 align="center">
  <img src="https://drive.google.com/uc?id=1G_ZeQNBIcMsOiaUeYps_vlNPBtntbjqv&export=download" width="1000"/>
  <br>
</h1> 
