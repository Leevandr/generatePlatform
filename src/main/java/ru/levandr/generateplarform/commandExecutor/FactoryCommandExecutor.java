package ru.levandr.generateplarform.commandExecutor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.levandr.generateplarform.entity.factory.Factory;
import ru.levandr.generateplarform.entity.factory.FactoryItem;
import ru.levandr.generateplarform.entity.manager.FactoryManager;
import ru.levandr.generateplarform.utils.AddRecipesToFactory;

public class FactoryCommandExecutor  implements CommandExecutor {

    private FactoryManager factoryManager;

    public FactoryCommandExecutor(FactoryManager factoryManager){
        this.factoryManager = factoryManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getInventory().addItem(FactoryItem.create());

            Factory factory = new Factory();
            AddRecipesToFactory.addRecipesToFactory(factory);

            factoryManager.addFactory(player, factory);

            return true;
        }
        return false;
    }

}
