package ru.college.i923a;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block BREAD_BLOCK = new Block(AbstractBlock.Settings.copy(Blocks.HAY_BLOCK));

    public static void initialize() {
        Registry.register(Registries.BLOCK, Identifier.of("minecraft-mod", "bread_block"), BREAD_BLOCK);

        Registry.register(Registries.ITEM, Identifier.of("minecraft-mod", "bread_block"),
                new BreadBlockItem(BREAD_BLOCK, new Item.Settings()
                        .maxCount(16)
                        .food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.3f).build()))
        );
    }
}