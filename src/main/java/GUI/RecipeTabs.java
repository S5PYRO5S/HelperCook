package GUI;

import org.helperCook.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipeTabs {
    JTabbedPane tabbedPane = new JTabbedPane();
    JSplitPane stepsPanel= new JSplitPane();
    JScrollPane recipeScrollPanel = new JScrollPane();
    JPanel shoppingListPanel= new JPanel();
    Recipe recipe;


    public RecipeTabs(File file) {

        makeRecipes(file);
        SwingUtilities.invokeLater(() -> {
            setShoppingListPanel();
            setRecipeScrollPanel(recipe);
            setStepsPanel();
            createTabs();
        });
    }
    private void makeRecipes(File file) {
        RecipeLoader recipeLoader = new RecipeLoader();
        try {
            recipe = recipeLoader.loadRecipe(file, 1);
        } catch (IOException e) {
            System.err.println("Unexpected Error:" + e.getMessage());
        }
    }

    public void createTabs(){
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.addTab("Recipe",recipeScrollPanel );
        tabbedPane.addTab("Execute Steps",stepsPanel );
        tabbedPane.addTab("ShoppingList",shoppingListPanel );

        tabbedPane.setVisible(true);
    }
    public void setRecipeScrollPanel(Recipe recipe) {
        JTextArea textArea = new JTextArea();

        // Populate the text area with recipe steps
        int stepNumber = 1;
        for (Step step : recipe.getSteps()) {
            textArea.append("Step " + stepNumber + ": " + step.getInstruction() + "\n\n");
            stepNumber++;
        }
        // Customize the appearance of the JTextArea
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Serif", Font.PLAIN, 16)); // Set a clean and readable font
        textArea.setForeground(new Color(0x333333));        // Dark gray text color
        textArea.setBackground(new Color(0xFAFAFA));        // Light gray background
        // Add the text area to the scroll pane
        recipeScrollPanel.setViewportView(textArea);
    }
    public void setShoppingListPanel(){
        shoppingListPanel.setLayout(new BoxLayout(shoppingListPanel, BoxLayout.Y_AXIS));
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        ShoppingList shoppingList = new ShoppingList(recipes);
        for (Ingredient ingredient : shoppingList.getTotalShoppingListIngredients().keySet()) {
            JCheckBox ingredientCheckBox = new JCheckBox(ingredient.getName() + " ".repeat(shoppingList.getMaxIngredientLength() - ingredient.getName().length()) + shoppingList.getTotalShoppingListIngredients().get(ingredient));
            ingredientCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
            shoppingListPanel.add(ingredientCheckBox);
        }
    }
    public void setStepsPanel(){
        RecipeStepsGUI recipeStepsGUI = new RecipeStepsGUI(recipe);
        stepsPanel = recipeStepsGUI.getSplitPane();
    }


    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

}
