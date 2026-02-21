package com.kainy.WeaponsExtra.mixin;

import com.kainy.WeaponsExtra.item.Kainys_Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LivingEntity.class, priority = 7998)
public abstract class LivingEntityMixin {

    @Inject(
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void finallySwordInstantKill(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity target = (LivingEntity) (Object) this;
        if (target.getWorld().isClient) return;

        Entity attacker = source.getAttacker();
        if (attacker instanceof PlayerEntity player) {
            if (player.getMainHandStack().isOf(Kainys_Items.finally_sword)) {
                // 所有实体生命值设置为0
                target.setHealth(0.0F);
                target.onDeath(source);  // 实体进入死亡流程

                // 特效（击飞 + 音效 + 粒子）
                target.addVelocity(0.0, 2.0, 0.0);
                target.getWorld().playSoundFromEntity(null, target, SoundEvents.ENTITY_WITHER_BREAK_BLOCK, SoundCategory.PLAYERS, 1.0F, 0.5F);

                if (target.getWorld() instanceof ServerWorld serverWorld) {
                    for (int i = 0; i < 30; i++) {
                        double offset = (serverWorld.random.nextDouble() - 0.5) * 0.5;
                        serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME,
                                target.getX() + offset,
                                target.getY() + 1.0,
                                target.getZ() + offset,
                                1, 0.0, 0.1, 0.0, 0.01);
                    }
                }

                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }
}