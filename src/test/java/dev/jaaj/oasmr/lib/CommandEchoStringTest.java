/*
 * Copyright (c) 2019. JaaJ-dev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.jaaj.oasmr.lib;

import dev.jaaj.oasmr.lib.example.CommandEchoString;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandEchoStringTest {
    private CommandEchoString c;

    @Before
    public void setUp() {
        c = new CommandEchoString("jeej");
    }

    @Test
    public void execute() throws Exception {
        assertEquals("jeej\n", c.execute());
    }

    @Test
    public void executeFailure() throws Exception {
        assertNotEquals("mmmmm", c.execute());
    }
}