/*
 * The MIT License
 *
 * Copyright (c) 2024 Anthony Afonin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.anon987666.ipd.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;

public final class ScreenHandlerEvents {

	public static final Event<InsertStackHandler> SET_STACK_IN_SLOT = EventFactory
			.createArrayBacked(InsertStackHandler.class, callbacks -> (handler, slot, revision, stack) -> {
				for (InsertStackHandler callback : callbacks) {
					callback.onSetStackInSlot(handler, slot, revision, stack);
				}
			});

	public static final Event<SlotClickHandler> ON_SLOT_CLICK = EventFactory.createArrayBacked(SlotClickHandler.class,
			callbacks -> (handler, slotIndex, button, actionType, player) -> {
				for (SlotClickHandler callback : callbacks) {
					callback.onSlotClick(handler, slotIndex, button, actionType, player);
				}
			});

	@FunctionalInterface
	public interface SlotClickHandler {
		void onSlotClick(ScreenHandler handler, int slotIndex, int button, SlotActionType actionType,
				PlayerEntity player);
	}

	@FunctionalInterface
	public interface InsertStackHandler {
		void onSetStackInSlot(ScreenHandler handler, int slot, int revision, ItemStack stack);
	}

	private ScreenHandlerEvents() {
		throw new AssertionError("No ScreenHandlerEvents instances for you!");
	}
}
