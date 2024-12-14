# HelperCook

### Simple command-line java application that does the following:
- reads a recipe file and displays the recipe with the ingredients adjusted for the number of servings specified. 
- displays a shopping list for multiple recipes.

The application is the project assignment for the course "Object-Oriented Programming 2" at the Harokoopio University of Athens.
### Team Members:
| Ονοματεπώνυμο         | Email                    |
|-----------------------|--------------------------|
| Αρβανίτης Σπυρίδων    | it2023003@hua.gr         |
| Πολίτης Αλέξιος       | it2023100@hua.gr         |
| Ξηρομερίτης Δημήτριος | it2023052@hua.gr         |

To use the app, you need to install the following dependencies:
- ***Java*** (version 21 or later)
- ***Apache Maven*** (version 3.8 or later)

## When running the app for the first time
### To create the jar file:
1. Unzip the project folder 
2. Open the terminal and navigate to the project directory (where the `pom.xml` file is located)
3. Run the following command: `mvn package`

#### The jar file will be created in the `target` directory of the project folder. After the jar file is created, you can run the app using the following commands.

## To display a single recipe, run the following command:
- `java -jar target/helperCook1.0.jar <path-to-input-file> [number of servings]`



- **Example:** `java -jar target/helperCook1.0.jar pancakes.cook 2` 

## To display shopping list, run the following:
- `java -jar target/helperCook1.0.jar -list <path-to-input-file-1> <path-to-input-file-2> ...]`


- **Example**: `java -jar target/helperCook1.0.jar -list pancakes.cook brownies.cook`

Shopping list ingredients quantities are the sum of the quantities of the same ingredient in all the recipes and cannot be adjusted for different number of servings.

#### If you wish to run the app from a different directory, you can specify the absolute path to the jar file and the input file(s) .


