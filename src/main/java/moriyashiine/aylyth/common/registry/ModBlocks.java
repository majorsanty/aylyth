package moriyashiine.aylyth.common.registry;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import moriyashiine.aylyth.common.block.*;
import moriyashiine.aylyth.common.util.AylythUtil;
import moriyashiine.aylyth.mixin.BlocksAccessor;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import static net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings.copyOf;

public class ModBlocks {
	private static final BlockSetType YMPE_BLOCK_SET_TYPE = new BlockSetTypeBuilder().register(AylythUtil.id("ympe"));
	private static final BlockSetType POMEGRANATE_BLOCK_SET_TYPE = new BlockSetTypeBuilder().register(AylythUtil.id("pomegranate"));
	private static final BlockSetType WRITHEWOOD_BLOCK_SET_TYPE = new BlockSetTypeBuilder().register(AylythUtil.id("writhewood"));
	private static final WoodType YMPE_WOOD_TYPE = new WoodTypeBuilder().register(AylythUtil.id("ympe"), YMPE_BLOCK_SET_TYPE);
	private static final WoodType POMEGRANATE_WOOD_TYPE = new WoodTypeBuilder().register(AylythUtil.id("pomegranate"), POMEGRANATE_BLOCK_SET_TYPE);
	private static final WoodType WRITHEWOOD_WOOD_TYPE = new WoodTypeBuilder().register(AylythUtil.id("writhewood"), WRITHEWOOD_BLOCK_SET_TYPE);

	public static final FabricBlockSettings STREWN_LEAVES = FabricBlockSettings.create().mapColor(MapColor.DARK_GREEN).notSolid().pistonBehavior(PistonBehavior.DESTROY).replaceable();

	public static final Block YMPE_STRIPPED_LOG = register("stripped_ympe_log", new PillarBlock(copyOf(Blocks.STRIPPED_OAK_LOG)));
	public static final Block YMPE_STRIPPED_WOOD = register("stripped_ympe_wood", new PillarBlock(copyOf(Blocks.STRIPPED_OAK_WOOD)));
	public static final Block YMPE_LOG = register("ympe_log", new PillarBlock(copyOf(Blocks.OAK_LOG)));
	public static final Block YMPE_WOOD = register("ympe_wood", new PillarBlock(copyOf(Blocks.OAK_WOOD)));
	public static final Block YMPE_SAPLING = register("ympe_sapling", new SaplingBlock(new LargeTreeSaplingGenerator() {

		@Override
		protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
			return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, AylythUtil.id("big_ympe_tree"));
		}

		@Override
		protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
			return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, AylythUtil.id("ympe_tree"));
		}
	}, copyOf(Blocks.OAK_SAPLING)));
	public static final Block YMPE_POTTED_SAPLING = register("potted_ympe_sapling", new FlowerPotBlock(YMPE_SAPLING, copyOf(Blocks.POTTED_OAK_SAPLING)));
	public static final Block YMPE_PLANKS = register("ympe_planks", new Block(copyOf(Blocks.OAK_PLANKS)));
	public static final Block YMPE_STAIRS = register("ympe_stairs", new StairsBlock(YMPE_PLANKS.getDefaultState(), copyOf(Blocks.OAK_STAIRS)));
	public static final Block YMPE_SLAB = register("ympe_slab", new SlabBlock(copyOf(Blocks.OAK_SLAB)));
	public static final Block YMPE_FENCE = register("ympe_fence", new FenceBlock(copyOf(Blocks.OAK_FENCE)));
	public static final Block YMPE_FENCE_GATE = register("ympe_fence_gate", new FenceGateBlock(copyOf(Blocks.OAK_FENCE_GATE), YMPE_WOOD_TYPE));
	public static final Block YMPE_PRESSURE_PLATE = register("ympe_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, copyOf(Blocks.OAK_PRESSURE_PLATE), YMPE_BLOCK_SET_TYPE));
	public static final Block YMPE_BUTTON = register("ympe_button", new ButtonBlock(copyOf(Blocks.OAK_BUTTON), YMPE_BLOCK_SET_TYPE, 30, true));
	public static final Block YMPE_TRAPDOOR = register("ympe_trapdoor", new TrapdoorBlock(copyOf(Blocks.OAK_TRAPDOOR), YMPE_BLOCK_SET_TYPE));
	public static final Block YMPE_DOOR = register("ympe_door", new DoorBlock(copyOf(Blocks.OAK_DOOR), YMPE_BLOCK_SET_TYPE));
	public static final TerraformSignBlock YMPE_SIGN = register("ympe_sign", new TerraformSignBlock(AylythUtil.id("entity/signs/ympe"), copyOf(Blocks.OAK_SIGN)));
	public static final Block YMPE_WALL_SIGN = register("ympe_wall_sign", new TerraformWallSignBlock(AylythUtil.id("entity/signs/ympe"), copyOf(Blocks.OAK_WALL_SIGN).dropsLike(YMPE_SIGN)));
	public static final TerraformHangingSignBlock YMPE_HANGING_SIGN = register("ympe_hanging_sign", new TerraformHangingSignBlock(AylythUtil.id("entity/signs/hanging/ympe"), AylythUtil.id("textures/gui/hanging_signs/ympe"), copyOf(Blocks.OAK_HANGING_SIGN)));
	public static final Block YMPE_WALL_HANGING_SIGN = register("ympe_wall_hanging_sign", new TerraformWallHangingSignBlock(AylythUtil.id("entity/signs/hanging/ympe"), AylythUtil.id("textures/gui/hanging_signs/ympe"), copyOf(Blocks.OAK_HANGING_SIGN).dropsLike(YMPE_HANGING_SIGN)));
	public static final Block YMPE_LEAVES = register("ympe_leaves", BlocksAccessor.callCreateLeavesBlock(BlockSoundGroup.GRASS));
	public static final Block FRUIT_BEARING_YMPE_LOG = register("fruit_bearing_ympe_log", new FruitBearingYmpeLogBlock(copyOf(ModBlocks.YMPE_LOG)));

	public static final Block POMEGRANATE_STRIPPED_LOG = register("stripped_pomegranate_log", new PillarBlock(copyOf(Blocks.STRIPPED_OAK_LOG)));
	public static final Block POMEGRANATE_STRIPPED_WOOD = register("stripped_pomegranate_wood", new PillarBlock(copyOf(Blocks.STRIPPED_OAK_WOOD)));
	public static final Block POMEGRANATE_LOG = register("pomegranate_log", new PillarBlock(copyOf(Blocks.OAK_LOG)));
	public static final Block POMEGRANATE_WOOD = register("pomegranate_wood", new PillarBlock(copyOf(Blocks.OAK_WOOD)));
	public static final Block POMEGRANATE_SAPLING = register("pomegranate_sapling", new SaplingBlock(new SaplingGenerator() {

		@Nullable
		@Override
		protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
			return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, AylythUtil.id("pomegranate_tree"));
		}
	}, copyOf(Blocks.OAK_SAPLING)));
	public static final Block POMEGRANATE_POTTED_SAPLING = register("potted_pomegranate_sapling", new FlowerPotBlock(POMEGRANATE_SAPLING, copyOf(Blocks.POTTED_OAK_SAPLING)));
	public static final Block POMEGRANATE_PLANKS = register("pomegranate_planks", new Block(copyOf(Blocks.OAK_PLANKS)));
	public static final Block POMEGRANATE_STAIRS = register("pomegranate_stairs", new StairsBlock(POMEGRANATE_PLANKS.getDefaultState(), copyOf(Blocks.OAK_STAIRS)));
	public static final Block POMEGRANATE_SLAB = register("pomegranate_slab", new SlabBlock(copyOf(Blocks.OAK_SLAB)));
	public static final Block POMEGRANATE_FENCE = register("pomegranate_fence", new FenceBlock(copyOf(Blocks.OAK_FENCE)));
	public static final Block POMEGRANATE_FENCE_GATE = register("pomegranate_fence_gate", new FenceGateBlock(copyOf(Blocks.OAK_FENCE_GATE), POMEGRANATE_WOOD_TYPE));
	public static final Block POMEGRANATE_PRESSURE_PLATE = register("pomegranate_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, copyOf(Blocks.OAK_PRESSURE_PLATE), POMEGRANATE_BLOCK_SET_TYPE));
	public static final Block POMEGRANATE_BUTTON = register("pomegranate_button", new ButtonBlock(copyOf(Blocks.OAK_BUTTON), POMEGRANATE_BLOCK_SET_TYPE, 30, true));
	public static final Block POMEGRANATE_TRAPDOOR = register("pomegranate_trapdoor", new TrapdoorBlock(copyOf(Blocks.OAK_TRAPDOOR), POMEGRANATE_BLOCK_SET_TYPE));
	public static final Block POMEGRANATE_DOOR = register("pomegranate_door", new DoorBlock(copyOf(Blocks.OAK_DOOR), POMEGRANATE_BLOCK_SET_TYPE));
	public static final TerraformSignBlock POMEGRANATE_SIGN = register("pomegranate_sign", new TerraformSignBlock(AylythUtil.id("entity/signs/pomegranate"), copyOf(Blocks.OAK_SIGN)));
	public static final Block POMEGRANATE_WALL_SIGN = register("pomegranate_wall_sign", new TerraformWallSignBlock(AylythUtil.id("entity/signs/pomegranate"), copyOf(Blocks.OAK_WALL_SIGN).dropsLike(POMEGRANATE_SIGN)));
	public static final TerraformHangingSignBlock POMEGRANATE_HANGING_SIGN = register("pomegranate_hanging_sign", new TerraformHangingSignBlock(AylythUtil.id("entity/signs/hanging/pomegranate"), AylythUtil.id("textures/gui/hanging_signs/pomegranate"), copyOf(Blocks.OAK_HANGING_SIGN)));
	public static final Block POMEGRANATE_WALL_HANGING_SIGN = register("pomegranate_wall_hanging_sign", new TerraformWallHangingSignBlock(AylythUtil.id("entity/signs/hanging/pomegranate"), AylythUtil.id("textures/gui/hanging_signs/pomegranate"), copyOf(Blocks.OAK_HANGING_SIGN).dropsLike(POMEGRANATE_HANGING_SIGN)));
	public static final Block POMEGRANATE_LEAVES = register("pomegranate_leaves", new PomegranateLeavesBlock(copyOf(Blocks.OAK_LEAVES)));

	public static final Block WRITHEWOOD_STRIPPED_LOG = register("stripped_writhewood_log", new PillarBlock(copyOf(Blocks.STRIPPED_OAK_LOG)));
	public static final Block WRITHEWOOD_STRIPPED_WOOD = register("stripped_writhewood_wood", new PillarBlock(copyOf(Blocks.STRIPPED_OAK_WOOD)));
	public static final Block WRITHEWOOD_LOG = register("writhewood_log", new PillarBlock(copyOf(Blocks.OAK_LOG)));
	public static final Block WRITHEWOOD_WOOD = register("writhewood_wood", new PillarBlock(copyOf(Blocks.OAK_WOOD)));
	public static final Block WRITHEWOOD_SAPLING = register("writhewood_sapling", new WaterloggableSaplingBlock(new SaplingGenerator() {

		@Nullable
		@Override
		protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
			return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, AylythUtil.id("writhewood_tree"));
		}
	}, copyOf(Blocks.OAK_SAPLING)));
	public static final Block WRITHEWOOD_POTTED_SAPLING = register("potted_writhewood_sapling", new FlowerPotBlock(WRITHEWOOD_SAPLING, copyOf(Blocks.POTTED_OAK_SAPLING)));
	public static final Block WRITHEWOOD_PLANKS = register("writhewood_planks", new Block(copyOf(Blocks.OAK_PLANKS)));
	public static final Block WRITHEWOOD_STAIRS = register("writhewood_stairs", new StairsBlock(WRITHEWOOD_PLANKS.getDefaultState(), copyOf(Blocks.OAK_STAIRS)));
	public static final Block WRITHEWOOD_SLAB = register("writhewood_slab", new SlabBlock(copyOf(Blocks.OAK_SLAB)));
	public static final Block WRITHEWOOD_FENCE = register("writhewood_fence", new FenceBlock(copyOf(Blocks.OAK_FENCE)));
	public static final Block WRITHEWOOD_FENCE_GATE = register("writhewood_fence_gate", new FenceGateBlock(copyOf(Blocks.OAK_FENCE_GATE), WRITHEWOOD_WOOD_TYPE));
	public static final Block WRITHEWOOD_PRESSURE_PLATE = register("writhewood_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, copyOf(Blocks.OAK_PRESSURE_PLATE), WRITHEWOOD_BLOCK_SET_TYPE));
	public static final Block WRITHEWOOD_BUTTON = register("writhewood_button", new ButtonBlock(copyOf(Blocks.OAK_BUTTON), WRITHEWOOD_BLOCK_SET_TYPE, 30, true));
	public static final Block WRITHEWOOD_TRAPDOOR = register("writhewood_trapdoor", new TrapdoorBlock(copyOf(Blocks.OAK_TRAPDOOR), WRITHEWOOD_BLOCK_SET_TYPE));
	public static final Block WRITHEWOOD_DOOR = register("writhewood_door", new DoorBlock(copyOf(Blocks.OAK_DOOR), WRITHEWOOD_BLOCK_SET_TYPE));
	public static final TerraformSignBlock WRITHEWOOD_SIGN = register("writhewood_sign", new TerraformSignBlock(AylythUtil.id("entity/signs/writhewood"), copyOf(Blocks.OAK_SIGN)));
	public static final Block WRITHEWOOD_WALL_SIGN = register("writhewood_wall_sign", new TerraformWallSignBlock(AylythUtil.id("entity/signs/writhewood"), copyOf(Blocks.OAK_WALL_SIGN).dropsLike(WRITHEWOOD_SIGN)));
	public static final TerraformHangingSignBlock WRITHEWOOD_HANGING_SIGN = register("writhewood_hanging_sign", new TerraformHangingSignBlock(AylythUtil.id("entity/signs/hanging/writhewood"), AylythUtil.id("textures/gui/hanging_signs/writhewood"), copyOf(Blocks.OAK_HANGING_SIGN)));
	public static final Block WRITHEWOOD_WALL_HANGING_SIGN = register("writhewood_wall_hanging_sign", new TerraformWallHangingSignBlock(AylythUtil.id("entity/signs/hanging/writhewood"), AylythUtil.id("textures/gui/hanging_signs/writhewood"), copyOf(Blocks.OAK_HANGING_SIGN).dropsLike(WRITHEWOOD_HANGING_SIGN)));
	public static final Block WRITHEWOOD_LEAVES = register("writhewood_leaves", BlocksAccessor.callCreateLeavesBlock(BlockSoundGroup.GRASS));

	public static final Block SEEPING_WOOD = register("seeping_wood", new PillarBlock(copyOf(Blocks.OAK_WOOD)));
	public static final Block GIRASOL_SAPLING = register("girasol_sapling", new GirasolSaplingBlock(new SaplingGenerator() {
		@Nullable
		@Override
		protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
			return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, AylythUtil.id("seeping_tree"));
		}
	}, copyOf(Blocks.OAK_SAPLING)));
	public static final Block GIRASOL_SAPLING_POTTED = register("potted_girasol_sapling", new FlowerPotBlock(GIRASOL_SAPLING, copyOf(Blocks.FLOWER_POT)));

	public static final Block AYLYTH_BUSH = register("aylyth_bush", new BushBlock());
	public static final Block ANTLER_SHOOTS = register("antler_shoots", new AntlerShootsBlock());
	public static final Block GRIPWEED = register("gripweed", new GripweedBlock());
	
	public static final Block NYSIAN_GRAPE_VINE = register("nysian_grape_vine", new NysianGrapeVineBlock(copyOf(Blocks.VINE)));

	public static final Block MARIGOLD = register("marigolds", new FlowerBlock(ModPotions.MORTECHIS_EFFECT, 9, copyOf(Blocks.DANDELION)));
	public static final Block MARIGOLD_POTTED = register("potted_marigolds", new FlowerPotBlock(MARIGOLD, copyOf(Blocks.FLOWER_POT)));
	public static final Block JACK_O_LANTERN_MUSHROOM = register("jack_o_lantern_mushroom", new JackolanternMushroomBlock(ModBlocks::wallSupplier, FabricBlockSettings.create().notSolid().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WART_BLOCK).noCollision().nonOpaque().ticksRandomly().luminance(state -> state.get(JackolanternShelfMushroomBlock.GLOWING) ? state.get(StagedMushroomPlantBlock.STAGE)+2 : 0)));
	public static final Block SHELF_JACK_O_LANTERN_MUSHROOM = register("shelf_jack_o_lantern_mushroom", new JackolanternShelfMushroomBlock(() -> JACK_O_LANTERN_MUSHROOM, FabricBlockSettings.create().notSolid().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.WART_BLOCK).noCollision().nonOpaque().ticksRandomly().luminance(state -> state.get(JackolanternShelfMushroomBlock.GLOWING) ? 3 : 0)));
	public static final Block GHOSTCAP_MUSHROOM = register("ghostcap_mushroom", new SpreadingPlantBlock(FabricBlockSettings.create().notSolid().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GLOW_LICHEN).noCollision().nonOpaque().ticksRandomly()));

	public static final Block OAK_STREWN_LEAVES = register("oak_strewn_leaves", new StrewnLeavesBlock(FabricBlockSettings.copyOf(STREWN_LEAVES).sounds(ModSoundEvents.STREWN_LEAVES)));
	public static final Block YMPE_STREWN_LEAVES = register("ympe_strewn_leaves", new StrewnLeavesBlock(FabricBlockSettings.copyOf(STREWN_LEAVES).sounds(ModSoundEvents.STREWN_LEAVES)));

	public static final Block SMALL_WOODY_GROWTH = register("small_woody_growth", new SmallWoodyGrowthBlock(FabricBlockSettings.create().burnable().strength(2.0f).sounds(BlockSoundGroup.WOOD)));
	public static final Block LARGE_WOODY_GROWTH = register("large_woody_growth", new LargeWoodyGrowthBlock(FabricBlockSettings.create().burnable().strength(2.0f).sounds(BlockSoundGroup.WOOD)));
	public static final Block WOODY_GROWTH_CACHE = register("woody_growth_cache", new WoodyGrowthCacheBlock(FabricBlockSettings.create().burnable().strength(2.0f).sounds(BlockSoundGroup.WOOD)));

	public static final Block OAK_SEEP = register("oak_seep", new SeepBlock());
	public static final Block SPRUCE_SEEP = register("spruce_seep", new SeepBlock());
	public static final Block DARK_OAK_SEEP = register("dark_oak_seep", new SeepBlock());
	public static final Block YMPE_SEEP = register("ympe_seep", new SeepBlock());
	public static final Block SEEPING_WOOD_SEEP = register("seeping_wood_seep", new SeepBlock());

	public static final Block DARK_WOODS_TILES = register("dark_woods_tiles", new Block(copyOf(Blocks.DARK_OAK_PLANKS)));
	public static final Block ESSTLINE_BLOCK = register("esstline_block", new Block(copyOf(Blocks.DIAMOND_BLOCK).mapColor(MapColor.BLACK)));
	public static final Block NEPHRITE_BLOCK = register("nephrite_block", new Block(copyOf(Blocks.DIAMOND_BLOCK).mapColor(MapColor.PALE_GREEN)));

	public static final Block CARVED_SMOOTH_NEPHRITE = register("carved_smooth_nephrite", new Block(copyOf(Blocks.DIAMOND_BLOCK).mapColor(MapColor.PALE_GREEN)));
	public static final Block CARVED_ANTLERED_NEPHRITE = register("carved_antlered_nephrite", new Block(copyOf(Blocks.DIAMOND_BLOCK).mapColor(MapColor.PALE_GREEN)));
	public static final Block CARVED_NEPHRITE_PILLAR = register("carved_nephrite_pillar", new PillarBlock(copyOf(Blocks.DIAMOND_BLOCK).mapColor(MapColor.PALE_GREEN)));
	public static final Block CARVED_NEPHRITE_TILES = register("carved_nephrite_tiles", new Block(copyOf(Blocks.DIAMOND_BLOCK).mapColor(MapColor.PALE_GREEN)));
	public static final Block CARVED_WOODY_NEPHRITE = register("carved_woody_nephrite", new Block(copyOf(Blocks.DIAMOND_BLOCK).mapColor(MapColor.PALE_GREEN)));

	public static final Block SOUL_HEARTH = register("soul_hearth", new SoulHearthBlock(copyOf(Blocks.DEEPSLATE)));
	public static final Block VITAL_THURIBLE = register("vital_thurible", new VitalThuribleBlock(copyOf(Blocks.DEEPSLATE)));

	private static <T extends Block> T register(String name, T item) {
		Registry.register(Registries.BLOCK, AylythUtil.id(name), item);
		return item;
	}

	public static void init() {
		FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
		flammableRegistry.add(YMPE_STRIPPED_LOG, 5, 5);
		flammableRegistry.add(YMPE_STRIPPED_WOOD, 5, 5);
		flammableRegistry.add(YMPE_LOG, 5, 5);
		flammableRegistry.add(YMPE_WOOD, 5, 5);
		flammableRegistry.add(YMPE_PLANKS, 5, 20);
		flammableRegistry.add(YMPE_STAIRS, 5, 20);
		flammableRegistry.add(YMPE_SLAB, 5, 20);
		flammableRegistry.add(YMPE_FENCE, 5, 20);
		flammableRegistry.add(YMPE_FENCE_GATE, 5, 20);
		flammableRegistry.add(YMPE_LEAVES, 30, 60);
		flammableRegistry.add(POMEGRANATE_STRIPPED_LOG, 5, 5);
		flammableRegistry.add(POMEGRANATE_STRIPPED_WOOD, 5, 5);
		flammableRegistry.add(POMEGRANATE_LOG, 5, 5);
		flammableRegistry.add(POMEGRANATE_WOOD, 5, 5);
		flammableRegistry.add(POMEGRANATE_PLANKS, 5, 20);
		flammableRegistry.add(POMEGRANATE_STAIRS, 5, 20);
		flammableRegistry.add(POMEGRANATE_SLAB, 5, 20);
		flammableRegistry.add(POMEGRANATE_FENCE, 5, 20);
		flammableRegistry.add(POMEGRANATE_FENCE_GATE, 5, 20);
		flammableRegistry.add(POMEGRANATE_LEAVES, 30, 60);
		flammableRegistry.add(WRITHEWOOD_STRIPPED_LOG, 5, 5);
		flammableRegistry.add(WRITHEWOOD_STRIPPED_WOOD, 5, 5);
		flammableRegistry.add(WRITHEWOOD_LOG, 5, 5);
		flammableRegistry.add(WRITHEWOOD_WOOD, 5, 5);
		flammableRegistry.add(WRITHEWOOD_PLANKS, 5, 20);
		flammableRegistry.add(WRITHEWOOD_STAIRS, 5, 20);
		flammableRegistry.add(WRITHEWOOD_SLAB, 5, 20);
		flammableRegistry.add(WRITHEWOOD_FENCE, 5, 20);
		flammableRegistry.add(WRITHEWOOD_FENCE_GATE, 5, 20);
		flammableRegistry.add(WRITHEWOOD_LEAVES, 30, 60);
		flammableRegistry.add(SEEPING_WOOD, 5, 5);
		flammableRegistry.add(AYLYTH_BUSH, 60, 100);

		StrippableBlockRegistry.register(YMPE_LOG, YMPE_STRIPPED_LOG);
		StrippableBlockRegistry.register(YMPE_WOOD, YMPE_STRIPPED_WOOD);
		StrippableBlockRegistry.register(FRUIT_BEARING_YMPE_LOG, YMPE_STRIPPED_LOG);

		StrippableBlockRegistry.register(POMEGRANATE_LOG, POMEGRANATE_STRIPPED_LOG);
		StrippableBlockRegistry.register(POMEGRANATE_WOOD, POMEGRANATE_STRIPPED_WOOD);

		StrippableBlockRegistry.register(WRITHEWOOD_LOG, WRITHEWOOD_STRIPPED_LOG);
		StrippableBlockRegistry.register(WRITHEWOOD_WOOD, WRITHEWOOD_STRIPPED_WOOD);
	}

	private static SpreadingPlantBlock wallSupplier() {
		return (SpreadingPlantBlock) SHELF_JACK_O_LANTERN_MUSHROOM;
	}
}
