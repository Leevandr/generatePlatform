package ru.levandr.generateplarform.ProcessingTask.FactoryProcessingTask;

import org.bukkit.inventory.ItemStack;
import ru.levandr.generateplarform.entity.factory.Factory;
import ru.levandr.generateplarform.entity.factory.FactoryRecipe;
import ru.levandr.generateplarform.entity.manager.FactoryManager;

public class FactoryProcessingTask implements Runnable {


    private FactoryManager factoryManager;

    public FactoryProcessingTask(FactoryManager factoryManager) {
        this.factoryManager = factoryManager;
    }

    @Override
    public void run() {
        System.out.println("Starting factory processing...");

        for (Factory factory : factoryManager.getPlayerFactories().values()) {
            factory.process();
        }
        for (Factory factory : factoryManager.getFactories().values()) {
            factory.process();
        }

        System.out.println("Finished factory processing...");
    }

    private void processFactory(Factory factory) {
        System.out.println("Processing factory...");

        ItemStack inputItem = factory.getInputSlot();
        if (inputItem != null && inputItem.getAmount() > 0) {
            System.out.println("Input item: " + inputItem.getType());

            FactoryRecipe recipe = factory.getRecipeForInput(inputItem);
            if (recipe != null) {
                System.out.println("Found recipe for input item");

                inputItem.setAmount(inputItem.getAmount() - 1);
                if (inputItem.getAmount() <= 0) {
                    factory.setInputSlot(null);
                } else {
                    factory.setInputSlot(inputItem);
                }

                ItemStack outputItem = factory.getOutputSlot();
                if (outputItem != null && outputItem.isSimilar(recipe.getOutput())) {
                    // Проверяем, достаточно ли места в слоте вывода
                    if (outputItem.getAmount() < outputItem.getMaxStackSize()) {
                        outputItem.setAmount(outputItem.getAmount() + 1);
                        factory.setOutputSlot(outputItem);
                    }
                } else {
                    factory.setOutputSlot(recipe.getOutput().clone());
                }
            } else {
                System.out.println("No recipe found for input item");
            }
        } else {
            System.out.println("No input item or input item amount is 0");
        }
    }
}
