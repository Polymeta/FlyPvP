package io.github.polymeta.disablepvpfly;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.IndirectEntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.game.state.GamePostInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

@Plugin(
        id = "disablepvpfly",
        name = "Disablepvpfly",
        description = "Disabled fly if players engage in pvp",
        authors = {"Polymeta"})
public class DisablePvPFly
{

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GamePostInitializationEvent event)
    {
        logger.info("Disable Fly during PvP is now active.");
    }

    @Listener
    public void onAttack(DamageEntityEvent event, @First EntityDamageSource source, @Getter("getTargetEntity") Player victim)
    {
        if (source.getSource() instanceof Player)
        {
            Player attacker = (Player) source.getSource();
            if (attacker.get(Keys.CAN_FLY).orElse(false))
            {
                attacker.offer(Keys.IS_FLYING, false);
                attacker.offer(Keys.CAN_FLY, false);
                attacker.sendMessage(Text.of(TextColors.GREEN, "You engaged into PvP and lost your flying ability."));
            }
            if (victim.get(Keys.CAN_FLY).orElse(false))
            {
                victim.offer(Keys.IS_FLYING, false);
                victim.offer(Keys.CAN_FLY, false);
                victim.sendMessage(Text.of(TextColors.GREEN, "You got engaged into PvP and lost your flying ability."));
            }
        }
    }

    @Listener
    public void onRangedAttack(DamageEntityEvent event, @First IndirectEntityDamageSource source, @Getter("getTargetEntity") Player victim)
    {
        if (source.getSource() instanceof Player)
        {
            Player attacker = (Player) source.getSource();
            if (attacker.get(Keys.CAN_FLY).orElse(false))
            {
                attacker.offer(Keys.IS_FLYING, false);
                attacker.offer(Keys.CAN_FLY, false);
                attacker.sendMessage(Text.of(TextColors.GREEN, "You engaged into PvP and lost your flying ability."));
            }
            if (victim.get(Keys.CAN_FLY).orElse(false))
            {
                victim.offer(Keys.IS_FLYING, false);
                victim.offer(Keys.CAN_FLY, false);
                victim.sendMessage(Text.of(TextColors.GREEN, "You got engaged into PvP and lost your flying ability."));
            }
        }
    }
}
