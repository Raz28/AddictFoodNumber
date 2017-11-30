package com.gsv28rus.android.addictfoodnumber.database;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gsv28rus.android.addictfoodnumber.AddictDeclarationActivity;
import com.gsv28rus.android.addictfoodnumber.CursorRecyclerAdapter;
import com.gsv28rus.android.addictfoodnumber.ListAddictFragment;
import com.gsv28rus.android.addictfoodnumber.R;



public class AddictRecyclerAdapter extends CursorRecyclerAdapter {

    public AddictRecyclerAdapter(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void onBindViewHolderCursor(final RecyclerView.ViewHolder holder, final Cursor cursor) {
        if (holder instanceof ViewHolderAddict) {
            ViewHolderAddict viewHolderAddict = (ViewHolderAddict) holder;
            viewHolderAddict.mTextViewName.setText(cursor.getString(2));
            viewHolderAddict.mTextViewNumber.setText(cursor.getString(1));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(holder.itemView.getContext(),cursor.getString(1), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(holder.itemView.getContext(), AddictDeclarationActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addict, parent, false);
        ViewHolderAddict viewHolderAddict = new ViewHolderAddict(view);

        return viewHolderAddict;
    }

    public class ViewHolderAddict extends RecyclerView.ViewHolder {

        TextView mTextViewNumber;
        TextView mTextViewName;

        public ViewHolderAddict(View itemView) {
            super(itemView);
            mTextViewNumber = itemView.findViewById(R.id.textViewName);
            mTextViewName = itemView.findViewById(R.id.textViewDeclaratiom);
        }


    }
}
