package spiderwand.util;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import spiderwand.SpiderWand;
import spiderwand.item.ItemSpiderWand;
import spiderwand.item.SpiderWandBase;
import spiderwand.item.WebbedContents;

@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        final Item[] items = {
                new SpiderWandBase("spider_gem"),
                new SpiderWandBase("wand_rod"),
                new ItemSpiderWand(),
                new WebbedContents()
        };
        event.getRegistry().registerAll(items);
    }
}
