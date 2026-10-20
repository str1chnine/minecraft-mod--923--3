package ru.college.i923a;

import net.minecraft.block.Block;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BreadBlockItem extends BlockItem {
    public BreadBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        // В новых версиях нужно передавать компонент еды третьим аргументом
        FoodComponent foodComponent = itemStack.getComponents().get(net.minecraft.component.DataComponentTypes.FOOD);

        if (user.canConsume(false) && foodComponent != null) {
            user.eatFood(world, itemStack, foodComponent);
            return TypedActionResult.success(itemStack, world.isClient());
        }
        return TypedActionResult.pass(itemStack);
    }
}