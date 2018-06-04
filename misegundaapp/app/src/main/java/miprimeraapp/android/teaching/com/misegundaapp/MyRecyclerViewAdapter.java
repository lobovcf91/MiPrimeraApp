package miprimeraapp.android.teaching.com.misegundaapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    private  String[] dataSet;

    public MyRecyclerViewAdapter (String[] dataSet) {
        this.dataSet = dataSet;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("ADAPTER", "MyAdapter: creating ViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_holder_item,
                parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).bind(dataSet[position]);

    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.length : 0;
    }
}
