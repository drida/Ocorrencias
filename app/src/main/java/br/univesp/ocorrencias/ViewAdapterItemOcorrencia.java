package br.univesp.ocorrencias;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.univesp.ocorrencias.PlaceholderItemOcorrencia.PlaceholderItem;
import br.univesp.ocorrencias.databinding.FragmentItemBinding;

import java.util.List;

public class ViewAdapterItemOcorrencia extends RecyclerView.Adapter<ViewAdapterItemOcorrencia.ViewHolderItem> {

    private static final String TAG = "tag";
    private final List<PlaceholderItem> mValues;

    public ViewAdapterItemOcorrencia(List<PlaceholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderItem(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolderItem holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mDetailsView.setText(mValues.get(position).details);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView mIdView;
        public final TextView mContentView;
        public final TextView mDetailsView;
        public PlaceholderItem mItem;

        public ViewHolderItem(FragmentItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
            mDetailsView = binding.details;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            Integer id = Integer.parseInt(mValues.get(getAdapterPosition()).id);
            StatusActivity.setIdOcorrencia(id);
            view.getContext().startActivity(new Intent(view.getContext(),StatusActivity.class));
        }
    }
}