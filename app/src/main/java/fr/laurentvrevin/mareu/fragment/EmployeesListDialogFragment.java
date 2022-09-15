package fr.laurentvrevin.mareu.fragment;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.laurentvrevin.mareu.DI.DI;
import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.adapter.EmployeesRecyclerViewAdapter;
import fr.laurentvrevin.mareu.model.Employees;
import fr.laurentvrevin.mareu.service.MareuApiService;


public class EmployeesListDialogFragment extends DialogFragment {

    private static final String EMPLOYEES_LIST = "employees";
    private static final String EMPLOYEES_CHECKED_LIST = "checked_employees";
    private static final String LISTENER = "listener";
    private Listener mListener;
    public List<Employees> mEmployees;
    private List<Employees> mEmployeesSelected;
    private RecyclerView mRecyclerView;
    private MareuApiService mMareuApiService;


    public static EmployeesListDialogFragment createDialogFragment(ArrayList<Employees> employees, ArrayList<Employees> employeesSelected, Listener listener) {
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_employees_list_dialog, container, false);
        assert getArguments() != null;
        mEmployees = (List<Employees>) getArguments().getSerializable(EMPLOYEES_LIST);
        mEmployeesSelected = (List<Employees>) getArguments().getSerializable(EMPLOYEES_CHECKED_LIST);
        mRecyclerView = view.findViewById(R.id.recycler_view_employees);
        mMareuApiService = DI.getEmployeesApiService();
        mEmployees = mMareuApiService.getEmployees();
        mRecyclerView.setAdapter(new EmployeesRecyclerViewAdapter(mEmployees));

        //On gère le bouton OK qui écoute les éléments de liste sélectionnées
        view.findViewById(R.id.ok_button).setOnClickListener(v -> mListener.onEmployeesSelected(mEmployeesSelected));
        //On gère le bouton Cancel pour annuler
        view.findViewById(R.id.cancel_button).setOnClickListener(v -> dismiss());
        return view;
    }



    //On crée un contrat Listener qui renvoie la Liste des Employees sélectionnés
    public interface Listener {
        void onEmployeesSelected(List<Employees> employees);
    }


}