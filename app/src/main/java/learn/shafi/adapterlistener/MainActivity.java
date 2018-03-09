package learn.shafi.adapterlistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import learn.shafi.adapterlistener.pojo.User;

public class MainActivity extends AppCompatActivity implements ContactListAdapter.CardViewLongClickListener{

    RecyclerView contactRV;
    Toolbar toolbar;
    ImageView deleteContactIV;
    Boolean islongCliked;
    ContactListAdapter contactListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.toolbar);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        contactRV = findViewById(R.id.contactRV);
        deleteContactIV = findViewById(R.id.deleteContactIV);


        // at first click notifying user to select an item to remove
        deleteContactIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Long click on a item to select", Toast.LENGTH_SHORT).show();
            }
        });

        contactRV.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // bulk insertion
        User user1 = new User("Shafi","0162971272");
        User user2 = new User("Tanveer","01741526392");
        User user3 = new User("Arup","01726356985");
        User user4 = new User("Emon","016236598563");
        User user5 = new User("Shahin","01818563265");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);

        // preparing adapter with bulk data
        contactListAdapter =  new ContactListAdapter(userList,this);
        contactRV.setAdapter(contactListAdapter);

    }

    @Override
    public void viewLongClicked(final int position, ImageView contactPhotoIV, final ArrayList<User> userList) {

        // when user long clicks on cardView, we are changing tag
        // so that during clicking delete icon it works
        deleteContactIV.setTag("delete_now");

        // give feel to user changing delete icon
        deleteContactIV.setImageResource(R.drawable.ic_action_delete_now);


        deleteContactIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // checking tag to ensure user has already long clicked on cardView
                if(deleteContactIV.getTag()== "delete_now"){

                        userList.remove(position);
                        Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                        contactListAdapter.notifyItemRemoved(position);
                        contactListAdapter.notifyItemRangeChanged(position,contactListAdapter.getItemCount());
                        deleteContactIV.setImageResource(R.drawable.ic_action_delete);

                        // changing tag so that if user clicks again on the delete icon,it does not work
                        deleteContactIV.setTag("select_now");

                }else
                    Toast.makeText(MainActivity.this, "Long click on a item to select", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
