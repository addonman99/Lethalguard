package addonman.lethalguard;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;

@Mod("lethalguard")
public class LethalGuard {

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (player.isCreative() || player.isSpectator()) return;

        float damage = event.getAmount();

        if (player.getHealth() - damage <= 1.0F) {
            event.setCanceled(true);
            player.setHealth(1.0F);
        }
    }
}
