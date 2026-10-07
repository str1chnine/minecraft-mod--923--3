package ru.college.i923a;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("minecraft-mod");

    @Override
    public void onInitialize() {
        LOGGER.info("Проект загружен успешно");
    }
}