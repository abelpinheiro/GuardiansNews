package abelpinheiro.github.io.guardiansnews;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class QueryUtils {

    private static final int SUCCESSFUL_REQUEST = 200;
    private static final String NEW_DATE_PATTERN = "dd/MM/yyyy, HH:mm";
    private static final String DATE_NOT_FOUND = "Date not found";
    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String CODE_RESPONSE_ERROR = "Error response code: ";
    private static final String JSON_RETRIEVING_ERROR = "Problem retrieving the news JSON results.";
    private static final String DATE_RETRIEVING_ERROR = "No date were found.";
    private static final String AUTHOR_RETRIEVING_ERROR = "No author was found.";
    private static final String EMPTY_TEXT = "";

    public static List<News> fetchNewsData(String requestUrl){
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<News> news = extractFeatureFromJson(jsonResponse);
        return news;
    }

    private static URL createUrl(String stringUrl){
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        if (url == null){
            return jsonResponse;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(10000 /* milliseconds */);
            httpURLConnection.setConnectTimeout(15000 /* milliseconds */);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == SUCCESSFUL_REQUEST){
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else {
                Log.e("QueryUtils.java", CODE_RESPONSE_ERROR + httpURLConnection.getResponseCode());
            }
        }catch (IOException e){
            Log.e("QueryUtils.java", JSON_RETRIEVING_ERROR, e);
        }finally {
            if (httpURLConnection != null){
                httpURLConnection.disconnect();
            }

            if (inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<News> extractFeatureFromJson(String jsonResponse){
        List newsList = new ArrayList<>();
        try {
            JSONObject raizJson = new JSONObject(jsonResponse);
            JSONObject responseJson = raizJson.getJSONObject("response");
            JSONArray listaNews = responseJson.getJSONArray("results");
            for (int i = 0; i< listaNews.length(); i++){
                JSONObject currentNews = listaNews.getJSONObject(i);
                String title = currentNews.getString("webTitle");
                String section =  currentNews.getString("sectionName");

                String date = currentNews.getString("webPublicationDate");
                String formattedDate = EMPTY_TEXT;
                try {
                    formattedDate = formatDate(date);
                }catch (Exception e){
                    Log.i("QueryUtils.java", DATE_RETRIEVING_ERROR, e);
                }

                JSONArray tags = currentNews.getJSONArray("tags");
                String author = EMPTY_TEXT;
                try {
                    JSONObject authorObject = tags.getJSONObject(0);
                    author = authorObject.getString("webTitle");
                }catch (Exception e){
                    Log.i("QueryUtils.java", AUTHOR_RETRIEVING_ERROR, e);
                }

                String url = currentNews.getString("webUrl");

                News newsItem = new News(title,section, author, formattedDate, url);
                newsList.add(newsItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public static String formatDate(String jsonDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.US);
        SimpleDateFormat newSimpleDateFormat = new SimpleDateFormat(NEW_DATE_PATTERN, Locale.US);
        try {
            Date date = simpleDateFormat.parse(jsonDate);
            return newSimpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return DATE_NOT_FOUND;
        }
    }
}