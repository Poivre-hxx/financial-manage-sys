package com.example.financial.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.financial.Expend;
import com.example.financial.ExpendListAdapter;
import com.example.financial.ExpendViewModel;
import com.example.financial.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    private ExpendViewModel mExpendViewModel;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button btnSave = binding.buttonSave;
        final ExpendListAdapter adapter = new ExpendListAdapter(getActivity());
        mExpendViewModel = new ViewModelProvider(this).get(ExpendViewModel.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edExpName = binding.edExpName;
                EditText edLabel = binding.edLabel;
                EditText edPrice = binding.edPrice;
                EditText edMonth = binding.edMonth;
                String ExpName = edExpName.getText().toString();
                String Label = edLabel.getText().toString();
                String Price = edPrice.getText().toString();
                String Month = edMonth.getText().toString();
                String exp = ExpName + " " + Label + " " + Price + " " + Month;
                if(exp != null){
                    Expend expend = new Expend(exp);
                    mExpendViewModel.insert(expend);
                    Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "请输入数据" + exp, Toast.LENGTH_SHORT).show();
                }
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