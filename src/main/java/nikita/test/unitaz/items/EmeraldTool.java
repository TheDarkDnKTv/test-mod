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
import net.minecraft.item.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import nikita.test.unitaz.ModItems;
import nikita.test.unitaz.UnitazMod;
import nikita.test.unitaz.common.handler.ModTab;

import java.util.List;
import java.util.Set;

import static nikita.test.unitaz.ModItems.EME_TOOL_MATERIAL;

public class EmeraldTool extends ItemTool {



//    public EmeraldTool() {
//        super(0, EME_TOOL_MATERIAL, null);
//
//        setHasSubtypes(true);
//        setUnlocalizedName("emerald_tool");
//        setTextureName(UnitazMod.MODID + ":emerald_tool");
//        setCreativeTab(ModTab.INSTANCE);
//    }
//
//    @Override
//    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
//        return 1;
//    }
public static final Set<Block> HARVEST_BLOCKS = Sets.newHashSet();

    public EmeraldTool() {
        super(5F, ModItems.EME_TOOL_MATERIAL, HARVEST_BLOCKS);
        setTextureName(UnitazMod.MODID + ":emerald_tool");
        setCreativeTab(ModTab.INSTANCE);

        final int harvestLvl = ModItems.EME_TOOL_MATERIAL.getHarvestLevel();
        setHarvestLevel("axe", harvestLvl);
        setHarvestLevel("pickaxe", harvestLvl);
        setHarvestLevel("shovel", harvestLvl);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        // Добавим суффикс, чтобы к item.balloon было дописано название цвета: item.balloon_*colorName* в зависимости от типа

        return super.getUnlocalizedName(stack) + '_' + ItemDye.field_150921_b[stack.getItemDamage() % ItemDye.field_150921_b.length];
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, List items) {
        for (int damage = 0, size = ItemDye.field_150922_c.length; damage < size; damage++) {
            items.add(new ItemStack(item, 1, damage));
        }
    }

    @Override
    public boolean onItemUse(ItemStack heldStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
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
                world.playSoundEffect((float) posX + 0.5F, (float) posY + 0.5F, (float) posZ + 0.5F, farmland.stepSound.getStepResourcePath(), (farmland.stepSound.getVolume() + 1.0F) / 2.0F, farmland.stepSound.getPitch() * 0.8F);

                if (!world.isRemote) {
                    world.setBlock(posX, posY, posZ, farmland);
                    heldStack.damageItem(1, player);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public float func_150893_a(ItemStack toolStack, Block block) {
        if (block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine)
            return super.func_150893_a(toolStack, block);
        return efficiencyOnProperMaterial;
    }

    @Override
    public boolean func_150897_b(Block block) {
        if (block == Blocks.obsidian)
            return toolMaterial.getHarvestLevel() == 1;
        return block != Blocks.diamond_block && block != Blocks.diamond_ore ? block != Blocks.emerald_ore && block != Blocks.emerald_block ? block != Blocks.gold_block && block != Blocks.gold_ore ? block != Blocks.iron_block && block != Blocks.iron_ore ? block != Blocks.lapis_block && block != Blocks.lapis_ore ? block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? block.getMaterial() == Material.rock || block.getMaterial() == Material.iron || block.getMaterial() == Material.anvil : toolMaterial.getHarvestLevel() >= 2 : toolMaterial.getHarvestLevel() >= 1 : toolMaterial.getHarvestLevel() >= 1 : toolMaterial.getHarvestLevel() >= 2 : toolMaterial.getHarvestLevel() >= 2 : toolMaterial.getHarvestLevel() >= 2;
    }

    static {
        HARVEST_BLOCKS.add(Blocks.obsidian);
        HARVEST_BLOCKS.add(Blocks.emerald_ore);
        HARVEST_BLOCKS.add(Blocks.emerald_block);

//        HARVEST_BLOCKS.addAll(RubyAxe.HARVEST_BLOCKS);
//        HARVEST_BLOCKS.addAll(RubyPickaxe.HARVEST_BLOCKS);
//        HARVEST_BLOCKS.addAll(RubySpade.HARVEST_BLOCKS);
    }


}
