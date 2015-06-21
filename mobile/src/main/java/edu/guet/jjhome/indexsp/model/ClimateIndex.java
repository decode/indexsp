package edu.guet.jjhome.indexsp.model;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "ClimateIndex")
public class ClimateIndex extends Model {

    @Column(name = "name")
    public String name;

    @Column(name = "loca")
    public String loca;

    @Column(name = "cate")
    public String cate;

    @Column(name = "date")
    public String date;

    @Column(name = "sme")
    public String sme;

    @Column(name = "ratio")
    public String ratio;

    @Column(name = "type")
    public String type;

    @Column(name = "graph")
    public String graph;

    public static ClimateIndex[] getAllIndex() {
        List<Model> index_list = new Select().from(ClimateIndex.class).orderBy("date ASC").execute();
        ClimateIndex[] index = new ClimateIndex[index_list.size()];
        return index_list.toArray(index);
    }


    public static ClimateIndex fetch(String date, String loca, String cate) {
        ClimateIndex climate = new Select().from(ClimateIndex.class).where("date = ? and loca = ? and cate = ?", date, loca, cate).orderBy("id ASC").executeSingle();

        if (climate == null) {
            climate = new ClimateIndex();
            Log.d("Not find existed", "create new");
        }
        return climate;
    }
}
