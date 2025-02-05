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
package org.iq80.leveldb.fileenv;

import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.iq80.leveldb.env.SequentialFile;
import org.iq80.leveldb.util.SliceOutput;

class SequentialFileImpl implements SequentialFile {
    private final FileInputStream inputStream;

    private SequentialFileImpl(FileInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public static SequentialFile open(File file) throws IOException {
        return new SequentialFileImpl(new FileInputStream(file));
    }

    @Override
    public void skip(long n) throws IOException {
        checkState(n >= 0, "n must be positive");
        if (inputStream.skip(n) != n) {
            throw new IOException(inputStream + " as not enough bytes to skip");
        }
    }

    @Override
    public int read(int atMost, SliceOutput destination) throws IOException {
        return destination.writeBytes(inputStream, atMost);
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
