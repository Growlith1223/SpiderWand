package spiderwand.items;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemVortexedBlock extends ItemSpiderWandBase{
    public ItemVortexedBlock(){
        this.setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer plr, World world, BlockPos pos, EnumHand Hand, EnumFacing face, float x, float y, float z) {
        //if (world.isRemote){
        ItemStack item = plr.getHeldItem(Hand);
        if (item.getTagCompound() == null)
            return EnumActionResult.FAIL;
            BlockPos sidePos = face == null ? pos : pos.add(face.getDirectionVec());
            if (item.getTagCompound().hasKey("tileEntity")) {
                Block block = Block.getBlockById(item.getTagCompound().getInteger("block"));
                IBlockState blockState = block.getStateFromMeta(item.getTagCompound().getInteger("meta"));
                world.setBlockState(sidePos, blockState, 3);

                world.setTileEntity(sidePos, TileEntity.create(world, (NBTTagCompound) item.getTagCompound().getTag("tileEntity")));
                //world.getBlockState(sidePos).getBlock().rotateBlock(world, sidePos, face);
                plr.setHeldItem(Hand, ItemStack.EMPTY);
                for (int i = 0; i < world.rand.nextInt(20); i++){
                    float f5 = (plr.getRNG().nextFloat() * 2.0F - 1.0F);
                    float f4 = (plr.getRNG().nextFloat() * 2.0F - 1.0F);
                    plr.getEntityWorld().spawnParticle(EnumParticleTypes.CLOUD, sidePos.getX() + f5, sidePos.getY(), sidePos.getZ() + f4, (plr.getRNG().nextFloat() - 0.5f) * 0.2f, plr.getRNG().nextFloat() * 0.1f, (plr.getRNG().nextFloat() - 0.5f) * 0.2f);                }
                return EnumActionResult.SUCCESS;
            }
        //}
        return EnumActionResult.FAIL;
    }
}
