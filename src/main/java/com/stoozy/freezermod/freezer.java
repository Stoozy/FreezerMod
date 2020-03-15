package com.stoozy.freezermod;

import com.stoozy.freezermod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class freezer extends Block {
//    public static Block block = new Block(Block.Properties
//            .create(Material.ANVIL)
//            .hardnessAndResistance(5)
//            .harvestLevel(2)
//            .harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "freezer");

    public freezer(Properties p_i48440_1_) {

        super(p_i48440_1_);
        new Block(p_i48440_1_);
        this.setRegistryName(Reference.MODID, "freezer");
    }


    @Override
    @SuppressWarnings("deprecation")
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        player.sendMessage(player.getDisplayName());
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        player.sendMessage(player.getDisplayName());
    }

}

