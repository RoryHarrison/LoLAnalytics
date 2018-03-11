package uk.co.roryharrison5hotmail.lolanalytics;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChampionPage extends AppCompatActivity {
    ProgressDialog pd;

    TextView txtLore;
    TextView titleView;

    int champID;
    String champSquare;
    String champName = "ERROR";

    GetChampionResponse champResponse;

    //Expandable List Variables
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_page);

        Bundle extras = getIntent().getExtras();
        this.champID = extras.getInt("id");
        this.champName = extras.getString("name");
        this.champSquare = extras.getString("square");

        //txtLore = (TextView) findViewById(R.id.textView);
        titleView = (TextView) findViewById(R.id.titleTextView);

        ChampionPage.this.setTitle(champName);
        ImageView squareView = (ImageView) findViewById(R.id.squareView);
        int resID = getResources().getIdentifier(champSquare, "drawable", getPackageName());
        squareView.setImageResource(resID);

        new JsonTask().execute("https://euw1.api.riotgames.com/lol/static-data/v3/champions/"+champID+"?locale=en_US&champData=lore&api_key=RGAPI-3dd85f10-fac4-4ed1-bdfd-bf4f74dc465d");

        listView = (ExpandableListView) findViewById(R.id.exp_listview);
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);

    }

    private void initData(){
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Lore");
        listDataHeader.add("Recommended Items");
        listDataHeader.add("Win Rate");
        listDataHeader.add("Runes");

        List<String> lore = new ArrayList<>();
        lore.add("Expandable!");

        List<String> recommmendedItems = new ArrayList<>();
        recommmendedItems.add("Woo!");

        List<String> winRate = new ArrayList<>();
        winRate.add("win");

        List<String> runes = new ArrayList<>();
        runes.add("RUNE!");

        listHash.put(listDataHeader.get(0), lore);
        listHash.put(listDataHeader.get(1), recommmendedItems);
        listHash.put(listDataHeader.get(2), winRate);
        listHash.put(listDataHeader.get(3), runes);
    }

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(ChampionPage.this);
            pd.setMessage("Loading");
            pd.setCancelable(false);
            pd.show();
        }

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

            if (pd.isShowing()){
                pd.dismiss();
            }

            champResponse = champResponse.parseJSON(result);

            String title = champResponse.getTitle();
            title = title.toUpperCase().charAt(0)+title.substring(1,title.length());

            titleView.setText(title);
            initData();
            //txtLore.setText(champResponse.getLore());

        }
    }
}