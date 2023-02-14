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

    public static final Set<Block> HARVEST_BLOCKS = Sets.newHashSet();
    public static final String[] ToolName = new String[]{
                "spade",
                "axe",
                "pickaxe",
                "hoe"
    };
    private static String ToolClass = "";

    public EmeraldTool() {
        super(3F, ModItems.EME_TOOL_MATERIAL, HARVEST_BLOCKS);
        setTextureName(UnitazMod.MODID + ":emerald_tool");
        setCreativeTab(ModTab.INSTANCE);
        for(String ToolName : ToolName)
        {
            ToolClass = ToolName;
        }

        final int harvestLvl = ModItems.EME_TOOL_MATERIAL.getHarvestLevel();

        setHarvestLevel("axe", harvestLvl);
        setHarvestLevel("pickaxe", harvestLvl);
        setHarvestLevel("shovel", harvestLvl);
    }

    public void toolClass(String toolClass){
        // EXPLANATION START

        // чел ты...
        // ты в курсе, что существует оператор switch?

        // все поля, переменные тоже с маленькой буквы в формате былОдинПидр
        switch (toolClass) {
            case "pickaxe":
                ToolClass = "pickaxe";
                break;
            case "axe":
                ToolClass = "axe";
                break;
        }

        // а вообще, если тебе надо убедиться что строка в границах этих 4 слов, то:
        final Set<String> strs = new HashSet<>();
        strs.add("pickaxe");
        strs.add("axe");
        strs.add("hoe");
        strs.add("spade");

        if (strs.contains(toolClass)) {
            ToolClass = toolClass; // но всё равно в этом методе смысла -0
        }

        // EXPLANATION END

        if(toolClass == "pickaxe"){
            this.ToolClass = "pickaxe";
        }
        else if (toolClass == "axe")
        {
            this.ToolClass = "axe";
        }
        else if (toolClass == "hoe")
        {
            this.ToolClass = "hoe";
        }
        else if (toolClass == "spade")
        {
            this.ToolClass = "spade";
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        /*
            EXPLANATION START

            какой ноль?! ToolName[0];
            а мету получить из стака?

            int meta = stack.getMetadata();
            и затем
            ToolName[meta];

            EXPLANATION END
         */


        return super.getUnlocalizedName(stack) + '_' + this.toolMaterial + '_' + ToolName[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item item, CreativeTabs tab, List items) {
        // этот метод нужен для того, чтобы на клиенте добавить в список предметов все стаки, которые должны отображаться
        // ибо на сервере стак может быть с метой аж 0-32767
        // нигде нет регистра какая мета валидная
        // ещё раз, внимательно ЧИТАЙ код, без гаданий на кофейной гуще

        for (int damage = 0, size = ToolName.length; damage < size; damage++) {
            items.add(new ItemStack(item, 1, ToolName.length));
        }

    }

    @Override
    public boolean onItemUse(ItemStack heldStack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        // опять же вслепую копируешь, не читая код, хотя всё на англ написанно по факту
        // если нажать выделив метод ctrl+u, то можно найти родительский, или просто навестись на имя тут
        // И воуля, ДОКУМЕНТАЦИЯ (описание)

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
        return false;
    }

    @Override
    public float getStrVsBlock(ItemStack toolStack, Block block) {
        /*
        как должно стать ясно из название и провки юзания, этот метод отвечает за СКОРОСТЬ ломания блока этим предметом
         */
        if (block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine)
            return super.getStrVsBlock(toolStack, block);
        return efficiencyOnProperMaterial;
    }

    @Override
    public boolean canItemHarvestBlock(Block block) {
        /*
        напротив этот метод же узнаёт, можно ли ломать этим предметом блок или нет
         */
        if (block == Blocks.obsidian)
            return toolMaterial.getHarvestLevel() == 1;
        return block != Blocks.diamond_block && block != Blocks.diamond_ore ? block != Blocks.emerald_ore && block != Blocks.emerald_block ? block != Blocks.gold_block && block != Blocks.gold_ore ? block != Blocks.iron_block && block != Blocks.iron_ore ? block != Blocks.lapis_block && block != Blocks.lapis_ore ? block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? block.getMaterial() == Material.rock || block.getMaterial() == Material.iron || block.getMaterial() == Material.anvil : toolMaterial.getHarvestLevel() >= 2 : toolMaterial.getHarvestLevel() >= 1 : toolMaterial.getHarvestLevel() >= 1 : toolMaterial.getHarvestLevel() >= 2 : toolMaterial.getHarvestLevel() >= 2 : toolMaterial.getHarvestLevel() >= 2;
    }

    static {
        // опять ебанный switch забыл...

        switch (ToolClass) {
            case "pickaxe": {
                // код тут

                break; // без него так же выполниться код внизу switch, то есть другие 'case'
            }
        }

        // тут даже IDE подсвечивает и говорит, что это не будет работать

        if (ToolClass == "pickaxe") {
            HARVEST_BLOCKS.add(Blocks.obsidian);
            HARVEST_BLOCKS.add(Blocks.emerald_ore);
            HARVEST_BLOCKS.add(Blocks.emerald_block);
            HARVEST_BLOCKS.addAll(BonePickaxe.HARVEST_BLOCKS);
        }
        if (ToolClass == "axe") {
            HARVEST_BLOCKS.addAll(BoneAxe.HARVEST_BLOCKS);
        }
        if (ToolClass == "spade") {
            HARVEST_BLOCKS.addAll(BoneSpade.HARVEST_BLOCKS);
        }
    }
}
