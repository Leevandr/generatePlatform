package ru.levandr.generateplarform.entity.factory;


import org.bukkit.inventory.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    private static final Logger logger = LoggerFactory.getLogger(Factory.class);

    private ItemStack inputSlot;
    private ItemStack outputSlot;
    private List<FactoryRecipe> recipes;
    private FactoryInventory inventory;

    public Factory() {
        this.inputSlot = null;
        this.outputSlot = null;

        this.inventory = new FactoryInventory(this);

        this.recipes = new ArrayList<>();
    }

    public ItemStack getInputSlot() {
        return inputSlot;
    }


    public void setInputSlot(ItemStack inputSlot) {
        // Проверяем, является ли предмет в слоте ввода допустимым входным предметом
        if (getRecipeForInput(inputSlot) != null) {
            System.out.println("Valid input item: " + inputSlot.getType());
            // Создаем новый экземпляр ItemStack с теми же свойствами, что и inputSlot
            ItemStack clonedInputSlot = new ItemStack(inputSlot.getType(), inputSlot.getAmount());
            this.inputSlot = clonedInputSlot;
            this.inventory.getInventory().setItem(0, clonedInputSlot);
            for (FactoryRecipe recipe : recipes) {
                if (clonedInputSlot.isSimilar(recipe.getInput())) {
                    setOutputSlot(recipe.getOutput().clone());
                    return;
                }
            }
            setOutputSlot(null);
        } else {
            System.out.println("Invalid input item or no input item");
        }
    }


    public ItemStack getOutputSlot() {
        return outputSlot;
    }

    public void setOutputSlot(ItemStack outputSlot) {
        // Проверяем, достаточно ли места в слоте вывода
        if (outputSlot == null || outputSlot.getAmount() < outputSlot.getMaxStackSize()) {
            System.out.println("Output slot is not full or no output item");
            this.outputSlot = outputSlot;
            this.inventory.getInventory().setItem(8, outputSlot);
        } else {
            System.out.println("Output slot is full");
        }
    }

    public void addRecipe(FactoryRecipe recipe) {
        this.recipes.add(recipe);
    }

    public FactoryRecipe getRecipeForInput(ItemStack input) {
        for (FactoryRecipe recipe : recipes) {
            if (input.isSimilar(recipe.getInput())) {
                System.out.println("getRecipeForInput");
                return recipe;
            }
        }
        System.out.println("getRecipeForInput = null");
        return null;
    }


    public void process() {
        logger.info("Processing factory...");

        ItemStack inputItem = getInputSlot();
        if (inputItem != null && inputItem.getAmount() > 0) {
            logger.info("Input item: {}", inputItem.getType());

            FactoryRecipe recipe = getRecipeForInput(inputItem);
            if (recipe != null) {
                logger.info("Found recipe for input item");

                inputItem.setAmount(inputItem.getAmount() - 1);
                if (inputItem.getAmount() <= 0) {
                    setInputSlot(null);
                } else {
                    setInputSlot(inputItem);
                }

                ItemStack outputItem = getOutputSlot();
                if (outputItem != null && outputItem.isSimilar(recipe.getOutput())) {
                    // Проверяем, достаточно ли места в слоте вывода
                    if (outputItem.getAmount() < outputItem.getMaxStackSize()) {
                        outputItem.setAmount(outputItem.getAmount() + 1);
                        setOutputSlot(outputItem);
                    }
                } else {
                    setOutputSlot(recipe.getOutput().clone());
                }
            } else {
                logger.info("No recipe found for input item");
            }
        } else {
            logger.info("No input item or input item amount is 0");
        }
    }
}


