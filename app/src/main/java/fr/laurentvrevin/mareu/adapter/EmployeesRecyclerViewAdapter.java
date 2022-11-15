package fr.laurentvrevin.mareu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.model.Employees;


public class EmployeesRecyclerViewAdapter extends RecyclerView.Adapter<EmployeesRecyclerViewAdapter.ViewHolder> {
    public ArrayList<Employees> mEmployees;
    public ArrayList<Employees> mEmployeesChecked;


    public EmployeesRecyclerViewAdapter(ArrayList<Employees> employees, ArrayList<Employees> checkedemployees) {
        this.mEmployees = new ArrayList<>(employees);
        this.mEmployeesChecked = new ArrayList<>(checkedemployees);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_employees_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employees employee = mEmployees.get(position);
        holder.mEmployeeName.setText(employee.getFirstname());
        holder.mEmployeeFunction.setText(employee.getFunction());
        holder.mEmployeeEmail.setText(employee.getEmail());
        if (mEmployeesChecked.contains(employee)) {
            holder.checkedButton.setChecked(true);
        }
        holder.checkedButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEmployeesChecked.add(employee);
                } else {
                    mEmployeesChecked.remove(employee);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mEmployeeName, mEmployeeFunction, mEmployeeEmail;
        public CheckBox checkedButton;


        public ViewHolder(View itemView) {
            super(itemView);
            mEmployeeName = itemView.findViewById(R.id.tv_employee_name);
            mEmployeeFunction = itemView.findViewById(R.id.tv_employee_function);
            mEmployeeEmail = itemView.findViewById(R.id.tv_employee_email);
            checkedButton = itemView.findViewById(R.id.checkbox_item);

        }
    }
}
