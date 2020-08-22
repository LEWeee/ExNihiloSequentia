package com.novamachina.exnihilosequentia.common.jei.crook;

import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.StringUtils;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class CrookTooltipCallback implements ITooltipCallback<ItemStack> {
    private final CrookRecipe crookRecipe;

    public CrookTooltipCallback(CrookRecipe crookRecipe) {
        this.crookRecipe = crookRecipe;
    }

    @Override
    public void onTooltip(int slotIndex, boolean input, ItemStack ingredient, List<String> tooltip) {
        if(!input) {
            ModRegistries.CROOK.getDrops().stream().filter(reward -> ItemStack.areItemsEqual(new ItemStack(ForgeRegistries.ITEMS
                .getValue(reward.getItem())), ingredient))
                .forEach(reward -> tooltip.add(String.format("%s", StringUtils.formatPercent(reward.getRarity()))));
        }
    }
}
