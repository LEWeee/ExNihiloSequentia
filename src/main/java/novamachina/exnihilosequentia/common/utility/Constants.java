package novamachina.exnihilosequentia.common.utility;

public class Constants {

    private static final String STONE_TAG = "stone";

    private Constants() {
    }

    public static class Blocks {

        public static final String CRUSHED_ANDESITE = "crushed_andesite";
        public static final String CRUSHED_DIORITE = "crushed_diorite";
        public static final String CRUSHED_END_STONE = "crushed_end_stone";
        public static final String CRUSHED_GRANITE = "crushed_granite";
        public static final String CRUSHED_NETHERRACK = "crushed_netherrack";
        public static final String DUST = "dust";
        public static final String END_CAKE = "end_cake";
        public static final String SIEVE = "sieve";
        public static final String WITCH_WATER = "witchwater";
        public static final String INFESTING_LEAVES = "infesting_leaves";
        public static final String INFESTED_LEAVES = "infested_leaves";
        public static final String CRUCIBLE_UNFIRED = "crucible_unfired";
        public static final String CRUCIBLE_FIRED = "crucible_fired";
        public static final String CRUCIBLE_WOOD = "crucible_wood";
        public static final String BARREL_WOOD = "barrel_wood";
        public static final String SEA_WATER = "sea_water";
        public static final String BARREL_STONE = "barrel_stone";
        public static final String CRUSHED_SKYSTONE = "crushed_skystone";
        private Blocks() {
        }
    }

    public static class Items {

        public static final String CROOK_WOOD = createCrookName("wood");
        public static final String CROOK_STONE = createCrookName(STONE_TAG);
        public static final String CROOK_ANDESITE = createCrookName("andesite");
        public static final String CROOK_GRANITE = createCrookName("granite");
        public static final String CROOK_DIORITE = createCrookName("diorite");
        public static final String CROOK_GOLD = createCrookName("gold");
        public static final String CROOK_IRON = createCrookName("iron");
        public static final String CROOK_DIAMOND = createCrookName("diamond");
        public static final String CROOK_BONE = createCrookName("bone");
        public static final String CROOK_CLAY_UNCOOKED = createCrookName("clay_uncooked");
        public static final String CROOK_CLAY = createCrookName("clay");
        public static final String CROOK_PRISMARINE = createCrookName("prismarine");
        public static final String CROOK_NETHERRACK = createCrookName("netherrack");
        public static final String CROOK_PURPUR = createCrookName("purpur");
        public static final String CROOK_BLAZE = createCrookName("blaze");
        public static final String SILKWORM = "silkworm";
        public static final String HAMMER_WOOD = createHammerName("wood");
        public static final String HAMMER_STONE = createHammerName(STONE_TAG);
        public static final String HAMMER_IRON = createHammerName("iron");
        public static final String HAMMER_DIAMOND = createHammerName("diamond");
        public static final String HAMMER_NETHERITE = createHammerName("netherite");
        public static final String HAMMER_GOLD = createHammerName("gold");
        public static final String SEED_OAK = "oak";
        public static final String SEED_SPRUCE = "spruce";
        public static final String SEED_BIRCH = "birch";
        public static final String SEED_JUNGLE = "jungle";
        public static final String SEED_ACACIA = "acacia";
        public static final String SEED_DARK_OAK = "darkoak";
        public static final String SEED_CACTUS = "cactus";
        public static final String SEED_SUGARCANE = "sugarcane";
        public static final String SEED_CARROT = "carrot";
        public static final String SEED_POTATO = "potato";
        public static final String SEED_SWEET_BERRY = "berry";
        public static final String ANCIENT_SPORE = "ancient_spores";
        public static final String GRASS_SEED = "grass_seeds";
        public static final String COOKED_SILKWORM = "cooked_silkworm";
        public static final String PORCELAIN_CLAY = "porcelain_clay";
        public static final String PEBBLE_STONE = createPebbleName(STONE_TAG);
        public static final String PEBBLE_GRANITE = createPebbleName("granite");
        public static final String PEBBLE_DIORITE = createPebbleName("diorite");
        public static final String PEBBLE_ANDESITE = createPebbleName("andesite");
        public static final String WITCH_WATER_BUCKET = "bucket_witchwater";
        public static final String CRAFTING_DOLL = "doll_crafting";
        public static final String SEED_KELP = "kelp";
        public static final String SEED_PICKLE = "pickle";
        public static final String SEA_WATER_BUCKET = "bucket_sea_water";
        public static final String BLUE_CORAL_SEED = "seed_blue_coral";
        public static final String PINK_CORAL_SEED = "seed_pink_coral";
        public static final String PURPLE_CORAL_SEED = "seed_purple_coral";
        public static final String RED_CORAL_SEED = "seed_red_coral";
        public static final String YELLOW_CORAL_SEED = "seed_yellow_coral";
        public static final String SEED_BAMBOO = "bamboo";
        public static final String BEEHIVE_FRAME = "beehive_frame";
        private Items() {
        }

        private static String createHammerName(String name) {
            return "hammer_" + name;
        }

        private static String createCrookName(String name) {
            return "crook_" + name;
        }

        private static String createPebbleName(String name) {
            return "pebble_" + name;
        }
    }

    public static class Ore {

        public static final String GOLD = "gold";
        public static final String IRON = "iron";
        public static final String COPPER = "copper";
        public static final String LEAD = "lead";
        public static final String NICKEL = "nickel";
        public static final String SILVER = "silver";
        public static final String TIN = "tin";
        public static final String ALUMINUM = "aluminum";
        public static final String PLATINUM = "platinum";
        public static final String OSMIUM = "osmium";
        public static final String URANIUM = "uranium";
        public static final String ZINC = "zinc";
        public static final String BISMUTH = "bismuth";
        private Ore() {
        }
    }

    public static class Fluids {

        public static final String WITCH_WATER = "witchwater";
        public static final String WITCH_WATER_FLOW = "witchwater_flow";
        public static final String SEA_WATER = "sea_water";
        public static final String SEA_WATER_FLOW = "sea_water_flow";
        private Fluids() {
        }
    }

    public static class BarrelModes {

        public static final String EMPTY = "empty";
        public static final String COMPOST = "compost";
        public static final String FLUID = "fluid";
        public static final String BLOCK = "block";
        public static final String MOB = "mob";
        public static final String TRANSFORM = "transform";
        private BarrelModes() {
        }
    }

    public static class ModIds {

        public static final String THERMAL_EXPANSION = "thermalexpansion";
        public static final String IMMERSIVE_ENGINEERING = "immersiveengineering";
        public static final String JEI = "jei";
        public static final String EX_NIHILO_SEQUENTIA = "exnihilosequentia";
        public static final String TOP = "theoneprobe";
        public static final String MEKANISM = "mekanism";
        public static final String CREATE = "create";
        public static final String MINECRAFT = "minecraft";
        public static final String SILENT_MECHANISM = "silent_mechanism";
        private ModIds() {
        }
    }

    public static class Tooltips {

        public static final String ENDERMAN = "tooltip.doll.enderman";
        public static final String BLAZE = "tooltip.doll.blaze";
        public static final String SHULKER = "tooltip.doll.shulker";
        public static final String GUARDIAN = "tooltip.doll.guardian";
        public static final String BEE = "tooltip.doll.bee";
        private Tooltips() {
        }
    }
}
