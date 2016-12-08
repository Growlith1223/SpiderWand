package spiderwand.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by Growlith1223 on 12/7/2016.
 */
public class ItemWandRod extends ItemSpiderWandMaterial {

    @Override
    public Object[] getRecipe() {
        return new Object[]{"III", "ISI", "III", 'I', Items.IRON_INGOT, 'S', Items.STICK};
    }
}
