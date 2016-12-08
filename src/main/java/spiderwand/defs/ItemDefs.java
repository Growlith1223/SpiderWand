package spiderwand.defs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import spiderwand.SpiderWand;
import spiderwand.items.ItemSpiderWand;
import spiderwand.items.ItemVortexedBlock;
import spiderwand.items.rendering.ItemRenderMesh;

public class ItemDefs {
    public static ItemDefs instance = new ItemDefs();
    public static ItemSpiderWand pickSpider;
    public static ItemVortexedBlock vortexBlock;

    public void initItems(){
        pickSpider = new ItemSpiderWand().register("spider_wand");
        vortexBlock = new ItemVortexedBlock().register("vortex");
    }

    @SideOnly(Side.CLIENT)
    public void registerTexture(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, new ItemRenderMesh(new ModelResourceLocation(item.getRegistryName(), "inventory")));
    }

    @SideOnly(Side.CLIENT)
    public void initItemTextures(){
        registerTexture(pickSpider);
        registerTexture(vortexBlock);
    }
}
