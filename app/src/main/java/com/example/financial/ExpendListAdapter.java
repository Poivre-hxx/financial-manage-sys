package com.example.financial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpendListAdapter extends RecyclerView.Adapter<ExpendListAdapter.ExpendViewHolder> {
    class ExpendViewHolder extends RecyclerView.ViewHolder {
        private final TextView expendItemView;
        private ExpendViewHolder(View itemView) {
            super(itemView);
            expendItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Expend> mExpends;

    public ExpendListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ExpendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ExpendViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(ExpendViewHolder holder, int position) {
        if (mExpends != null) {
            Expend current = mExpends.get(position);
            holder.expendItemView.setText(current.getExpend());
        } else {
            holder.expendItemView.setText("No Expend");
        }
    }
    public void setExpends(List<Expend> expends) {
        mExpends = expends;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (mExpends != null)
            return mExpends.size();
        else return 0;
    }

    public Expend getExpendAtPosition (int position) {
        return mExpends.get(position);
    }
}
