/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * 
 * Botania is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 * 
 * File Created @ [Mar 25, 2014, 8:49:01 PM (GMT)]
 */
package vazkii.botania.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.lib.LibResources;
import vazkii.botania.common.item.ItemLexicon;
import vazkii.botania.common.item.ModItems;

public class RenderLexicon implements IItemRenderer {

	ModelBook model = new ModelBook();
	ResourceLocation texture = new ResourceLocation(LibResources.MODEL_LEXICA);

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		Minecraft mc = Minecraft.getMinecraft();
		mc.renderEngine.bindTexture(texture);
		float opening = 0F;
		float pageFlip = 0F;

		int ticks = ClientTickHandler.ticksWithLexicaOpen;
		GL11.glTranslatef(0.3F + 0.02F * ticks, 0.475F + 0.01F * ticks, -0.2F - 0.01F * ticks);
		GL11.glRotatef(87.5F + ticks * 5, 0F, 1F, 0F);
		GL11.glRotatef(ticks * 2.5F, 0F, 0F, 1F);
		GL11.glScalef(0.9F, 0.9F, 0.9F);
		opening = ticks / 12F;
		pageFlip = ClientTickHandler.pageFlipTicks / 5F;

		model.render(null, 0F, 0F, pageFlip, opening, 0F, 1F / 16F);
		if(ticks < 3) {
			FontRenderer font = Minecraft.getMinecraft().fontRenderer;
			GL11.glRotatef(180F, 0F, 0F, 0F);
			GL11.glTranslatef(-0.3F, -0.2F, 0.07F);
			GL11.glScalef(0.0035F, 0.0035F, 0.0035F);
			boolean bevo = Minecraft.getMinecraft().thePlayer.getCommandSenderName().equals("BevoLJ");
			boolean slak = Minecraft.getMinecraft().thePlayer.getCommandSenderName().equals("Slakkoo");
			String title = ModItems.lexicon.getItemStackDisplayName(null);
			String origTitle = title;
			
			if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null)
				title = Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getDisplayName();
			if(title.equals(origTitle) && bevo)
				title = StatCollector.translateToLocal("item.botania:lexicon.bevo");
			
			font.drawString(font.trimStringToWidth(title, 80), 0, 0, 0xD69700);
			GL11.glTranslatef(0F, 10F, 0F);
			GL11.glScalef(0.6F, 0.6F, 0.6F);
			font.drawString(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.BOLD + String.format(StatCollector.translateToLocal("botaniamisc.edition"), ItemLexicon.getEdition()), 0, 0, 0xA07100);
			
			if(slak)
			{
				GL11.glTranslatef(0F, 15F, 0F);
				font.drawString(StatCollector.translateToLocal("botaniamisc.XX_lexiconcover0"), 0, 0, 0x79ff92);
				
				GL11.glTranslatef(0F, 10F, 0F);
				font.drawString(EnumChatFormatting.UNDERLINE + StatCollector.translateToLocal("botaniamisc.XX_lexiconcover1"), 0, 0, 0x79ff92);
	
				GL11.glTranslatef(0F, 25F, 0F);
				GL11.glPushMatrix();
				GL11.glScalef(6F, 6F, 6F);
				GL11.glTranslatef(5F, -0.3F, 0F);
				font.drawString(EnumChatFormatting.BOLD + "~", 0, 0, 0x002206);
				GL11.glPopMatrix();
				
				GL11.glTranslatef(0F, 25F, 0F);
				font.drawString(StatCollector.translateToLocal("botaniamisc.XX_lexiconcover2"), 0, 0, 0x79ff92);
				GL11.glTranslatef(0F, 10F, 0F);
				font.drawString(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("botaniamisc.XX_lexiconcover3"), 0, 0, 0x79ff92);
			}
			else
			{
				GL11.glTranslatef(0F, 15F, 0F);
				font.drawString(StatCollector.translateToLocal("botaniamisc.lexiconcover0"), 0, 0, 0x79ff92);
				
				GL11.glTranslatef(0F, 10F, 0F);
				font.drawString(EnumChatFormatting.UNDERLINE + StatCollector.translateToLocal("botaniamisc.lexiconcover1"), 0, 0, 0x79ff92);
	
				GL11.glTranslatef(0F, 25F, 0F);
				GL11.glPushMatrix();
				GL11.glScalef(6F, 6F, 6F);
				GL11.glTranslatef(5F, -0.3F, 0F);
				font.drawString(EnumChatFormatting.BOLD + "~", 0, 0, 0x002206);
				GL11.glPopMatrix();
				
				GL11.glTranslatef(0F, 25F, 0F);
				font.drawString(StatCollector.translateToLocal("botaniamisc.lexiconcover2"), 0, 0, 0x79ff92);
				GL11.glTranslatef(0F, 10F, 0F);
				font.drawString(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("botaniamisc.lexiconcover3"), 0, 0, 0x79ff92);
			}
			
			if(bevo) {
				GL11.glTranslatef(0F, 10F, 0F);
				font.drawString(StatCollector.translateToLocal("botaniamisc.lexiconcover4"), 0, 0, 0x79ff92);
			}
		}		

		GL11.glPopMatrix();
	}

}
