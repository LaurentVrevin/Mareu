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

import java.util.ArrayList;
import java.util.List;

import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.model.Employees;

public class EmployeesListDialogFragment extends android.app.DialogFragment {
    private static final String EMPLOYEES_LIST = "employees";
    private static final String EMPLOYEES_CHECKED_LIST = "checked_employees";
    private static final String LISTENER = "listener";
    private Listener mListener;
    private ArrayList<Employees> mEmployees;
    private ArrayList<Employees> mEmployeesSelected;

    public static EmployeesListDialogFragment  createdialogfragment (ArrayList<Employees> employees, ArrayList<Employees> employeesSelected, Listener listener) {

        EmployeesListDialogFragment f = new EmployeesListDialogFragment();

        Bundle args = new Bundle();
        args.putSerializable(EMPLOYEES_LIST, employees);
        args.putSerializable(EMPLOYEES_CHECKED_LIST, employeesSelected);
        f.setArguments(args);
        f.setListener(listener);
        return f;

    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_employees_list_dialog, container, false);
        mEmployees = (ArrayList<Employees>)getArguments().getSerializable(EMPLOYEES_LIST );
        mEmployeesSelected = (ArrayList<Employees>)getArguments().getSerializable(EMPLOYEES_CHECKED_LIST );



         view.findViewById(R.id.OkButton).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mListener.onEmployeesSelected(mEmployeesSelected);
             }
         });
        view.findViewById(R.id.CancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



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
    public interface Listener{
        void onEmployeesSelected (List<Employees> employees);
    }



}