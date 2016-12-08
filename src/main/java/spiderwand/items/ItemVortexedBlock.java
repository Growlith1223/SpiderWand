package spiderwand.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import spiderwand.SpiderWand;

public class ItemVortexedBlock extends Item {
    public ItemVortexedBlock(){
        this.setMaxStackSize(1);
    }
    public ItemVortexedBlock register(String name){
        this.setUnlocalizedName(new ResourceLocation(SpiderWand.MODID, name).toString());
        GameRegistry.register(this, new ResourceLocation(SpiderWand.MODID, name));
        return this;
    }

    @Override
    public EnumActionResult onItemUse(ItemStack item, EntityPlayer plr, World world, BlockPos pos, EnumHand Hand, EnumFacing face, float x, float y, float z) {
        //if (world.isRemote){
            BlockPos sidePos = face == null ? pos : pos.add(face.getDirectionVec());
            if (item.getTagCompound().hasKey("tileEntity")) {
                world.setBlockState(sidePos, Block.getStateById(item.getTagCompound().getInteger("block")), 3);
                world.setTileEntity(sidePos, TileEntity.create(world, (NBTTagCompound) item.getTagCompound().getTag("tileEntity")));
                plr.setHeldItem(Hand, null);
                return EnumActionResult.SUCCESS;
            }
        //}
        return EnumActionResult.FAIL;
    }
}
