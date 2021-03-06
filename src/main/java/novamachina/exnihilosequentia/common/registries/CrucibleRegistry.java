package novamachina.exnihilosequentia.common.registries;

import novamachina.exnihilosequentia.api.crafting.crucible.CrucibleRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import novamachina.exnihilosequentia.api.registry.ICrucibleRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrucibleRegistry implements ICrucibleRegistry {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    private final List<CrucibleRecipe> recipeList = new ArrayList<>();

    @Override
    public List<CrucibleRecipe> getRecipeList() {
        return recipeList;
    }

    @Override
    public void setRecipes(List<CrucibleRecipe> recipes) {
        logger.debug("Crucible Registry recipes: " + recipes.size());
        recipeList.addAll(recipes);
    }

    @Override
    public CrucibleRecipe findRecipe(IItemProvider item) {
        Optional<CrucibleRecipe> optional = recipeList.stream().filter(recipe -> recipe.getInput().test(new ItemStack(item))).findFirst();
        return optional.orElse(null);
    }

    @Override
    public boolean isMeltable(IItemProvider item, int level) {
        return recipeList.stream().anyMatch(recipe -> recipe.getInput().test(new ItemStack(item)) && recipe.getCrucibleType().getLevel() >= level);
    }

    @Override
    public void clearRecipes() {
        recipeList.clear();
    }
}
