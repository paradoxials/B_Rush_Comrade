package com.example.finalb_rushadmin.fragments.Dashboard;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.finalb_rushadmin.DatabaseHelper;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.material.textfield.TextInputLayout;
import com.example.finalb_rushadmin.R;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthStatisticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthStatisticFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MonthStatisticFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonthStatisticFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthStatisticFragment newInstance(String param1, String param2) {
        MonthStatisticFragment fragment = new MonthStatisticFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // Dropdown Text
    private TextInputLayout textInputLayout;
    private AutoCompleteTextView autoCompleteTextView;

    // Pie Charts
    private PieChart pieTickets, pieTicketsCancelled, pieSeatNumber;
    private PieDataSet dataSetTickets, dataSetTicketsCancelled, dataSetSeatNumber;
    private PieData dataTickets, dataTicketsCancelled, dataSeatNumber;
    private Legend legendTickets, legendTicketsCancelled, legendSeatNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_month_statistic, container, false);

        setMonthDropdown(root);
        createPieChartTime(root);
        
        return root;
    }

    private void setMonthDropdown(View root) {
        Calendar cal = Calendar.getInstance();

        textInputLayout = root.findViewById(R.id.dropdownMonth);
        autoCompleteTextView = root.findViewById(R.id.dropdown_month_choices);

        final String[] items = new String[] {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December",
        };

        final ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.layout_dropdown_list, items);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setText(arrayAdapter.getItem(cal.get(Calendar.MONTH)).toString(), false);
    }

    public void createPieChartTime(View root) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this.getContext());
        HashMap<String, Integer> hashMap = databaseHelper.getCountTicketDataMonth(1, Integer.parseInt(String.valueOf(autoCompleteTextView.getText())));
        // Pie Time (Day)
        pieTickets = root.findViewById(R.id.ticket);
        // Sample Data
        ArrayList<PieEntry> timeFreqDay = new ArrayList<>();

        Iterator iterator = hashMap.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)iterator.next();
            timeFreqDay.add(new PieEntry((Float) mapElement.getValue(), mapElement.getKey()));
        }

        dataSetTickets = new PieDataSet(timeFreqDay, "");
        dataSetTickets.setColors(COLORFUL_COLORS);
        dataSetTickets.setValueTextColor(Color.WHITE);
        dataSetTickets.setValueTextSize(12f);

        dataTickets = new PieData(dataSetTickets);

        pieTickets.setData(dataTickets);
        pieTickets.setUsePercentValues(true);
        pieTickets.getDescription().setEnabled(false);
        pieTickets.setCenterText("Time Frequency (Day)");
        pieTickets.setHoleColor(Color.TRANSPARENT);
        pieTickets.setEntryLabelColor(Color.TRANSPARENT);
        pieTickets.animate();

        legendTickets = pieTickets.getLegend();
        legendTickets.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legendTickets.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        HashMap<String, Integer> hashMap1 = databaseHelper.getCountTicketDataMonthCancelled(1, Integer.parseInt(String.valueOf(autoCompleteTextView.getText())));
        Iterator iterator1 = hashMap1.entrySet().iterator();

        // Pie Time (Night)
        pieTicketsCancelled = root.findViewById(R.id.ticketCancelled);
        // Sample Data
        ArrayList<PieEntry> timeFreqNight = new ArrayList<>();

        while(iterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)iterator.next();
            timeFreqNight.add(new PieEntry((Float) mapElement.getValue(), mapElement.getKey()));
        }

        dataSetTicketsCancelled = new PieDataSet(timeFreqNight, "");
        dataSetTicketsCancelled.setColors(COLORFUL_COLORS);
        dataSetTicketsCancelled.setValueTextColor(Color.WHITE);
        dataSetTicketsCancelled.setValueTextSize(12f);

        dataTicketsCancelled = new PieData(dataSetTicketsCancelled);

        pieTicketsCancelled.setData(dataTicketsCancelled);
        pieTicketsCancelled.setUsePercentValues(true);
        pieTicketsCancelled.getDescription().setEnabled(false);
        pieTicketsCancelled.setCenterText("Time Frequency (Night)");
        pieTicketsCancelled.setHoleColor(Color.TRANSPARENT);
        pieTicketsCancelled.setEntryLabelColor(Color.TRANSPARENT);
        pieTicketsCancelled.animate();

        legendTicketsCancelled = pieTicketsCancelled.getLegend();
        legendTicketsCancelled.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legendTicketsCancelled.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

        HashMap<String, Integer> hashMap2 = databaseHelper.getCountTicketSeatNumberMonth(1, Integer.parseInt(String.valueOf(autoCompleteTextView.getText())));
        Iterator iterator2 = hashMap2.entrySet().iterator();

        pieSeatNumber = root.findViewById(R.id.seatNumber);
        // Sample Data
        ArrayList<PieEntry> seatNumber = new ArrayList<>();

        while(iterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)iterator.next();
            timeFreqNight.add(new PieEntry((Float) mapElement.getValue(), mapElement.getKey()));
        }

        dataSetSeatNumber = new PieDataSet(timeFreqNight, "");
        dataSetSeatNumber.setColors(COLORFUL_COLORS);
        dataSetSeatNumber.setValueTextColor(Color.WHITE);
        dataSetSeatNumber.setValueTextSize(12f);

        dataSeatNumber = new PieData(dataSetSeatNumber);

        pieSeatNumber.setData(dataSeatNumber);
        pieSeatNumber.setUsePercentValues(true);
        pieSeatNumber.getDescription().setEnabled(false);
        pieSeatNumber.setCenterText("Time Frequency (Night)");
        pieSeatNumber.setHoleColor(Color.TRANSPARENT);
        pieSeatNumber.setEntryLabelColor(Color.TRANSPARENT);
        pieSeatNumber.animate();

        legendSeatNumber = pieSeatNumber.getLegend();
        legendSeatNumber.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legendSeatNumber.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
    }
}