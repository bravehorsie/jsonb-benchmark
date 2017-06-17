package org.istanbuljug.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by usta on 24.09.2016.
 */
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.Throughput})
@State(value = Scope.Benchmark)
public class DeserializeBenchmark {

    private static final int ITERATION_COUNT = 10;
    private static final int WARMUP_COUNT = 10;
    private static final int FORK_COUNT = 1;

    private Jsonb jsonb;
    private ObjectMapper objectMapper;
    private Gson gson;
    private String jug;

    @Setup(Level.Trial)
    public void init() {
        jsonb = JsonbBuilder.create();
        objectMapper = new ObjectMapper();
        gson = new Gson();
        jug = "{\"name\":\"İstanbul JUG\",\"year\":2010}";
    }

    @Benchmark
    @Fork(value = FORK_COUNT)
    @Warmup(iterations = WARMUP_COUNT)
    @Measurement(iterations = ITERATION_COUNT)
    public Jug jsonb_from_json() {
        return jsonb.fromJson(jug, Jug.class);
    }

    @Benchmark
    @Fork(value = FORK_COUNT)
    @Warmup(iterations = WARMUP_COUNT)
    @Measurement(iterations = ITERATION_COUNT)
    public Jug jackson_from_json() throws IOException {
        return objectMapper.readValue(jug, Jug.class);
    }

    @Benchmark
    @Fork(value = FORK_COUNT)
    @Warmup(iterations = WARMUP_COUNT)
    @Measurement(iterations = ITERATION_COUNT)
    public Jug gson_from_json() throws IOException {
        return gson.fromJson(jug, Jug.class);
    }

    public static void main(String[] args) throws RunnerException {
        String name = DeserializeBenchmark.class.getSimpleName();
        Options opt = new OptionsBuilder()
                .include(name)
                .output("results/" + name + ".txt")
                .build();

        Collection<RunResult> result = new Runner(opt).run();
    }

}
