package net.sotp_kat.dimensional_mining;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sotp_kat.dimensional_mining.registries.BlockRegistry;
import net.sotp_kat.dimensional_mining.registries.CodecRegistry;
import net.sotp_kat.dimensional_mining.registries.CreativeTabRegistry;
import net.sotp_kat.dimensional_mining.registries.ItemRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DimensionalMining.MODID)
public class DimensionalMining {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "dimensional_mining";


    public DimensionalMining() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ItemRegistry.register(modEventBus);
        BlockRegistry.register(modEventBus);
        CreativeTabRegistry.register(modEventBus);
        CodecRegistry.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}
