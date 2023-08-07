package com.example.dictionaryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<Word> words = new ArrayList<>();
    ArrayList<Word> wordsFull;
    public RecyclerViewAdapter(Context context, ArrayList<Word> words, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.words = words;
        this.recyclerViewInterface = recyclerViewInterface;
        this.wordsFull = new ArrayList<>(words);
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item_row,parent,false);
        return new RecyclerViewAdapter.MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.word_original.setText(words.get(position).getWord_original());
        holder.word_translation.setText(words.get(position).getWord_translate());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    @Override
    public Filter getFilter() {
        return wordsFilter;
    }
    private Filter wordsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Word> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                filteredList.addAll(wordsFull);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Word item : wordsFull){
                    if(item.getWord_original().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            words.clear();
            words.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView word_original;
        private TextView word_translation;

        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            word_original = itemView.findViewById(R.id.word_original);
            word_translation = itemView.findViewById(R.id.word_translation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if(recyclerViewInterface != null){
                       int pos = getAdapterPosition();
                       if(pos != RecyclerView.NO_POSITION){
                           recyclerViewInterface.onItemClick(pos);
                       }
                   }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemLongClick(pos);
                        }
                    }
                    return true;
                }
            });
        }
    }
}
