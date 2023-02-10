package nikita.test.unitaz.items;

import net.minecraft.item.Item;
import nikita.test.unitaz.UnitazMod;

public class RingItem extends Item{
    public RingItem(){
        setUnlocalizedName("ring");
        setTextureName(UnitazMod.MODID + ":ring");
        setMaxStackSize(1);
    }
}
