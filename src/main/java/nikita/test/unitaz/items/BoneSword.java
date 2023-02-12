package nikita.test.unitaz.items;

import net.minecraft.item.ItemSword;
import nikita.test.unitaz.ModItems;
import nikita.test.unitaz.UnitazMod;
import nikita.test.unitaz.common.handler.ModTab;

public class BoneSword extends ItemSword {
    public BoneSword() {
        super(ModItems.BONE_TOOL_MATERIAL);
        setUnlocalizedName("bone_sword");
        setTextureName(UnitazMod.MODID + ":bone_sword");
        setCreativeTab(ModTab.INSTANCE);
    }
}
