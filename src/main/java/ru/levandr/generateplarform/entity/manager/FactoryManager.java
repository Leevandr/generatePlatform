package ru.levandr.generateplarform.entity.manager;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import ru.levandr.generateplarform.entity.factory.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FactoryManager {

    private HashMap<UUID, Factory> playerFactories;
    private Map<Block, Factory> factories;

    public FactoryManager() {
        factories = new HashMap<>();
        this.playerFactories = new HashMap<>();
    }

    public Factory getFactory(Block block) {
        return factories.get(block);
    }

    public Factory createFactory(Block block) {
        Factory factory = new Factory();
        factories.put(block, factory);
        return factory;
    }

    public void addFactory(Player player, Factory factory){
        playerFactories.put(player.getUniqueId(), factory);
    }

    public Factory getFactory(Player player){
        return playerFactories.get(player.getUniqueId());
    }


    public Map<UUID, Factory> getPlayerFactories() {
        return playerFactories;
    }
    public Map<Block, Factory> getFactories() {
        return factories;
    }

}
