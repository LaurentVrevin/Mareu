package fr.laurentvrevin.mareu.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import fr.laurentvrevin.mareu.R;



public class ItemEmployeesFragment extends android.app.DialogFragment {
    private RecyclerView recyclerviewemployees;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_employees_list_dialog, container, false);
        Context context = view.getContext();
        recyclerviewemployees = (RecyclerView) view;
        recyclerviewemployees.setLayoutManager(new LinearLayoutManager(context));
        recyclerviewemployees.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }


}