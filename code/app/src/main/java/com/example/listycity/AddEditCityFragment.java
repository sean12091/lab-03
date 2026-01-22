package com.example.listycity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddEditCityFragment extends DialogFragment {

    public interface OnCityEditedListener {
        void onCityEdited(); // simplest callback: just tell Activity to refresh
    }

    private static final String ARG_CITY = "arg_city";
    private City city; // city being edited

    public AddEditCityFragment() {
        // required empty constructor
    }

    // preferred pattern: pass data via Bundle
    public static AddEditCityFragment newInstance(City city) {
        AddEditCityFragment frag = new AddEditCityFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() != null) {
            city = (City) getArguments().getSerializable(ARG_CITY);
        }

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_city, null);

        EditText nameEt = view.findViewById(R.id.city_name);
        EditText provEt = view.findViewById(R.id.city_province);

        // pre-fill for edit
        if (city != null) {
            nameEt.setText(city.getName());
            provEt.setText(city.getProvince());
        }

        return new AlertDialog.Builder(requireContext())
                .setTitle("Edit city")
                .setView(view)
                .setPositiveButton("Save", (dialog, which) -> {
                    String newName = nameEt.getText().toString().trim();
                    String newProv = provEt.getText().toString().trim();

                    if (city != null && !newName.isEmpty() && !newProv.isEmpty()) {
                        city.setName(newName);
                        city.setProvince(newProv);

                        // tell Activity to refresh adapter
                        if (getActivity() instanceof OnCityEditedListener) {
                            ((OnCityEditedListener) getActivity()).onCityEdited();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }
}
