package net.babywipes.crankmod2.item;


import net.babywipes.crankmod2.sounds.SpeakerManager;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class SpeakerItem extends Item {
    private boolean playing = false;
    public SpeakerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        if (!level.isClientSide()) {
            SpeakerManager.startPlaying(user.asLivingEntity().getName().getString(), user.asLivingEntity().getId() ,level);
            return InteractionResult.SUCCESS;
        }
        return super.use(level, user, hand);
    }
}
