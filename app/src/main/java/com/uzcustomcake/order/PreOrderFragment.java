package com.uzcustomcake.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.uzcustomcake.MainActivity;
import com.uzcustomcake.OrderViewModel;
import com.uzcustomcake.R;
import com.uzcustomcake.core.domain.Filling;
import com.uzcustomcake.core.domain.Order;
import org.w3c.dom.Text;

import java.util.*;

/**
 * Created by horlock on 10/23/17.
 */

public class PreOrderFragment extends Fragment {

  Button order;
  RecyclerView items;
  ImageView close;
  OrderViewModel model;
  TextView totalPrice;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.pre_order_sheet, container, false);
    order = v.findViewById(R.id.order);
    items = v.findViewById(R.id.items);
    close = v.findViewById(R.id.close);
    totalPrice = v.findViewById(R.id.totalPrice);
    return v;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    model = ((MainActivity) getActivity()).getModel();

    order.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ((MainActivity) getActivity()).finilizeOrderProcess();
      }
    });

    items.setLayoutManager(new LinearLayoutManager(getContext()));
    populateList();
    close.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ((MainActivity) getActivity()).hideBottomSheet();
      }
    });

    totalPrice.setText(model.getTotalCount());
  }

  public void populateList(){
//    Map<String, Filling> map = model.getChosenFillings();
//    for(Iterator<Map.Entry<String, Filling>> it = map.entrySet().iterator(); it.hasNext(); ) {
//      Map.Entry<String, Filling> entry = it.next();
//      if(!entry.getValue().isSelected()) {
//        it.remove();
//      }
//    }
    items.setAdapter(new PreOrderAdapter(model.getFilteredList()));
  }

  static class PreOrderAdapter extends RecyclerView.Adapter<PreOrderAdapter.ViewHolder>{

    protected final List<PreOrderItem> items;

    public PreOrderAdapter(List<PreOrderItem> items) {
      this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
      return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pre_order_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
      holder.name.setText(items.get(i).name);
      holder.price.setText(items.get(i).price);
    }

    @Override
    public int getItemCount() {
      return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

      TextView name, price;

      public ViewHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);
        price = itemView.findViewById(R.id.price);
      }
    }
  }
}
