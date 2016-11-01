/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.eclipselink.jsonb.tests;

import com.fasterxml.jackson.core.JsonFactory;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import javax.json.spi.JsonProvider;
import javax.json.stream.JsonGenerator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Timeout(time = 20, timeUnit = TimeUnit.SECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JsonWriterTest extends AbstractCustomerTest {

    private JsonProvider provider;

    private static final String[] strings = {"str1", "str2", "str3", "str4", "str5"};

    @Setup(Level.Trial)
    public void setup() {
        provider = JsonProvider.provider();
    }

    @Benchmark
    public void test1JsonbStringWriter(Blackhole bh) {
        StringWriter jsonbWriter = new StringWriter();
        JsonGenerator jsonbGenerator = provider.createGenerator(jsonbWriter);
        writeJsonb(jsonbGenerator);
        final String result = jsonbWriter.toString();
        bh.consume(result);
    }

    @Benchmark
    public void test2JacksonStringWriter(Blackhole bh) throws IOException {
        StringWriter jacksonWriter = new StringWriter();
        JsonFactory jsonFactory = new JsonFactory();
        com.fasterxml.jackson.core.JsonGenerator jacksonGenerator= jsonFactory.createGenerator(jacksonWriter);
        writeJackson(jacksonGenerator);
        bh.consume(jacksonWriter.toString());
    }

    @Benchmark
    public void test3JsonbOuptutStream(Blackhole bh) {
        //JsonGenerator does buffering inside anyway.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JsonGenerator jsonbGenerator = provider.createGenerator(out);

        writeJsonb(jsonbGenerator);
        bh.consume(out.toString());
    }

    @Benchmark
    public void test4JacksonOutputStream(Blackhole bh) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JsonFactory jsonFactory = new JsonFactory();
        com.fasterxml.jackson.core.JsonGenerator jacksonGenerator= jsonFactory.createGenerator(out);
        writeJackson(jacksonGenerator);
        bh.consume(out.toString());
    }

    private void writeJsonb(JsonGenerator jsonbGenerator) {
        jsonbGenerator.writeStartObject();
        jsonbGenerator.write("val1", strings[0]);
        jsonbGenerator.write("val2", strings[1]);
        jsonbGenerator.write("val3", strings[2]);
        jsonbGenerator.write("val4", strings[3]);
        jsonbGenerator.write("val5", strings[4]);
        jsonbGenerator.writeEnd();
        jsonbGenerator.flush();
        jsonbGenerator.close();
    }

    private void writeJackson(com.fasterxml.jackson.core.JsonGenerator jacksonGenerator) throws IOException {
        jacksonGenerator.writeStartObject();
        jacksonGenerator.writeStringField("val1", strings[0]);
        jacksonGenerator.writeStringField("val2", strings[1]);
        jacksonGenerator.writeStringField("val3", strings[2]);
        jacksonGenerator.writeStringField("val4", strings[3]);
        jacksonGenerator.writeStringField("val5", strings[4]);
        jacksonGenerator.writeEndObject();
        jacksonGenerator.flush();
        jacksonGenerator.close();
    }

}
