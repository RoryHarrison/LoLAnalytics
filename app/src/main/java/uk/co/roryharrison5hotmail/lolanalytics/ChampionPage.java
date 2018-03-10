package uk.co.roryharrison5hotmail.lolanalytics;

import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ChampionPage extends AppCompatActivity {
    TextView txtJson;
    int champID;
    String champSquare;
    String champName = "ERROR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_page);

        Bundle extras = getIntent().getExtras();
        this.champID = extras.getInt("id");
        this.champName = extras.getString("name");
        this.champSquare = extras.getString("square");

        ChampionPage.this.setTitle(champName);
        ImageView squareView = (ImageView) findViewById(R.id.squareView);
        int resID = getResources().getIdentifier(champSquare, "drawable", getPackageName());
        squareView.setImageResource(resID);

        txtJson = (TextView) findViewById(R.id.textView);
        new JsonTask().execute("https://euw1.api.riotgames.com/lol/static-data/v3/champions/"+champID+"?locale=en_GB&api_key=RGAPI-f9333cc7-7e18-4fca-9b9d-d44fe9a4f802");
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                if(connection.getResponseCode()!= 200) {
                    return "Bad Response";
                }

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            txtJson.setText(result);
        }
    }
}