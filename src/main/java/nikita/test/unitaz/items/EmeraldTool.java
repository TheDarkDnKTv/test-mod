package nikita.test.unitaz.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;
import nikita.test.unitaz.UnitazMod;
import nikita.test.unitaz.common.handler.ModTab;

import java.util.List;

public class EmeraldTool extends ItemTool {

    public static final ToolMaterial EME_TOOL_MATERIAL = EnumHelper.addToolMaterial("unitaz:emerald", 2, 1000, 10F, 2F, 30);

    public EmeraldTool() {
        super(0,EME_TOOL_MATERIAL,null);
        setHasSubtypes(true);
        setUnlocalizedName("emerald_tool");
        setTextureName(UnitazMod.MODID + ":emerald_tool");
        setCreativeTab(ModTab.INSTANCE);
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
    public float func_150893_a(ItemStack p_150893_1_, Block p_150893_2_) {
        return 1;
    }
}
