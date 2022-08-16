package spiderwand.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import spiderwand.init.modItems;

public class ItemSpiderWand extends SpiderWandBase {
    public ItemSpiderWand() {
        super("spider_wand");
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return EnumActionResult.SUCCESS;
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        if (!world.isRemote) {
            return pickupStorage(player, world, pos);
        }
        return EnumActionResult.FAIL;
    }

    private EnumActionResult pickupStorage(EntityPlayer player, World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        TileEntity te = world.getTileEntity(pos);
        if (te == null) return EnumActionResult.FAIL;

        ItemStack webbedContents = new ItemStack(modItems.Webbed_Contents, 1);
        webbedContents.setStackDisplayName("\u00A73" + webbedContents.getDisplayName());
        NBTTagCompound tileEntityContents = new NBTTagCompound();
        te.writeToNBT(tileEntityContents);
        tileEntityContents.removeTag("x");
        tileEntityContents.removeTag("y");
        tileEntityContents.removeTag("z");
        world.removeTileEntity(pos);
        world.setBlockToAir(pos);

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("block", block.getRegistryName().toString());
        tag.setInteger("state", block.getMetaFromState(state));
        tag.setTag("contents", tileEntityContents);
        webbedContents.setTagCompound(tag);

        EntityItem webbedEntityItem = new EntityItem(world);
        webbedEntityItem.setItem(webbedContents);
        webbedEntityItem.setPosition(player.posX, player.posY, player.posZ);
        world.spawnEntity(webbedEntityItem);
        return EnumActionResult.SUCCESS;
    }
}
