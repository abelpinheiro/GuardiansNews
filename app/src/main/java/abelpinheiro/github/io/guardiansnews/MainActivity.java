package abelpinheiro.github.io.guardiansnews;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.list);

        final List<News> newsList= QueryUtils.extractFeatureFromJson();

        //TODO PRIMEIRO: EXTRAIR DADOS DO JSON. SEGUNDO: POR OS DADOS NA ARRAY E SETAR NO LAYOUT //TERCEIRO, REFATORAR PRA PUXAR PELA INTERNET
        NewsAdapter adapter = new NewsAdapter(this, newsList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = newsList.get(position);
                String url = currentNews.getUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }
}