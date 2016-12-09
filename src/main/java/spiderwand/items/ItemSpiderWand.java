package spiderwand.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import spiderwand.SpiderWand;
import spiderwand.defs.ItemDefs;

import java.util.List;

public class ItemSpiderWand extends ItemSpiderWandBase {
    private int charges;
    private final int MaxCharges = 4600;
    @Override
    public Object[] getRecipe() {
        return new Object[]{"RGR", " I ", " I ", 'R', Items.REDSTONE, 'G', ItemDefs.vortexGem, 'I', ItemDefs.wandRod};
    }

    public ItemSpiderWand(){
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setMaxStackSize(1);
        this.charges = this.MaxCharges - 1;
        this.setMaxDamage(4600);
        this.setNoRepair();
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
            EntityItem vortexEntity = new EntityItem(world);
            vortexEntity.setEntityItemStack(vortex);
            world.removeTileEntity(pos);
            world.setBlockToAir(pos);
            vortexEntity.setPosition(plr.posX, plr.posY, plr.posZ);
            world.spawnEntityInWorld(vortexEntity);
            plr.swingArm(Hand);
            if (!plr.isCreative()) {
                item.getTagCompound().setInteger("spiderWandCharge", this.getChargesLeft(item) - 1);
                if (this.getChargesLeft(item) > this.getMaxCharges(item)) {
                    plr.setHeldItem(Hand, new ItemStack(ItemDefs.vortexGem));
                }
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
    public int getChargesLeft(ItemStack stack){
        if (stack.getTagCompound() != null)
            if (stack.getTagCompound().hasKey("spiderWandCharge"))
                return stack.getTagCompound().getInteger("spiderWandCharge");
        return 0;
    }

    public int getMaxCharges(ItemStack stack){
        if (stack.getTagCompound() != null)
            if (stack.getTagCompound().hasKey("spiderWandMaxCharge"))
                return stack.getTagCompound().getInteger("spiderWandMaxCharge");
        return 0;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_onUpdate_4_, boolean p_onUpdate_5_) {
        if (stack.getTagCompound() == null){
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setInteger("spiderWandCharge", 4599);
            stack.getTagCompound().setInteger("spiderWandMaxCharge", 4600);
        }
        if (stack.getTagCompound().getInteger("spiderWandMaxCharge") - stack.getItemDamage() != stack.getTagCompound().getInteger("spiderWandCharge"))
            stack.setItemDamage(Math.abs(stack.getTagCompound().getInteger("spiderWandMaxCharge") - stack.getTagCompound().getInteger("spiderWandCharge")));
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTab, List<ItemStack> stackList) {
        ItemStack stack = new ItemStack(item, 1);
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setInteger("spiderWandCharge", 1);
        stack.getTagCompound().setInteger("spiderWandMaxCharge", 4600);
        stack.setItemDamage(4599);
        stackList.add(stack);
        stack = new ItemStack(item, 1);
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setInteger("spiderWandCharge", 4599);
        stack.getTagCompound().setInteger("spiderWandMaxCharge", 4600);
        stack.setItemDamage(1);
        stackList.add(stack);
    }
}
