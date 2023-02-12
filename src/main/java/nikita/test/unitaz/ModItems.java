package nikita.test.unitaz;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;
import nikita.test.unitaz.items.*;
//import nikita.test.unitaz.items.EmeraldSword;

public class ModItems {
    public static final Item DILDO = new DildoItem();
    public static final Item RING = new RingItem();
    public static final Item.ToolMaterial EME_TOOL_MATERIAL = EnumHelper.addToolMaterial("unitaz:emerald", 2, 1000, 10F, 2F, 30);
    public static final Item.ToolMaterial BONE_TOOL_MATERIAL = EnumHelper.addToolMaterial("unitaz:bone", 1, 200, 10F, 2F, 30);
    public static final EmeraldTool EMERALD_TOOL = new EmeraldTool();
    public static final BoneSword BONE_SWORD = new BoneSword();
    public static final BoneSpade BONE_SPADE = new BoneSpade();
    public static final BonePickaxe BONE_PICKAXE = new BonePickaxe();
    public static final BoneAxe BONE_AXE = new BoneAxe();
    public static final BoneHoe BONE_HOE = new BoneHoe();

    public static void register() {
        GameRegistry.registerItem(DILDO, "dildo");
        GameRegistry.registerItem(RING, "ring");
        GameRegistry.registerItem(EMERALD_TOOL, "emerald tool");
        GameRegistry.registerItem(BONE_SWORD, "bone sword");
        GameRegistry.registerItem(BONE_AXE,"bone axe");
        GameRegistry.registerItem(BONE_HOE, "bone hoe");
        GameRegistry.registerItem(BONE_SPADE, "bone spade");
        GameRegistry.registerItem(BONE_PICKAXE, "bone pickaxe");

    }
}
