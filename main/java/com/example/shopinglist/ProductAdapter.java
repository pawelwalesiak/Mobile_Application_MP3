package com.example.shopinglist;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter <ProductAdapter.ProducktViewHolder> {

    public OnItemClickListener mListener;

    public interface OnItemClickListener
    {
       void onCheckbocClic(Product produckt);
    }
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    Context aContext;
    //Cursor aCursor;
    private boolean color;
    private boolean size;
    List<Product> productList;


    public ProductAdapter(Context context, List<Product> list, boolean size, boolean color)
    {
        aContext = context;
        productList = list;
        this.size = size;
        this.color = color;
    }

    public class ProducktViewHolder extends RecyclerView.ViewHolder{
        TextView textName;
        TextView textAmmount;
        TextView textPrice;
        TextView id;
        CheckBox check;

        public ProducktViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textName = itemView.findViewById(R.id.textview_name_item);
            textAmmount = itemView.findViewById(R.id.textview_amount_item);
            textPrice = itemView.findViewById(R.id.textview_price_item);
            check = itemView.findViewById(R.id.checkbox_item);
            id = itemView.findViewById(R.id.id);

            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      listener.onCheckbocClic(new Product(id.getText().toString(),
                              textName.getText().toString() ,Double.parseDouble(textPrice.getText().toString()),Integer.parseInt(textAmmount.getText().toString()), isChecked));
//                        if (isChecked) {
////                        Toast.makeText(ProductAdapter.this.aContext,
////                                textName.getText()+ " został kupiony!",
////                                Toast.LENGTH_LONG).show();
//                    }
                }
            });

        }
    }


    @NonNull
    @Override
    public ProducktViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lf = LayoutInflater.from(aContext);
        View view = lf.inflate(R.layout.product_item, parent, false);
        return new ProducktViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProducktViewHolder holder, final int position) {
//        if(!aCursor.moveToPosition(position))
//        {
//            return;
//        }
//        final String name = aCursor.getString(aCursor.getColumnIndex(DatabaseHelper.Collumn2));
//        final int ammount = aCursor.getInt(aCursor.getColumnIndex(DatabaseHelper.Collumn3));
//        final double price = aCursor.getDouble(aCursor.getColumnIndex(DatabaseHelper.Collumn4));
//        final long id =aCursor.getLong(aCursor.getColumnIndex(DatabaseHelper.Collumn1));
//        final boolean ischeck = aCursor.getInt(aCursor.getColumnIndex(DatabaseHelper.Collumn5))>0;


        final Product product = productList.get(position);
        holder.textName.setText(product.getName());
        holder.textAmmount.setText(String.valueOf(product.getCount()));
        holder.textPrice.setText(String.valueOf(product.getPrice()));
        holder.id.setText(String.valueOf(product.getSId()));
        holder.itemView.setTag(product.getSId());
        holder.check.setChecked(product.getBought());

        holder.textName.setTextSize(size ? 66 : 22);
        holder.textAmmount.setTextSize(size ? 66 : 22);
        holder.textPrice.setTextSize(size ? 66 : 22);
        holder.textName.setTextColor(color ? Color.RED : Color.BLACK);
        holder.textAmmount.setTextColor(color ? Color.RED : Color.BLACK);
        holder.textPrice.setTextColor(color ? Color.RED : Color.BLACK);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void swapCursor(List<Product> newCursor)
    {
        if(productList.size() > 0)
        {
            productList.clear();
        }
        productList = newCursor;
        if(productList.size() > 0)
        {
            notifyDataSetChanged();
        }
    }
}
