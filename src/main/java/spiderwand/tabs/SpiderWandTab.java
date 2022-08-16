package spiderwand.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import spiderwand.SpiderWand;
import spiderwand.init.modItems;

public class SpiderWandTab extends CreativeTabs {
    public SpiderWandTab(String name) {
        super(SpiderWand.MODID + "." + name);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(modItems.SpiderGem);
    }
}
