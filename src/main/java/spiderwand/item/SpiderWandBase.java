package spiderwand.item;

import net.minecraft.item.Item;
import spiderwand.SpiderWand;

public class SpiderWandBase extends Item {
    public SpiderWandBase(String name) {
        setRegistryName(name);
        setUnlocalizedName(SpiderWand.MODID + "." + name);
        setCreativeTab(SpiderWand.SpiderWandTab);
    }
}
