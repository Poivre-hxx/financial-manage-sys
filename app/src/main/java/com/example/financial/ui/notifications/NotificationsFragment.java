package com.example.financial.ui.notifications;

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
import androidx.lifecycle.ViewModelProvider;

import com.example.financial.MainActivity;
import com.example.financial.databinding.FragmentNotificationsBinding;

import org.w3c.dom.Text;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button btnLogout = binding.LogoutButton;
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

        Button btnpwd = binding.pwdButton;
        EditText newpwd = binding.editpwd;
        Button btncheck = binding.checkBtn;

        btnpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newpwd.setVisibility(View.VISIBLE);
                btncheck.setVisibility(View.VISIBLE);
                btncheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pwd = newpwd.getText().toString();
                        MainActivity.pwd = pwd;
                        newpwd.setVisibility(View.INVISIBLE);
                        btncheck.setVisibility(View.INVISIBLE);
                        // 跳出弹窗
                        Toast.makeText(getActivity(), "密码修改成功", Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }
                });
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