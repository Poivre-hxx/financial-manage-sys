package com.example.financial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave, btnDelete, btnClose;
    private EditText edExpName, edLabel, edPrice, edMonth, edDate;
    private int _Exp_Id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClosed);

        edExpName = (EditText) findViewById(R.id.edExpName);
        edLabel = (EditText) findViewById(R.id.edLabel);
        edPrice = (EditText) findViewById(R.id.edPrice);
        edMonth = (EditText) findViewById(R.id.edMonth);
        edDate = (EditText) findViewById(R.id.edDate);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);

        _Exp_Id = 0;
        Intent intent = getIntent();
        _Exp_Id = intent.getIntExtra("exp_Id", 0);
        ExpendRepo repo = new ExpendRepo(this);
        Expend expend = new Expend();
        expend = repo.getExpendById(_Exp_Id);

        edExpName.setText(String.valueOf(expend.expName));
        edLabel.setText(String.valueOf(expend.label));
        edPrice.setText(String.valueOf(expend.price));
        edMonth.setText(String.valueOf(expend.month));
        edDate.setText(String.valueOf(expend.date));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(MenuItem item) {
        // Inflate the menu; this adds items to the action bar if it is present.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.btnSave)){
            ExpendRepo repo = new ExpendRepo(this);
            Expend expend = new Expend();
            expend.expName = edExpName.getText().toString();
            expend.label = edLabel.getText().toString();
            expend.price = Float.parseFloat(edPrice.getText().toString());
            expend.month = Integer.parseInt(edMonth.getText().toString());
            expend.date = Integer.parseInt(edDate.getText().toString());
            expend.id = _Exp_Id;

            if (_Exp_Id == 0){
                _Exp_Id = repo.insert(expend);

                Toast.makeText(this,"New Expend Insert",Toast.LENGTH_SHORT).show();
            }else{
                repo.update(expend);
                Toast.makeText(this,"Expend Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (v == findViewById(R.id.btnDelete)){
            ExpendRepo repo = new ExpendRepo(this);
            repo.delete(_Exp_Id);
            Toast.makeText(this,"Expend Record Deleted",Toast.LENGTH_SHORT).show();
            finish();
        }else if (v == findViewById(R.id.btnClosed)){
            finish();
        }
    }
}