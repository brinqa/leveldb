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

import static org.iq80.leveldb.util.SizeOf.SIZE_OF_INT;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import net.jpountz.lz4.LZ4Factory;

/** Use LZ4 compression to reduce the size of the blocks. */
public final class Compression {
    private Compression() {}

    private static final LZ4Factory factory = LZ4Factory.fastestJavaInstance();

    public static boolean available() {
        return true;
    }

    public static ByteBuffer uncompress(final ByteBuffer compressed) {
        byte[] rawLen = new byte[SIZE_OF_INT];
        compressed.get(rawLen, 0, SIZE_OF_INT);
        int uncompressedLen = ByteArrayUtils.readInt(rawLen, 0);
        ByteBuffer ret = ByteBuffer.allocateDirect(uncompressedLen);
        factory.fastDecompressor().decompress(compressed, ret);
        ret.flip();
        return ret;
    }

    public static void uncompress(ByteBuffer compressed, ByteBuffer uncompressed)
            throws IOException {
        factory.safeDecompressor().decompress(compressed, uncompressed);
    }

    public static void uncompress(
            byte[] input, int inputOffset, int length, byte[] output, int outputOffset)
            throws IOException {
        factory.safeDecompressor().decompress(input, inputOffset, length, output, outputOffset);
    }

    public static int compress(
            final byte[] input,
            final int inputOffset,
            final int length,
            final byte[] output,
            final int outputOffset)
            throws IOException {
        int offset = outputOffset + SIZE_OF_INT;
        // write out the uncompressed length
        ByteArrayUtils.writeInt(output, 0, length);
        int size = factory.fastCompressor().compress(input, inputOffset, length, output, offset);
        return size + SIZE_OF_INT;
    }

    public static byte[] compress(String text) {
        return factory.fastCompressor().compress(text.getBytes(StandardCharsets.UTF_8));
    }

    public static int maxCompressedLength(int length) {
        return factory.fastCompressor().maxCompressedLength(length) + SIZE_OF_INT;
    }
}
