package edu.guet.jjhome.indexsp.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Highlight;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;

import java.util.ArrayList;

import edu.guet.jjhome.indexsp.R;
import edu.guet.jjhome.indexsp.model.ClimateIndex;
import edu.guet.jjhome.indexsp.util.AppConstants;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChartActivityFragment extends Fragment {

    private LineChartView chart;
    private View rootView;
    private Axis axisX;
    private ArrayList<String> xVals;
    private Bundle param;

    public ChartActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chart, container, false);

        param = getArguments();

//        initChart();
//        initMPChart();

        Toast.makeText(getActivity().getBaseContext(), "click tab", Toast.LENGTH_SHORT).show();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        initMPChart();
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
    private ClimateIndex[] getIndexDataByType() {
        String index_type = param.getString("index_type");
        int position = FragmentPagerItem.getPosition(getArguments());
        Log.d("index_type:", index_type);

        Toast.makeText(getActivity().getBaseContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
        ClimateIndex[] indexes;
        switch (index_type) {
            case AppConstants.PREDICT_PRODUCT_PMI:
                indexes = ClimateIndex.getIndexByParams("全国", "旅游行业");
                break;
            default:
                indexes = ClimateIndex.getIndexByParams("全国", "全行业");
        }
        return indexes;
    }

    private void initMPChart() {
        LineChart chart = (LineChart) rootView.findViewById(R.id.chart);

        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();

        xVals = new ArrayList<String>();

        ClimateIndex[] indexes = getIndexDataByType();
        Log.d("climate index length:", String.valueOf(indexes.length));

//        DateTime dt = new DateTime();
//        dt = dt.minus(Period.months(indexes.length));
        for (int i = 0; i < indexes.length ; i++) {
//            Log.d("climate index:", indexes[i].sme);
            valsComp1.add(new Entry(Integer.valueOf(indexes[i].sme), i));
//            valsComp2.add(new Entry(new Random().nextInt(30), i));
//            dt = dt.plus(Period.months(1));
//            xVals.add(dt.toString("YYYY-MM")); //为每个对应的i设置相应的label(显示在X轴)
            xVals.add(indexes[i].date); //为每个对应的i设置相应的label(显示在X轴)
        }

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Index 1");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        setComp1.setColor(getResources().getColor(R.color.md_red_400));
        setComp1.setLineWidth(4);
//        LineDataSet setComp2 = new LineDataSet(valsComp2, "Index 2");
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
//        setComp2.setColor(getResources().getColor(R.color.md_green_400));
//        setComp2.setLineWidth(4);

        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);
//        dataSets.add(setComp2);

        LineData data = new LineData(xVals, dataSets);
        chart.setData(data);

//        chart.setDescription("");
//        chart.animateX(2000);
        chart.setVisibleXRange(5);
//        chart.setVisibleYRange(30, YAxis.AxisDependency.LEFT);
        chart.moveViewToX(indexes.length);
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
