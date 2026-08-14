package net.babywipes.crankmod2.entity.gorilla;

import net.babywipes.crankmod2.entity.ModEntityTypes;
import net.babywipes.crankmod2.entity.VisibleEntityState;
import net.babywipes.crankmod2.networking.ClientboundVisibleEntityPayload;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.status.ServerStatus.Players;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.PlayerMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class GorillaEntity extends Monster {
    public class TempGoal extends HurtByTargetGoal {
        public TempGoal(final PathfinderMob mob, final Class<?>... ignoreDamageFromTheseTypes) {
            super(mob, ignoreDamageFromTheseTypes);
        }

        @Override
        public void start() {
            super.start();
            updateTargetVisible(true);
        }

        @Override
        public void stop() {
            updateTargetVisible(false);
            super.stop();
        }

        private void updateTargetVisible(boolean visible) {
            if (! (this.targetMob instanceof ServerPlayer)) {
                return;
            }
            int id = this.mob.getId();
            var payload = new ClientboundVisibleEntityPayload(new VisibleEntityState(id, visible));
            ServerPlayer player = (ServerPlayer)this.targetMob;
            ServerPlayNetworking.send(player, payload);
        }
    }
    public GorillaEntity(Level world) {
        this(ModEntityTypes.GORILLA, world);
    }
    public GorillaEntity(EntityType<? extends GorillaEntity> entityType, Level world) {
        super(entityType, world);
    }


    public static AttributeSupplier.Builder createCubeAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.TEMPT_RANGE, 10)
                .add(Attributes.MOVEMENT_SPEED, .3)
                .add(Attributes.ATTACK_DAMAGE, 5);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 2, true));
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1));

        this.targetSelector.addGoal(1, new TempGoal(this));
        //this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }


    public final AnimationState poundChestAnimationState = new AnimationState();

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        super.onSyncedDataUpdated(data);

        if (data == CHESTPOUND) {
            this.poundChestAnimationState.animateWhen(this.isChestPounding(), this.tickCount);
        }
    }

    //Chest Pound Animation Controller
    private static final EntityDataAccessor<Boolean> CHESTPOUND = SynchedEntityData.defineId(GorillaEntity.class, EntityDataSerializers.BOOLEAN);
    private int aniTimeLeft;

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CHESTPOUND, false);
    }

    public boolean isChestPounding() {
        return entityData.get(CHESTPOUND);
    }

    private void setChestPounding(boolean dancing) {
        entityData.set(CHESTPOUND, dancing);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            if (this.isChestPounding()) {
                if (this.aniTimeLeft-- <= 0) {
                    this.setChestPounding(false);
                }
            } else {
                if (this.random.nextInt(2000) == 0) {
                    this.setChestPounding(true);
                    this.aniTimeLeft = 90;
                }
            }
        }

    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {
        super.addAdditionalSaveData(valueOutput);
        valueOutput.putInt("dancing_time_left", this.aniTimeLeft);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {
        super.readAdditionalSaveData(valueInput);
        this.aniTimeLeft = valueInput.getInt("dancing_time_left").orElse(0);
        this.setChestPounding(this.aniTimeLeft > 0);
    }
}
