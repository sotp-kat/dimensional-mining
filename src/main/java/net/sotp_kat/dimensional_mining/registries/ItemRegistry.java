package net.sotp_kat.dimensional_mining.registries;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sotp_kat.dimensional_mining.DimensionalMining;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DimensionalMining.MODID);



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
