package sandbuckets;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class BucketManager {

	public static final Item SAND_BUCKET = new SandBucket();

	public static ActionResult handleInteract(PlayerEntity player, World world, Hand hand,
			BlockHitResult blockHitResult) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (!itemStack.isOf(Items.BUCKET) || !world.getBlockState(blockHitResult.getBlockPos()).isOf(Blocks.SAND)) {
			return ActionResult.PASS;
		}

		world.setBlockState(blockHitResult.getBlockPos(), Blocks.AIR.getDefaultState());

		PlayerInventory inv = player.getInventory();
		if (inv.getStack(inv.selectedSlot).getCount() == 1) {
			inv.setStack(inv.selectedSlot, new ItemStack(SAND_BUCKET));
		} else {
			inv.insertStack(new ItemStack(SAND_BUCKET));
			itemStack.setCount(itemStack.getCount() - 1);
		}
		return ActionResult.PASS;
	}

	public static void RegisterItems() {
		Registry.register(Registries.ITEM, Identifier.of(Sandbuckets.MOD_ID, "sand_bucket"), SAND_BUCKET);
	}
}
