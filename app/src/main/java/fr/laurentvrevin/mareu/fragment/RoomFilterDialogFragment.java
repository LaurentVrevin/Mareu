package fr.laurentvrevin.mareu.fragment;

import static fr.laurentvrevin.mareu.R.id.spinner_room_filter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;

import java.util.Arrays;
import java.util.List;

import fr.laurentvrevin.mareu.R;
import fr.laurentvrevin.mareu.model.Room;
import fr.laurentvrevin.mareu.service.DummyRoomGenerator;


public class RoomFilterDialogFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "roomsList";
    private static RoomListener mRoomListener;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Spinner roomsSpinner;
    private List<Room> roomsList;
    private Button buttonOkRoomFilter;


    // TODO: Rename and change types and number of parameters


    public static RoomFilterDialogFragment createDialogFragment(List<Room> roomsList, RoomListener roomListener) {
        mRoomListener = roomListener;
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
        buttonOkRoomFilter = view.findViewById(R.id.button_OK_RoomFilter);


        //ON GERE LE SPINNER DES SALLES DE REUNION
        //rooms = les salles étant dans getRooms
        roomsList = Arrays.asList(DummyRoomGenerator.getRooms());
        //On crée l'adapter de type Rooms dans un nouvel adapter
        ArrayAdapter<Room> adapter = new ArrayAdapter<Room>(getContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, roomsList);
        this.roomsSpinner.setAdapter(adapter);
        //On définit ce qu'il se passera quand on le déroule, selon ce que l'on fait.

        this.roomsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //On récupère la vue de l'item sélectionné
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
                mRoomListener.onRoomSelected(roomsList.get((int) id));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }

            //gestionnaire de l'item sélectionné, on récupère l'item selon sa position, servira pour plus tard avec rooms
            private void onItemSelectedHandler(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                Room rooms = (Room) adapter.getItem(position);
            }
        });
        buttonOkRoomFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;

    }

    //on crée une interface pour le listener afin de récupérer la donnée rooms
    public interface RoomListener {
        void onRoomSelected(Room rooms);


    }
}