package com.example.admin.blueui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by admin on 24/06/2016.
 */
public class FragmentControlSwitches extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        if(container==null)
        {
            return null;
        }

        return inflater.inflate(R.layout.fragment_switches, container, false);
    }
}
