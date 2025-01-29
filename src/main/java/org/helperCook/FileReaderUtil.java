package org.helperCook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for reading a file and when it finds empty line it saves it to a different index of an array,
 * and then it returns the array of the different instructions
 */
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
        return content.toString().trim().split(RegexConstants.BLANK_LINE_REGEX);
    }

    /**
     * method to update the quantities of an instructions given a factor
     * @param instructions the list of instructions
     * @param factor the factor to multiply the quantities
     */
    public static void updateInstructionQuantities(String[] instructions, int factor)
    {
        Pattern ingredientPattern = Pattern.compile(RegexConstants.INGREDIENT_REGEX);

        for (int i = 0; i < instructions.length; i++) {
            Matcher matcher = ingredientPattern.matcher(instructions[i]);
            StringBuilder updatedInstruction = new StringBuilder();

            while (matcher.find()) {
                String ingredientName = matcher.group(1);
                String quantityStr = matcher.group(2);
                String unitName = matcher.group(3) != null ? matcher.group(3) : "";

                //check if the quantity is missing
                if (quantityStr == null) quantityStr = "1"; //default quantity to 1 if {} is missing

                //parse the quantity and calculate the new value
                double originalQuantity = Double.parseDouble(quantityStr);
                double newQuantity = originalQuantity * factor;

                //format the replacement string based on the presence of a unit
                String replacement;
                if (unitName.isEmpty())
                {
                    replacement = String.format("@%s{%d}", ingredientName, (int) newQuantity); //no unit
                }
                else
                {
                    replacement = String.format("@%s{%d%%%s}", ingredientName, (int) newQuantity, unitName); //with unit
                }
                matcher.appendReplacement(updatedInstruction, replacement);
            }
            matcher.appendTail(updatedInstruction);
            instructions[i] = updatedInstruction.toString(); //update the instruction in the array
        }
    }
}