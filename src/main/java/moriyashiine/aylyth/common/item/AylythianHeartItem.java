package moriyashiine.aylyth.common.item;

import moriyashiine.aylyth.common.registry.ModBlocks;
import moriyashiine.aylyth.common.util.AylythUtil;
import moriyashiine.aylyth.common.registry.key.ModDimensionKeys;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AylythianHeartItem extends Item {
	public AylythianHeartItem(Settings settings) {
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (user.isSneaking()) {
			if (user.isCreative() || user.experienceLevel >= 5) {
				if (user.getWorld().getRegistryKey() != ModDimensionKeys.AYLYTH) {
					return ItemUsage.consumeHeldItem(world, user, hand);
				}
			}
		}
		return super.use(world, user, hand);
	}
	
	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		if (world instanceof ServerWorld serverWorld && user instanceof PlayerEntity player && serverWorld.getRegistryKey() != ModDimensionKeys.AYLYTH) {
			if (player.isCreative() || player.experienceLevel >= 5) {
				for (BlockPos pos : BlockPos.iterateRandomly(world.random, world.random.nextInt(5), player.getBlockPos(), 3)) {
					BlockState state = world.getBlockState(pos);
					if (state.isAir() && ModBlocks.MARIGOLD.getDefaultState().canPlaceAt(world, pos)) {
						world.setBlockState(pos, ModBlocks.MARIGOLD.getDefaultState());
					}
				}
				AylythUtil.teleportTo(player, serverWorld.getServer().getWorld(ModDimensionKeys.AYLYTH), 0);
				if (!player.isCreative()) {
					player.addExperience(-55);
					stack.decrement(1);
				}
			}
		}
		return super.finishUsing(stack, world, user);
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}
	
	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 40;
	}
}
