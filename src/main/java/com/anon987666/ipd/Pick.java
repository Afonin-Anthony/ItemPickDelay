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

package com.anon987666.ipd;

import java.util.Optional;

public final class Pick {

	private final long timestamp;

	private final Optional<Pick> parent;

	public Pick(long timestamp) {
		this(null, timestamp);
	}

	public Pick(Pick parent, long timestamp) {
		this.parent = Optional.ofNullable(parent);
		this.timestamp = timestamp;
	}

	public boolean isRoot() {
		return parent.isEmpty();
	}

	public Pick getParent() {
		return parent.orElseThrow(IllegalStateException::new);
	}

	public long getTimestamp() {
		return timestamp;
	}

	public long getInterval() {
		return timestamp - getParent().timestamp;
	}

}
