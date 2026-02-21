package com.kainy.WeaponsExtra.mixin;

import com.kainy.WeaponsExtra.item.Kainys_Items;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
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

@Mixin(value = WitherEntity.class, priority = 7999)
public abstract class WitherEntityMixin {

    @Inject(
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void forceKillWitherInAnyPhase(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        WitherEntity wither = (WitherEntity) (Object) this;
        if (wither.getWorld().isClient) return;

        Entity attacker = source.getAttacker();
        if (attacker instanceof PlayerEntity player && player.getMainHandStack().isOf(Kainys_Items.finally_sword)) {
            // 无视凋零预备阶段
            wither.setHealth(0.0F);
            wither.onDeath(source);  // 进入死亡流程

            // 特效
            wither.addVelocity(0.0, 3.0, 0.0);  // 更高击飞
            wither.getWorld().playSoundFromEntity(null, wither, SoundEvents.ENTITY_WITHER_DEATH, SoundCategory.HOSTILE, 1.0F, 0.8F);  // 凋零死亡音效

            if (wither.getWorld() instanceof ServerWorld serverWorld) {
                for (int i = 0; i < 60; i++) {  // 更多粒子
                    double offsetX = (serverWorld.random.nextDouble() - 0.5) * 1.0;
                    double offsetY = serverWorld.random.nextDouble() * 2.0;
                    double offsetZ = (serverWorld.random.nextDouble() - 0.5) * 1.0;
                    serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME,
                            wither.getX() + offsetX,
                            wither.getY() + offsetY,
                            wither.getZ() + offsetZ,
                            1, 0.2, 0.2, 0.2, 0.05);
                }
            }

            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}