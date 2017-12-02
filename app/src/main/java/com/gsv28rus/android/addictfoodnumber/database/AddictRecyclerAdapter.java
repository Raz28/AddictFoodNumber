package com.gsv28rus.android.addictfoodnumber.database;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gsv28rus.android.addictfoodnumber.AddictDeclarationActivity;
import com.gsv28rus.android.addictfoodnumber.R;

public class AddictRecyclerAdapter extends CursorRecyclerAdapter {

    private final static String SELECT_ADDICT_POSITION = "position selected";

    public AddictRecyclerAdapter(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void onBindViewHolderCursor(final RecyclerView.ViewHolder holder, final Cursor cursor) {
        if (holder instanceof ViewHolderAddict) {
            final ViewHolderAddict viewHolderAddict = (ViewHolderAddict) holder;
            viewHolderAddict.mTextViewName.setText(cursor.getString(2));
            viewHolderAddict.mTextViewNumber.setText("E" + cursor.getString(1));
            int selectId = cursor.getInt(0);
            viewHolderAddict.setAddictHolderPosition(selectId);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addict, parent, false);
        return new ViewHolderAddict(view);
    }

    public class ViewHolderAddict extends RecyclerView.ViewHolder {

        TextView mTextViewNumber;
        TextView mTextViewName;
        private int addictHolderPosition;


        ViewHolderAddict(final View itemView) {
            super(itemView);
            mTextViewNumber = itemView.findViewById(R.id.textViewNumber);
            mTextViewName = itemView.findViewById(R.id.textViewName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), AddictDeclarationActivity.class);
                    intent.putExtra(SELECT_ADDICT_POSITION, addictHolderPosition);
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        void setAddictHolderPosition(int id) {
            addictHolderPosition = id;
        }

    }
}
