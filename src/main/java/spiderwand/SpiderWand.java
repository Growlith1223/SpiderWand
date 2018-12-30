package spiderwand;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import spiderwand.proxy.CommonProxy;

import java.io.File;

@Mod(modid= SpiderWand.MODID, version= SpiderWand.VERSION)
public class SpiderWand {
    // Mod Info
    public static final String MODID = "spiderwand";
    public static final String VERSION = "GRADLE:VERSION" + "GRADLE:BUILD";

    // Proxy
    @SidedProxy(clientSide="spiderwand.proxy.ClientProxy", serverSide="spiderwand.proxy.CommonProxy", modId=MODID)
    public static CommonProxy proxy;

    @Instance(MODID)
    public static SpiderWand instance = new SpiderWand();
    private File configDir;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        configDir = new File(event.getModConfigurationDirectory(), "SpiderWand");
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){ proxy.init(); }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){ proxy.postInit(); }
}
