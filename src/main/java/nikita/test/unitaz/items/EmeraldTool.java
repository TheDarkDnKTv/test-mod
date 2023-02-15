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
import net.minecraftforge.event.entity.player.UseHoeEvent;
import nikita.test.unitaz.ModItems;
import nikita.test.unitaz.UnitazMod;
import nikita.test.unitaz.common.handler.ModTab;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmeraldTool extends ItemTool {

    public EmeraldTool() {
        super(3F, ModItems.EME_TOOL_MATERIAL, null);
        setUnlocalizedName("emerald_tool");
        setTextureName(UnitazMod.MODID + ":emerald_tool");
        setCreativeTab(ModTab.INSTANCE);


    }


    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        switch (meta){
            case 1:
                return super.getUnlocalizedName(stack) + '_' + ToolClass.PICKAXE;
            case 2:
                return super.getUnlocalizedName(stack) + '_' + ToolClass.AXE;
            case 3:
                return super.getUnlocalizedName(stack) + '_' + ToolClass.HOE;
            case 4:
                return super.getUnlocalizedName(stack) + '_' + ToolClass.SPADE;
        }
        return super.getUnlocalizedName(stack);
    }
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, List items) {
        for (int damage = 1, size = ToolClass.values().length; damage <= size; damage++) {
            items.add(new ItemStack(item, 1, damage));
        }

    }

    @Override
    public boolean onItemUse(ItemStack heldStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if(heldStack.getMetadata() == 3) {
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
//        if (toolStack.getMetadata() == 2 && block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine)
//            return super.getStrVsBlock(toolStack, block);
//        if (toolStack.getMetadata() == 1 && block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock)
//            return super.getStrVsBlock(toolStack, block);
//        return efficiencyOnProperMaterial;
        return 20.0F;
    }

    @Override
    public void setDamage(ItemStack stack, int damage)
    {
        //ДА ЗДРАСТВУЮТ БЕССМЕРТНЫЕ ЧИТ ПРЕДМЕТЫ. Считать прочность предмета через метаданные грустно(если я правильно понял как работает это)

    }

    @Override
    public boolean canItemHarvestBlock(Block block) {
        return true;
    }

    static enum ToolClass{
        PICKAXE,
        AXE,
        HOE,
        SPADE;
    }
}
