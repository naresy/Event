package com.team.eventbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>{



     static Context mCtx;
      static TextView event;
    boolean istextclicked=false;

    private List<post_view> productList;



    public ProductsAdapter(Context mCtx, List<post_view> productList) {
        this.mCtx = mCtx;
        this.productList = productList;


    }



    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.post_home_view, null);

        return new ProductViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        post_view product = productList.get(position);

        //loading the image


        holder.description.setText(product.getDescription());
        holder.title.setText(product.getTitile());
        holder.startdate.setText(product.getDatefor());
        holder.endDate.setText(product.getDateto());
        holder.Starttime.setText(product.getTimefor());
        holder.EndTime.setText(product.getTimeto());


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView description,title,startdate,endDate,Starttime,EndTime;



        public ProductViewHolder(final View itemView) {
            super(itemView);

            description=itemView.findViewById(R.id.post_view_description);
            title=itemView.findViewById(R.id.home_post_title);
            startdate=itemView.findViewById(R.id.homestart_date);
            endDate=itemView.findViewById(R.id.homestart_enddate);
            Starttime=itemView.findViewById(R.id.homestart_time);
            EndTime=itemView.findViewById(R.id.homeEnd_time);
            event=itemView.findViewById(R.id.Postview_with);
            description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(istextclicked)
                    {
                        description.setMaxLines(4);
                        istextclicked =false;
                    }
                    else {
                        description.setMaxLines(Integer.MAX_VALUE);
                        istextclicked=true;
                    }
                }
            });
            event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mCtx.getApplicationContext(),MapsActivity.class);
                    intent.putExtra("id",getItemId());
                    mCtx.startActivity(intent);

                }
            });


        }
    }




}