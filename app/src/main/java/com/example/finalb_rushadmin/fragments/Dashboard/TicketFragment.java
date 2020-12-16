package com.example.finalb_rushadmin.fragments.Dashboard;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.finalb_rushadmin.DatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.example.finalb_rushadmin.R;
import com.example.finalb_rushadmin.activities.Location.MapActivity;
import com.example.finalb_rushadmin.activities.TicketDetail.TicketDetailActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TicketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TicketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TicketFragment newInstance(String param1, String param2) {
        TicketFragment fragment = new TicketFragment();
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

    private TextInputEditText inputEditText;
    private TextInputLayout inputLayout;
    private int hour, minu;
    private LinearLayout linearLayoutNewTickets, linearLayoutExTickets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ticket, container, false);

        showTimeDialog(root);
        addDynamicNewTickets(root);
        addDynamicExpiredTickets(root);

        return root;
    }

    public void showTimeDialog(View root) {
        inputEditText = root.findViewById(R.id.timeSearchInput);

        inputEditText.setText("6:30 AM");

        inputEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("In EditText");
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour = hourOfDay;
                                minu = minute;

                                Calendar cal = Calendar.getInstance();

                                cal.set(0, 0, 0, hour, minu);
                                SimpleDateFormat simpleformat = new SimpleDateFormat("hh:mm aa");

                                inputEditText.setText(""+simpleformat.format(cal.getTime()));
                            }
                        }, 6, 30, false);

                timePickerDialog.updateTime(hour, minu);
                timePickerDialog.show();
            }
        });

        inputLayout = root.findViewById(R.id.timeSearchField);

        inputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("In Layout");
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour = hourOfDay;
                                minu = minute;

                                Calendar cal = Calendar.getInstance();

                                cal.set(0, 0, 0, hour, minu);
                            }
                        }, 12, 0, false);

                timePickerDialog.updateTime(hour, minu);
                timePickerDialog.show();
            }
        });
    }

    public void addDynamicNewTickets(View root) {
        DatabaseHelper databaseHelper = new DatabaseHelper(this.getContext());
        ArrayList<String> arrayList = databaseHelper.getListTicketsAvailable(1);
        linearLayoutNewTickets = root.findViewById(R.id.linearLayoutNewTicket);

        for (int i = 0; i <= arrayList.size(); i++) {
            View wizardView = getLayoutInflater().inflate(R.layout.layout_ticket_button, linearLayoutNewTickets, false);
            LinearLayout linearLayout = wizardView.findViewById(R.id.btn_ticket);
            TextView textView = wizardView.findViewById(R.id.TicketName);
            textView.setText(arrayList.get(i));
            final int j = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTicketDetail(j);
                }
            });
            ImageView imageView = wizardView.findViewById(R.id.btn_location);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLocation();
                }
            });
            linearLayoutNewTickets.addView(wizardView);
        }
    }

    public void openTicketDetail(int i) {
        Intent intent = new Intent(getContext(), TicketDetailActivity.class);
        DatabaseHelper databaseHelper = new DatabaseHelper(this.getContext());
        ArrayList<String> arrayList = databaseHelper.getTicketDetails(1, i);
        intent.putExtra("data", arrayList);
        startActivity(intent);
    }

    public void openLocation() {
        Intent intent = new Intent(getContext(), MapActivity.class);
        startActivity(intent);
    }
    public void addDynamicExpiredTickets(View root) {
        linearLayoutExTickets = root.findViewById(R.id.linearLayoutExpiredTicket);

        DatabaseHelper databaseHelper = new DatabaseHelper(this.getContext());
        ArrayList<String> arrayList = databaseHelper.getListTicketsExpired(1);

        for (int i = 0; i <= arrayList.size(); i++) {
            View wizardView = getLayoutInflater().inflate(R.layout.layout_ticket_button, linearLayoutNewTickets, false);
            LinearLayout linearLayout = wizardView.findViewById(R.id.btn_ticket);
            TextView textView = wizardView.findViewById(R.id.TicketName);
            textView.setText(arrayList.get(i));
            final int j = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTicketDetail(j);
                }
            });
            ImageView imageView = wizardView.findViewById(R.id.btn_location);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLocation();
                }
            });
            linearLayoutNewTickets.addView(wizardView);
        }

        for (int i = 1; i <= 10; i++) {
            View wizardView = getLayoutInflater().inflate(R.layout.layout_ticket_button, linearLayoutExTickets, false);
            LinearLayout linearLayout = wizardView.findViewById(R.id.btn_ticket);
            TextView textView = wizardView.findViewById(R.id.TicketName);
            textView.setText(arrayList.get(i));
            final int j = i;
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTicketDetail(j);
                }
            });
            ImageView imageView = wizardView.findViewById(R.id.btn_location);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLocation();
                }
            });
            linearLayoutExTickets.addView(wizardView);
        }
    }

}