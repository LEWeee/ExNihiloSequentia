package novamachina.exnihilosequentia.common.block;

import novamachina.exnihilosequentia.common.builder.BlockBuilder;
import novamachina.exnihilosequentia.common.compat.top.ITOPInfoProvider;
import novamachina.exnihilosequentia.common.init.ModBlocks;
import novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import novamachina.exnihilosequentia.common.utility.Config;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.Random;

// TODO: Add progressive render
public class InfestingLeavesBlock extends BaseBlock implements ITOPInfoProvider {

    public InfestingLeavesBlock() {
        super(new BlockBuilder()
            .properties(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(
                SoundType.PLANT).notSolid()).tileEntitySupplier(InfestingLeavesTile::new));
    }

    public static void normalToInfesting(World world, BlockState state, BlockPos pos) {
        world.setBlockState(pos, ModBlocks.INFESTING_LEAVES.get().getDefaultState());
    }

    public static void finishInfestingBlock(World world, BlockPos pos) {
        world.setBlockState(pos, ModBlocks.INFESTED_LEAVES.get().getDefaultState());
    }

    public static void spread(World world, BlockPos pos) {
        if (!world.isRemote()) {
            NonNullList<BlockPos> nearbyLeaves = getNearbyLeaves(world, pos);

            Random random = new Random();
            nearbyLeaves.forEach(leafPos -> {
                if (random.nextDouble() <= Config.SPREAD_CHANCE.get()) {
                    normalToInfesting(world, world.getBlockState(leafPos), leafPos);
                }
            });
        }
    }

    private static NonNullList<BlockPos> getNearbyLeaves(World world, BlockPos pos) {
        NonNullList<BlockPos> nearbyLeaves = NonNullList.create();

        BlockPos.getAllInBox(new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1),
            new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1)).forEach(item -> {
            if (world.getBlockState(item).getBlock() instanceof LeavesBlock) {
                nearbyLeaves.add(new BlockPos(item));
            }
        });

        return nearbyLeaves;
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {

    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {
        InfestingLeavesTile infestingLeavesTile = (InfestingLeavesTile) world.getTileEntity(iProbeHitData.getPos());

        iProbeInfo.text(new TranslationTextComponent("waila.progress", StringUtils
            .formatPercent((float) infestingLeavesTile.getProgress() / 100)));
    }
}
