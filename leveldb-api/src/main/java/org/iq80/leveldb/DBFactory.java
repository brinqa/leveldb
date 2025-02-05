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
package org.iq80.leveldb;

import java.io.File;
import java.io.IOException;

/** @author <a href="http://hiramchirino.com">Hiram Chirino</a> */
public interface DBFactory {
    /**
     * Open/Create a new Database using provided "options" to configure DB behavior.
     *
     * @param path DB root folder
     * @param options DB options
     * @return a new DB instance
     * @throws IOException if unable to open/read DB or preconditions failed
     */
    DB open(File path, Options options) throws IOException;

    /**
     * Destroy a database, delete DB files and root directory
     *
     * @param path DB root folder
     * @param options options used to open DB
     * @throws IOException if failed to destruct DB
     */
    void destroy(File path, Options options) throws IOException;

    /**
     * Try to repair a corrupted DB or not closed properly.
     *
     * @param path DB root directory
     * @param options DB options
     * @throws IOException if failed to open/recover DB
     */
    void repair(File path, Options options) throws IOException;
}
