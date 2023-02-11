package nikita.test.unitaz.common.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import nikita.test.unitaz.UnitazMod;
import nikita.test.unitaz.ModItems;

public class ModTab extends CreativeTabs{
    public static final ModTab INSTANCE = new ModTab();

    private ModTab() {
        super(UnitazMod.MODID);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return ModItems.DILDO;
    }
}
