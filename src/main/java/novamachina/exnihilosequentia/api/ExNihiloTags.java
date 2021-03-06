package novamachina.exnihilosequentia.api;

import novamachina.exnihilosequentia.common.item.ore.EnumOre;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ExNihiloTags {
    public static final ITag.INamedTag<Item> MEAT_UNCOOKED = createItemWrapper(forgeLoc("meat_uncooked"));
    public static final ITag.INamedTag<Item> MEAT_COOKED = createItemWrapper(forgeLoc("meat_cooked"));
    public static final ITag.INamedTag<Item> HAMMER = createItemWrapper(new ResourceLocation(Constants.ModIds.EX_NIHILO_SEQUENTIA, "hammer"));
    public static final ITag.INamedTag<Item> CLAY = createItemWrapper(forgeLoc("clay"));
    private static Map<EnumOre, OreTag> ores = new HashMap<>();

    static {
        for (EnumOre ore : EnumOre.values()) {
            ores.put(ore, new OreTag(ore));
        }
    }

    private ExNihiloTags() {
    }

    public static OreTag getOreTags(EnumOre ore) {
        return ores.get(ore);
    }

    public static ResourceLocation getIngot(String ingot) {
        return forgeLoc("ingots/" + ingot);
    }

    public static ResourceLocation getOre(String ore) {
        return forgeLoc("ores/" + ore);
    }

    private static ResourceLocation forgeLoc(String path) {
        return new ResourceLocation("forge", path);
    }

    public static ITag.INamedTag<Item> createItemWrapper(ResourceLocation name) {
        return createWrapperTag(ItemTags.getAllTags(), name, ItemTags::makeWrapperTag);
    }

    private static <T> ITag.INamedTag<T> createWrapperTag(List<? extends ITag.INamedTag<T>> allExisting, ResourceLocation name,
                                                          Function<String, ITag.INamedTag<T>> createNew) {
        Optional<? extends ITag.INamedTag<T>> existing = allExisting
            .stream()
            .filter(tag -> tag.getName().equals(name))
            .findAny();
        if (existing.isPresent())
            return existing.get();
        else
            return createNew.apply(name.toString());
    }

    public static class OreTag {
        private final ITag.INamedTag<Item> ingot;
        private final ITag.INamedTag<Item> ore;

        public OreTag(EnumOre ore) {
            this.ingot = createItemWrapper(getIngot(ore.getName()));
            this.ore = createItemWrapper(getOre(ore.getName()));
        }

        public ITag.INamedTag<Item> getIngotTag() {
            return ingot;
        }

        public ITag.INamedTag<Item> getOreTag() {
            return ore;
        }
    }

}
