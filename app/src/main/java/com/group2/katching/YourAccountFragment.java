package com.group2.katching;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group2.katching.entity.User;
import com.group2.katching.ui.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YourAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YourAccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public YourAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment YourAccount.
     */
    // TODO: Rename and change types and number of parameters
    public static YourAccountFragment newInstance(String param1, String param2) {
        YourAccountFragment fragment = new YourAccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static YourAccountFragment newInstance() {
        return new YourAccountFragment();
    }

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
        DatabaseReference mFirebaseDatabase;
        FirebaseDatabase mFirebaseInstance;
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        UserViewModel viewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
        viewModel.userData.observeForever(new Observer<User>() {
            @Override
            public void onChanged(User s) {
                String balance;
                balance = String.valueOf(s.getBalance());

                TextView tvBalance = getView().findViewById(R.id.yourAccount_value);
                tvBalance.setText("$" + balance + " CAD");
            }
        });

        User user = null;


        final View rootView = inflater.inflate(R.layout.fragment_your_account, container, false);

        final TextView tvBalance = rootView.findViewById(R.id.yourAccount_value);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_account, container, false);
    }
}