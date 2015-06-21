package edu.guet.jjhome.indexsp.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "ClimateIndex")
public class PredictIndex extends Model {

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

}
