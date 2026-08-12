package net.babywipes.crankmod2.entity.gorilla;

import net.babywipes.crankmod2.entity.ModEntityTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.Identifier;
import net.minecraft.util.TimeUtil;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

import javax.smartcardio.ATR;

public class GorillaEntity extends Monster {
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

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }


    public final AnimationState poundChestAnimationState = new AnimationState();

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> data) {
        super.onSyncedDataUpdated(data);

        if (data == CHESTPOUND) {
            this.poundChestAnimationState.animateWhen(this.isDancing(), this.tickCount);
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

    public boolean isDancing() {
        return entityData.get(CHESTPOUND);
    }

    private void setDancing(boolean dancing) {
        entityData.set(CHESTPOUND, dancing);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            if (this.isDancing()) {
                if (this.aniTimeLeft-- <= 0) {
                    this.setDancing(false);
                }
            } else {
                if (this.random.nextInt(2000) == 0) {
                    this.setDancing(true);
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
        this.setDancing(this.aniTimeLeft > 0);
    }
}
