package spiderwand.events;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import spiderwand.items.ItemVortexedBlock;

/**
 * Created by Growlith1223 on 12/7/2016.
 */
public class EventsHandler {

    @SubscribeEvent
    public void onItemTooltip(ItemTooltipEvent e){
        if (e.getItemStack().getItem() instanceof ItemVortexedBlock) {
            if (e.getItemStack().getTagCompound() != null) {
                if (e.getItemStack().getTagCompound().hasKey("block")) {
                    e.getToolTip().add("currently holding: \u00A7e" + new ItemStack(Block.getBlockById(e.getItemStack().getTagCompound().getInteger("block")), 1).getDisplayName());
                }
            }
        }
    }
}
