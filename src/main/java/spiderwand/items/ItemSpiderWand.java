package spiderwand.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import spiderwand.SpiderWand;
import spiderwand.defs.ItemDefs;

public class ItemSpiderWand extends Item {
    public ItemSpiderWand(){
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setMaxStackSize(1);
    }

    public ItemSpiderWand register(String name){
        this.setUnlocalizedName(new ResourceLocation(SpiderWand.MODID, name).toString());
        GameRegistry.register(this, new ResourceLocation(SpiderWand.MODID, name));
        return this;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack item, EntityPlayer plr, World world, BlockPos pos, EnumHand Hand, EnumFacing face, float x, float y, float z) {
        if (!world.isRemote && world.getBlockState(pos).getBlock().hasTileEntity(world.getBlockState(pos))) {
            ItemStack vortex = new ItemStack(ItemDefs.vortexBlock, 1);
            TileEntity te = world.getTileEntity(pos);
            if (te == null)
                return EnumActionResult.FAIL;
            NBTTagCompound nbtContainer = new NBTTagCompound();
            te.writeToNBT(nbtContainer);
            world.removeTileEntity(pos);
            nbtContainer.removeTag("x");
            nbtContainer.removeTag("y");
            nbtContainer.removeTag("z");
            vortex.setTagCompound(new NBTTagCompound());
            vortex.getTagCompound().setInteger("block", Block.getIdFromBlock(world.getBlockState(pos).getBlock()));
            vortex.getTagCompound().setInteger("meta", world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos)));
            vortex.getTagCompound().setTag("tileEntity", (NBTBase)nbtContainer);
            vortex.setStackDisplayName("\u00A73" + vortex.getDisplayName());
            //vortex.getTagCompound().setTag("spiderWandTileData", te.getTileData());
            //vortex.getTagCompound().setTag("SpiderWandTileData",teTags);
            EntityItem vortexEntity = new EntityItem(world);
            vortexEntity.setEntityItemStack(vortex);
            world.removeTileEntity(pos);
            world.setBlockToAir(pos);
            vortexEntity.setPosition(plr.posX, plr.posY, plr.posZ);
            world.spawnEntityInWorld(vortexEntity);
            plr.swingArm(Hand);
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }


}
