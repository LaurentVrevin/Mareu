package fr.laurentvrevin.mareu.fragment;

import static fr.laurentvrevin.mareu.R.id.spinner_room_filter;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.model.Rooms;
import fr.laurentvrevin.mareu.service.DummyRoomsGenerator;


public class RoomFilterDialogFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "roomsList";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner roomsSpinner;
    private TextView txtroomfilter;
    private Button buttonOK;
    private List<Rooms> roomsList;


    // TODO: Rename and change types and number of parameters


    public static RoomFilterDialogFragment createDialogFragment(List<Rooms> roomsList) {
        RoomFilterDialogFragment fragment = new RoomFilterDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, String.valueOf(roomsList));
        fragment.setArguments(args);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_room_filter_dialog, container, false);
        roomsSpinner = view.findViewById(spinner_room_filter);
        txtroomfilter = view.findViewById(R.id.txt_room_filter);
        buttonOK = view.findViewById(R.id.button_OK);


        //ON GERE LE SPINNER DES SALLES DE REUNION
        //rooms = les salles étant dans getRooms
        Rooms[] rooms = DummyRoomsGenerator.getRooms();
        //On crée l'adapter de type Rooms dans un nouvel adapter
        ArrayAdapter<Rooms> adapter = new ArrayAdapter<Rooms>(getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, rooms);
        this.roomsSpinner.setAdapter(adapter);
        //On définit ce qu'il se passera quand on le déroule, selon ce que l'on fait.

        this.roomsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //On récupère la vue de l'item sélectionné
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            //gestionnaire de l'item sélectionné, on récupère l'item selon sa position, servira pour plus tard avec rooms
            private void onItemSelectedHandler(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                Rooms rooms = (Rooms) adapter.getItem(position);
            }
        });
        return view;
    }
}