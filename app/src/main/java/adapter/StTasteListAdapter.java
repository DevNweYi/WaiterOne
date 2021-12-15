package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bosictsolution.waiterone.R;

import java.util.List;

import data.TasteData;
import listener.SetupEditDeleteButtonClickListener;

/**
 * Created by NweYiAung on 01-03-2017.
 */
public class StTasteListAdapter extends BaseAdapter {

    private Context context;
    SetupEditDeleteButtonClickListener setupEditDeleteButtonClickListener;
    List<TasteData> lstTasteData;

    public StTasteListAdapter(Context context, List<TasteData> lstTasteData){
        this.context=context;
        this.lstTasteData =lstTasteData;
    }

    public void setOnSetupEditDeleteButtonClickListener(SetupEditDeleteButtonClickListener setupEditDeleteButtonClickListener){
        this.setupEditDeleteButtonClickListener =setupEditDeleteButtonClickListener;
    }

    @Override
    public int getCount(){
        return lstTasteData.size();
    }

    @Override
    public String getItem(int position){
        return lstTasteData.get(position).getTasteName();
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    static class ViewHolder {
        TextView tvTasteName;
        ImageButton btnEdit,btnDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View row;
        final ViewHolder holder;
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_st_waiter_tabletype_taste_stype, parent,false);
            holder=new ViewHolder();
            holder.tvTasteName=(TextView) row.findViewById(R.id.tvName);
            holder.btnEdit=(ImageButton) row.findViewById(R.id.btnEdit);
            holder.btnDelete=(ImageButton) row.findViewById(R.id.btnDelete);

            row.setTag(holder);
        }
        else{
            row=convertView;
            holder=(ViewHolder) row.getTag();
        }

        holder.tvTasteName.setText(lstTasteData.get(position).getTasteName());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(setupEditDeleteButtonClickListener !=null){
                    setupEditDeleteButtonClickListener.onEditButtonClickListener(position);
                }
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(setupEditDeleteButtonClickListener !=null){
                    setupEditDeleteButtonClickListener.onDeleteButtonClickListener(position);
                }
            }
        });

        return row;
    }
}
