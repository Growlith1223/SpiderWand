package spiderwand.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import spiderwand.SpiderWand;
import spiderwand.defs.CreativeTabDefs;

public abstract class ItemSpiderWandMaterial extends Item {
    public abstract Object[] getRecipe();
    ItemSpiderWandMaterial(){
        this.setCreativeTab(CreativeTabDefs.tabSpiderWandItems);
        this.setHasSubtypes(true);
        if (this.getRecipe() != null)
            GameRegistry.addRecipe(new ItemStack(this, 1), this.getRecipe());
    }

    public ItemSpiderWandMaterial register(String name){
        this.setUnlocalizedName(new ResourceLocation(SpiderWand.MODID, name).toString());
        GameRegistry.register(this, new ResourceLocation(SpiderWand.MODID, name));
        return this;
    }
}
