package net.sotp_kat.dimensional_mining.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.sotp_kat.dimensional_mining.DimensionalMining;

public class BiomeRegistry {
    public static final ResourceKey<Biome> MINING_DIM_BIOME = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(DimensionalMining.MODID, "mining_dim_biome"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(MINING_DIM_BIOME, miningDimBiome(context));
    }

    public static void defaultFeaturesGen(BiomeGenerationSettings.Builder builder) {
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE);
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);
    }

    public static Biome miningDimBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        defaultFeaturesGen(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder, true);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(1.0f)
                .temperature(0.9f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(7521781)
                        .waterFogColor(1323088)
                        .skyColor(11301116)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(13689599)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
                .build();
    }
}
