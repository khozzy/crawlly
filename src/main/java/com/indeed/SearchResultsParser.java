package com.indeed;

import com.indeed.entity.SearchResult;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class SearchResultsParser {

    private JsonParser jsonParser;

    public SearchResultsParser(InputStream jsonInputStream) {
        jsonParser = Json.createParser(jsonInputStream);
    }

    public SearchResultsParser(String jsonString) {
        jsonParser = Json.createParser(new StringReader(jsonString));
    }

    /**
     * http://docs.oracle.com/javaee/7/tutorial/doc/jsonp004.htm
     */
    public List<SearchResult> parse() {

        List<SearchResult> results = new ArrayList<>();

        while (jsonParser.hasNext()) {
            JsonParser.Event event = jsonParser.next();

            switch (event) {
                case START_ARRAY:
                case END_ARRAY:
                case START_OBJECT:
                case END_OBJECT:
                case VALUE_FALSE:
                case VALUE_NULL:
                case VALUE_TRUE:
                    System.out.println(event.toString());
                    break;
                case KEY_NAME:
                    System.out.print(event.toString() + " " +
                            jsonParser.getString() + " - ");
                    break;
                case VALUE_STRING:
                case VALUE_NUMBER:
                    System.out.println(event.toString() + " " +
                            jsonParser.getString());
                    break;
            }
        }

        return results;
    }

}
