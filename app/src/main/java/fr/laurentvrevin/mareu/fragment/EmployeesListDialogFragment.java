package fr.laurentvrevin.mareu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
    private EmployeesRecyclerViewAdapter mEmployeesAdapter;
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
        //On vérifie à la création de la vue si la liste comporte des employees sélectionnés et non sélectionnés
        ArrayList<Employees> employees = (ArrayList<Employees>) getArguments().getSerializable(EMPLOYEES_LIST);
        ArrayList<Employees> employeesChecked = (ArrayList<Employees>) getArguments().getSerializable(EMPLOYEES_CHECKED_LIST);
        //adapter = nouvel adapter de recyclerview comprenant les employes cochés et non cochés
        mEmployeesAdapter = new EmployeesRecyclerViewAdapter(employees, employeesChecked);
        mRecyclerView = view.findViewById(R.id.recycler_view_employees);
        mMareuApiService = DI.getEmployeesApiService();
        //
        mRecyclerView.setAdapter(mEmployeesAdapter);

        //On gère le bouton OK qui écoute les éléments de liste sélectionnées
        view.findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onEmployeesSelected(mEmployeesAdapter.mEmployeesChecked);

            }});

        //On gère le bouton Cancel pour annuler
        view.findViewById(R.id.cancel_button).setOnClickListener(v -> dismiss());
        return view;
    }

    //On crée un contrat Listener qui renvoie la Liste des Employees sélectionnés
    public interface Listener {
        void onEmployeesSelected(ArrayList<Employees> employees);

    }


}