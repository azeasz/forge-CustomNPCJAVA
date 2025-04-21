package com.example.customnpcmod;

import com.example.customnpcmod.entity.ModEntityTypes;
import com.example.customnpcmod.entity.client.CustomNPCRenderer;
import com.example.customnpcmod.entity.CustomNPCEntity;
import com.example.customnpcmod.item.ModItems;
import com.example.customnpcmod.menu.ModMenuTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CustomNPCMod.MOD_ID)
public class CustomNPCMod {
    public static final String MOD_ID = "customnpcmod";
    private static final Logger LOGGER = LogManager.getLogger();

    public CustomNPCMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register Items
        ModItems.register(eventBus);
        
        // Register Entity Types
        ModEntityTypes.register(eventBus);

        // Register Menu Types
        ModMenuTypes.register(eventBus);

        eventBus.addListener(this::setup);
        eventBus.addListener(this::registerRenderers);
        eventBus.addListener(this::createAttributes);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("Custom NPC Mod initialized");
    }

    private void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.CUSTOM_NPC.get(), CustomNPCRenderer::new);
    }

    private void createAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.CUSTOM_NPC.get(), CustomNPCEntity.createAttributes().build());
    }
}