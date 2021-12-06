package com.group2.katching;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.group2.katching.entity.User;
import com.group2.katching.ui.UserViewModel;

public class TransferFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransferFragment() {
        // Required empty public constructor
    }

//    public static TransferFragment newInstance(String param1, String param2) {
//        TransferFragment fragment = new TransferFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        container.setOnClickListener(this::onClick);
        return inflater.inflate(R.layout.fragment_transfer, container, false);
    }

    @Override
    public void onClick(View view) {
        final String[] dbKey = new String[1];
        final Double[] currentBalance = new Double[1];
        final String[] senderEmail = new String[1];

        UserViewModel viewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
        viewModel.userData.observeForever(new Observer<User>() { // sets view model as observer

            @Override
            public void onChanged(User s) {
                dbKey[0] = s.getDataBaseId();
                currentBalance[0] = Double.valueOf(s.getBalance());
                senderEmail[0] = s.getEmail();
            }
        });

        Intent intent = new Intent(getActivity(), TransferActivity.class);
        intent.putExtra("key", dbKey[0]);
        intent.putExtra("balance", currentBalance[0]);
        intent.putExtra("email", senderEmail[0]);
        startActivity(intent);
    }
}