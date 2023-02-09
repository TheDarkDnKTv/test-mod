package nikita.test.unitaz.Items;

import net.minecraft.item.ItemSword;
import nikita.test.unitaz.ModItems;
import nikita.test.unitaz.UnitazMod;

public class EmeraldSword extends ItemSword {

    public EmeraldSword() {
        super(ModItems.EME_TOOL_MATERIAL);
        setUnlocalizedName("emerald_sword");
        setTextureName(UnitazMod.MODID + ":emerald_sword");
//        setCreativeTab(ModTab.INSTANCE);
    }
}
