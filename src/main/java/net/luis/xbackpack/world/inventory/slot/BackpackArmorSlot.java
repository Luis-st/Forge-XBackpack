package net.luis.xbackpack.world.inventory.slot;

import com.mojang.datafixers.util.Pair;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

/**
 * 
 * @author Luis-st
 *
 */

public class BackpackArmorSlot extends Slot {
	
	private final Player player;
	private final EquipmentSlot equipmentSlot;
	
	public BackpackArmorSlot(Inventory inventory, EquipmentSlot equipmentSlot, int index, int xPosition, int yPosition) {
		super(inventory, index, xPosition, yPosition);
		this.player = inventory.player;
		this.equipmentSlot = equipmentSlot;
	}
	
	@Override
	public int getMaxStackSize() {
		return 1;
	}
	
	@Override
	public int getMaxStackSize(ItemStack stack) {
		return 1;
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.canEquip(this.equipmentSlot, this.player);
	}
	
	@Override
	public boolean mayPickup(Player player) {
		ItemStack stack = this.getItem();
		return !stack.isEmpty() && !player.isCreative() && EnchantmentHelper.hasBindingCurse(stack) ? false : super.mayPickup(player);
	}
	
	@Override
	public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
		switch (this.equipmentSlot) {
		case HEAD: return Pair.of(InventoryMenu.BLOCK_ATLAS, InventoryMenu.EMPTY_ARMOR_SLOT_HELMET);
		case CHEST: return Pair.of(InventoryMenu.BLOCK_ATLAS, InventoryMenu.EMPTY_ARMOR_SLOT_CHESTPLATE);
		case LEGS: return Pair.of(InventoryMenu.BLOCK_ATLAS, InventoryMenu.EMPTY_ARMOR_SLOT_LEGGINGS);
		case FEET: return Pair.of(InventoryMenu.BLOCK_ATLAS, InventoryMenu.EMPTY_ARMOR_SLOT_BOOTS);
		default: break;
		}
		return super.getNoItemIcon();
	}
	
}
