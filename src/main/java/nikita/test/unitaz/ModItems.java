package nikita.test.unitaz;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.*;
import nikita.test.unitaz.items.DildoItem;
import nikita.test.unitaz.items.EmeraldTool;
import nikita.test.unitaz.items.RingItem;
//import nikita.test.unitaz.items.EmeraldSword;

public class ModItems {
    public static final Item DILDO = new DildoItem();
    public static final Item RING = new RingItem();
    public static final EmeraldTool EMERALD_TOOL = new EmeraldTool();

    public static void register() {
        GameRegistry.registerItem(DILDO, "dildo");
        GameRegistry.registerItem(RING, "ring");
        GameRegistry.registerItem(EMERALD_TOOL, "emerald tool");
    }
}
