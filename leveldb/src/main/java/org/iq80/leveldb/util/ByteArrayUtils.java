/*
 * Copyright (C) 2011 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.iq80.leveldb.util;

public class ByteArrayUtils {

    /** Gets a 32-bit integer at the specified absolute {@code index} in this buffer. */
    public static int readInt(byte[] data, int index) {
        return (data[index] & 0xff)
                | (data[index + 1] & 0xff) << 8
                | (data[index + 2] & 0xff) << 16
                | (data[index + 3] & 0xff) << 24;
    }

    /** Gets a 64-bit long at the specified absolute {@code index} in this buffer. */
    public static long readLong(byte[] data, int index) {
        return ((long) data[index] & 0xff)
                | ((long) data[index + 1] & 0xff) << 8
                | ((long) data[index + 2] & 0xff) << 16
                | ((long) data[index + 3] & 0xff) << 24
                | ((long) data[index + 4] & 0xff) << 32
                | ((long) data[index + 5] & 0xff) << 40
                | ((long) data[index + 6] & 0xff) << 48
                | ((long) data[index + 7] & 0xff) << 56;
    }

    /** Sets the specified 32-bit integer at the specified absolute {@code index} in this buffer. */
    public static void writeInt(byte[] data, int index, int value) {
        data[index] = (byte) (value);
        data[index + 1] = (byte) (value >>> 8);
        data[index + 2] = (byte) (value >>> 16);
        data[index + 3] = (byte) (value >>> 24);
    }

    /**
     * Sets the specified 64-bit long integer at the specified absolute {@code index} in this
     * buffer.
     */
    public static void writeLong(byte[] data, int index, long value) {
        data[index] = (byte) (value);
        data[index + 1] = (byte) (value >>> 8);
        data[index + 2] = (byte) (value >>> 16);
        data[index + 3] = (byte) (value >>> 24);
        data[index + 4] = (byte) (value >>> 32);
        data[index + 5] = (byte) (value >>> 40);
        data[index + 6] = (byte) (value >>> 48);
        data[index + 7] = (byte) (value >>> 56);
    }
}
