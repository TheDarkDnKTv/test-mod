package nikita.test.unitaz.items;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;
import nikita.test.unitaz.ModItems;
import nikita.test.unitaz.UnitazMod;
import nikita.test.unitaz.common.handler.ModTab;

import java.util.Set;

public class BoneSpade extends ItemTool {
    public static final Set<Block> HARVEST_BLOCKS = Sets.newHashSet(
                Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer,
                Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium
    );

    public BoneSpade() {
            super(1F, ModItems.BONE_TOOL_MATERIAL, HARVEST_BLOCKS);
            setUnlocalizedName("bone_spade");
            setTextureName(UnitazMod.MODID + ":bone_spade");
            setCreativeTab(ModTab.INSTANCE);

            setHarvestLevel("shovel", ModItems.BONE_TOOL_MATERIAL.getHarvestLevel());
    }
}
