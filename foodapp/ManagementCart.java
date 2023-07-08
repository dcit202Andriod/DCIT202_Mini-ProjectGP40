package com.example.foodapp;

import android.content.Context;
import android.widget.Toast;

import com.example.foodapp.interface1.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context){
        this.context=context;
        this.tinyDB= new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getlistCart();
        boolean existAlready = false;
        int n=0;
        for(int i = 0; i<listFood.size(); i++){
            if(listFood.get(i).getTitle().equal(item.getTitle())){
                existAlready = true;
                n=i;
                break;
            }
        }
    }

    if(existAlready){
        listFood.get(n).setNumberInCart();
    }else {
        listFood.add(item);
    }
    tinyDB.piutListObject(key:"Cardlist",listFood);
    Toast.makeText(context, text:"Added To Your Cart", Toast.LENGTH_SHORT ).show();

}

public ArrayList<FoodDomain> getlistCart(){
    return tinyDB.getListObject(key "CartList");
}

public Void plusNumberFood(ArrayList<FoodDomain> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
    ListFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()+1);
    tinyDB.putListObject(key "Cartlist", listFood);
    changeNumberItemsListener.changed();
}
public void minusNumberFood(ArrayList<FoodDomain> listfood.int position , ChangeNumberItemsListener changeNumberItemsListener){
  if(listfood.get(position).getNumberInCart==1){
      listfood.remove((position));}
  else{listfood.get(position).setNumberInCart(position).getNumberInCart()-1;}
  tinyDB.putListObject(key "Carlist", listfood);
  changeNumberItemsListener.changed();
  }
public Double getTotalFee(){
    ArrayList<FoodDomain> listfood=getlistCart();
    double fee= 0;
    for (int i=0;i < listfood.size(); i++){
        fee=fee+(listfood.get(i).getFee()*listfood.get(i).getNumberInCart());
    }
    return fee;
}
        }
