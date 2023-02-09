package nikita.test.unitaz;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;



//класс для выполнения действий, которые должны выполняться на стороне и клиента, и сервера.

public class CommonProxy {
    //класс для выполнения действий, которые должны выполняться на стороне и клиента, и сервера.
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.register();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
