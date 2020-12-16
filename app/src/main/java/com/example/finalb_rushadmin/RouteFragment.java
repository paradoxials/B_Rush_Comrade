package com.example.finalb_rushadmin;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class RouteFragment extends Fragment{
    private Button b1, view, delete;
    private DatabaseHelper databaseHelper;
    private ListView listView;
    private ArrayList<String> listRoute;
    private ArrayAdapter adapter;
    private Dialog dialog;
    private String string;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bus_route, container, false);
        initializeDatabase();

        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_box);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        view = (Button) dialog.findViewById(R.id.btnView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateRoute.class);
                Bundle bundle = new Bundle();
                bundle.putString("Name", string);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        delete = (Button) dialog.findViewById(R.id.btnDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DeleteRoute.class);
                Bundle bundle = new Bundle();
                bundle.putString("Name", string);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        listRoute = new ArrayList<String>();
        listView = (ListView) v.findViewById(R.id.listViewRoutes);
        viewData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                string = (String)parent.getItemAtPosition(position);
                dialog.show();
            }
        });

        b1 = v.findViewById(R.id.btnAddRoute);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddRoute.class);
                startActivity(intent);
            }
        });
        return v;
    }

    private void initializeDatabase()
    {
        if(databaseHelper == null)
        {
            databaseHelper = new DatabaseHelper(getActivity());
        }
    }
    private void viewData(){
        Cursor cursor = databaseHelper.getListBusSched();
        if(cursor.getCount() == 0){ Toast.makeText(getActivity(), "Database is empty", Toast.LENGTH_SHORT).show();}
        else{
            while(cursor.moveToNext()){
                long ID = cursor.getLong(cursor.getColumnIndex("ID"));
                String id = String.valueOf(ID);
                long busID = cursor.getLong(cursor.getColumnIndex("BusID"));
                Cursor bus = databaseHelper.getBus(busID);
                long routeID = bus.getLong(bus.getColumnIndex("RouteID"));
                Cursor route = databaseHelper.getRoute(routeID);
                String name = id+"-"+route.getString(route.getColumnIndex("Destination"));
                listRoute.add(name);
            }
            adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listRoute);
            listView.setAdapter(adapter);
        }
    }
}
