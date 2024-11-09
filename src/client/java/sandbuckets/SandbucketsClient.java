package sandbuckets;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class SandbucketsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(Sandbuckets.MY_PARTICLE, SandParticle.Factory::new);
	}
}
