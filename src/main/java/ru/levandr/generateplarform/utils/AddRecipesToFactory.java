package ru.levandr.generateplarform.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ru.levandr.generateplarform.entity.factory.Factory;
import ru.levandr.generateplarform.entity.factory.FactoryRecipe;

public class AddRecipesToFactory {

    public static void addRecipesToFactory(Factory factory) {
        ItemStack inputStick = new ItemStack(Material.STICK);
        ItemStack outputObsidian = new ItemStack(Material.OBSIDIAN);
        FactoryRecipe recipeStickToObsidian = new FactoryRecipe(inputStick,outputObsidian);

        factory.addRecipe(recipeStickToObsidian);
    }

}
