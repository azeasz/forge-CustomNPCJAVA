package com.example.customnpcmod.entity;

import com.example.customnpcmod.CustomNPCMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
        DeferredRegister.create(ForgeRegistries.ENTITIES, CustomNPCMod.MOD_ID);

    public static final RegistryObject<EntityType<CustomNPCEntity>> CUSTOM_NPC = 
        ENTITY_TYPES.register("custom_npc",
            () -> EntityType.Builder.of(CustomNPCEntity::new, MobCategory.CREATURE)
                .sized(0.6f, 1.8f)
                .build(new ResourceLocation(CustomNPCMod.MOD_ID, "custom_npc").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}