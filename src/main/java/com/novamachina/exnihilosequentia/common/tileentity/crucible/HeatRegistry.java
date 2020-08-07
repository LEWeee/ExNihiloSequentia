package com.novamachina.exnihilosequentia.common.tileentity.crucible;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.novamachina.exnihilosequentia.common.json.AnnotatedDeserializer;
import com.novamachina.exnihilosequentia.common.json.CrucibleRegistriesJson;
import com.novamachina.exnihilosequentia.common.json.FluidOnTopJson;
import com.novamachina.exnihilosequentia.common.json.HeatJson;
import com.novamachina.exnihilosequentia.common.setup.AbstractModRegistry;
import com.novamachina.exnihilosequentia.common.setup.ModRegistries;
import com.novamachina.exnihilosequentia.common.utility.Constants;
import com.novamachina.exnihilosequentia.common.utility.LogUtil;
import com.novamachina.exnihilosequentia.common.utility.TagUtils;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeatRegistry extends AbstractModRegistry {

    private final Map<ResourceLocation, Integer> heatMap     = new HashMap<>();

    public HeatRegistry(ModRegistries.ModBus bus) {
        bus.register(this);
    }

    public void addHeatSource(ForgeRegistryEntry<? extends IItemProvider> entry, int amount) {
        addHeatSource(entry.getRegistryName(), amount);
    }

    public void addHeatSource(ResourceLocation entry, int amount) {
        // Who do I own?
        List<ResourceLocation> idList = TagUtils.getTagsOwnedBy(entry);
        for(ResourceLocation id : idList) {
            if(heatMap.containsKey(id)) {
                LogUtil.info(String.format("ID: %s falls under Tag: %s. Removing %s ...", id.toString(), entry.toString(), id.toString()));
                heatMap.remove(id);
            }
        }

        // Does a tag who owns me already exist in the map?
        Collection<ResourceLocation> tags = TagUtils.getTags(entry);
        if(tags != null) {
            for (ResourceLocation tag : tags) {
                if (heatMap.containsKey(tag)) {
                    LogUtil
                        .info(String.format("Tag: %s already registered. Skipping item %s ...", tag.toString(), entry));
                    return;
                }
            }
        }

        // Am I in map?
        if(heatMap.containsKey(entry)) {
            LogUtil.info(String.format("Tag: %s already registered. Skipping...", entry));
            return;
        }

        insertIntoMap(entry, amount);
    }

    private void insertIntoMap(ResourceLocation name, int amount) {
        heatMap.put(name, amount);
    }

    public int getHeatAmount(ForgeRegistryEntry<? extends IItemProvider> entry) {
        return heatMap.getOrDefault(entry.getRegistryName(), 0);
    }

    @Override
    protected void useJson() {
        try {
            List<HeatJson> registriesJson = readJson();
            for(HeatJson entry : registriesJson) {
                if(itemExists(entry.getEntry())) {
                    ResourceLocation entryID = new ResourceLocation(entry.getEntry());
                    addHeatSource(entryID, entry.getRate());
                } else {
                    LogUtil.warn(String.format("Entry \"%s\" does not exist...Skipping...", entry.getEntry()));
                }
            }
        } catch (JsonParseException e) {
            LogUtil.error(String.format("Malformed %s", Constants.Json.HEAT_FILE));
            LogUtil.error(e.getMessage());
            if(e.getMessage().contains("IllegalStateException")) {
                LogUtil.error("Please consider deleting the file and regenerating it.");
            }
            LogUtil.error("Falling back to defaults");
            clear();
            useDefaults();
        }
    }

    private boolean itemExists(String entry) {
        ResourceLocation itemID = new ResourceLocation(entry);
        return TagUtils.isTag(itemID) || ForgeRegistries.BLOCKS.containsKey(itemID) || ForgeRegistries.ITEMS.containsKey(itemID) || ForgeRegistries.FLUIDS.containsKey(itemID);
    }

    private List<HeatJson> readJson() throws JsonParseException {
        Type listType = new TypeToken<ArrayList<HeatJson>>(){}.getType();
        Gson gson = new GsonBuilder().registerTypeAdapter(listType, new AnnotatedDeserializer<ArrayList<HeatJson>>()).create();
        Path path = Constants.Json.baseJsonPath.resolve(Constants.Json.HEAT_FILE);
        List<HeatJson> registryJson = null;
        try {
            StringBuilder builder = new StringBuilder();
            Files.readAllLines(path).forEach(builder::append);
            registryJson = gson.fromJson(builder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registryJson;
    }

    @Override
    protected void useDefaults() {
        addHeatSource(Blocks.LAVA, 3);
        addHeatSource(Blocks.FIRE, 4);
        addHeatSource(Blocks.TORCH, 1);
        addHeatSource(Blocks.WALL_TORCH, 1);
        addHeatSource(Blocks.MAGMA_BLOCK, 2);
        addHeatSource(Blocks.GLOWSTONE, 2);
    }

    @Override
    public void clear() {
        heatMap.clear();
    }

    @Override
    public List<HeatJson> toJSONReady() {
        List<HeatJson> jsonList = new ArrayList<>();
        for(Map.Entry<ResourceLocation, Integer> entry: heatMap.entrySet())
        {
            jsonList.add(new HeatJson(entry.getKey().toString(), entry.getValue()));
        }
        return jsonList;
    }
}