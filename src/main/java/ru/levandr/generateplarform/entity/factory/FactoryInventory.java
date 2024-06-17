package ru.levandr.generateplarform.entity.factory;


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class FactoryInventory {

    private Factory factory;
    private Inventory inventory;

    public FactoryInventory(Factory factory) {
        this.factory = factory;
        this.inventory = create();
    }

    public Factory getFactory() {
        return this.factory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public Inventory create() {
        Inventory inventory = Bukkit.createInventory(null,9, "Factory Interface");
        inventory.setItem(0, factory.getInputSlot());
        inventory.setItem(8, factory.getOutputSlot());
        return inventory;
    }
}

