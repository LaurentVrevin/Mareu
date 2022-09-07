package fr.laurentvrevin.mareu.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.model.Employees;

public class EmployeesListDialogFragment extends android.app.DialogFragment {
    private Context mContext;
    private Button OkButton, CancelButton;
    private TextView title, message;
    private List<Employees> mEmployees;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_employees_list_dialog, container, false);

        OkButton = view.findViewById(R.id.OkButton);
        CancelButton = view.findViewById(R.id.CancelButton);

        //title = view.findViewById(R.id.textView);

        /**yes.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {

        yes.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {

        Log.e("yes", "Clicked Yes");

        }
        });

        }
        });

         no.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
        Log.e("no", "Clicked No");
        }
        });

         */
        return view;

    }



}