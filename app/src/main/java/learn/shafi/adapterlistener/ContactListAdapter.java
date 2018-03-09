package learn.shafi.adapterlistener;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import learn.shafi.adapterlistener.pojo.User;


public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {


    private ArrayList<User> userList;
    public CardViewLongClickListener cardViewLongClickListener;

    public ContactListAdapter(ArrayList<User> userList,CardViewLongClickListener cardViewLongClickListener){

        this.userList =userList;
        this.cardViewLongClickListener =cardViewLongClickListener;
    }

    @Override
    public ContactListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View contactView = layoutInflater.inflate(R.layout.contact_list_row,null);
        return new ContactListAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(final ContactListAdapter.ViewHolder holder, final int position) {

        final User user = userList.get(position);

        holder.contactNameTV.setText(user.getUserName());
        holder.contactNumberTV.setText(user.getPhoneNumber());

        holder.contactCV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                cardViewLongClickListener.viewLongClicked(position, holder.contactPhotoIV,userList);


                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

     public class ViewHolder extends RecyclerView.ViewHolder{

        TextView  contactNameTV;
        TextView  contactNumberTV;
        ImageView callPhotoIV;
        ImageView contactPhotoIV;
        CardView contactCV;

      public ViewHolder(View itemView) {
            super(itemView);
            contactNameTV = itemView.findViewById(R.id.contactNameTV);
            contactNumberTV = itemView.findViewById(R.id.contactNumberTV);
            callPhotoIV = itemView.findViewById(R.id.callPhotoIV);
            contactCV = itemView.findViewById(R.id.contactCV);
            contactPhotoIV = itemView.findViewById(R.id.contactPhotoIV);
        }


    }

    public interface CardViewLongClickListener{

        void viewLongClicked(int position, ImageView textView, ArrayList<User> userList);
    }
}
