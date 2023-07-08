package com.example.foodapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.interface1.ChangeNumberItemsListener;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagerCart managerCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<FoodDomain> foodDomains, Context context,  ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodDomains = foodDomains;
        this.managerCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType){
        View inflate LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,attachtoroot:false))
        return new ViewHolder(inflate) ;
    }

    @Override
    public void onBinderViewHolder(@NonNull @org.jetbrains.annotations.NotNull CartListAdapter.ViewHolder holder,int position){
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totialEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart()*foodDomains.get(position).getfee())*getFee())*100 )/100));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),
                defType:"drawable",holder.itemView.getContext().getPackageName();
         Glide.with(holder.itemView.getContext())
                 .load(drawableResourceId)
                 .into(holder.pic);

         holder.plusitem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 managerCart.plusNumberFood(foodDomains, position, new ChangeNumberItemsListener() {
                     @Override
                     public void changed() {
                         notifyDataSetChanged();
                         changeNumberItemsListener.changed();
                     }
                 });
                 holder.minusitem.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                      managerCart.minusNumberFood(foodDomains, position, new ChangeNumberItemsListener(){
                          @Override
                         public void changed() {
                          notifyDataSetChanged();
                          changeNumberItemsListener.changed();
                          }
                              }
                      )
                     }
                 });

             }
         });
    }


    @Override
    public int getItemCount(){
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView title, feeEachItem;
       ImageView pic,plusitem,minusitem;
       TextView totialEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titletext);
            feeEachItem=itemView.findViewById(R.id.feeEachitem);
            pic=itemView.findViewById(R.id.picCart);
            totialEachItem=itemView.findViewById(R.id.totalEachitem);
            num=itemView.findViewById(R.id.numberItemtxt);
            plusitem=itemView.findViewById(R.id.pluscartbtn);
            minusitem=itemView.findViewById(R.id.minuscartbtn);
        }
    }
}
