package abelpinheiro.github.io.guardiansnews;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public final class QueryUtils {

    private static final String DATTEPATTERN = "dd/MM/yyyy, HH:mm";
    private static final String DATE_NOT_FOUND = "Date not found";
    static  String JSON_RESPONSE_SAMPLE = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":2051368,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":205137,\"orderBy\":\"newest\",\"results\":[{\"id\":\"football/2018/jul/30/fulham-swansea-city-alfie-mawson-transfer\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2018-07-30T17:12:00Z\",\"webTitle\":\"Fulham sign Aleksandar Mitrovic and close in on Swansea’s Alfie Mawson\",\"webUrl\":\"https://www.theguardian.com/football/2018/jul/30/fulham-swansea-city-alfie-mawson-transfer\",\"apiUrl\":\"https://content.guardianapis.com/football/2018/jul/30/fulham-swansea-city-alfie-mawson-transfer\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"football/2018/jul/30/manchester-united-back-jose-mourinho-despite-managers-transfer-frustrations\",\"type\":\"article\",\"sectionId\":\"football\",\"sectionName\":\"Football\",\"webPublicationDate\":\"2018-07-30T17:00:19Z\",\"webTitle\":\"Manchester United back José Mourinho despite manager's transfer frustrations\",\"webUrl\":\"https://www.theguardian.com/football/2018/jul/30/manchester-united-back-jose-mourinho-despite-managers-transfer-frustrations\",\"apiUrl\":\"https://content.guardianapis.com/football/2018/jul/30/manchester-united-back-jose-mourinho-despite-managers-transfer-frustrations\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"world/2018/jul/30/spains-foreign-minister-scorns-ceuta-mass-immigration-claim\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-07-30T17:00:09Z\",\"webTitle\":\"Spain's foreign minister scorns Ceuta mass immigration claim\",\"webUrl\":\"https://www.theguardian.com/world/2018/jul/30/spains-foreign-minister-scorns-ceuta-mass-immigration-claim\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/jul/30/spains-foreign-minister-scorns-ceuta-mass-immigration-claim\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2018/jul/30/zimbabwe-election-robert-mugabes-mnangagwa-chamisa\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-07-30T16:53:42Z\",\"webTitle\":\"Zimbabwe election: long queues to vote in first post-Mugabe poll\",\"webUrl\":\"https://www.theguardian.com/world/2018/jul/30/zimbabwe-election-robert-mugabes-mnangagwa-chamisa\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/jul/30/zimbabwe-election-robert-mugabes-mnangagwa-chamisa\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"world/2018/jul/30/c-of-e-leeds-diocese-anglican-financial-crisis\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-07-30T16:53:15Z\",\"webTitle\":\"Anglican diocese of Leeds in serious financial crisis\",\"webUrl\":\"https://www.theguardian.com/world/2018/jul/30/c-of-e-leeds-diocese-anglican-financial-crisis\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/jul/30/c-of-e-leeds-diocese-anglican-financial-crisis\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"us-news/shortcuts/2018/jul/30/us-embassy-online-auction-toilet-paper-rugs-vacuum-cleaners\",\"type\":\"article\",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2018-07-30T16:48:23Z\",\"webTitle\":\"Need 1,200 rolls of toilet paper? Try the US embassy’s online auction\",\"webUrl\":\"https://www.theguardian.com/us-news/shortcuts/2018/jul/30/us-embassy-online-auction-toilet-paper-rugs-vacuum-cleaners\",\"apiUrl\":\"https://content.guardianapis.com/us-news/shortcuts/2018/jul/30/us-embassy-online-auction-toilet-paper-rugs-vacuum-cleaners\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"sport/2018/jul/30/adil-rashid-row-alastair-cook-england-india-first-test-edgbaston\",\"type\":\"article\",\"sectionId\":\"sport\",\"sectionName\":\"Sport\",\"webPublicationDate\":\"2018-07-30T16:47:17Z\",\"webTitle\":\"Adil Rashid will cope with England call-up row, insists Alastair Cook\",\"webUrl\":\"https://www.theguardian.com/sport/2018/jul/30/adil-rashid-row-alastair-cook-england-india-first-test-edgbaston\",\"apiUrl\":\"https://content.guardianapis.com/sport/2018/jul/30/adil-rashid-row-alastair-cook-england-india-first-test-edgbaston\",\"isHosted\":false,\"pillarId\":\"pillar/sport\",\"pillarName\":\"Sport\"},{\"id\":\"world/2018/jul/30/uproar-in-france-over-video-of-woman-marie-laguerre-hit-by-harasser-in-paris-street\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-07-30T16:39:00Z\",\"webTitle\":\"Uproar in France over video of woman hit by harasser in Paris street\",\"webUrl\":\"https://www.theguardian.com/world/2018/jul/30/uproar-in-france-over-video-of-woman-marie-laguerre-hit-by-harasser-in-paris-street\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/jul/30/uproar-in-france-over-video-of-woman-marie-laguerre-hit-by-harasser-in-paris-street\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"film/2018/jul/30/mission-impossible-tom-cruise-reliable-franchise\",\"type\":\"article\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webPublicationDate\":\"2018-07-30T16:38:37Z\",\"webTitle\":\"How did Mission: Impossible become Hollywood's most reliable franchise?\",\"webUrl\":\"https://www.theguardian.com/film/2018/jul/30/mission-impossible-tom-cruise-reliable-franchise\",\"apiUrl\":\"https://content.guardianapis.com/film/2018/jul/30/mission-impossible-tom-cruise-reliable-franchise\",\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"commentisfree/2018/jul/30/social-media-anonymous-online-abuse-public-figures-nameless-people\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-07-30T16:36:20Z\",\"webTitle\":\"Where does the buck stop with anonymous online abuse? | Suzanne Moore\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/jul/30/social-media-anonymous-online-abuse-public-figures-nameless-people\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/jul/30/social-media-anonymous-online-abuse-public-figures-nameless-people\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"}]}}";

    public static List<News> extractFeatureFromJson(){
        List newsList = new ArrayList<>();
        try {
            JSONObject raizJson = new JSONObject(JSON_RESPONSE_SAMPLE);
            JSONObject responseJson = raizJson.getJSONObject("response");
            JSONArray listaNews = responseJson.getJSONArray("results");
            for (int i = 0; i< listaNews.length(); i++){
                JSONObject currentNews = listaNews.getJSONObject(i);
                String title = currentNews.getString("webTitle");
                String section =  currentNews.getString("sectionName");
                String date = currentNews.getString("webPublicationDate");
               //String formattedDate = formatDate(date);
                String url = currentNews.getString("webUrl");
                News newsItem = new News(title,section, "", date, url);
                newsList.add(newsItem);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    /*public static String formatDate(String jsonDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATTEPATTERN, Locale.US);
        Date date = new Date(jsonDate);
        String formattedDate = simpleDateFormat.format(jsonDate);
        return formattedDate;
    }*/
}