package spiderwand.items;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

/**
 * Created by Growlith1223 on 12/7/2016.
 */
public class ItemVortexGem extends ItemSpiderWandMaterial {

    @Override
    public Object[] getRecipe() {
        return new Object[]{"SSS", "SGS", "SSS", 'S', Blocks.GLASS, 'G', Items.GOLD_INGOT};
    }
}
