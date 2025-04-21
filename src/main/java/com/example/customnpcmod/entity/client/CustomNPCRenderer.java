package com.example.customnpcmod.entity.client;

import com.example.customnpcmod.CustomNPCMod;
import com.example.customnpcmod.entity.CustomNPCEntity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CustomNPCRenderer extends HumanoidMobRenderer<CustomNPCEntity, PlayerModel<CustomNPCEntity>> {
    private static final ResourceLocation TEXTURE = 
        new ResourceLocation(CustomNPCMod.MOD_ID, "textures/entity/custom_npc.png");

    public CustomNPCRenderer(EntityRendererProvider.Context context) {
        super(context, new PlayerModel<>(context.bakeLayer(ModelLayers.PLAYER), false), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(CustomNPCEntity entity) {
        return TEXTURE;
    }
}