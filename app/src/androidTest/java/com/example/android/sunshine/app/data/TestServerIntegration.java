package com.example.android.sunshine.app.data;

import android.content.ContentValues;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.text.format.Time;
import android.util.Log;

import com.example.android.sunshine.app.BuildConfig;
import com.example.android.sunshine.app.Utility;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;


/**
 * Created by origavish on 10/01/2016.
 */
public class TestServerIntegration extends AndroidTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }


    public void testDailyWeatherRequest() throws Exception {
        assertTrue(true);

        // Create a MockWebServer. These are lean enough that you can create a new
        // instance for every unit test.
        MockWebServer server = new MockWebServer();

        String response = "{\"city\":{\"id\":3093133,\"name\":\"Lodz\",\"coord\":{\"lon\":19.466669,\"lat\":51.75},\"country\":\"PL\",\"population\":0},\"cod\":\"200\",\"message\":0.0083,\"cnt\":14,\"list\":[{\"dt\":1452420000,\"temp\":{\"day\":1,\"min\":0.52,\"max\":1.2,\"night\":0.52,\"eve\":1.2,\"morn\":1},\"pressure\":1002.45,\"humidity\":96,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":4.67,\"deg\":124,\"clouds\":48,\"snow\":0.01},{\"dt\":1452506400,\"temp\":{\"day\":1,\"min\":-0.3,\"max\":1.65,\"night\":1.65,\"eve\":0.88,\"morn\":-0.3},\"pressure\":994.8,\"humidity\":97,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":2.66,\"deg\":224,\"clouds\":80,\"rain\":4.21,\"snow\":1.05},{\"dt\":1452592800,\"temp\":{\"day\":1.5,\"min\":0.4,\"max\":1.57,\"night\":0.63,\"eve\":0.55,\"morn\":0.4},\"pressure\":982.87,\"humidity\":93,\"weather\":[{\"id\":601,\"main\":\"Snow\",\"description\":\"snow\",\"icon\":\"13d\"}],\"speed\":5.46,\"deg\":235,\"clouds\":76,\"rain\":0.31,\"snow\":2.39},{\"dt\":1452679200,\"temp\":{\"day\":-1.51,\"min\":-2.92,\"max\":0,\"night\":-2.33,\"eve\":-2.61,\"morn\":0},\"pressure\":1002.58,\"humidity\":94,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":8.18,\"deg\":287,\"clouds\":80,\"snow\":0.88},{\"dt\":1452765600,\"temp\":{\"day\":-1.5,\"min\":-13.67,\"max\":-1.5,\"night\":-13.67,\"eve\":-5.73,\"morn\":-2.92},\"pressure\":1014.29,\"humidity\":98,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":3.31,\"deg\":345,\"clouds\":92,\"snow\":1.24},{\"dt\":1452852000,\"temp\":{\"day\":3.13,\"min\":-0.21,\"max\":3.13,\"night\":-0.21,\"eve\":1.48,\"morn\":1.66},\"pressure\":986.34,\"humidity\":0,\"weather\":[{\"id\":601,\"main\":\"Snow\",\"description\":\"snow\",\"icon\":\"13d\"}],\"speed\":7.97,\"deg\":217,\"clouds\":99,\"rain\":7.09,\"snow\":4.84},{\"dt\":1452938400,\"temp\":{\"day\":-2.53,\"min\":-4.14,\"max\":-1.78,\"night\":-4.14,\"eve\":-3.23,\"morn\":-1.78},\"pressure\":1001.64,\"humidity\":0,\"weather\":[{\"id\":601,\"main\":\"Snow\",\"description\":\"snow\",\"icon\":\"13d\"}],\"speed\":8.72,\"deg\":277,\"clouds\":97,\"snow\":3.77},{\"dt\":1453024800,\"temp\":{\"day\":-6.03,\"min\":-13.73,\"max\":-6.03,\"night\":-13.73,\"eve\":-12.08,\"morn\":-6.7},\"pressure\":1009.04,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":3.56,\"deg\":257,\"clouds\":38,\"snow\":0.02},{\"dt\":1453111200,\"temp\":{\"day\":-5.92,\"min\":-12.17,\"max\":-5.92,\"night\":-6.78,\"eve\":-9.86,\"morn\":-12.17},\"pressure\":1006.52,\"humidity\":0,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":4,\"deg\":227,\"clouds\":48,\"snow\":0.05},{\"dt\":1453197600,\"temp\":{\"day\":-3.87,\"min\":-6.95,\"max\":-3.87,\"night\":-5.2,\"eve\":-6.33,\"morn\":-6.95},\"pressure\":1005.79,\"humidity\":0,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":3.51,\"deg\":236,\"clouds\":30,\"snow\":0.32},{\"dt\":1453284000,\"temp\":{\"day\":-6.62,\"min\":-10.17,\"max\":-6.62,\"night\":-7.73,\"eve\":-9.83,\"morn\":-10.17},\"pressure\":1015.68,\"humidity\":0,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":3.2,\"deg\":242,\"clouds\":25,\"snow\":0.17},{\"dt\":1453370400,\"temp\":{\"day\":-2.74,\"min\":-5.27,\"max\":-1.99,\"night\":-4.95,\"eve\":-1.99,\"morn\":-5.27},\"pressure\":1014.85,\"humidity\":0,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":6.52,\"deg\":242,\"clouds\":95,\"snow\":0.56},{\"dt\":1453456800,\"temp\":{\"day\":-2.03,\"min\":-8.8,\"max\":-2.03,\"night\":-8.8,\"eve\":-3.36,\"morn\":-5.3},\"pressure\":1009.74,\"humidity\":0,\"weather\":[{\"id\":601,\"main\":\"Snow\",\"description\":\"snow\",\"icon\":\"13d\"}],\"speed\":2.2,\"deg\":203,\"clouds\":97,\"snow\":1.53},{\"dt\":1453543200,\"temp\":{\"day\":-2.05,\"min\":-9.45,\"max\":-2.05,\"night\":-9.45,\"eve\":-7.99,\"morn\":-7.45},\"pressure\":1023.26,\"humidity\":0,\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13d\"}],\"speed\":4.85,\"deg\":301,\"clouds\":28,\"snow\":0.17}]}\n";

        // Schedule some responses.
//        server.enqueue(new MockResponse().setBody("hello, world!"));
        server.enqueue(new MockResponse().setBody(response));

        // Start the server.
        server.start();

        // Ask the server for its URL. You'll need this to make HTTP requests.
//        HttpUrl baseUrl = server.url("/v1/chat/");
        URL baseUrl = server.getUrl("/weather/daily/");


        // Exercise your application code, which should make those HTTP requests.
        // Responses are returned in the same order that they are enqueued.
        String res = doDailyRequest(baseUrl);
        assertEquals(response, res);

        RecordedRequest request1 = server.takeRequest();
        String path = request1.getPath();

        // Optional: confirm that your app made the HTTP requests you were expecting.
//        RecordedRequest request1 = server.takeRequest();
//        assertEquals("/v1/chat/messages/", request1.getPath());
//        assertNotNull(request1.getHeader("Authorization"));
//
//        RecordedRequest request2 = server.takeRequest();
//        assertEquals("/v1/chat/messages/2", request2.getPath());
//
//        RecordedRequest request3 = server.takeRequest();
//        assertEquals("/v1/chat/messages/3", request3.getPath());

        // Shut down the server. Instances cannot be reused.
        server.shutdown();

    }

    private String doDailyRequest(URL baseUrl) {

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        String locationQuery = "94043";
        String format = "json";
        String units = "metric";
        int numDays = 14;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
//            final String FORECAST_BASE_URL =
//                    "http://api.openweathermap.org/data/2.5/forecast/daily?";
            final String FORECAST_BASE_URL = baseUrl.toString();
            final String QUERY_PARAM = "q";
            final String FORMAT_PARAM = "mode";
            final String UNITS_PARAM = "units";
            final String DAYS_PARAM = "cnt";
            final String APPID_PARAM = "APPID";


            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, locationQuery)
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(UNITS_PARAM, units)
                    .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                    .appendQueryParameter(APPID_PARAM, BuildConfig.OPEN_WEATHER_MAP_API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();
        } catch (IOException e) {
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }

        return forecastJsonStr;
    }


    private void getWeatherDataFromJson(String forecastJsonStr)
            throws JSONException {

        // Now we have a String representing the complete forecast in JSON Format.
        // Fortunately parsing is easy:  constructor takes the JSON string and converts it
        // into an Object hierarchy for us.

        // These are the names of the JSON objects that need to be extracted.

        // Location information
        final String OWM_CITY = "city";
        final String OWM_CITY_NAME = "name";
        final String OWM_COORD = "coord";

        // Location coordinate
        final String OWM_LATITUDE = "lat";
        final String OWM_LONGITUDE = "lon";

        // Weather information.  Each day's forecast info is an element of the "list" array.
        final String OWM_LIST = "list";

        final String OWM_PRESSURE = "pressure";
        final String OWM_HUMIDITY = "humidity";
        final String OWM_WINDSPEED = "speed";
        final String OWM_WIND_DIRECTION = "deg";

        // All temperatures are children of the "temp" object.
        final String OWM_TEMPERATURE = "temp";
        final String OWM_MAX = "max";
        final String OWM_MIN = "min";

        final String OWM_WEATHER = "weather";
        final String OWM_DESCRIPTION = "main";
        final String OWM_WEATHER_ID = "id";

        try {
            JSONObject forecastJson = new JSONObject(forecastJsonStr);
            JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

            JSONObject cityJson = forecastJson.getJSONObject(OWM_CITY);
            String cityName = cityJson.getString(OWM_CITY_NAME);

            JSONObject cityCoord = cityJson.getJSONObject(OWM_COORD);
            double cityLatitude = cityCoord.getDouble(OWM_LATITUDE);
            double cityLongitude = cityCoord.getDouble(OWM_LONGITUDE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
