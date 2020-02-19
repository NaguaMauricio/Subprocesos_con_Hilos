package xyz.net7.sampleroom.fragment;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.net7.sampleroom.R;
import xyz.net7.sampleroom.fragment.ListFragment.OnListFragmentInteractionListener;
import xyz.net7.sampleroom.model.DataItem;

import java.util.ArrayList;
import java.util.List;

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ViewHolder> {

    private List<DataItem> mDataItemList;
    private final OnListFragmentInteractionListener mListener;

    public ListRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    public void setListData(List<DataItem> dataItemList) {
        //configurar nueva lista
        if (mDataItemList == null) {
            mDataItemList = new ArrayList<>();
        }
        mDataItemList.clear();
        mDataItemList.addAll(dataItemList);
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mItem = mDataItemList.get(position);
        holder.mNumberView.setText(String.valueOf(position + 1));
        holder.mNameView.setText(mDataItemList.get(position).getName());
        holder.mSectionView.setText(mDataItemList.get(position).getSection());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notificar a la interfaz de devolución de llamada activa (la actividad, si el
                    // se adjunta un fragmento a uno) que se ha seleccionado un elemento.
                     mListener.onListClickItem(holder.mItem);
                }
            }
        });
        final long itemId = holder.mItem.getId();
        //elimina el elemento de la lista
        holder.mImageDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentDeleteItemById(itemId);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mDataItemList != null) {
            return mDataItemList.size();
        } else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mNumberView;
        private final TextView mNameView;
        private final TextView mSectionView;
        private final ImageView mImageDeleteView;

        private DataItem mItem;

        private ViewHolder(View view) {
            super(view);
            mView = view;
            mNumberView = (TextView) view.findViewById(R.id.item_number);
            mNameView = (TextView) view.findViewById(R.id.item_name);
            mSectionView = (TextView) view.findViewById(R.id.item_section);
            mImageDeleteView = (ImageView) view.findViewById(R.id.image_delete);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}
