package GUI;

import org.helperCook.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleRecipeTabs extends JTabbedPane {
    JSplitPane stepsPanel = new JSplitPane();
    JScrollPane recipeScrollPanel = new JScrollPane();
    JPanel shoppingListPanel = new JPanel();
    File recipeFile;
    Recipe recipe;


    public SingleRecipeTabs(File file, int servings) {
        recipeFile = file;
        makeRecipe(file, servings);
        SwingUtilities.invokeLater(() -> {
            setShoppingListPanel(servings);
            setRecipeScrollPanel();
            setStepsPanel();
            createTabs();
        });
    }
    public void changeFactor(int factor) {
        recipe =  makeRecipe(recipeFile, factor);
        SwingUtilities.invokeLater(() -> {
            updatePanels(factor);
            repaint();
            revalidate();
        });

    }

    private Recipe makeRecipe(File file, int servings) {
        RecipeLoader recipeLoader = new RecipeLoader();
        try {
            return recipe = recipeLoader.loadRecipe(file, servings);
        } catch (IOException e) {
            System.err.println("Unexpected Error:" + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    private void createTabs() {
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        addTab("Recipe", recipeScrollPanel);
        addTab("Execute Steps", stepsPanel);
        addTab("ShoppingList", shoppingListPanel);
        setVisible(true);
    }
    private void updatePanels(int factor) {

        setRecipeScrollPanel();
        setShoppingListPanel(factor);
        setStepsPanel();
    }
    private void setRecipeScrollPanel() {
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
        // Add the text area to the scroll pane
        recipeScrollPanel.setViewportView(textArea);
    }

    private void setShoppingListPanel(int factor) {
        // Clear the shopping list panel before adding new items (when changing the factor)
        shoppingListPanel.removeAll();
        shoppingListPanel.setLayout(new BoxLayout(shoppingListPanel, BoxLayout.Y_AXIS));
        shoppingListPanel.add( new JLabel("Shopping List for " + factor + " serving" + (factor > 1 ? "s" : "") + ":"));
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        ShoppingList shoppingList = new ShoppingList(recipes);
        for (Ingredient ingredient : shoppingList.getTotalShoppingListIngredients().keySet()) {
            JCheckBox ingredientCheckBox = new JCheckBox(ingredient.getName() + " ".repeat(shoppingList.getMaxIngredientLength() - ingredient.getName().length()) + shoppingList.getTotalShoppingListIngredients().get(ingredient));
            ingredientCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
            shoppingListPanel.add(ingredientCheckBox);
        }
    }

    private void setStepsPanel() {

        JSplitPane newStepsPanel = new RecipeStepsGUI(recipe);

        //update the steps panel (when changing the factor)
        // Find the index of the "Steps" tab
        int tabIndex = indexOfComponent(stepsPanel);
        if (tabIndex != -1) {
            // Replace the old panel with the new one in the tabbed pane
            setComponentAt(tabIndex, newStepsPanel);
        }
        // Update the `stepsPanel` reference
        stepsPanel = newStepsPanel;
    }

}
