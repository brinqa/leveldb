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
package org.iq80.leveldb.iterator;

enum Direction
{
    START_OF_ITERATOR(false),
    RELEASED(false),
    END_OF_ITERATOR(false),
    REVERSE(true),
    FORWARD(true);

    private boolean valid;

    Direction(boolean valid)
    {
        this.valid = valid;
    }

    public boolean isValid()
    {
        return valid;
    }
}
