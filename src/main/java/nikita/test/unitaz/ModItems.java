package nikita.test.unitaz;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.*;
import net.minecraftforge.common.util.*;
import nikita.test.unitaz.items.DildoItem;
import nikita.test.unitaz.items.RingItem;
//import nikita.test.unitaz.items.EmeraldSword;

public class ModItems {
    public static final Item DILDO = new DildoItem();
    public static final Item RING = new RingItem();

    //public static final Item.ToolMaterial EME_TOOL_MATERIAL = EnumHelper.addToolMaterial("unitaz:emerald", 2, 1000, 10F, 2F, 30);

    public static void register() {
        GameRegistry.registerItem(DILDO, "dildo");
        GameRegistry.registerItem(RING, "ring");
    }
}
