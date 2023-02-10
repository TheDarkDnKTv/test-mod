package nikita.test.unitaz.common;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import nikita.test.unitaz.ModItems;




public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModItems.register();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
