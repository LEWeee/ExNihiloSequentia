package com.novamachina.exnihilosequentia.common.json;

import com.novamachina.exnihilosequentia.common.api.crafting.fluidontop.FluidOnTopRecipe;

public class FluidOnTopJson {
    @JsonRequired
    private final String fluidInBarrel;
    @JsonRequired
    private final String fluidOnTop;
    @JsonRequired
    private final String result;

    public FluidOnTopJson(String fluidInBarrel, String fluidOnTop, String result) {
        this.fluidInBarrel = fluidInBarrel;
        this.fluidOnTop = fluidOnTop;
        this.result = result;
    }

    public FluidOnTopJson(FluidOnTopRecipe recipe) {
        this.fluidInBarrel = recipe.getFluidInTank().toString();
        this.fluidOnTop = recipe.getFluidOnTop().toString();
        this.result = recipe.getResult().toString();
    }

    public String getFluidInBarrel() {
        return fluidInBarrel;
    }

    public String getFluidOnTop() {
        return fluidOnTop;
    }

    public String getResult() {
        return result;
    }
}
