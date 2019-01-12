/*
 *  Copyright (c) 2019. CCC-Development-Team
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package fr.ensicaen.ecole.oasmr.lib.packagemanagment.apt;

import fr.ensicaen.ecole.oasmr.lib.command.Command;
import fr.ensicaen.ecole.oasmr.lib.packagemanagment.apt.exceptions.ExceptionAptFailGettingList;

import java.io.Serializable;

public class CommandAptList extends Command {

    public CommandAptList() {
    }

    @Override
    public Serializable execute(Object... params) throws Exception {
        throw new ExceptionAptFailGettingList("DOES NOT WORK !");
        /*ProcessBuilder processBuilder = new ProcessBuilder("apt", "list");
        try {
            Process p = processBuilder.start();
            p.waitFor();
            int ret = p.exitValue();
            switch (ret) {
                case 0:
                    String output = ProcessBuilderUtil.getOutput(p);
                    System.out.println(output);
                    return output;
                default:
                    throw new ExceptionAptFailGettingList(ProcessBuilderUtil.getOutputError(p));
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return e;
        }
        */
    }

    @Override
    public String toString() {
        return "apt list";
    }
}
