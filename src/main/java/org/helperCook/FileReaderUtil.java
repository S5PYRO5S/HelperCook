package org.helperCook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReaderUtil
{
    //returns an array of strings that represent each step instruction
    public static String[] readFileContent(File file) throws IOException
    {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8))
        {
            String line;
            while ((line = reader.readLine()) != null) {content.append(line).append(System.lineSeparator());}
        }
        return content.toString().trim().split("\\n\\s+\\n");
    }

    public static void updateInstructionQuantities(String[] instructions, int factor)
    {
        Pattern ingredientPattern = Pattern.compile(RegexConstants.INGREDIENT_REGEX);

        // Process each instruction and update quantities
        for (int i = 0; i < instructions.length; i++) {
            Matcher matcher = ingredientPattern.matcher(instructions[i]);
            StringBuilder updatedInstruction = new StringBuilder();

            // Iterate through all matches in the instruction
            while (matcher.find())
            {
                String ingredientName = matcher.group(1);
                String quantityStr = matcher.group(2);
                String unitName = matcher.group(3) != null ? matcher.group(3) : "";

                if (quantityStr != null)
                {
                    // Parse and multiply the quantity
                    double originalQuantity = Double.parseDouble(quantityStr);
                    double newQuantity = originalQuantity * factor;

                    // If there's a unit, include it in the replacement, otherwise omit it
                    String replacement;
                    if (unitName.isEmpty()) {
                        replacement = String.format("@%s{%d}", ingredientName, (int) newQuantity); // No unit
                    } else {
                        replacement = String.format("@%s{%d%%%s}", ingredientName, (int) newQuantity, unitName); // With unit
                    }
                    matcher.appendReplacement(updatedInstruction, replacement);
                }
                else
                {
                    // If no quantity is specified, keep the ingredient as is
                    matcher.appendReplacement(updatedInstruction, matcher.group());
                }
            }

            matcher.appendTail(updatedInstruction);
            instructions[i] = updatedInstruction.toString(); // Update the instruction in the array
        }
    }
}