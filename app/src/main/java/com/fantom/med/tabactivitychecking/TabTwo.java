package com.fantom.med.tabactivitychecking;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class TabTwo extends Fragment {

    private RecyclerView mRecyclerView;
    ShoppingListRecyclerAdapter adapter ;
    private ManagerForAddShoppingList managerForAddShoppingList;
    private Button mAddShpListBtn;
    private Button mItemBtn;

    private ArrayList<Double> priceValue = new ArrayList<>();

    public TabTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_two, container, false);
        mAddShpListBtn = (Button) view.findViewById(R.id.btnActivityCall);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.shoppingListRecyclerView);
        mItemBtn = (Button) view.findViewById(R.id.itemBtn);


        managerForAddShoppingList = new ManagerForAddShoppingList(getActivity());

        mAddShpListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),AddShoppingList.class);
                startActivity(intent);
            }
        });

        ArrayList<ShoppingRequireData> shoppingRequireDatas = managerForAddShoppingList.getShopingList();
        adapter = new ShoppingListRecyclerAdapter(getContext(),shoppingRequireDatas);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        for( ShoppingRequireData a: shoppingRequireDatas ){
            String totoal = a.getProductTotalPrice();
            Double d = Double.parseDouble(totoal);
            priceValue.add(d);
        }
        Double j =0.0;
        int i=0;
        for(i =0;i<shoppingRequireDatas.size();i++) {
            j += priceValue.get(i);
        }
        getActivity().setTitle("Cost = "+j );
        mItemBtn.setText("Item : "+i);

        return view;

    }
}