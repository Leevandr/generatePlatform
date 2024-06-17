package ru.levandr.generateplarform.entity.factory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FactoryItem {

    public static ItemStack create() {
        ItemStack factoryItem = new ItemStack(Material.BOOK);
        ItemMeta meta = factoryItem.getItemMeta();
        meta.setDisplayName("Factory Item");
        factoryItem.setItemMeta(meta);
        return factoryItem;
    }
}
