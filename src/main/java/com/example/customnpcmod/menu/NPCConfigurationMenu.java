package com.example.customnpcmod.menu;

import com.example.customnpcmod.entity.CustomNPCEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class NPCConfigurationMenu extends AbstractContainerMenu implements MenuProvider {
    private final CustomNPCEntity npc;
    private final Player player;

    public NPCConfigurationMenu(int containerId, Inventory inventory, CustomNPCEntity npc) {
        super(null, containerId);
        this.npc = npc;
        this.player = inventory.player;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("NPC Configuration");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new NPCConfigurationMenu(containerId, inventory, npc);
    }

    public void setNPCName(String name) {
        npc.setCustomName(name);
    }

    public void setArmorersSkin(String skinId) {
        npc.setArmorersSkin(skinId);
    }
}