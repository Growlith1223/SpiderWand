package spiderwand;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import spiderwand.proxy.IProxy;
import spiderwand.tabs.SpiderWandTab;

@Mod(modid = SpiderWand.MODID, name = SpiderWand.NAME, version = SpiderWand.VERSION)
public class SpiderWand
{
    public static final String MODID = "spiderwand";
    public static final String NAME = "Spider Wand";
    public static final String VERSION = "1.0";

    public static final String CLIENT = "spiderwand.proxy.ClientProxy";
    public static final String SERVER = "spiderwand.proxy.ServerProxy";

    public static final CreativeTabs SpiderWandTab = new SpiderWandTab("tabSpiderWand");

    private static Logger logger;

    @SidedProxy(clientSide = SpiderWand.CLIENT, serverSide = SpiderWand.SERVER)
    public static IProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
