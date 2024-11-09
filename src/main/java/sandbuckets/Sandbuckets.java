package sandbuckets;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sandbuckets implements ModInitializer {
	public static final String MOD_ID = "sand-buckets";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final SimpleParticleType MY_PARTICLE = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		BucketManager.RegisterItems();
		UseBlockCallback.EVENT.register(BucketManager::handleInteract);
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "sand_particle"), MY_PARTICLE);
	}
}
