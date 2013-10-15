package com.indeed.parser;

import com.indeed.domain.search_result.ParsingSearchResults;
import com.indeed.domain.search_result.SearchResult;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless
public class SearchResultsParser {

    private JsonParser jsonParser;
    private SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ROOT);

    public void setJsonParser(InputStream jsonInputStream) {
        jsonParser = Json.createParser(jsonInputStream);
    }

    public void setJsonParser(String jsonString) {
        jsonParser = Json.createParser(new StringReader(jsonString.trim()));
    }

    public ParsingSearchResults parse() throws ParseException {

        ParsingSearchResults results = new ParsingSearchResults();
        SearchResult singleResult = new SearchResult();
        List<SearchResult> jobsResults = new ArrayList<>();
        Boolean parsingResults = false;

        while (jsonParser.hasNext()) {
            JsonParser.Event event = jsonParser.next();

            switch (event) {
                case START_ARRAY: {
                    parsingResults = true;
                    break;
                }
                case END_ARRAY: {
                    parsingResults = false;
                    break;
                }
                case START_OBJECT: {
                    if (parsingResults) {
                        singleResult = new SearchResult();
                        break;
                    }
                }
                case END_OBJECT: {
                    if (parsingResults) {
                        jobsResults.add(singleResult);
                        break;
                    }
                }
                case VALUE_FALSE:
                case VALUE_NULL:
                case VALUE_TRUE:
                    break;
                case KEY_NAME: {

                    if (!parsingResults) {
                        if (jsonParser.getString().equals("start")) {
                            jsonParser.next();
                            results.setStart(jsonParser.getInt());
                        }

                        if (jsonParser.getString().equals("end")) {
                            jsonParser.next();
                            results.setEnd(jsonParser.getInt());
                        }

                        if (jsonParser.getString().equals("totalResults")) {
                            jsonParser.next();
                            results.setTotalResults(jsonParser.getInt());
                        }

                        if (jsonParser.getString().equals("pageNumber")) {
                            jsonParser.next();
                            results.setPageNumber(jsonParser.getInt());
                        }
                    } else {
                        if (jsonParser.getString().equals("jobtitle")) {
                            jsonParser.next();
                            singleResult.setJobTitle(jsonParser.getString());
                        }

                        if (jsonParser.getString().equals("company")) {
                            jsonParser.next();
                            singleResult.setCompany(jsonParser.getString());
                        }

                        if (jsonParser.getString().equals("city")) {
                            jsonParser.next();
                            singleResult.setCity(jsonParser.getString());
                        }

                        if (jsonParser.getString().equals("country")) {
                            jsonParser.next();
                            singleResult.setCountry(jsonParser.getString());
                        }

                        if (jsonParser.getString().equals("source")) {
                            jsonParser.next();
                            singleResult.setSource(jsonParser.getString());
                        }

                        if (jsonParser.getString().equals("date")) {
                            jsonParser.next();
                            singleResult.setDate(sdf.parse(jsonParser.getString()));
                        }

                        if (jsonParser.getString().equals("url")) {
                            jsonParser.next();
                            singleResult.setUrl(jsonParser.getString());
                        }

                        if (jsonParser.getString().equals("latitude")) {
                            jsonParser.next();
                            singleResult.setLatitude(Double.parseDouble(jsonParser.getString()));
                        }

                        if (jsonParser.getString().equals("longitude")) {
                            jsonParser.next();
                            singleResult.setLongtitude(Double.parseDouble(jsonParser.getString()));
                        }

                        if (jsonParser.getString().equals("jobkey")) {
                            jsonParser.next();
                            singleResult.setJobKey(jsonParser.getString());
                        }

                        if (jsonParser.getString().equals("sponsored")) {
                            event = jsonParser.next();

                            if (event == JsonParser.Event.VALUE_TRUE) {
                                singleResult.setSponsorded(true);
                            } else {
                                singleResult.setSponsorded(false);
                            }
                            break;
                        }

                        if (jsonParser.getString().equals("expired")) {
                            event = jsonParser.next();

                            if (event == JsonParser.Event.VALUE_TRUE) {
                                singleResult.setExpired(true);
                            } else {
                                singleResult.setExpired(false);
                            }
                        }
                        break;
                    }

                    break;
                }
                case VALUE_STRING:
                case VALUE_NUMBER:
                    break;
            }
        }

        results.setSearchResults(jobsResults);

        return results;
    }

}
