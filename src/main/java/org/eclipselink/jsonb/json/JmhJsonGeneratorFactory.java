package org.eclipselink.jsonb.json;

import com.fasterxml.jackson.core.JsonFactory;
import org.glassfish.json.api.BufferPool;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author Roman Grigoriadi
 */
public class JmhJsonGeneratorFactory implements JsonGeneratorFactory {

    private static JsonFactory jsonFactory = new JsonFactory();

    @Override
    public JsonGenerator createGenerator(Writer writer) {
        try {
            return new RedirectToJacksonGenerator(jsonFactory.createGenerator(writer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out) {
        try {
            return new RedirectToJacksonGenerator(jsonFactory.createGenerator(out));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator createGenerator(OutputStream out, Charset charset) {
        return null;
    }

    @Override
    public Map<String, ?> getConfigInUse() {
        return null;
    }
}
