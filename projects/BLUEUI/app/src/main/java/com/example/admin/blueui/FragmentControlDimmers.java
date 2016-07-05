package com.example.admin.blueui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by admin on 24/06/2016.
 */
public class FragmentControlDimmers extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        if(container==null)
        {
            return null;
        }
        View view =  inflater.inflate(R.layout.fragment_dimmers, container, false);
        try
        {
        ListView lvwDimmers = (ListView) view.findViewById(R.id.lvwDimmers);
        List<String> listOfDimmers = new ArrayList<>();
        listOfDimmers.add("dimmer 1");
        listOfDimmers.add("dimmer 2");
        final ArrayAdapter<String> aaDimmers = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.lvw_dimmers_element, R.id.txtDimmersElement, listOfDimmers);
        lvwDimmers.setAdapter(aaDimmers);
        }
        catch (Exception e) {
            TextView textView = (TextView) container.findViewById(R.id.txtFragmentDimmers);
            textView.setText(e.toString());
        }

        return view;
    }
}
