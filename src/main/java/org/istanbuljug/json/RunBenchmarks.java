package org.istanbuljug.json;

import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by usta on 24.09.2016.
 */
public class RunBenchmarks {

    public static void main(String[] args) throws RunnerException, IOException {

        Files.createDirectories(Paths.get("./results"));

        List<Class<?>> benchmarkList = Arrays.asList(
                SerializeBenchmark.class,
                DeserializeBenchmark.class,
                ConvertCollectionBenchmark.class,
                ConvertMapBenchmark.class);

        for (Class<?> benchmark : benchmarkList) {

            String name = benchmark.getSimpleName();
            Options opt = new OptionsBuilder()
                    .include(name)
                    .output("results/" + name + ".txt")
                    .build();

            Collection<RunResult> result = new Runner(opt).run();
        }

    }
}
