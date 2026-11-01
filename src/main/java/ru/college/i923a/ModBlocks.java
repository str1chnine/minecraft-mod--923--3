package ru.college.i923a;

import net.minecraft.block.AbstractBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    // Используем наш кастомный BreadBlock
    public static final BreadBlock BREAD_BLOCK = new BreadBlock(AbstractBlock.Settings.create()
            .strength(2.0f, 6.0f)
            .luminance(state -> 3)
            .ticksRandomly() // ВАЖНО: Разрешаем блоку "тикать" для гниения
    );

    public static void initialize() {
        Registry.register(Registries.BLOCK, Identifier.of("minecraft-mod", "bread_block"), BREAD_BLOCK);

        // Предмет теперь обычный, без функции еды
        Registry.register(Registries.ITEM, Identifier.of("minecraft-mod", "bread_block"),
                new BlockItem(BREAD_BLOCK, new Item.Settings().maxCount(16))
        );
    }
}