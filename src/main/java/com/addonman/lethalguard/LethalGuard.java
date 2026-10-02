package com.addonman.lethalguard;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.minecraft.server.level.ServerPlayer;

@Mod(LethalGuard.MOD_ID)
public class LethalGuard {
    public static final String MOD_ID = "lethalguard";

    public LethalGuard() {
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent.Pre event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        float health = player.getHealth();
        float absorption = player.getAbsorptionAmount();
        float incoming = event.getNewDamage();

        float totalEffectiveHealth = health + absorption;

        if (health > 1.0F && incoming >= totalEffectiveHealth) {
            event.setNewDamage(Math.max(0.0F, totalEffectiveHealth - 1.0F));
        }
    }
}
