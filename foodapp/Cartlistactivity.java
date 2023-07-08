package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodapp.interface1.ChangeNumberItemsListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Cartlistactivity extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView recyclerViewlist;
private Managementcart managementcart;
TextView totalFeeText, taxText,itemtotaltxt,deliverytxt,emptytxt;
private double tax;
private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartlistactivity);

        managementcart=new Managementcart(context this)
        initView();
        initlist();
        CalculateCart();
        BottomNavigation();
    }
     private void BottomNavigation(){
         FloatingActionButton floatingActionButton=findViewById(R.id.cartbtn);
         LinearLayout homebtn=findViewById(R.id.homeBtn);

         FloatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(packagecontext: Cartlistactivity.this,Cartlistactivity.class) );
             }
         });
homebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(packageContext: Cartlistactivity.this,MainActivity.class));
    }
});

    }
}
      private void initView(){
        recyclerViewlist=findViewById(R.id.cartlist);
        totalFeeText=findViewById(R.id.totaltxt);
        taxText=findViewById(R.id.taxText);
        deliverytxt=findViewById(R.id.deliverytxt);
        itemtotaltxt=findViewById(R.id.itemtotaltxt);
        emptytxt=findViewById(R.id.emptytxt);
        scrollView=findViewById(R.id.scrollview1);
        recyclerViewlist=findViewById(R.id.cartlist);
      }

      private void initlist(){
          LinearLayoutManager linearLayoutManager=new LinearLayoutManager((context: this,LinearLayoutManager.VERTICAL,reserveLayout:false )
          recyclerViewlist.setLayoutManager(linearLayoutManager);
          adapter= new CartListAdapter(managementcart.getListCart(),managementcart.this, new ChangeNumberItemsListener(){
            @Override
          public Void changed() {
                CalculateCart();
                return null;
            }


          })

          recyclerViewlist.setAdapter(adapter);
          if(managementcart.getList().isEmpty()){
              emptytxt.setVisibility(View.Visible);
              scrollView.setVisibility(View.GONE);
          }else{
              emptytxt.setVisibility(View.GONE);
              scrollView.setVisibility(View.VISIBLE);
          }

      }

    public void CalculateCart() {
     double percentTax= 0.02;
    double delivery= 4;

    tax= Math.round((managementcart.getTotalfee()+percentTax)*100)/100;
    double total= Math.round((managementcart.getTotalfee()+tax+delivery)*100)/100;
        double total= Math.round((managementcart.getTotalfee())*100)/100;

        totalFeeText.setText(("$"+itemtotaltxt));
        taxText.setText("$"+tax);
        deliverytxt.setText("$"+delivery);
        totalFeeText.setText("$"+ total);


    }

