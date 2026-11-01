package ru.college.i923a;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("minecraft-mod");

    @Override
    public void onInitialize() {
        LOGGER.info("Проект загружен успешно");

        // Регистрируем блок и предмет
        ModBlocks.initialize();

        // Добавляем наш блок в творческую вкладку "Строительные блоки"
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.add(ModBlocks.BREAD_BLOCK);
        });
    }
}