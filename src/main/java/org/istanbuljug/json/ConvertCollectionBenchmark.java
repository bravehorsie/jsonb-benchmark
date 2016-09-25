package org.istanbuljug.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by usta on 24.09.2016.
 */
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode({Mode.Throughput})
@State(value = Scope.Benchmark)
public class ConvertCollectionBenchmark {

    private static final int ITERATION_COUNT = 10;
    private static final int WARMUP_COUNT = 10;
    private static final int FORK_COUNT = 1;

    private Jsonb jsonb;
    private ObjectMapper objectMapper;

    private List<String> colors;

    private Class<? extends ArrayList<String>> listClass;
    private TypeReference<List<String>> listType;

    @Setup(Level.Trial)
    public void init() {
        jsonb = JsonbBuilder.create();
        objectMapper = new ObjectMapper();
        colors = new ArrayList<>(Arrays.asList("Ali", "Veli", "Selami"));
        listClass = new ArrayList<String>() {
        }.getClass();
        listType = new TypeReference<List<String>>() {
        };
    }

    @Benchmark
    @Fork(value = FORK_COUNT)
    @Warmup(iterations = WARMUP_COUNT)
    @Measurement(iterations = ITERATION_COUNT)
    public List<String> jsonb_to_from_list() {
        String colorsJsonArray = jsonb.toJson(colors);
        return jsonb.fromJson(colorsJsonArray, listClass);
    }

    @Benchmark
    @Fork(value = FORK_COUNT)
    @Warmup(iterations = WARMUP_COUNT)
    @Measurement(iterations = ITERATION_COUNT)
    public List<String> jackson_to_from_list() throws IOException {
        String colorsJsonArray = objectMapper.writeValueAsString(colors);

        return objectMapper.readValue(colorsJsonArray, listType);
    }

    public static void main(String[] args) throws RunnerException {
        String name = ConvertCollectionBenchmark.class.getSimpleName();
        Options opt = new OptionsBuilder()
                .include(name)
                .output("results/" + name + ".txt")
                .build();

        Collection<RunResult> result = new Runner(opt).run();
    }

}
