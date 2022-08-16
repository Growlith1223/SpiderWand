package spiderwand.util;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import spiderwand.item.ItemSpiderWand;
import spiderwand.item.WebbedContents;

@Mod.EventBusSubscriber
public class EventsHandler {

//    @SubscribeEvent
//    public void onItemTooltip(ItemTooltipEvent event) {
//        if (event.getItemStack().getItem() instanceof WebbedContents) {
//
//            if (event.getItemStack().getTagCompound() != null) {
//                if (event.getItemStack().getTagCompound().hasKey("block")) {
//                    event.getToolTip().add("currently holding: \u00A7e" + new ItemStack(Block.getBlockById(event.getItemStack().getTagCompound().getInteger("contents")), 1).getDisplayName());
//                }
//            }
//        }
//    }
}
