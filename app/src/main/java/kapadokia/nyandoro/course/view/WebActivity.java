package kapadokia.nyandoro.course.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import kapadokia.nyandoro.course.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView browser = (WebView) findViewById(R.id.web_view);
        browser.loadUrl("https://drive.google.com/drive/u/0/folders/1QZuc0WyzO8EJ7x--KBU1HdHIeFysjZM1");
    }
}