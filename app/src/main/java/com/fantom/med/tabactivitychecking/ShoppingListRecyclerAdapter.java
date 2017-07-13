package com.fantom.med.tabactivitychecking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 7/13/2017.
 */

public class ShoppingListRecyclerAdapter extends RecyclerView.Adapter<ShoppingListRecyclerAdapter.MyView> {

    private ArrayList<ShoppingRequireData> shoppingListDetails;
    private Context mContext;

    public ShoppingListRecyclerAdapter(Context mContext,ArrayList<ShoppingRequireData> shoppingListDetails) {
        this.shoppingListDetails = shoppingListDetails;
        this.mContext = mContext;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout,parent,false);

        return new MyView(view,mContext,shoppingListDetails);
    }

    @Override
    public void onBindViewHolder(MyView holder, int position) {

        ShoppingRequireData shoppingData = shoppingListDetails.get(position);
        holder.mProductNameTv.setText(shoppingData.getProductName());
        holder.mProductQuantityTv.setText(shoppingData.getProductQuintity());
        holder.mProductPricePerKgTv.setText(shoppingData.getProductPricePerKg());
        holder.mTotalPriceTv.setText(shoppingData.getProductTotalPrice());

    }

    @Override
    public int getItemCount() {
        return shoppingListDetails.size();
    }

    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mProductIdTv;
        private TextView mProductNameTv;
        private TextView mProductQuantityTv;
        private TextView mProductPricePerKgTv;
        private TextView mTotalPriceTv;
        private Context mContext;
        private ArrayList<ShoppingRequireData> shopDataArrayList;

        public MyView(View itemView,Context mContext,ArrayList<ShoppingRequireData> shopDataArrayList) {
            super(itemView);

            this.mContext = mContext;
            this.shopDataArrayList = shopDataArrayList;

            itemView.setOnClickListener(this);

            mProductNameTv = (TextView) itemView.findViewById(R.id.productNameTv);
            mProductQuantityTv = (TextView) itemView.findViewById(R.id.productQuintityTv);
            mProductPricePerKgTv = (TextView) itemView.findViewById(R.id.pricePerKgTv);
            mTotalPriceTv = (TextView) itemView.findViewById(R.id.totalPriceTv);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ShoppingRequireData shoppingRequireData = shopDataArrayList.get(position);
            Intent intent = new Intent(this.mContext, AddShoppingList.class);
            int id = shoppingRequireData.getProductId();
            intent.putExtra("productId",id);
            this.mContext.startActivity(intent);
            ((Activity)mContext).finish();

        }
    }
}