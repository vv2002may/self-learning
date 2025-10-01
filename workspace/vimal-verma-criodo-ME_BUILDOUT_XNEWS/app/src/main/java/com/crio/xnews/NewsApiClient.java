package com.crio.xnews;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.List;
import java.util.Collections;
import com.crio.xnews.NewsParser;


public class NewsApiClient {

    private static final String API_URL = "https://newsapi.org/v2/everything";
    private static final String API_KEY = "599c7b00272f4bc78dd84d7f1ac0b929";

    private OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        // System.out.println("***********URL:"+url);
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // TODO: CRIO_TASK_MODULE_PROJECT
    // Utilize the Okhttp3 library to send a request to the News API, including the provided query,
    // language, and sortBy parameters.
    // Ensure that the Gradle dependency for Okhttp3 is included in build.gradle.
    // Parse the JSON response using NewsParser.
    // If the "query" parameter is empty, an IllegalArgumentException is thrown.
    // If there is an error during the API request or response parsing, IOException is thrown.

    public List<NewsArticle> fetchNewsArticles(String query, String language, String sortBy)
            throws IOException {
                if (query==null || query=="") {
                    System.out.println("Query parameter must not be empty");
                    throw new IllegalArgumentException("Query parameter must not be empty");
                }
                String json=run(buildUrl(query, language, sortBy));
                // System.out.println("***********:"+json);
                return NewsParser.parse(json);
    }

    // TODO: CRIO_TASK_MODULE_PROJECT
    // Construct the URL required to make a request to the News API and use this in above method.
    // Refer to https://newsapi.org/docs/endpoints/everything for guidance on URL construction.
    // The "query" parameter is mandatory and must not be empty.
    // If the "query" parameter is empty, throw IllegalArgumentException with message "Query
    // parameter must not be empty".
    // The "language" and "sortBy" parameters are optional and will be included in the URL if they
    // are non-empty.


    private String buildUrl(String query, String language, String sortBy) {
        if (query.equals(null) || query.equals("")) {
            throw new IllegalArgumentException("Query parameter must not be empty");
        }
        if(sortBy==null){
            sortBy="";
        }
        if(language==null){
            language="";
        }
        return API_URL + "?q=" + query + "&sortBy=" + sortBy + "&language=" + language + "&apiKey="
                + API_KEY;
    }
}
