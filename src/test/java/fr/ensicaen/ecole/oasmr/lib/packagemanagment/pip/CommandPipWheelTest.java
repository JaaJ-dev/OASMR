package fr.ensicaen.ecole.oasmr.lib.packagemanagment.pip;

import fr.ensicaen.ecole.oasmr.lib.packagemanagment.pip.exceptions.PipException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandPipWheelTest {
    @Test
    public void execute() throws Exception{
        CommandPipWheel c = new CommandPipWheel("numpy");
        assert(c.execute() instanceof String);
    }

    @Test(expected = PipException.class)
    public void executeFailure() throws Exception{
        CommandPipWheel c = new CommandPipWheel("nonexistentPackage");
        c.execute();
    }
}
