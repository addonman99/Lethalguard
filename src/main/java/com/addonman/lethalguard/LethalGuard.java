package com.addonman.lethalguard;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@Mod(LethalGuard.MOD_ID)
public class LethalGuard {
    public static final String MOD_ID = "lethalguard";

    public LethalGuard() {
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent.Pre event) {
        if (event.getEntity().getHealth() <= 1.0F) {
            return;
        }

        float incoming = event.getNewDamage();
        float health = event.getEntity().getHealth();

        if (incoming >= health) {
            event.setNewDamage(Math.max(0.0F, health - 1.0F));
        }
    }
}
