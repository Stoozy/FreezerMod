package com.stoozy.freezermod;

import com.stoozy.freezermod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class freezer extends Block {
    public static Block block = new Block(Block.Properties
            .create(Material.ANVIL)
            .hardnessAndResistance(5)
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "freezer");


    public freezer(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @SubscribeEvent
    public boolean handleInteraction(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult btr){
        player.addStat(Stats.OPEN_CHEST);
        return true;
    }
}
