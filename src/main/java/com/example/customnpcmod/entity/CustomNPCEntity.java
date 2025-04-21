package com.example.customnpcmod.entity;

import com.example.customnpcmod.menu.NPCConfigurationMenu;
import moe.plushie.armourers_workshop.api.ArmourApi;
import moe.plushie.armourers_workshop.api.skin.ISkinType;
import moe.plushie.armourers_workshop.api.skin.ISkinWardrobe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.HumanoidMobPatch;

public class CustomNPCEntity extends PathfinderMob implements MenuProvider {
    private String customName = "";
    private String armorersSkin = "";
    private boolean isHostile = false;

    public CustomNPCEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.registerEpicFightCapability();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            player.openMenu(this);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 2.0D);
    }

    private void registerEpicFightCapability() {
        if (this.level.isClientSide) {
            return;
        }
        this.getCapability(EpicFightCapabilities.CAPABILITY_ENTITY).ifPresent(cap -> {
            if (cap instanceof HumanoidMobPatch) {
                // Epic Fight integration is automatic through the mobpatch system
            }
        });
    }

    public void setArmorersSkin(String skinId) {
        this.armorersSkin = skinId;
        if (!this.level.isClientSide) {
            ISkinWardrobe wardrobe = ArmourApi.getEntitySkinWardrobe(this);
            if (wardrobe != null && !skinId.isEmpty()) {
                ISkinType skinType = ArmourApi.getSkinTypeRegistry().getSkinType("npc");
                if (skinType != null) {
                    wardrobe.setSkin(skinType, skinId);
                }
            }
        }
    }

    public String getArmorersSkin() {
        return this.armorersSkin;
    }

    public void setHostile(boolean hostile) {
        this.isHostile = hostile;
    }

    public boolean isHostile() {
        return this.isHostile;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("CustomName", this.customName);
        tag.putString("ArmorersSkin", this.armorersSkin);
        tag.putBoolean("IsHostile", this.isHostile);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.customName = tag.getString("CustomName");
        this.setArmorersSkin(tag.getString("ArmorersSkin"));
        this.isHostile = tag.getBoolean("IsHostile");
    }

    @Override
    public Component getDisplayName() {
        return Component.literal(this.customName.isEmpty() ? "Custom NPC" : this.customName);
    }

    public void setCustomName(String name) {
        this.customName = name;
        this.setCustomName(Component.literal(name));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!this.isHostile && source.getEntity() instanceof Player) {
            return false; // Prevent damage if NPC is not hostile
        }
        return super.hurt(source, amount);
    }
}