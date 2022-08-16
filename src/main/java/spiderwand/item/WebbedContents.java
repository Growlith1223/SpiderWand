package spiderwand.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class WebbedContents extends SpiderWandBase {
    public WebbedContents() {
        super("webbed_contents");
        this.setMaxStackSize(1);
        this.setCreativeTab(null);
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip, ITooltipFlag tooltipFlag) {
        NBTTagCompound tag = itemStack.getTagCompound();
        if (tag == null) return;
        if (!tag.hasKey("contents")) return;
        NBTTagCompound contents = tag.getCompoundTag("contents");
        String id = tag.getString("block");
        Block block = Block.getBlockFromName(id);
        tooltip.add("Currently Holding: [" + TextFormatting.YELLOW + (block != null ? block.getLocalizedName() : id) + TextFormatting.WHITE + "]");
        int limit = 6;
        if (GuiScreen.isShiftKeyDown()) {
            NBTTagList items = (NBTTagList)contents.getTag("Items");
            if (items != null) {
                for (NBTBase item : (NBTTagList) contents.getTag("Items")) {
                    if (limit == 0) {
                        tooltip.add("And more...");
                        return;
                    }
                    limit--;
                    NBTTagCompound localizedTag = (NBTTagCompound) item;
                    ItemStack tagItem = new ItemStack(Item.REGISTRY.getObject(new ResourceLocation(localizedTag.getString("id"))), localizedTag.getInteger("Count"));
                    tooltip.add(TextFormatting.BOLD.toString() + TextFormatting.WHITE.toString() + tagItem.getCount() + "x " + tagItem.getDisplayName());
                }
            }
        } else {
            tooltip.add("Hold shift to reveal contents");
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float x, float y, float z) {
        ItemStack item = player.getHeldItem(hand);

        if (item.getTagCompound() == null) return EnumActionResult.FAIL;
        BlockPos destPos = facing == null ? pos : pos.add(facing.getDirectionVec());
        NBTTagCompound tag = item.getTagCompound();
        Block block = Block.getBlockFromName(tag.getString("block"));
        if (!tag.hasKey("contents")) return EnumActionResult.FAIL;
        NBTTagCompound contents = tag.getCompoundTag("contents");
        IBlockState blockState = block.getStateFromMeta(tag.getInteger("state"));
        if (!world.mayPlace(block, destPos, false, facing, player)) return EnumActionResult.FAIL;
        world.setBlockState(destPos, blockState, 3);
        world.setTileEntity(destPos, TileEntity.create(world, (NBTTagCompound)contents));
        player.setHeldItem(hand, ItemStack.EMPTY);
        return EnumActionResult.SUCCESS;
    }
}
