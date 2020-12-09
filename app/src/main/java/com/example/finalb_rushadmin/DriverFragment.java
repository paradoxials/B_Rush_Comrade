package com.example.finalb_rushadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DriverFragment extends Fragment {
    private Button b1;
    private ImageView imgUp, imgDel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bus_driver, container, false);

        b1 = v.findViewById(R.id.btnAddDriver);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDriver.class);
                startActivity(intent);
            }
        });

        imgUp = v.findViewById(R.id.imgUpdateDriver);
        imgUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateDriver.class);
                startActivity(intent);
            }
        });

        imgDel = v.findViewById(R.id.imgDeleteDriver);
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DeleteDriver.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
