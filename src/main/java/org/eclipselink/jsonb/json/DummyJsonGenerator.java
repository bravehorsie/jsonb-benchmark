package org.eclipselink.jsonb.json;

import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author Roman Grigoriadi
 */
public class DummyJsonGenerator implements JsonGenerator {
    @Override
    public JsonGenerator writeStartObject() {
        return this;
    }

    @Override
    public JsonGenerator writeStartObject(String name) {
        return this;
    }

    @Override
    public JsonGenerator writeStartArray() {
        return this;
    }

    @Override
    public JsonGenerator writeStartArray(String name) {
        return this;
    }

    @Override
    public JsonGenerator write(String name, JsonValue value) {
        return this;
    }

    @Override
    public JsonGenerator write(String name, String value) {
        return this;
    }

    @Override
    public JsonGenerator write(String name, BigInteger value) {
        return this;
    }

    @Override
    public JsonGenerator write(String name, BigDecimal value) {
        return this;
    }

    @Override
    public JsonGenerator write(String name, int value) {
        return this;
    }

    @Override
    public JsonGenerator write(String name, long value) {
        return this;
    }

    @Override
    public JsonGenerator write(String name, double value) {
        return this;
    }

    @Override
    public JsonGenerator write(String name, boolean value) {
        return this;
    }

    @Override
    public JsonGenerator writeNull(String name) {
        return this;
    }

    @Override
    public JsonGenerator writeEnd() {
        return this;
    }

    @Override
    public JsonGenerator write(JsonValue value) {
        return this;
    }

    @Override
    public JsonGenerator write(String value) {
        return this;
    }

    @Override
    public JsonGenerator write(BigDecimal value) {
        return this;
    }

    @Override
    public JsonGenerator write(BigInteger value) {
        return this;
    }

    @Override
    public JsonGenerator write(int value) {
        return this;
    }

    @Override
    public JsonGenerator write(long value) {
        return this;
    }

    @Override
    public JsonGenerator write(double value) {
        return this;
    }

    @Override
    public JsonGenerator write(boolean value) {
        return this;
    }

    @Override
    public JsonGenerator writeNull() {
        return this;
    }

    @Override
    public void close() {

    }

    @Override
    public void flush() {

    }
}
