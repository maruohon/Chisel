package com.cricketcraft.chisel.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.cricketcraft.chisel.init.ChiselBlocks;
import com.cricketcraft.ctmlib.CTM;
import com.cricketcraft.ctmlib.TextureSubmap;

import static com.cricketcraft.ctmlib.Dir.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SubmapManagerFakeController extends SubmapManagerBase {

	private TextureSubmap map;
	private CTM ctm = CTM.getInstance();

	public SubmapManagerFakeController() {
		ctm.disableObscuredFaceCheck = true;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return map.getSubIcon(0, 0);
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		ctm.buildConnectionMap(world, x, y, z, side, ChiselBlocks.futura, 2);
		if (ctm.connectedAnd(TOP, BOTTOM, LEFT, RIGHT)) {
			return map.getSubIcon(1, 1);
		} else if (ctm.connectedAnd(TOP, BOTTOM)) {
			return map.getSubIcon(0, 1);
		} else if (ctm.connectedAnd(LEFT, RIGHT)) {
			return map.getSubIcon(1, 0);
		} else {
			return map.getSubIcon(0, 0);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(String modName, Block block, IIconRegister register) {
		map = new TextureSubmap(register.registerIcon(modName + ":futura/WIP/controllerWIP-ctm"), 2, 2);
	}
}
