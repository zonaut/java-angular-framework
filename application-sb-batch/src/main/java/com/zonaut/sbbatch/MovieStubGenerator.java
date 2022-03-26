package com.zonaut.sbbatch;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class MovieStubGenerator {

    public static final int NUMBER_OF_OBJECTS = 100;
    public static final Random RANDOM = new Random();
    public static final boolean PRETTY_PRINT = false;

    public static void main(String[] args) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        generateFile(jsonFactory, getAbsoluteFilePathToMoviesJsonFile());
    }

    private static void generateFile(JsonFactory jsonFactory, String filePath) throws IOException {
        try (OutputStream fos = new FileOutputStream(filePath, false);
             JsonGenerator generator = jsonFactory.createGenerator(fos, JsonEncoding.UTF8)) {

            generator.setPrettyPrinter(PRETTY_PRINT ? new DefaultPrettyPrinter() : null);

            generator.writeStartArray();
            for (int x = 0; x < NUMBER_OF_OBJECTS; x++) {
                writeObject(generator);
            }
            generator.writeEndArray();
        }
    }

    private static void writeObject(JsonGenerator generator) throws IOException {
        generator.writeStartObject();
        writeString(generator, "title");
        writeNumber(generator, "year");
        writeArray(generator, "cast");
        writeArray(generator, "genres");
        generator.writeEndObject();
    }

    private static void writeString(JsonGenerator generator, String field) throws IOException {
        generator.writeStringField(field, generateRandomWords(1)[0]);
    }

    private static void writeNumber(JsonGenerator generator, String field) throws IOException {
        generator.writeNumberField(field, RANDOM.nextInt(1970, 2022));
    }

    private static void writeArray(JsonGenerator generator, String genres) throws IOException {
        generator.writeFieldName(genres);
        generator.writeStartArray();
        for (String arg : generateRandomWords(3)) {
            generator.writeString(arg);
        }
        generator.writeEndArray();
    }

    private static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[RANDOM.nextInt(8) + 3];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + RANDOM.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    private static String getAbsoluteFilePathToMoviesJsonFile() {
        String ps = File.separator;
        String baseFilePath = String.format("application-sb-batch%ssrc%smain%sresources%s", ps, ps, ps, ps);
        Path relativePath = Paths.get(baseFilePath, "movies.json");
        return relativePath.toAbsolutePath().toString();
    }

}
