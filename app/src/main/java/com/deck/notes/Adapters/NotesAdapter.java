package com.deck.notes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deck.notes.Model.Notes.NotesView;
import com.deck.notes.Model.Notes.NotesViewHolder;
import com.deck.notes.R;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    private View.OnClickListener onClick;
    private Context context;
    private ArrayList<NotesView> notes;

    public NotesAdapter(ArrayList<NotesView> notes, Context context, View.OnClickListener onClick) {
        this.notes = notes;
        this.context = context;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.adapter_notes, parent, false);

        view.setOnClickListener(this.onClick);

        return new NotesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        NotesView notes = this.notes.get(position);

        holder.getTitle().setText(notes.getTitle());
        holder.getMessage().setText(notes.getMessage());
        holder.getDate().setText(notes.getDate());

    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

}
