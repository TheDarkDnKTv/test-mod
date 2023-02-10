package nikita.test.unitaz.items;

import net.minecraft.item.Item;
import nikita.test.unitaz.UnitazMod;

public class DildoItem extends Item {
    public DildoItem(){
        setUnlocalizedName("dildo");
        setTextureName(UnitazMod.MODID + ":dildo");
        setMaxStackSize(1);
    }
}
