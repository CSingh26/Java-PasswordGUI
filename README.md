
# Password Management System

## Introduction
This Password Management System is a Java-based application designed to securely store and manage passwords. It features a graphical user interface (GUI) built with Swing, allowing users to save new passwords, retrieve existing ones, and navigate through multiple entries for a given service. The application uses encryption to ensure that all passwords are stored securely.

## Getting Started

### Prerequisites
- Java JDK 11 or later
- MySQL Database

### Setup
1. **Database Configuration**: Set up your MySQL database using the provided schema in `database_schema.sql`.
2. **Key and IV Generation**: Before running the application, you must generate a key and IV for encryption. Use the `KeyIVGenerator` utility provided with the project. Follow the steps below to generate and save your key and IV.

### Generating Key and IV
The application uses AES encryption for securing passwords. You must generate a unique key and IV before the first run:

1. Navigate to the directory containing `KeyIVGenerator.java`.
2. Compile the utility using the Java compiler:
   ```shell
   javac KeyIVGenerator.java
   ```
3. Run the utility to generate a key and IV:
   ```shell
   java KeyIVGenerator
   ```
4. The utility will output a key and IV, and save them to files named `encryptionKey.txt` and `encryptionIv.txt`, respectively. Ensure these files are located in a secure and accessible location for the application.

### Running the Application
After setting up the database and generating the key and IV, you can run the application:

1. Compile the application source code. If using an IDE, import the project and build it. For command-line compilation, navigate to the source directory and run:
   ```shell
   javac main/java/gui/App.java
   ```
2. Run the compiled `App` class to start the application:
   ```shell
   java main.java.gui.App
   ```
3. The main window of the Password Management System will open, allowing you to navigate through its features.

## Features
- **Save Password**: Securely save passwords with details like service name, email, and username.
- **Retrieve Password**: Retrieve and display passwords based on the service name.
- **Password Generation**: Generate strong passwords using the built-in password generator.

## Security
This application encrypts passwords using AES with a secure key and IV. It's crucial to keep the key and IV confidential and to use a secure MySQL database configuration.

## Contributing
Feel free to fork this repository and submit pull requests to contribute to the project. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
