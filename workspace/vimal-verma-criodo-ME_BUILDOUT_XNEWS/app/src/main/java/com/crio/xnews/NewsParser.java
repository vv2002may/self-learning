package com.crio.xnews;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NewsParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

// TODO: CRIO_TASK_MODULE_PROJECT
// Deserialize JSON String representing the response from the News API and 
// then return the list of NewsArticle objects contained in the response.

    public static List<NewsArticle> parse(String json) throws IOException {
        JsonNode jsonNode=objectMapper.readTree(json);
        JsonNode articles=jsonNode.get("articles");
        // System.out.println(articles);
        List<NewsArticle> newsArticles=objectMapper.readValue(articles.toString(), new TypeReference<ArrayList<NewsArticle>>() {});
        return newsArticles;
    }
}