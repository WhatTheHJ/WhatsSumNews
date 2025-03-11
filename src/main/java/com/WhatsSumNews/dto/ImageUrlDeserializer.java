package com.WhatsSumNews.dto;import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class ImageUrlDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        // "media" 배열 안에서 첫 번째 요소 찾기
        if (node.has("media") && node.get("media").isArray() && node.get("media").size() > 0) {
            JsonNode mediaNode = node.get("media").get(0);
            
            // "media-metadata" 배열 안에서 첫 번째 URL 찾기
            if (mediaNode.has("media-metadata") && mediaNode.get("media-metadata").isArray() &&
                mediaNode.get("media-metadata").size() > 0) {
                return mediaNode.get("media-metadata").get(0).get("url").asText();
            }
        }
        return null;  // 이미지 URL이 없을 경우 null 반환
    }
}
