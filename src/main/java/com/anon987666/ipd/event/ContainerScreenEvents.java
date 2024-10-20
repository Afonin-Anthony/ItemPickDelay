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
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.text.Text;

public final class ContainerScreenEvents {

	private ContainerScreenEvents() {
		throw new AssertionError("No GenericContainerScreenEvents for you!");
	}

	public static final Event<PostRender> POST_RENDER = EventFactory.createArrayBacked(PostRender.class,
			callbacks -> (screen, context, mouseX, mouseY, delta) -> {
				for (PostRender callback : callbacks) {
					callback.onPostRender(screen, context, mouseX, mouseY, delta);
				}
			});

	public static final Event<Init> INIT = EventFactory.createArrayBacked(Init.class,
			callbacks -> (screen, handler, inventory, text) -> {
				for (Init callback : callbacks) {
					callback.onInit(screen, handler, inventory, text);
				}
			});

	@FunctionalInterface
	public interface PostRender {
		void onPostRender(GenericContainerScreen screen, DrawContext context, int mouseX, int mouseY, float delta);
	}

	@FunctionalInterface
	public interface Init {
		void onInit(GenericContainerScreen screen, GenericContainerScreenHandler handler, PlayerInventory inventory,
				Text title);
	}
}
