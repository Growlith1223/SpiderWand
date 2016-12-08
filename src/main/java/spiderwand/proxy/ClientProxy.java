package spiderwand.proxy;

import net.minecraftforge.common.MinecraftForge;
import spiderwand.defs.ItemDefs;
import spiderwand.events.EventsHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();
        MinecraftForge.EVENT_BUS.register(new EventsHandler());
    }

    @Override
    public void init() {
        super.init();
        ItemDefs.instance.initItemTextures();
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
