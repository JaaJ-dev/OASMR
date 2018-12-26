package fr.ensicaen.ecole.oasmr.supervisor.node.request;

import fr.ensicaen.ecole.oasmr.supervisor.Supervisor;
import fr.ensicaen.ecole.oasmr.supervisor.request.Request;

import java.io.Serializable;

public class RequestGetNode extends Request {
    private final Integer id;

    public RequestGetNode(Integer id) {
        this.id = id;
    }

    @Override
    public Serializable execute(Supervisor supervisor) throws Exception {
        return supervisor.getNodeFlyweightFactory().getNode(id);
    }

    @Override
    public String toString() {
        return "get node " + id;
    }
}
