package org.istanbuljug.json;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by usta on 24.09.2016.
 */
public class RunBenchmarks {

    public static void main(String[] args) throws Exception {

        Files.createDirectories(Paths.get("./results"));

        List<Class<?>> benchmarkList = Arrays.asList(
                SerializeBenchmark.class,
                DeserializeBenchmark.class,
                ConvertCollectionBenchmark.class,
                ConvertMapBenchmark.class,
                SerializeWithStackProfiler.class);

        for (Class<?> benchmark : benchmarkList) {
            Method method = benchmark.getMethod("main", String[].class);
            method.invoke(null, (Object) args);
        }

    }
}
