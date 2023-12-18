package pt.iade.edjasilva.notes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;

import pt.iade.edjasilva.notes.R;
import pt.iade.edjasilva.notes.models.NoteItem;



    public class NoteItemAdapter extends RecyclerView.Adapter<NoteItemAdapter.ViewHolder> {
        private ArrayList<NoteItem> items;
        private LayoutInflater inflater;
        private ItemClickListener clickListener;

        public NoteItemAdapter(Context context, ArrayList<NoteItem> items) {
            inflater = LayoutInflater.from(context);
            this.items = items;
            clickListener = null;
        }

        public void setOnClickListener(ViewHolder.ItemClickListener listener) {
            clickListener = listener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.row_note_item, parent, false);
            return new ViewHolder(view);
        }




        /**
         * Binds the data from each item in the list to a row in the list.
         *
         * @param holder The ViewHolder which should be updated to represent the contents of the
         *        item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         */
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            NoteItem item = items.get(position);


            holder.titleLabel.setText(item.getTitle());
            holder.notesLabel.setText(item.getContent());
            holder.dateLabel.setText(item.getContent());


        }

        @Override
        public int getItemCount() {
            return items.size();
        }



        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView titleLabel;
            public TextView notesLabel;
            public TextView dateLabel;

            /**
             * Sets up the view that was inflated.
             *
             * @param itemView Layout view that was inflated.
             */
            public ViewHolder(View itemView) {
                super(itemView);

                // Get the components in the view.
                titleLabel = itemView.findViewById(R.id.titleLabel);
                notesLabel = itemView.findViewById(R.id.notesLabel);
                dateLabel = itemView.findViewById(R.id.dateLabel);

                // Set what happens when the view gets clicked.
                itemView.setOnClickListener(this);
            }

            /**
             * Handles the onclick event of the view.
             */
            @Override
            public void onClick(View view) {
                // Pass the event to our custom listener in the activity.
                if (clickListener != null) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            }

            public interface ItemClickListener {
                void onItemClick(View view, int position);
            }

        }
    }