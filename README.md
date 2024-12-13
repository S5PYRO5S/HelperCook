# HelperCook

To use the app, you need to install the following dependencies:
- ***Java*** (version 21 or later)
- ***Apache Maven*** (version 3.8 or later)

### Install Java
- Download Java from the [official Oracle website](https://www.oracle.com/java/technologies/javase-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/).
- Follow the installation instructions for your operating system.

### Install Maven
- Download Maven from the [official Maven website](https://maven.apache.org/download.cgi).
- Follow the [installation guide](https://maven.apache.org/install.html).

### Verify Installation
Run the following commands to verify the installations:
```bash
java -version
mvn -v
```

## To run the app, you need to do the following:

- Unzip the project folder
- Open the terminal and navigate to the project directory (where the `pom.xml` file is located)
- Run the following command: `mvn package`

### To display a single recipe, run the following
- Run the following command: `java -jar target/helperCook1.0.jar <path-to-input-file> [number of servings]`


- Example: `java -jar target/helperCook1.0.jar path/to/input.cook 2` 

### To display shopping list, run the following
- `java -jar target/helperCook1.0.jar -list <path-to-input-file-1> <path-to-input-file-2> ...]`
- Example: `java -jar target/helperCook1.0.jar -list path/to/input1.cook path/to/input2.cook`

#### If you wish to run the app from a different directory, you can specify the absolute path to the jar file and the input file(s) .