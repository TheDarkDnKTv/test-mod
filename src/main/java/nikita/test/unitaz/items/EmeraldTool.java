package nikita.test.unitaz.items;

import com.google.common.collect.Sets;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import nikita.test.unitaz.ModItems;
import nikita.test.unitaz.UnitazMod;
import nikita.test.unitaz.common.handler.ModTab;

import java.util.List;
import java.util.Set;


public class EmeraldTool extends ItemTool {

    float efficiencyOnProperMaterial = 2.0F;

    public EmeraldTool() {
        super(3F, ModItems.EME_TOOL_MATERIAL, null);
        setUnlocalizedName("emerald_tool");
        setTextureName(UnitazMod.MODID + ":emerald_tool");
        setCreativeTab(ModTab.INSTANCE);
    }


    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta >= 0 && meta < ToolClass.values().length) {
            ToolClass itemClass = ToolClass.values()[meta];
            return super.getUnlocalizedName() + '_' + itemClass.name().toLowerCase();
        }
        return super.getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, List items) {
        for (int damage = 0, size = ToolClass.values().length; damage < size; damage++) {
            items.add(new ItemStack(item, 1, damage));
        }

    }

    @Override
    public boolean onItemUse(ItemStack heldStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (heldStack.getMetadata() == ToolClass.HOE.ordinal()) {
            if (player.canPlayerEdit(posX, posY, posZ, side, heldStack)) {
                final UseHoeEvent event = new UseHoeEvent(player, heldStack, world, posX, posY, posZ);
                if (MinecraftForge.EVENT_BUS.post(event)) {
                    return false;
                }

                if (event.getResult() == Event.Result.ALLOW) {
                    heldStack.damageItem(1, player);
                    return true;
                }

                final Block blockAtPos = world.getBlock(posX, posY, posZ);

                if (side != 0 && world.getBlock(posX, posY + 1, posZ).isAir(world, posX, posY + 1, posZ) && (blockAtPos == Blocks.grass || blockAtPos == Blocks.dirt)) {
                    final Block farmland = Blocks.farmland;
                    world.playSoundEffect((float) posX + 0.5F, (float) posY + 0.5F, (float) posZ + 0.5F, farmland.stepSound.getStepSound(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.getFrequency() * 0.8F);

                    if (!world.isRemote) {
                        world.setBlock(posX, posY, posZ, farmland);
                        heldStack.damageItem(1, player);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public float getStrVsBlock(ItemStack toolStack, Block block) {
        Set effectiveBlocks;
        if (toolStack.getMetadata() ==  ToolClass.PICKAXE.ordinal()) {

            effectiveBlocks = Sets.newHashSet(Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail);
            if (block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock && !effectiveBlocks.contains(block)) {
                return 1.0F;
            }
            return efficiencyOnProperMaterial;
        } else if (toolStack.getMetadata() ==  ToolClass.AXE.ordinal()) {
            effectiveBlocks = Sets.newHashSet(Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin);
            if (block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine && !effectiveBlocks.contains(block)) {
                return 1.0F;
            }
                return efficiencyOnProperMaterial;
        } else if (toolStack.getMetadata() == ToolClass.SPADE.ordinal()) {
            effectiveBlocks = Sets.newHashSet(Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium);
            if (!effectiveBlocks.contains(block)) {
                return 1.0F;
            }
            return efficiencyOnProperMaterial;
        }
        return 1.0F;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean canHarvestBlock(Block block,ItemStack toolStack)
    {
        Set effectiveBlocks;
        if (toolStack.getMetadata() ==  ToolClass.PICKAXE.ordinal()) {
            effectiveBlocks = Sets.newHashSet(Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail);
            return effectiveBlocks.contains(block);
        } else if (toolStack.getMetadata() ==  ToolClass.AXE.ordinal()) {
            effectiveBlocks = Sets.newHashSet(Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin);
            return effectiveBlocks.contains(block);
        } else if (toolStack.getMetadata() == ToolClass.SPADE.ordinal()) {
            effectiveBlocks = Sets.newHashSet(Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium);
            return effectiveBlocks.contains(block);
        }
        return false;
    }
    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
////        Integer ret = toolClasses.get(toolClass);
//        return ret == null ? -1 : ret;
        return 1;
    }


    enum ToolClass {
        PICKAXE,
        AXE,
        HOE,
        SPADE;
    }
}
