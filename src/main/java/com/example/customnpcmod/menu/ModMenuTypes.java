package com.example.customnpcmod.menu;

import com.example.customnpcmod.CustomNPCMod;
import com.example.customnpcmod.entity.CustomNPCEntity;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = 
        DeferredRegister.create(ForgeRegistries.CONTAINERS, CustomNPCMod.MOD_ID);

    public static final RegistryObject<MenuType<NPCConfigurationMenu>> NPC_CONFIGURATION = 
        MENUS.register("npc_configuration",
            () -> IForgeMenuType.create((windowId, inv, data) -> {
                CustomNPCEntity npc = (CustomNPCEntity) inv.player.level.getEntity(data.readInt());
                return new NPCConfigurationMenu(windowId, inv, npc);
            }));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}