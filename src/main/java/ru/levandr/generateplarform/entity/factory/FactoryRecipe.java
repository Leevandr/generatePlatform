package ru.levandr.generateplarform.entity.factory;

import org.bukkit.inventory.ItemStack;

public class FactoryRecipe {

    private ItemStack input;
    private ItemStack output;

    public FactoryRecipe(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public ItemStack getInput() {
        return input;
    }

    public void setInput(ItemStack input) {
        this.input = input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }
}
