package nikita.test.unitaz;

import net.minecraft.item.ItemSword;

public class EmeraldSword extends ItemSword {

    public EmeraldSword() {
        super(ModItems.EMERALD_TOOL_MATERIAL);
        setUnlocalizedName("emerald_sword");
        setTextureName(UnitazMod.MODID + ":emerald_sword");
//        setCreativeTab(ModTab.INSTANCE);
    }
}
