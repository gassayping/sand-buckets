package sandbuckets;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;

public class SandParticle extends SpriteBillboardParticle {

	SandParticle(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ,
			SpriteProvider spriteProvider) {
		super(world, x, y, z);
		this.maxAge = 40;
		this.scale = 0.1f;
		this.velocityX = velX;
		this.velocityY = velY;
		this.velocityZ = velZ;
		this.x = x;
		this.y = y;
		this.z = z;
		this.collidesWithWorld = true;
		this.alpha = 1.0f;
		this.setSprite(spriteProvider);
	}

	public void tick() {
		this.prevPosX = this.x;
		this.prevPosY = this.y;
		this.prevPosZ = this.z;
		if (++this.age > this.maxAge || this.alpha <= 0) {
			this.markDead();
		} else {
			this.velocityX *= 0.90;
			this.velocityZ *= 0.90;
			this.velocityY *= 0.90;
			this.velocityY -= 0.025;
			if (this.onGround) {
				this.velocityX = 0;
				this.velocityZ = 0;

				this.alpha -= 0.15;
			}
			this.move(this.velocityX, this.velocityY, this.velocityZ);
		}
	}

	@Override
	public ParticleTextureSheet getType() {
		return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
	}

	public static class Factory<DefaultParticleType extends ParticleEffect>
			implements ParticleFactory<DefaultParticleType> {

		private final SpriteProvider spriteProvider;

		public Factory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double x,
				double y, double z, double velX, double velY, double velZ) {
			return new SandParticle(clientWorld, x, y, z, velX, velY, velZ, this.spriteProvider);
		}
	}
}
