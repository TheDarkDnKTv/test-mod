package nikita.test.unitaz;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = UnitazMod.MODID, version = UnitazMod.VERSION)
public class UnitazMod {
    public static final String MODID = "unitaz";
    public static final String VERSION = "1.0";
    @SidedProxy(
            clientSide = "ru.mcmodding.tut  orial.client.ClientProxy",
            serverSide = "ru.mcmodding.tutorial.common.CommonProxy"
    )
    public static CommonProxy proxy;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
    public void init(FMLInitializationEvent event) {
		// some example code
        proxy.init(event);
    }
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
