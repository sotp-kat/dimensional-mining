package net.sotp_kat.dimensional_mining.util;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.sotp_kat.dimensional_mining.registries.CodecRegistry;

import java.util.List;

import static net.minecraft.world.level.biome.MobSpawnSettings.*;

public record RemoveAllSpawnsBiomeModifier(HolderSet<Biome> biomes) implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.REMOVE && biomes.contains(biome)) {
            MobSpawnSettingsBuilder spawnBuilder = builder.getMobSpawnSettings();
            for (MobCategory category : MobCategory.values()) {
                List<SpawnerData> spawns = spawnBuilder.getSpawner(category);
                spawns.clear();
            }
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return CodecRegistry.REMOVE_ALL_SPAWNS_BIOME_MODIFIER_TYPE.get();
    }
}
