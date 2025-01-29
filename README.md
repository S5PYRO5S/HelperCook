# HelperCook

### Simple recipe viewing java application that can be used both through the command line and the GUI:

#### For the command line the following features are available:

- reads a recipe file and displays the recipe with the ingredients adjusted for the number of servings specified.
- displays a shopping list for multiple recipes.

#### For the GUI the following features are available:

- files can be selected through the GUI and then the user can view the entire recipe, execute the recipe step by step (
  with a timer provided for the steps that have duration ) or view the shopping list.
- all of the above features can be adjusted for the number of servings specified through the GUI.
- reads multiple recipe files from the command line at the start and opens the window with those files pre-selected.

The application is the project assignment for the course "Object-Oriented Programming 2" at the Harokopio University of
Athens.

### Team Members:

| Ονοματεπώνυμο         | Email            |
|-----------------------|------------------|
| Αρβανίτης Σπυρίδων    | it2023003@hua.gr |
| Πολίτης Αλέξιος       | it2023100@hua.gr |
| Ξηρομερίτης Δημήτριος | it2023052@hua.gr |

To use the app, you need to install the following dependencies:

- ***Java***
- ***Apache Maven***

## When running the app for the first time

### To create the jar file:

1. Unzip the project folder
2. Open the terminal and navigate to the project directory (where the `pom.xml` file is located)
3. Run the following command: `mvn package`

#### The jar file will be created in the `target` directory of the project folder. After the jar file is created, you can run the app using the following
commands.

## To use the app through the command line:

### To display a single recipe, run the following command:

- `java -jar target/helperCook2.0.jar <path-to-input-file> [number of servings] -t`


- **Example:** `java -jar target/helperCook2.0.jar pancakes.cook 2 -t`

### To display shopping list, run the following:

- `java -jar target/helperCook2.0.jar -list <path-to-input-file-1> <path-to-input-file-2> ... -t`


- **Example**: `java -jar target/helperCook2.0.jar -list pancakes.cook brownies.cook -t`

Shopping list ingredients quantities are the sum of the quantities of the same ingredient in all the recipes and cannot
be adjusted for different number of servings ( for cli ).

The flag `-list` must be placed **RIGHT AFTER the jar file name** and the flag `-t` must be placed **ONLY at the end of
the command**.

#### If you wish to run the app from a different directory, you can specify the absolute path to the jar file and the input file(s) .

## To use the app through the GUI:

### To start the gui with no files selected:

- `java -jar target/helperCook2.0.jar `

### To start the gui with pre-selected files:

- `java -jar target/helperCook2.0.jar <path-to-input-file> <path-to-input-file-1> <path-to-input-file-2> ...`
- **Example**: `java -jar target/helperCook2.0.jar -list pancakes.cook crepes.cook `

The gui does not require any special flags to be placed and to adjust the number of servings or view the shopping list
for the recipes you can do so only through the window .
