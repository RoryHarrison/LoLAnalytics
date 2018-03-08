package uk.co.roryharrison5hotmail.lolanalytics;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by roryh on 08/03/2018.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.aatroxsquare, R.drawable.ahrisquare, R.drawable.akalisquare,
            R.drawable.alistarsquare, R.drawable.amumusquare, R.drawable.aniviasquare,
            R.drawable.anniesquare, R.drawable.ashesquare, R.drawable.aurelion_solsquare,
            R.drawable.azirsquare, R.drawable.bardsquare, R.drawable.blitzcranksquare,
            R.drawable.brandsquare, R.drawable.braumsquare, R.drawable.caitlynsquare,
            R.drawable.camillesquare, R.drawable.cassiopeiasquare, R.drawable.chogathsquare,
            R.drawable.corkisquare, R.drawable.dariussquare, R.drawable.dianasquare,
            R.drawable.dr_mundosquare, R.drawable.dravensquare, R.drawable.ekkosquare,
            R.drawable.elisesquare, R.drawable.evelynnsquare, R.drawable.ezrealsquare,
            R.drawable.fiddlestickssquare, R.drawable.fiorasquare, R.drawable.fizzsquare,
            R.drawable.galiosquare, R.drawable.gangplanksquare, R.drawable.garensquare,
            R.drawable.gnarsquare, R.drawable.gragassquare, R.drawable.gravessquare,
            R.drawable.hecarimsquare, R.drawable.heimerdingersquare, R.drawable.illaoisquare,
            R.drawable.ireliasquare, R.drawable.ivernsquare, R.drawable.jannasquare,
            R.drawable.jarvan_ivsquare, R.drawable.jaxsquare, R.drawable.jaycesquare,
            R.drawable.jhinsquare, R.drawable.jinxsquare, R.drawable.kaisasquare,
            R.drawable.kalistasquare, R.drawable.karmasquare, R.drawable.karthussquare,
            R.drawable.kassadinsquare, R.drawable.katarinasquare, R.drawable.kaylesquare,
            R.drawable.kaynsquare, R.drawable.kennensquare, R.drawable.khazixsquare,
            R.drawable.kindredsquare, R.drawable.kledsquare, R.drawable.kogmawsquare,
            R.drawable.leblancsquare, R.drawable.lee_sinsquare, R.drawable.leonasquare,
            R.drawable.lissandrasquare, R.drawable.luciansquare, R.drawable.lulusquare,
            R.drawable.luxsquare, R.drawable.malphitesquare, R.drawable.malzaharsquare,
            R.drawable.maokaisquare, R.drawable.master_yisquare, R.drawable.miss_fortunesquare,
            R.drawable.mordekaisersquare, R.drawable.morganasquare, R.drawable.namisquare,
            R.drawable.nasussquare, R.drawable.nautilussquare, R.drawable.nidaleesquare,
            R.drawable.nocturnesquare, R.drawable.nunusquare, R.drawable.olafsquare,
            R.drawable.oriannasquare, R.drawable.ornnsquare, R.drawable.pantheonsquare,
            R.drawable.poppysquare, R.drawable.quinnsquare, R.drawable.rakansquare,
            R.drawable.rammussquare, R.drawable.reksaisquare, R.drawable.renektonsquare,
            R.drawable.rengarsquare, R.drawable.rivensquare, R.drawable.rumblesquare,
            R.drawable.ryzesquare, R.drawable.sejuanisquare, R.drawable.shacosquare,
            R.drawable.shensquare, R.drawable.shyvannasquare, R.drawable.singedsquare,
            R.drawable.sionsquare, R.drawable.sivirsquare, R.drawable.skarnersquare,
            R.drawable.sonasquare, R.drawable.sorakasquare, R.drawable.swainsquare,
            R.drawable.syndrasquare, R.drawable.tahm_kenchsquare, R.drawable.taliyahsquare,
            R.drawable.talonsquare, R.drawable.taricsquare, R.drawable.teemosquare,
            R.drawable.threshsquare, R.drawable.tristanasquare, R.drawable.trundlesquare,
            R.drawable.tryndameresquare, R.drawable.twisted_fatesquare, R.drawable.twitchsquare,
            R.drawable.udyrsquare, R.drawable.urgotsquare, R.drawable.varussquare,
            R.drawable.vaynesquare, R.drawable.veigarsquare, R.drawable.velkozsquare,
            R.drawable.viktorsquare, R.drawable.visquare, R.drawable.vladimirsquare,
            R.drawable.volibearsquare, R.drawable.warwicksquare, R.drawable.wukongsquare,
            R.drawable.xayahsquare, R.drawable.xerathsquare, R.drawable.xin_zhaosquare,
            R.drawable.yasuosquare, R.drawable.yoricksquare, R.drawable.zacsquare,
            R.drawable.zedsquare, R.drawable.ziggssquare, R.drawable.zileansquare,
            R.drawable.zoesquare, R.drawable.zyrasquare
    };
}
