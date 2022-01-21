package org.example.data;

import org.example.data.model.SetModel;

import java.util.Map;
import java.util.Objects;

public class ModelConverter {
    public static SetModel convertMap(Map<String, Object> rawData) throws NullPointerException{
        String name = Objects.requireNonNull((String) rawData.get("name"));
        String theme = Objects.requireNonNull((String) rawData.get("theme"));
        int year = ((Double)rawData.get("year")).intValue();
        int pieceCount = ((Double)rawData.get("pieces")).intValue();
        String imageLink = "";
        return new SetModel(name,pieceCount,theme,year,imageLink);
    }
}
