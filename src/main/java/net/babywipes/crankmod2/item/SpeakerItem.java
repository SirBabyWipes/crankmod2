package net.babywipes.crankmod2.item;


import net.babywipes.crankmod2.networking.ClientboundSpeakerPayload;
import net.babywipes.crankmod2.sounds.SpeakerState;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
            if (!playing) {
                var payload = new ClientboundSpeakerPayload(new SpeakerState(true, 1.0f, false, false));
                playing = true;
                for (ServerPlayer player : PlayerLookup.level((ServerLevel) level)) {
                    //calculate volume and check for zero
                    ServerPlayNetworking.send(player, payload);
                }
                return InteractionResult.SUCCESS;
            } else {
                //calculate volume
                var payload = new ClientboundSpeakerPayload(new SpeakerState(false, 0.5f, false, true));
                for (ServerPlayer player : PlayerLookup.level((ServerLevel) level)) {
                    ServerPlayNetworking.send(player, payload);
                }
                return InteractionResult.SUCCESS;
            }
        }

        return super.use(level, user, hand);
    }

    private float calculateVolume() {
        return 0.0f;
    }
}
