package com.kpekala.data.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {

    @Test
    public void testConvertMap() {
        Map<String, Object> rawData = new HashMap<>();
        rawData.put("name", "Police ambush");
        rawData.put("theme", "lego city");
        rawData.put("year", 2013.0);
        rawData.put("pieces", 2137.0);

        Map<String, Object> imageData = new HashMap<>();
        imageData.put("imageURL", "url");
        rawData.put("image", imageData);

        SetModel setModel = ModelConverter.convertMap(rawData);

        assertEquals(setModel.name(), "Police ambush");
        assertEquals(setModel.theme(), "lego city");
        assertEquals(setModel.year(), 2013);
        assertEquals(setModel.pieceCount(), 2137);
        assertEquals(setModel.imageLink(), "url");
    }
}
