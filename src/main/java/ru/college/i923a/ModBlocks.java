package ru.college.i923a;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    // Задание 1: Настраиваем свойства (прочность 2.0, сопротивление 6.0, свечение 3)
    public static final Block BREAD_BLOCK = new Block(AbstractBlock.Settings.create()
            .strength(2.0f, 6.0f)
            .luminance(state -> 3)
    );

    public static void initialize() {
        // Регистрируем сам блок
        Registry.register(Registries.BLOCK, Identifier.of("minecraft-mod", "bread_block"), BREAD_BLOCK);

        // Регистрируем предмет для блока (съедобный, восстанавливает 4 единицы голода)
        Registry.register(Registries.ITEM, Identifier.of("minecraft-mod", "bread_block"),
                new BlockItem(BREAD_BLOCK, new Item.Settings()
                        .maxCount(16)
                        .food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.3f).build()))
        );
    }
}