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
package org.iq80.leveldb.table;

import org.iq80.leveldb.env.Env;
import org.iq80.leveldb.fileenv.EnvImpl;
import org.iq80.leveldb.fileenv.MmapLimiter;

public class MMRandomInputFileTableTest extends TableTest {
    @Override
    protected Env getEnv() {
        // force MMap files
        return EnvImpl.createEnv(MmapLimiter.newLimiter(1000));
    }
}
