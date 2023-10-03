package com.kpekala.data;

import com.kpekala.data.model.SetModel;

import java.util.Map;
import java.util.Objects;

public class ModelConverter {
    public static SetModel convertMap(Map<String, Object> rawData) throws NullPointerException{
        String name = Objects.requireNonNull((String) rawData.get("name"));
        String theme = Objects.requireNonNull((String) rawData.get("theme"));
        int year = ((Double)rawData.get("year")).intValue();
        int pieceCount = ((Double)rawData.get("pieces")).intValue();
        String imageLink = Objects.requireNonNull((String) ((Map<String, Object>)rawData.get("image")).get("imageURL"));
        return new SetModel(name,pieceCount,theme,year,imageLink);
    }
}
