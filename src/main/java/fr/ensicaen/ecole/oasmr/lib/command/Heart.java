package fr.ensicaen.ecole.oasmr.lib.command;

import fr.ensicaen.ecole.oasmr.lib.command.Command;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Execute a command at a fixed rate
 */
public class Heart {
    private ScheduledFuture<?> scheduleAtFixedRateHeartBeat;
    private int heartBeatPeriodInSeconds = 10;
    private Command heartbeat;
    private Object[] params;

    public Heart(Command r, int heartBeatPeriodInSeconds) {
        this.heartBeatPeriodInSeconds = heartBeatPeriodInSeconds;
        heartbeat = r;
    }

    public void start() {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        scheduleAtFixedRateHeartBeat = executor.scheduleAtFixedRate(() -> {
            try {
                heartbeat.execute(params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 10, heartBeatPeriodInSeconds, SECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(executor::shutdown));
    }

    public void stop() {
        scheduleAtFixedRateHeartBeat.cancel(true);
    }
}
