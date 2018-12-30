package spiderwand.items;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import spiderwand.SpiderWand;
import spiderwand.defs.CreativeTabDefs;

public abstract class ItemSpiderWandBase extends Item {
    ItemSpiderWandBase(){
        this.setCreativeTab(CreativeTabDefs.tabSpiderWandItems);
       // this.setHasSubtypes(true);

    }

    public ItemSpiderWandBase register(String name){
        this.setUnlocalizedName(new ResourceLocation(SpiderWand.MODID, name).toString());
        this.setRegistryName(new ResourceLocation(SpiderWand.MODID, name));
        ForgeRegistries.ITEMS.register(this);
        return this;
    }
}
