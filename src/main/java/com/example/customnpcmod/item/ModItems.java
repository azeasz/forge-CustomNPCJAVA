package com.example.customnpcmod.item;

import com.example.customnpcmod.CustomNPCMod;
import com.example.customnpcmod.entity.ModEntityTypes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = 
        DeferredRegister.create(ForgeRegistries.ITEMS, CustomNPCMod.MOD_ID);

    public static final RegistryObject<Item> CUSTOM_NPC_SPAWN_EGG = ITEMS.register("custom_npc_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.CUSTOM_NPC,
                    0x995533, // Primary color (skin tone)
                    0x553322, // Secondary color (darker tone)
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}