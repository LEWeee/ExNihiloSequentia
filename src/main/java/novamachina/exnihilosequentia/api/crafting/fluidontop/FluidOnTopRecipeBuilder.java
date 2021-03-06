package novamachina.exnihilosequentia.api.crafting.fluidontop;

import novamachina.exnihilosequentia.api.crafting.ExNihiloFinishedRecipe;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.IItemProvider;

public class FluidOnTopRecipeBuilder extends ExNihiloFinishedRecipe<FluidOnTopRecipeBuilder> {
    private FluidOnTopRecipeBuilder() {
        super(FluidOnTopRecipe.getStaticSerializer().get());
    }

    public static FluidOnTopRecipeBuilder builder() {
        return new FluidOnTopRecipeBuilder();
    }

    public FluidOnTopRecipeBuilder fluidOnTop(Fluid fluid) {
        return this.addFluid("fluidOnTop", fluid);
    }

    public FluidOnTopRecipeBuilder fluidInTank(Fluid fluid) {
        return this.addFluid("fluidInTank", fluid);
    }

    public FluidOnTopRecipeBuilder result(IItemProvider result) {
        return this.addResult(result);
    }
}
