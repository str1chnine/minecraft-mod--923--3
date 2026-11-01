package ru.college.i923a;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("minecraft-mod");

    @Override
    public void onInitialize() {
        LOGGER.info("Проект загружен успешно");
        ModBlocks.initialize();

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.add(ModBlocks.BREAD_BLOCK);
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            var state = world.getBlockState(pos);

            if (state.isOf(ModBlocks.BREAD_BLOCK)) {
                if (!world.isClient) {
                    int stage = state.get(BreadBlock.STAGE);
                    int bites = state.get(BreadBlock.BITES);

                    // Если блок сгнил (стадия 1 или 2) - есть нельзя
                    if (stage > 0) {
                        player.sendMessage(Text.literal("Фу! Этот сухарь уже испортился."), true);
                        return ActionResult.FAIL;
                    }

                    // Кормим игрока
                    player.getHungerManager().add(2, 0.3f);
                    player.sendMessage(Text.literal("Хруст! Сухарь очень вкусный!"), true);

                    // Если это последний кусочек
                    if (bites >= 6) {
                        world.removeBlock(pos, false);
                    } else {
                        // Увеличиваем количество укусов
                        world.setBlockState(pos, state.with(BreadBlock.BITES, bites + 1), 3);
                    }

                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }
}