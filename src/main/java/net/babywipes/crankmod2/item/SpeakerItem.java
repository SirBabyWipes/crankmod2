package net.babywipes.crankmod2.item;


import net.babywipes.crankmod2.sounds.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class SpeakerItem extends Item {
    public SpeakerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        if (!level.isClientSide()) {
            return InteractionResult.PASS;
        }

        level.playSound(null, user.blockPosition(), ModSounds.RIP, SoundSource.PLAYERS);
        return super.use(level, user, hand);
    }
}
