package fr.laurentvrevin.mareu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.model.Employees;


public class EmployeesRecyclerViewAdapter extends RecyclerView.Adapter<EmployeesRecyclerViewAdapter.ViewHolder> {
    Context mContext;
    List<Employees> mEmployees;


    public EmployeesRecyclerViewAdapter(List<Employees> employees) {
        this.mEmployees = employees;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_employees_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.displayEmployees(this.mEmployees.get(position));

    }

    @Override
    public int getItemCount() {
        return mEmployees.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mEmployeeName, mEmployeeFunction, mEmployeeEmail;
        public ImageButton checkedButton;


        public ViewHolder(View itemView) {
            super(itemView);
            mEmployeeName = itemView.findViewById(R.id.tv_employee_name);
            mEmployeeFunction = itemView.findViewById(R.id.tv_employee_function);
            mEmployeeEmail = itemView.findViewById(R.id.tv_employee_email);
            //checkedButton = itemView.findViewById(R.id.item_list_meeting_delete_button);

        }

        public void displayEmployees(Employees employees) {
            mEmployeeName.setText(employees.getFirstname());
            mEmployeeFunction.setText(employees.getFunction());
            mEmployeeEmail.setText(employees.getEmail());

        }
    }

}
