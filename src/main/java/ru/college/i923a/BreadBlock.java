package ru.college.i923a;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class BreadBlock extends Block {
    // Кусочки (0-6) для визуального уменьшения
    public static final IntProperty BITES = IntProperty.of("bites", 0, 6);
    // Стадия гниения (0 - свежий, 1 - плесень, 2 - гнилой)
    public static final IntProperty STAGE = IntProperty.of("stage", 0, 2);

    public BreadBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(BITES, 0)
                .with(STAGE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES, STAGE);
    }

    // Гниение
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int stage = state.get(STAGE);
        // Портим блок, если он не сгнил и если он не надкусан (или надкусан, но на 1-2)
        if (stage < 2 && random.nextInt(20) == 0) {
            // Меняем стадию гниения, НО НЕ сбрасываем BITES (кусочки)
            world.setBlockState(pos, state.with(STAGE, stage + 1), 3);
        }
    }

    // Выпадение
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!world.isClient && !state.isOf(newState.getBlock())) {
            int bites = state.get(BITES);
            int stage = state.get(STAGE);

            // Выпадает 1 блок только если он целый (0 укусов) и свежий (0 гниения)
            if (bites == 0 && stage == 0) {
                ItemStack dropStack = new ItemStack(ModBlocks.BREAD_BLOCK);
                dropStack(world, pos, dropStack);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}