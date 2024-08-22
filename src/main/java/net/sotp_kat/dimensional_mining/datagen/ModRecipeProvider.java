package net.sotp_kat.dimensional_mining.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.sotp_kat.dimensional_mining.registries.BlockRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, BlockRegistry.PORTAL_BLOCK.get())
                .pattern("x@x")
                .pattern("@o@")
                .pattern("x@x")
                .define('x', Tags.Items.STONE)
                .define('@', Items.STONE_PICKAXE)
                .define('o', Items.SMOOTH_STONE)
                .unlockedBy(getHasName(Items.STONE_PICKAXE), has(Items.STONE_PICKAXE))
                .save(consumer);

    }
}
