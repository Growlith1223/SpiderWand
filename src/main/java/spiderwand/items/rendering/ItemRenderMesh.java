package spiderwand.items.rendering;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public class ItemRenderMesh implements ItemMeshDefinition {
    private final ModelResourceLocation loc;
    public ItemRenderMesh(ModelResourceLocation loc){
        this.loc = loc;
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack itemStack) {
        return loc;
    }
}
