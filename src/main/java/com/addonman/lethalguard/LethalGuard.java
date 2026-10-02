package com.addonman.lethalguard;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@Mod(LethalGuard.MOD_ID)
@EventBusSubscriber(modid = LethalGuard.MOD_ID)
public class LethalGuard {
    public static final String MOD_ID = "lethalguard";

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Pre event) {
        float health = event.getEntity().getHealth();

        if (health <= 1.0F) {
            return;
        }

        float incoming = event.getNewDamage();

        if (incoming >= health) {
            event.setNewDamage(health - 1.0F);
        }
    }
}
