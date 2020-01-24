package com.stoozy.freezermod;

import com.stoozy.freezermod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(Reference.MODID)
public class freezerMod {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public void freezerMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(modid = Reference.MODID, bus=Mod.EventBusSubscriber.Bus.MOD)
    @ObjectHolder(Reference.MODID)

    public static class RegistryEvents {

        public static Block FREEZER_BLOCK = null;

        @SubscribeEvent

        public static void RegisterBlocks(final RegistryEvent.Register<Block> event) {
            IForgeRegistry registry = event.getRegistry();

            // register a new block here
             RegistryEvents.FREEZER_BLOCK = new Block(Block.Properties
                    .create(Material.ANVIL)
                    .hardnessAndResistance(5)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)).setRegistryName(Reference.MODID, "freezer");

            registry.register(FREEZER_BLOCK);
            LOGGER.info("BLOCKS REGISTERED");
        }

        @SubscribeEvent
        public static void RegisterItems(final RegistryEvent.Register<Item> event){
            IForgeRegistry registry = event.getRegistry();
//            Item FREEZER_ITEM = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(FREEZER_BLOCK.getRegistryName());
            Item FREEZER_BLOCKITEM = new BlockItem(RegistryEvents.FREEZER_BLOCK, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(FREEZER_BLOCK.getRegistryName());

            registry.register(FREEZER_BLOCKITEM);
//            registry.register(FREEZER_ITEM );

            LOGGER.info("ITEMS REGISTERED");
        }


    }
}
