package org.eclipselink.jsonb.json;

import com.fasterxml.jackson.core.JsonFactory;

import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Roman Grigoriadi
 */
public class RedirectToJacksonGenerator implements JsonGenerator {

    private com.fasterxml.jackson.core.JsonGenerator jsonGenerator;

    public RedirectToJacksonGenerator(com.fasterxml.jackson.core.JsonGenerator jsonGenerator) {
        this.jsonGenerator = jsonGenerator;
    }

    @Override
    public JsonGenerator writeStartObject() {
        try {
            jsonGenerator.writeStartObject();
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator writeStartObject(String name) {
        try {
            jsonGenerator.writeObjectFieldStart(name);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator writeStartArray() {
        try {
            jsonGenerator.writeStartArray();
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator writeStartArray(String name) {
        try {
            jsonGenerator.writeArrayFieldStart(name);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(String name, JsonValue value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonGenerator write(String name, String value) {
        try {
            jsonGenerator.writeStringField(name, value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(String name, BigInteger value) {
        try {
            jsonGenerator.writeNumberField(name, new BigDecimal(value));
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(String name, BigDecimal value) {
        try {
            jsonGenerator.writeNumberField(name, value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(String name, int value) {
        try {
            jsonGenerator.writeNumberField(name, value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(String name, long value) {
        try {
            jsonGenerator.writeNumberField(name, value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(String name, double value) {
        try {
            jsonGenerator.writeNumberField(name, value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(String name, boolean value) {
        try {
            jsonGenerator.writeBooleanField(name, value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator writeNull(String name) {
        try {
            jsonGenerator.writeNullField(name);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator writeEnd() {
        try {
            //TODO !!! no context here, will not work for any arrays
            jsonGenerator.writeEndObject();
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(JsonValue value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonGenerator write(String value) {
        try {
            jsonGenerator.writeString(value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(BigDecimal value) {
        try {
            jsonGenerator.writeNumber(value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(BigInteger value) {
        try {
            jsonGenerator.writeNumber(value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(int value) {
        try {
            jsonGenerator.writeNumber(value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(long value) {
        try {
            jsonGenerator.writeNumber(value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(double value) {
        try {
            jsonGenerator.writeNumber(value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator write(boolean value) {
        try {
            jsonGenerator.writeBoolean(value);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonGenerator writeNull() {
        try {
            jsonGenerator.writeNull();
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            jsonGenerator.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void flush() {
        try {
            jsonGenerator.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
