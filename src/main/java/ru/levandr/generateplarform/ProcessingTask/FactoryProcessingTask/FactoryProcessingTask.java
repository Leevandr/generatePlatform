package ru.levandr.generateplarform.ProcessingTask.FactoryProcessingTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.levandr.generateplarform.entity.factory.Factory;
import ru.levandr.generateplarform.entity.manager.FactoryManager;

import java.util.Collection;

public class FactoryProcessingTask implements Runnable {


    private FactoryManager factoryManager;

    public FactoryProcessingTask(FactoryManager factoryManager) {
        this.factoryManager = factoryManager;
    }

    @Override
    public void run() {
        Logger logger = LoggerFactory.getLogger(FactoryProcessingTask.class);
        logger.info("Starting factory processing...");

        processFactories(factoryManager.getPlayerFactories().values(), "Player factories");
        processFactories(factoryManager.getFactories().values(), "Block factories");

        logger.info("Finished factory processing...");
    }

    private void processFactories(Collection<Factory> factories, String factoryType) {
        Logger logger = LoggerFactory.getLogger(FactoryProcessingTask.class);
        for (Factory factory : factories) {
            try {
                logger.info("Processing {}...", factoryType);
                factory.process();
            } catch (Exception e) {
                logger.error("Error processing factory: ", e);
            }
        }
    }
}
