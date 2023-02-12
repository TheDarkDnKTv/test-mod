package nikita.test.unitaz.items;

import net.minecraft.item.ItemHoe;
import nikita.test.unitaz.ModItems;
import nikita.test.unitaz.UnitazMod;
import nikita.test.unitaz.common.handler.ModTab;

public class BoneHoe extends ItemHoe {
    public BoneHoe(){
        super(ModItems.BONE_TOOL_MATERIAL);
        setUnlocalizedName("bone_hoe");
        setTextureName(UnitazMod.MODID + "bone_hoe");
        setCreativeTab(ModTab.INSTANCE);
    }

}
