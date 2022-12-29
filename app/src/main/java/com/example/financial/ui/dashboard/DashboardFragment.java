package com.example.financial.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financial.Expend;
import com.example.financial.ExpendListAdapter;
import com.example.financial.ExpendViewModel;
import com.example.financial.R;
import com.example.financial.databinding.FragmentDashboardBinding;
import com.example.financial.databinding.FragmentHomeBinding;
import com.example.financial.ui.home.HomeFragment;
import com.example.financial.ui.home.HomeViewModel;

import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private ExpendViewModel mExpendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        RecyclerView recyclerView = binding.recyclerView;
        final ExpendListAdapter adapter = new ExpendListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mExpendViewModel = new ViewModelProvider(this).get(ExpendViewModel.class);

        mExpendViewModel.getAllExpends().observe(getViewLifecycleOwner(), new Observer<List<Expend>>() {
            @Override
            public void onChanged(final List<Expend> expends) {
                adapter.setExpends(expends);
            }
        });


        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}