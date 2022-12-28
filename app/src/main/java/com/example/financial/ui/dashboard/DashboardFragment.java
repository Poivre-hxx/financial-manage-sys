package com.example.financial.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.financial.DBHelper;
import com.example.financial.MainActivity;
import com.example.financial.R;
import com.example.financial.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        Spinner querySpinner = binding.querySpinner;
        EditText queryData = binding.queryData;
        Button queryBtn = binding.queryBtn;
        Button btnAdd = binding.btnAdd;
        Button ListAll = binding.ListAll;

        String query = querySpinner.getSelectedItem().toString();
        // 监听querySpinner发生改变
        querySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 获取选中的值
                String query = querySpinner.getSelectedItem().toString();

                if(!query.equals("请选择")) {
                    queryData.setVisibility(View.VISIBLE);
                    queryBtn.setVisibility(View.VISIBLE);
                    btnAdd.setVisibility(View.VISIBLE);
                    ListAll.setVisibility(View.VISIBLE);

                    queryBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String data = queryData.getText().toString();
                            // 查询 spend 表中 query字段为 data 的数据
                            if(data != "") {
                                Toast.makeText(getActivity(), "查询成功", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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