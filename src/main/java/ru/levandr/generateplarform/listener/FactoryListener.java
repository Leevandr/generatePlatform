package ru.levandr.generateplarform.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.levandr.generateplarform.entity.factory.Factory;
import ru.levandr.generateplarform.entity.factory.FactoryInventory;
import ru.levandr.generateplarform.entity.factory.FactoryItem;
import ru.levandr.generateplarform.entity.manager.FactoryManager;
import ru.levandr.generateplarform.utils.AddRecipesToFactory;

public class FactoryListener implements Listener {

    private FactoryManager factoryManager;

    public FactoryListener(FactoryManager factoryManager) {
        this.factoryManager = factoryManager;
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (block.getType() == Material.DIAMOND_BLOCK) {
            Factory factory = new Factory();
            AddRecipesToFactory.addRecipesToFactory(factory);

            factoryManager.addFactory(player, factory);
        }

    }


    @EventHandler
    public void opPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        Block block = event.getClickedBlock();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (action == Action.RIGHT_CLICK_BLOCK && block.getType() == Material.DIAMOND_BLOCK && itemInHand.isSimilar(FactoryItem.create())) {
            Factory factory = factoryManager.getFactory(player);
            if (factory != null) {
                FactoryInventory factoryInventory = new FactoryInventory(factory);
                player.openInventory(factoryInventory.create());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (inventory.getHolder() instanceof FactoryInventory) {
            FactoryInventory factoryInventory = (FactoryInventory) inventory.getHolder();
            Factory factory = factoryInventory.getFactory();

            if (event.getRawSlot() == 0) {
                ItemStack inputItem = inventory.getItem(0);
                // Проверяем, является ли предмет в слоте ввода допустимым входным предметом
                if (inputItem != null && factory.getRecipeForInput(inputItem) != null) {
                    System.out.println("Valid input item: " + inputItem.getType());
                    factory.setInputSlot(inputItem.clone());
                    inputItem.setAmount(inputItem.getAmount() - 1);
                    if (inputItem.getAmount() <= 0) {
                        inventory.setItem(0, null);
                    } else {
                        inventory.setItem(0, inputItem);
                    }
                    player.updateInventory();
                } else {
                    System.out.println("Invalid input item or no input item");
                }
            } else if (event.getRawSlot() == 8) {
                ItemStack outputItem = inventory.getItem(8);
                if (outputItem != null) {
                    System.out.println("Output item: " + outputItem.getType());
                    factory.setOutputSlot(outputItem);
                    inventory.setItem(8, factory.getOutputSlot());
                    player.updateInventory();
                } else {
                    System.out.println("No output item");
                }
            }
        }
    }
}


