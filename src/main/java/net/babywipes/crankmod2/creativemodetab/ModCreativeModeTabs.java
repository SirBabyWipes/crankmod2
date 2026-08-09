package net.babywipes.crankmod2.creativemodetab;

import net.babywipes.crankmod2.CrankMod2;
import net.babywipes.crankmod2.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
    public static final CreativeModeTab CRANK_MOD_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(CrankMod2.MOD_ID, "crank_mod_items"),
            FabricCreativeModeTab.builder().icon(()-> new ItemStack(ModItems.MONKEY))
                    .title(Component.translatable("creativemodetab.crankmod2.crank_mod_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.MONKEY);
                    })
                    .build());

    public static void registerModCreativeModeTabs() {
        CrankMod2.LOGGER.info("Registering Creative Mode Tabs for " + CrankMod2.MOD_ID);
    }
}
