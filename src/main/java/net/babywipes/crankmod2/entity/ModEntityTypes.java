package net.babywipes.crankmod2.entity;

import net.babywipes.crankmod2.CrankMod2;
import net.babywipes.crankmod2.entity.gorilla.GorillaEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntityTypes {
    public static final EntityType<GorillaEntity> GORILLA = register(
            "gorilla",
            EntityType.Builder.<GorillaEntity>of(GorillaEntity::new, MobCategory.MISC)
                    .sized(1.5f, 2.25f)
    );

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(CrankMod2.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
    }

    public static void registerModEntityTypes() {
        CrankMod2.LOGGER.info("Registering EntityTypes for " + CrankMod2.MOD_ID);
    }

    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(GORILLA, GorillaEntity.createCubeAttributes());
    }
}