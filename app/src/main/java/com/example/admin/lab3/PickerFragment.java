package com.example.admin.lab3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 4/28/2017.
 */

public class PickerFragment extends Fragment {
    boolean dateOk = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        if (dateOk){
            view = inflater.inflate(R.layout.frag_date,container,false);
        }else {
            view = inflater.inflate(R.layout.frag_time,container,false);
        }
        return view;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        dateOk=args.getBoolean("dateOK");
    }
}
