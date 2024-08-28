package net.sotp_kat.dimensional_mining.registries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sotp_kat.dimensional_mining.DimensionalMining;
import net.sotp_kat.dimensional_mining.util.RemoveAllSpawnsBiomeModifier;

public class CodecRegistry {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, DimensionalMining.MODID);

    public static void register(IEventBus eventBus) {
        BIOME_MODIFIER_SERIALIZERS.register(eventBus);
    }

    public static final RegistryObject<Codec<RemoveAllSpawnsBiomeModifier>> REMOVE_ALL_SPAWNS_BIOME_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("remove_all_spawns", () ->
            RecordCodecBuilder.create(builder -> builder.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(RemoveAllSpawnsBiomeModifier::biomes)
    ).apply(builder, RemoveAllSpawnsBiomeModifier::new)));

}
