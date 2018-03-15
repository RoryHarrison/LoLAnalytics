package uk.co.roryharrison5hotmail.lolanalytics.Web.JsonFormats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChampionBasics {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("title")
    @Expose
    private String title = "oops";
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("lore")
    @Expose
    private String lore;
    @SerializedName("id")
    @Expose
    private Integer id;

    public ChampionBasics(){

    }

    public static ChampionBasics parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        ChampionBasics championBasics = gson.fromJson(response, ChampionBasics.class);
        return championBasics;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }

    public String getLore() {
        return lore;
    }

    public Integer getId() {
        return id;
    }

}
