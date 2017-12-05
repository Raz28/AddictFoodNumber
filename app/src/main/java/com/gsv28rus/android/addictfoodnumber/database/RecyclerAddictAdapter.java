package com.gsv28rus.android.addictfoodnumber.database;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gsv28rus.android.addictfoodnumber.DeclarationAddictActivity;
import com.gsv28rus.android.addictfoodnumber.R;

public class RecyclerAddictAdapter extends CursorRecyclerAdapter {

    private final static String SELECT_ADDICT_POSITION = "position selected";

    public RecyclerAddictAdapter(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void onBindViewHolderCursor(final RecyclerView.ViewHolder holder, final Cursor cursor) {
        final ViewHolderAddict viewHolderAddict = (ViewHolderAddict) holder;
        viewHolderAddict.mTextViewName.setText(cursor.getString(cursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.NAME)));
        viewHolderAddict.mTextViewNumber.setText(cursor.getString(cursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.NUMBER)));
        int selectId = cursor.getInt(cursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.ID));
        int danger = cursor.getInt(cursor.getColumnIndex(SafeFoodDbSchema.NumbersTable.Cols.DANGER));

        switch (danger) {
            case 1:
                viewHolderAddict.mTextViewNumber.setBackgroundResource(R.drawable.rectangle_rounded_green);
                break;
            case 2:
                viewHolderAddict.mTextViewNumber.setBackgroundResource(R.drawable.rectangle_rounded_yelllow);
                break;
            case 3:
                viewHolderAddict.mTextViewNumber.setBackgroundResource(R.drawable.rectangle_rounded_orange);
                break;
            case 4:
                viewHolderAddict.mTextViewNumber.setBackgroundResource(R.drawable.rectangle_rounded_red);
                break;
        }
        viewHolderAddict.setBinder(selectId);
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
                    Intent intent = new Intent(itemView.getContext(), DeclarationAddictActivity.class);
                    intent.putExtra(SELECT_ADDICT_POSITION, addictHolderPosition);
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        void setBinder(int id) {
            addictHolderPosition = id;
        }

    }
}
