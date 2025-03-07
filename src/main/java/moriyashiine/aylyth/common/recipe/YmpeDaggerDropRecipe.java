package moriyashiine.aylyth.common.recipe;

import com.google.gson.JsonObject;
import moriyashiine.aylyth.common.registry.ModRecipeTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class YmpeDaggerDropRecipe implements Recipe<Inventory> {
	private final Identifier identifier;
	public final EntityType<?> entity_type;
	private final ItemStack output;
	public final float chance;
	public final int min;
	public final int max;
	
	public YmpeDaggerDropRecipe(Identifier id, EntityType<?> entity_type, ItemStack output, float chance, int min, int max) {
		this.identifier = id;
		this.entity_type = entity_type;
		this.output = output;
		this.chance = chance;
		this.min = min;
		this.max = max;
	}
	
	@Override
	public boolean matches(Inventory inventory, World world) {
		return false;
	}
	
	@Override
	public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
		return output;
	}
	
	@Override
	public boolean fits(int width, int height) {
		return false;
	}
	
	@Override
	public ItemStack getOutput(DynamicRegistryManager registryManager) {
		return output;
	}
	
	@Override
	public Identifier getId() {
		return identifier;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModRecipeTypes.YMPE_DAGGER_DROP_RECIPE_SERIALIZER;
	}
	
	@Override
	public RecipeType<?> getType() {
		return ModRecipeTypes.YMPE_DAGGER_DROP_RECIPE_TYPE;
	}
	
	public static class Serializer implements RecipeSerializer<YmpeDaggerDropRecipe> {
		@Override
		public YmpeDaggerDropRecipe read(Identifier id, JsonObject json) {
			return new YmpeDaggerDropRecipe(
					id,
					Registries.ENTITY_TYPE.get(new Identifier(JsonHelper.getString(json, "entity_type"))),
					ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result")),
					JsonHelper.getFloat(json, "chance"),
					JsonHelper.getInt(json, "min"),
					JsonHelper.getInt(json, "max")
			);
		}
		
		@Override
		public YmpeDaggerDropRecipe read(Identifier id, PacketByteBuf buf) {
			return new YmpeDaggerDropRecipe(
					id,
					Registries.ENTITY_TYPE.get(new Identifier(buf.readString())),
					buf.readItemStack(),
					buf.readFloat(),
					buf.readInt(),
					buf.readInt()
			);
		}
		
		@Override
		public void write(PacketByteBuf buf, YmpeDaggerDropRecipe recipe) {
			buf.writeString(Registries.ENTITY_TYPE.getId(recipe.entity_type).toString());
			buf.writeItemStack(recipe.getOutput(DynamicRegistryManager.EMPTY));
			buf.writeFloat(recipe.chance);
			buf.writeInt(recipe.min);
			buf.writeInt(recipe.max);
		}
	}
}
