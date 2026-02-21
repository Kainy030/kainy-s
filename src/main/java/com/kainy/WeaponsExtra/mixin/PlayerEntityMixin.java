package com.kainy.WeaponsExtra.mixin;

import com.kainy.WeaponsExtra.item.Kainys_Items;
import net.minecraft.entity.Entity;
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

@Mixin(value = PlayerEntity.class, priority = 8000)  // Mixin优先级，默认1000，本模组op神器默认8000
public abstract class PlayerEntityMixin {

    @Inject(
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void bypassCreativeForFinallySword(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity target = (PlayerEntity) (Object) this;  // 被攻击的玩家
        if (target.getWorld().isClient) return;  // 服务端执行，客户端被动同步

        // 检查攻击者
        Entity attackerEntity = source.getAttacker();
        if (attackerEntity instanceof PlayerEntity attacker) {
            if (attacker.getMainHandStack().isOf(Kainys_Items.finally_sword)) {  // 主手是终焉之剑
                // 关闭创造玩家的invulnerable状态，设置血量为0，进入死亡状态
                target.setHealth(0.0F);  // 设置生命值为0
                target.onDeath(source);  // 进入死亡状态

                // 服务端执行客户端被动同步↓武器特效
                target.addVelocity(0.0, 2.0, 0.0);  // 向上击飞2格
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

                cir.setReturnValue(true);  // 告诉调用者“伤害已应用”
                cir.cancel();  // 取消原方法后续执行（避免冲突）
            }
        }
    }
}