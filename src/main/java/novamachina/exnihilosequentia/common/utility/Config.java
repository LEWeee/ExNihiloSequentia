package novamachina.exnihilosequentia.common.utility;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    public static final ForgeConfigSpec COMMON_CONFIG;
    private static final String CATEGORY_BARREL = "barrel";
    private static final String CATEGORY_CROOK = "crook";
    private static final String CATEGORY_CRUCIBLE = "crucible";
    private static final String CATEGORY_INFESTED_LEAVES = "infested_leaves";
    private static final String CATEGORY_SIEVE = "sieve";
    private static final String CATEGORY_COMPAT = "compatibility";
    private static final String CATEGORY_DEBUG = "debug";
    private static final String SUBCATEGORY_BARREL_MOB = "mob_spawn";
    private static final String SUBCATEGORY_BARREL_COMPOST = "compost";
    private static final String SUBCATEGORY_BARREL_FLUID = "fluid_transform";
    private static final String SUBCATEGORY_CRUCIBLE_WOOD = "wood";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new Builder();
    // Barrel
    private static ForgeConfigSpec.IntValue secondsToSpawn;
    private static ForgeConfigSpec.IntValue barrelMaxSolidAmount;
    private static ForgeConfigSpec.IntValue secondsToCompost;
    private static ForgeConfigSpec.IntValue secondsToFluidTransform;
    private static ForgeConfigSpec.IntValue barrelNumberOfBuckets;
    private static ForgeConfigSpec.IntValue rainFillAmount;
    private static ForgeConfigSpec.IntValue woodBarrelMaxTemp;
    // Crook
    private static ForgeConfigSpec.IntValue maxBonusStringCount;
    private static ForgeConfigSpec.IntValue minStringCount;
    private static ForgeConfigSpec.IntValue vanillaSimulateDropCount;
    // Crucible
    private static ForgeConfigSpec.IntValue ticksBetweenMelts;
    private static ForgeConfigSpec.IntValue crucibleNumberOfBuckets;
    private static ForgeConfigSpec.IntValue woodHeatRate;
    // Infested Leaves
    private static ForgeConfigSpec.IntValue secondsToTransformLeaves;
    private static ForgeConfigSpec.DoubleValue spreadChance;
    private static ForgeConfigSpec.IntValue ticksBetweenSpreadAttempt;
    // Sieve
    private static ForgeConfigSpec.BooleanValue flattenSieveRecipes;
    private static ForgeConfigSpec.IntValue sieveRange;
    private static ForgeConfigSpec.BooleanValue enableMeshDurability;
    // Compat
    private static ForgeConfigSpec.BooleanValue enableThermal;
    private static ForgeConfigSpec.BooleanValue enableImmersive;
    private static ForgeConfigSpec.BooleanValue enableMekanism;
    private static ForgeConfigSpec.BooleanValue enableCreate;
    private static ForgeConfigSpec.BooleanValue enableSilent;
    // Debugging
    private static ForgeConfigSpec.BooleanValue enableDebugLogging;

    static {
        COMMON_BUILDER.comment("Barrel Configs").push(CATEGORY_BARREL);
        barrelConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Crook Configs").push(CATEGORY_CROOK);
        crookConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Crucible Configs").push(CATEGORY_CRUCIBLE);
        crucibleConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Infested Leaves Configs").push(CATEGORY_INFESTED_LEAVES);
        infestedLeavesConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Sieve Configs").push(CATEGORY_SIEVE);
        sieveConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Compatibility Configs").push(CATEGORY_COMPAT);
        compatConfigs();
        COMMON_BUILDER.pop();
        COMMON_BUILDER.comment("Debug Configs").push(CATEGORY_DEBUG);
        debugConfigs();
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
    }
    private Config() {
    }

    public static int getSecondsToSpawn() {
        return secondsToSpawn.get();
    }

    public static int getBarrelMaxSolidAmount() {
        return barrelMaxSolidAmount.get();
    }

    public static int getSecondsToCompost() {
        return secondsToCompost.get();
    }

    public static int getSecondsToFluidTransform() {
        return secondsToFluidTransform.get();
    }

    public static int getBarrelNumberOfBuckets() {
        return barrelNumberOfBuckets.get();
    }

    public static int getRainFillAmount() {
        return rainFillAmount.get();
    }

    public static int getWoodBarrelMaxTemp() {
        return woodBarrelMaxTemp.get();
    }

    public static int getMaxBonusStringCount() {
        return maxBonusStringCount.get();
    }

    public static int getMinStringCount() {
        return minStringCount.get();
    }

    public static int getVanillaSimulateDropCount() {
        return vanillaSimulateDropCount.get();
    }

    public static int getTicksBetweenMelts() {
        return ticksBetweenMelts.get();
    }

    public static int getCrucibleNumberOfBuckets() {
        return crucibleNumberOfBuckets.get();
    }

    public static int getWoodHeatRate() {
        return woodHeatRate.get();
    }

    public static int getSecondsToTransformLeaves() {
        return secondsToTransformLeaves.get();
    }

    public static double getSpreadChance() {
        return spreadChance.get();
    }

    public static int getTicksBetweenSpreadAttempt() {
        return ticksBetweenSpreadAttempt.get();
    }

    public static boolean getFlattenSieveRecipes() {
        return flattenSieveRecipes.get();
    }

    public static int getSieveRange() {
        return sieveRange.get();
    }

    public static boolean getEnableMeshDurability() {
        return enableMeshDurability.get();
    }

    public static boolean getEnableThermal() {
        return enableThermal.get();
    }

    public static boolean getEnableImmersive() {
        return enableImmersive.get();
    }

    public static boolean getEnableMekanism() {
        return enableMekanism.get();
    }

    public static boolean getEnableCreate() {
        return enableCreate.get();
    }

    public static boolean getEnableSilent() {
        return enableSilent.get();
    }

    public static boolean getEnableDebugLogging() {
        return enableDebugLogging.get();
    }

    private static void debugConfigs() {
        enableDebugLogging = COMMON_BUILDER.comment("Enable extra logging? (Default: false)")
            .define("enableDebugLogging", false);
    }

    private static void compatConfigs() {
        enableThermal = COMMON_BUILDER.comment("Should Thermal Expansion ores be enabled? (Default: false)")
            .define("enableThermalExpansion", false);
        enableImmersive = COMMON_BUILDER.comment("Should Immersive Engineering ores be enabled? (Default: false)")
            .define("enableImmersiveEngineering", false);
        enableMekanism = COMMON_BUILDER.comment("Should Mekanism ores be enabled? (Default: false)")
            .define("enableMekanism", false);
        enableCreate = COMMON_BUILDER.comment("Should Create ores be enabled? (Default: false)")
            .define("enableCreate", false);
        enableSilent = COMMON_BUILDER.comment("Should Silent Mechanism ores be enabled? (Default: false)")
            .define("enableSilentMechanism", false);
    }

    private static void sieveConfigs() {
        flattenSieveRecipes = COMMON_BUILDER
            .comment("Sieve will get results for all mesh tiers below the one in the sieve (Default: true)")
            .define("flattenSieveRecipes", true);
        sieveRange = COMMON_BUILDER
            .comment("Defines the radius that a sieve will attempt to activate other sieves (Default: 2)")
            .defineInRange("sieveRange", 2, 0, 5);
        enableMeshDurability = COMMON_BUILDER
            .comment("Meshes will have durability and can break. (Default: false)")
            .define("enableMeshDurability", false);
    }

    private static void infestedLeavesConfigs() {
        secondsToTransformLeaves = COMMON_BUILDER
            .comment("Number of seconds to for leaves to become completely infested (Default: 10)")
            .defineInRange("secondsToTransformLeaves", 10, 1, Integer.MAX_VALUE);
        spreadChance = COMMON_BUILDER.comment("Percentage of the time that infested leaves will spread (Default: 0.3)")
            .defineInRange("spreadChance", 0.3, 0.001, 1.0);
        ticksBetweenSpreadAttempt = COMMON_BUILDER
            .comment("Number of ticks between infested leave spread attempts (Default: 100)")
            .defineInRange("ticksBetweenSpreadAttempt", 100, 1, Integer.MAX_VALUE);
    }

    private static void crucibleConfigs() {
        ticksBetweenMelts = COMMON_BUILDER.comment("Ticks between melting operations (Default: 20)")
            .defineInRange("ticksBetweenMelts", 20, 1, Integer.MAX_VALUE);
        crucibleNumberOfBuckets = COMMON_BUILDER.comment("Number of buckets the crucible will hold (Default: 4)")
            .defineInRange("crucibleNumberOfBuckets", 4, 1, Integer.MAX_VALUE);

        COMMON_BUILDER.comment("Wooden Crucible Configs").push(SUBCATEGORY_CRUCIBLE_WOOD);
        woodHeatRate = COMMON_BUILDER
            .comment("Heat rate the Wood Crucible will use regardless of heat source below (Default: 2)")
            .defineInRange("woodHeatRate", 2, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }

    private static void crookConfigs() {
        maxBonusStringCount = COMMON_BUILDER
            .comment("Maximum additional string that a crook will drop from infested leaves in addition to the minimum string count (Default: 3)")
            .defineInRange("maxBonusStringCount", 3, 0, Integer.MAX_VALUE);
        minStringCount = COMMON_BUILDER
            .comment("Minimum string that a crook will drop from infested leaves (Default: 2)")
            .defineInRange("minStringCount", 2, 1, Integer.MAX_VALUE);
        vanillaSimulateDropCount = COMMON_BUILDER
            .comment("Number of times the crook will \"break\" a leaf block to get drops (Default: 3)")
            .defineInRange("vanillaDropSimulateCount", 3, 1, Integer.MAX_VALUE);
    }

    private static void barrelConfigs() {
        barrelNumberOfBuckets = COMMON_BUILDER.comment("Number of buckets the barrel will hold (Default: 1)")
            .defineInRange("barrelNumberOfBuckets", 1, 1, Integer.MAX_VALUE);
        rainFillAmount = COMMON_BUILDER.comment("How much fluid rain will fill per iteration (Default: 2)")
            .defineInRange("rainFillAmount", 2, 1, Integer.MAX_VALUE);
        woodBarrelMaxTemp = COMMON_BUILDER
            .comment("The max temperature a barrel can accept; water is 300 (Default: 300)")
            .defineInRange("woodBarrelMaxTemp", 300, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.comment("Mob Spawn Configs").push(SUBCATEGORY_BARREL_MOB);
        secondsToSpawn = COMMON_BUILDER.comment("Number of seconds to spawn mobs (Default: 10)")
            .defineInRange("secondsToSpawnMobs", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Compost Configs").push(SUBCATEGORY_BARREL_COMPOST);
        barrelMaxSolidAmount = COMMON_BUILDER
            .comment("How much solids need to be in barrel before composting starts (Default: 1000)")
            .defineInRange("maxSolidAmount", 1000, 1, Integer.MAX_VALUE);
        secondsToCompost = COMMON_BUILDER.comment("Number of seconds to spawn mobs (Default: 10)")
            .defineInRange("secondsToCompost", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Fluid Transform Configs").push(SUBCATEGORY_BARREL_FLUID);
        secondsToFluidTransform = COMMON_BUILDER.comment("Number of seconds to transform fluids (Default: 10)")
            .defineInRange("secondsToTransformFluid", 10, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave()
            .writingMode(
                WritingMode.REPLACE).build();

        configData.load();
        spec.setConfig(configData);
    }
}
