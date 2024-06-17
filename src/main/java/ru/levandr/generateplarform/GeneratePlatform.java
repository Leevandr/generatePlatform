package ru.levandr.generateplarform;

import org.bukkit.plugin.java.JavaPlugin;
import ru.levandr.generateplarform.ProcessingTask.FactoryProcessingTask.FactoryProcessingTask;
import ru.levandr.generateplarform.commandExecutor.FactoryCommandExecutor;
import ru.levandr.generateplarform.entity.manager.FactoryManager;
import ru.levandr.generateplarform.listener.FactoryListener;

public final class GeneratePlatform extends JavaPlugin {
// Todo: Реализовать ноды(небольшие предметы похожие на кнопки которые переносят предметы между
//    хранилищами.
// Todo: Реализовать генерацию территории(платформа) для каждого игрока


    private FactoryManager factoryManager;

    @Override
    public void onEnable() {
        factoryManager = new FactoryManager();
        getCommand("factory").setExecutor(new FactoryCommandExecutor(factoryManager));
        getServer().getPluginManager().registerEvents(new FactoryListener(factoryManager), this);

        getServer().getScheduler().runTaskTimer(this, new FactoryProcessingTask(factoryManager), 0L, 1L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public FactoryManager getFactoryManager() {
        return factoryManager;
    }
}
