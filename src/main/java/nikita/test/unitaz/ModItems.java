package nikita.test.unitaz;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.*;
import net.minecraftforge.common.util.*;

public class ModItems {
    public static final ItemSword SWORD = new EmeraldSword();
    public static final Item.ToolMaterial EMERALD_TOOL_MATERIAL = EnumHelper.addToolMaterial("unitaz:emerald", 2, 1000, 10F, 2F, 30);
    public static void register() {
        GameRegistry.registerItem(SWORD, "emerald sword");
    }
}
