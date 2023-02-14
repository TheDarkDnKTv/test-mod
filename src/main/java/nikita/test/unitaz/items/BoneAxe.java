package nikita.test.unitaz.items;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import nikita.test.unitaz.ModItems;
import nikita.test.unitaz.UnitazMod;
import nikita.test.unitaz.common.handler.ModTab;

import java.util.Set;

public class BoneAxe extends ItemTool {
    public static final Set<Block> HARVEST_BLOCKS = Sets.newHashSet(
            Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin
    );

    public BoneAxe() {
        super(3F, ModItems.BONE_TOOL_MATERIAL, HARVEST_BLOCKS);
        setUnlocalizedName("bone_axe");
        setTextureName(UnitazMod.MODID + ":bone_axe");
        setCreativeTab(ModTab.INSTANCE);

        setHarvestLevel("axe", ModItems.BONE_TOOL_MATERIAL.getHarvestLevel());
    }

    @Override
    public float getStrVsBlock(ItemStack toolStack, Block block) {
        if (block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine)
            return super.getStrVsBlock(toolStack, block);
        return efficiencyOnProperMaterial;
    }
}
