package edu.guet.jjhome.indexsp.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.guet.jjhome.indexsp.R;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChartActivityFragment extends Fragment {

    private LineChartView chart;
    private View rootView;
    private Axis axisX;
    private ArrayList<String> xVals;

    public ChartActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chart, container, false);

//        initChart();
        initMPChart();

        return rootView;
    }

//    private void initChart() {
//
//        chart = (LineChartView) rootView.findViewById(R.id.chart);
//        chart.setOnValueTouchListener(new ValueTouchListener());
//
//        DateTime dt = new DateTime();
//        dt = dt.minus(Period.months(20));
//
//        ArrayList<AxisValue> mAxisValues = new ArrayList<>();
//        ArrayList<PointValue> mPointValues = new ArrayList<>();
//        ArrayList<PointValue> mPointValues1 = new ArrayList<>();
//        ArrayList<PointValue> mPointValues2 = new ArrayList<>();
//        for (int i = 0; i < 20 ; i++) {
//            mPointValues.add(new PointValue(i, new Random().nextInt(30)));
//            mPointValues1.add(new PointValue(i, new Random().nextInt(30)));
//            mPointValues2.add(new PointValue(i, new Random().nextInt(30)));
//            dt = dt.plus(Period.months(1));
//            mAxisValues.add(new AxisValue(i).setLabel(dt.toString("YYYY-MM"))); //为每个对应的i设置相应的label(显示在X轴)
//        }
//        Line line = new Line(mPointValues).setColor(Color.BLUE).setCubic(false);
//        Line line1 = new Line(mPointValues1).setColor(Color.RED).setCubic(false).setShape(ValueShape.DIAMOND);
//        Line line2 = new Line(mPointValues2).setColor(Color.GREEN).setCubic(false).setShape(ValueShape.SQUARE);
//        List<Line> lines = new ArrayList<>();
//        lines.add(line);
//        lines.add(line1);
//        lines.add(line2);
//
//        LineChartData data = new LineChartData();
//        data.setLines(lines);
//
//        //坐标轴
//        axisX = new Axis(); //X轴
//        axisX.setTextColor(Color.BLACK);
//        axisX.setValues(mAxisValues);
//        axisX.setHasLines(true);
//        data.setAxisXBottom(axisX);
//
//        Axis axisY = new Axis();  //Y轴
//        axisY.setTextColor(Color.BLACK);
//        axisY.setHasLines(true);
//        data.setAxisYLeft(axisY);
//
////        data.setAxisYRight(new Axis().setMaxLabelChars(5));
//
////        LineChartView chart = new LineChartView(context);
//        chart.setInteractive(true);
//        chart.setZoomType(ZoomType.HORIZONTAL);
//        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
//        chart.setLineChartData(data);
//
//        final Viewport v = new Viewport(chart.getMaximumViewport());
//        Log.d("view port: ", String.valueOf(v.left) + "|" + String.valueOf(v.right));
//        v.bottom = 0f;
//        v.right = 31f;
//        v.left = 14f;
////        chart.setMaximumViewport(v);
//        chart.setCurrentViewport(v);
////        chart.setCurrentViewportWithAnimation(v);
//
//    }

    private void initMPChart() {
        LineChart chart = (LineChart) rootView.findViewById(R.id.chart);

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();

        xVals = new ArrayList<String>();

        DateTime dt = new DateTime();
        dt = dt.minus(Period.months(20));
        for (int i = 0; i < 20 ; i++) {
            valsComp1.add(new Entry(new Random().nextInt(30), i));
            valsComp2.add(new Entry(new Random().nextInt(30), i));
            dt = dt.plus(Period.months(1));
            xVals.add(dt.toString("YYYY-MM")); //为每个对应的i设置相应的label(显示在X轴)
        }

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Index 1");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(getResources().getColor(R.color.md_red_400));
        setComp1.setLineWidth(4);
        LineDataSet setComp2 = new LineDataSet(valsComp2, "Index 2");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp2.setColor(getResources().getColor(R.color.md_green_400));
        setComp2.setLineWidth(4);

        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        LineData data = new LineData(xVals, dataSets);
        chart.setData(data);

//        chart.setDescription("");
//        chart.animateX(2000);
        chart.setVisibleXRange(5);
        chart.setVisibleYRange(30, YAxis.AxisDependency.LEFT);
        chart.moveViewToX(20);
        chart.setOnChartValueSelectedListener(new ChartValueSelectedListener());
        chart.invalidate(); // refresh
    }

    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
            showPoint(value);
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub
        }

        public void showPoint(PointValue value) {
            AxisValue v = axisX.getValues().get((int) value.getX());
            new MaterialDialog.Builder(getActivity())
                    .title(String.valueOf(v.getLabelAsChars()))
                    .content(String.valueOf(value.getY()))
                    .positiveText(R.string.action_back)
                    .show();
        }
    }

    private class ChartValueSelectedListener implements OnChartValueSelectedListener {

        @Override
        public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
            String v = String.valueOf(e.getVal());
            new MaterialDialog.Builder(getActivity())
                    .title(xVals.get(e.getXIndex()))
                    .content(v)
                    .positiveText(R.string.action_back)
                    .show();
        }

        @Override
        public void onNothingSelected() {

        }
    }
}
