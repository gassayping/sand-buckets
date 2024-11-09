package sandbuckets;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;

public class SandBucket extends Item {

	public SandBucket() {
		super(new Item.Settings()
				.registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Sandbuckets.MOD_ID, "sand_bucket")))
				.maxCount(1));
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		user.playSound(SoundEvents.BLOCK_ANVIL_LAND, 0.5F, 1.0F);
		Vec3d userRot = user.getRotationVector();
		Vec3d spawnPos = user.getEyePos().add(userRot);
		userRot = userRot.multiply(0.4);
		world.addParticle(Sandbuckets.MY_PARTICLE, spawnPos.x, spawnPos.y, spawnPos.z, userRot.x, userRot.y, userRot.z);
		return ActionResult.SUCCESS;
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		context.getPlayer().playSound(SoundEvents.BLOCK_SAND_PLACE, 1.0F, 1.0F);
		BlockPos pos = context.getBlockPos().add(context.getSide().getVector());

		ItemStack stack = context.getStack();
		stack.setCount(stack.getCount() - 1);

		World world = context.getWorld();
		if (world.isClient()) {
			return ActionResult.SUCCESS;
		}

		context.getPlayer().getInventory().setStack(context.getPlayer().getInventory().selectedSlot,
				Items.BUCKET.getDefaultStack());
		world.setBlockState(pos, Blocks.SAND.getDefaultState());
		return ActionResult.SUCCESS;
	}
}
