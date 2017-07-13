package com.fantom.med.tabactivitychecking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddShoppingList  extends AppCompatActivity {

    private EditText mProductNmeEt;
    private EditText mProductQuantityEt;
    private EditText mProductPriceEt;
    private TextView mShowTotalPriceTv;
    private Button mSaveDataBtn;
    private Button mDeleteBtn;
    private Button mCalculateTotalPriceBtn;
    private Button mCancleBtn;
    private   ManagerForAddShoppingList managerForAddShoppingList;
    String productQuantity;
    String productPrice;
    int mProductId;
    private  ShoppingRequireData shoppingRequireData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopping_list);

        mProductNmeEt = (EditText) findViewById(R.id.productNameEt);
        mProductQuantityEt = (EditText) findViewById(R.id.productQuatityEt);
        mProductPriceEt = (EditText) findViewById(R.id.productPriceEt);
        mShowTotalPriceTv = (TextView) findViewById(R.id.totalPrieTv);

        mSaveDataBtn = (Button) findViewById(R.id.saveData);
        mDeleteBtn = (Button) findViewById(R.id.deleteDataBtn);
        mCancleBtn = (Button) findViewById(R.id.cancleBtn);

        mCalculateTotalPriceBtn = (Button) findViewById(R.id.calculateTotalPrieBtn);

        managerForAddShoppingList = new ManagerForAddShoppingList(AddShoppingList.this);



        mProductId = getIntent().getIntExtra("productId",0);
        if(mProductId>0){
            mSaveDataBtn.setText("Update");
            mDeleteBtn.setVisibility(View.VISIBLE);
            shoppingRequireData = managerForAddShoppingList.getShopList(mProductId);
            if(shoppingRequireData != null){
                mProductNmeEt.setText(shoppingRequireData.getProductName());
                mProductQuantityEt.setText(shoppingRequireData.getProductQuintity());
                mProductPriceEt.setText(shoppingRequireData.getProductPricePerKg());
            }
        }


        mCalculateTotalPriceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPrice = mProductPriceEt.getText().toString();
                productQuantity = mProductQuantityEt.getText().toString();
                if(mProductQuantityEt.length()<=0){
                    Toast toast = Toast.makeText(AddShoppingList.this, "Product Quantity is Empty", Toast.LENGTH_SHORT);
                    toast.setGravity(0,0,0);
                    toast.show();
                }
                else if(mProductPriceEt.length()<=0){
                    Toast toast = Toast.makeText(AddShoppingList.this, "Price per kgis Empty", Toast.LENGTH_SHORT);
                    toast.setGravity(0,0,0);
                    toast.show();
                }
                else{
                    Double price = Double.parseDouble(productPrice);
                    Double quantiy = Double.parseDouble(productQuantity);
                    Double total = price * quantiy;
                    mShowTotalPriceTv.setText(""+total);
                }
            }
        });

        mSaveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = mProductNmeEt.getText().toString();
                String productQuantity = mProductQuantityEt.getText().toString();
                String productPrice = mProductPriceEt.getText().toString();
                String totalPrice = mShowTotalPriceTv.getText().toString();

                ShoppingRequireData srd = new ShoppingRequireData(productName,productQuantity,productPrice,totalPrice);

                if(productName.isEmpty()){
                    Toast toast =  Toast.makeText(AddShoppingList.this, "Product Name Is Empty", Toast.LENGTH_SHORT);
                    toast.setGravity(0,0,30);
                    toast.show();
                }
                else if(productQuantity.isEmpty()){
                    Toast toast =   Toast.makeText(AddShoppingList.this, "Product Quantity is Empty", Toast.LENGTH_SHORT);
                    toast.setGravity(0,0,30);
                    toast.show();
                }
                else if(productPrice.isEmpty()){
                    Toast toast =  Toast.makeText(AddShoppingList.this, "Product Price is Empty", Toast.LENGTH_SHORT);
                    toast.setGravity(0,0,30);
                    toast.show();
                }
                else if(totalPrice.isEmpty()){
                    Toast toast =  Toast.makeText(AddShoppingList.this, "Calculate Total Price", Toast.LENGTH_SHORT);
                    toast.setGravity(0,0,30);
                    toast.show();
                }
                else{
                    if(mProductId>0){
                        updateClassNoteData(srd,mProductId);
                    }else{
                        insertShoppingData(srd);
                    }

                }
            }
        });

        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long result = managerForAddShoppingList.deleteShopinList(mProductId);
                if(result>0){
                    Intent intent = new Intent(AddShoppingList.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(AddShoppingList.this, "Failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateClassNoteData(ShoppingRequireData shoppingRequireData, int mProductId) {
        long result = managerForAddShoppingList.updateShopingList(shoppingRequireData,mProductId);
        if(result>0){
            Intent intent = new Intent(AddShoppingList.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(AddShoppingList.this, "Failed !", Toast.LENGTH_SHORT).show();
        }
    }
    private void insertShoppingData(ShoppingRequireData srd) {
        long result = managerForAddShoppingList.addShoppingList(srd);
        Intent intent = new Intent(AddShoppingList.this, MainActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(this, "Data Save Successfully", Toast.LENGTH_SHORT).show();
    }

}