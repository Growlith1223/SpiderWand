package spiderwand.defs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import spiderwand.items.ItemSpiderWand;
import spiderwand.items.ItemSpiderWandMaterial;
import spiderwand.items.ItemVortexedBlock;
import spiderwand.items.rendering.ItemRenderMesh;

public class ItemDefs {
    public static ItemDefs instance = new ItemDefs();

    // Materials
    public static ItemSpiderWandMaterial vortexGem = (ItemSpiderWandMaterial) new ItemSpiderWandMaterial().register("vortex_gem");
    public static ItemSpiderWandMaterial wandRod = (ItemSpiderWandMaterial) new ItemSpiderWandMaterial().register("wand_rod");
    // Tools
    public static ItemSpiderWand pickSpider = (ItemSpiderWand) new ItemSpiderWand().register("spider_wand");
    public static ItemVortexedBlock vortexBlock = (ItemVortexedBlock) new ItemVortexedBlock().register("vortex");
    @SideOnly(Side.CLIENT)
    public void registerTexture(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, new ItemRenderMesh(new ModelResourceLocation(item.getRegistryName(), "inventory")));
    }

    @SideOnly(Side.CLIENT)
    public void initItemTextures(){
        registerTexture(pickSpider);
        registerTexture(vortexBlock);
        registerTexture(vortexGem);
        registerTexture(wandRod);
    }
}
