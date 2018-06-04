package miprimeraapp.android.teaching.com.misegundaapp;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView myTextView;

    public MyViewHolder(View itemView) {
        super(itemView);
        myTextView = itemView.findViewById(R.id.text_view_view_holder);
    }

    public void bind(String value) {
        myTextView.setText(value);
        myTextView.setBackgroundColor(Color.parseColor(value));
    }
}
