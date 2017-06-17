package org.istanbuljug.json;

import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by usta on 24.09.2016.
 */
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.Throughput})
@State(value = Scope.Benchmark)
public class ConvertMapBenchmark {

    private static final int ITERATION_COUNT = 10;
    private static final int WARMUP_COUNT = 10;
    private static final int FORK_COUNT = 1;

    private Jsonb jsonb;
    private ObjectMapper objectMapper;
    private Gson gson;
    private Map<String, Object> jug;

    private TypeReference<HashMap<String, Object>> mapType;

    @Setup(Level.Trial)
    public void init() {
        jsonb = JsonbBuilder.create();
        objectMapper = new ObjectMapper();
        gson = new Gson();
        jug = new HashMap();
        ;
        jug.put("name", "İstanbul JUG");
        jug.put("age", 2010);

        mapType = new TypeReference<HashMap<String, Object>>() {
        };
    }

    @Benchmark
    @Fork(value = FORK_COUNT)
    @Warmup(iterations = WARMUP_COUNT)
    @Measurement(iterations = ITERATION_COUNT)
    public HashMap<String, Object> jsonb_to_from_json() {
        String colorsJsonArray = jsonb.toJson(jug);
        return jsonb.fromJson(colorsJsonArray, mapType.getType());
    }

    @Benchmark
    @Fork(value = FORK_COUNT)
    @Warmup(iterations = WARMUP_COUNT)
    @Measurement(iterations = ITERATION_COUNT)
    public HashMap<String, Object> jackson_to_from_json() throws IOException {
        String colorsJsonArray = objectMapper.writeValueAsString(jug);
        return objectMapper.readValue(colorsJsonArray, mapType);
    }

    @Benchmark
    @Fork(value = FORK_COUNT)
    @Warmup(iterations = WARMUP_COUNT)
    @Measurement(iterations = ITERATION_COUNT)
    public HashMap<String, Object> gson_to_from_json() throws IOException {
        String colorsJsonArray = gson.toJson(jug);
        return gson.fromJson(colorsJsonArray, mapType.getType());
    }

    public static void main(String[] args) throws RunnerException {
        String name = ConvertMapBenchmark.class.getSimpleName();
        Options opt = new OptionsBuilder()
                .include(name)
                .output("results/" + name + ".txt")
                .build();

        Collection<RunResult> result = new Runner(opt).run();
    }

}
