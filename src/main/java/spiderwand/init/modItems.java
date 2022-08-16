package spiderwand.init;


import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import spiderwand.SpiderWand;

@GameRegistry.ObjectHolder(SpiderWand.MODID)
public class modItems {
    @GameRegistry.ObjectHolder("spider_wand")
    public static final Item SpiderWand = null;

    @GameRegistry.ObjectHolder("spider_gem")
    public static final Item SpiderGem = null;

    @GameRegistry.ObjectHolder("wand_rod")
    public static final Item IronRod = null;
    @GameRegistry.ObjectHolder("webbed_contents")
    public static final Item Webbed_Contents = null;
}
