package com.novamachina.exnihilosequentia.common.item.dolls;

import com.novamachina.exnihilosequentia.common.utility.Constants;

public enum DollEnum {
    BLAZE("minecraft","blaze", "minecraft", "lava", 1),
    ENDERMAN("minecraft","enderman", Constants.ModInfo.MOD_ID, Constants.Fluids.WITCH_WATER_STILL, 2),
    SHULKER("minecraft","shulker", Constants.ModInfo.MOD_ID, Constants.Fluids.WITCH_WATER_STILL, 1.5),
    GUARDIAN("minecraft","guardian", "minecraft", "water", 1);

    private final String entityModId;
    private final String entityName;
    private final String fluidModId;
    private final String fluidName;
    private final double yOffset;

    DollEnum(String entityModId, String entityName, String fluidModId, String fluidName, double yOffset) {
        this.entityModId = entityModId;
        this.entityName = entityName;
        this.fluidModId = fluidModId;
        this.fluidName = fluidName;
        this.yOffset = yOffset;
    }

    public String getFluidModId() {
        return fluidModId;
    }

    public String getEntityModId() {
        return entityModId;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getFluidName() {
        return fluidName;
    }

    public double getYOffset() {
        return yOffset;
    }

    public String getDollName() {
        return "doll_" + entityName;
    }
}
