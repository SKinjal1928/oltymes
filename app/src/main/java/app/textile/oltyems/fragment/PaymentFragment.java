package app.textile.oltyems.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import app.textile.oltyems.databinding.FragDashboardBinding;

public class PaymentFragment extends Fragment {

    FragDashboardBinding binding;

    public PaymentFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.frag_dashboard, container, false);
        binding = FragDashboardBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

}
