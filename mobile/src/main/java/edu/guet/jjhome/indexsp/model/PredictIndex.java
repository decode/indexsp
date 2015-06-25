package edu.guet.jjhome.indexsp.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "PredictIndex")
public class PredictIndex extends Model implements IndexData {

    @Column(name = "name")
    public String name;

    @Column(name = "date")
    public String date;

    @Column(name = "forecast")
    public String forecast;

    @Column(name = "real")
    public String real;

    @Column(name = "real3")
    public String real3;

    @Column(name = "real4")
    public String real4;

    @Column(name = "real5")
    public String real5;

    @Column(name = "type")
    public String type;

    @Column(name = "seq")
    public String seq;

    public String getValue() {
        return forecast;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getSeq() {
        return seq;
    }

    public static IndexData[] getIndexByParams(String type) {
        List<Model> index_list = new Select().from(PredictIndex.class).where("type = ?", type).orderBy("id ASC").execute();
        PredictIndex[] index = new PredictIndex[index_list.size()];
        return index_list.toArray(index);
    }
}
