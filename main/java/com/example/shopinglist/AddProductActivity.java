package com.example.shopinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {

   // private SQLiteDatabase database;
   SharedPreferences sharedPref;
    private ProductAdapter pAdapter;
    private EditText editTextName;
    private EditText editTextAmmount;
    private EditText editTextPrice;
    FirebaseDatabase fdb = FirebaseDatabase.getInstance();
    DatabaseReference dr;
    List<Product> productList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

       // DatabaseHelper dbhelper = new DatabaseHelper(this);
      //  database = dbhelper.getWritableDatabase();

/////////////
        productList = new ArrayList<>();
        dr = FirebaseDatabase.getInstance().getReference("products");


        sharedPref = getSharedPreferences("options", Activity.MODE_PRIVATE);

        RecyclerView rView = findViewById(R.id.recyclerViewTasks);
        rView.setLayoutManager(new LinearLayoutManager(this));

        pAdapter = new ProductAdapter(this, getAllItemToList(),sharedPref.getBoolean("bigFont",true),sharedPref.getBoolean("color",true));
        rView.setAdapter(pAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;

            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((String) viewHolder.itemView.getTag());

            }
        }).attachToRecyclerView(rView);


        editTextName = findViewById(R.id.edit_text_Product);
        editTextAmmount = findViewById(R.id.edit_text_HowMany);
        editTextPrice = findViewById(R.id.edit_text_Price);

        Button buttonAdd = findViewById(R.id.saveButton);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
       pAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
           @Override
           public void onCheckbocClic(Product produckt) {
               updateData(produckt);

           }
       });
    }

    private void addItem()
    {
        String name = editTextName.getText().toString();
        int ammount = Integer.parseInt(editTextAmmount.getText().toString());
        double price = Double.parseDouble(editTextPrice.getText().toString());
        boolean isBought = false;

        String key = dr.push().getKey();
        dr.child(key).setValue(new Product(key, name ,price,ammount, isBought));


        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.Collumn2, name);
        cv.put(DatabaseHelper.Collumn3, ammount);
        cv.put(DatabaseHelper.Collumn4, price);

       // database.insert(DatabaseHelper.TABLE_NAME,null, cv);


        Intent intent = new Intent("com.example.EXAMPLE_ACTION");
        intent.putExtra("com.example.EXTRA_TEXT", name + " " + ammount + " " + price );
        sendBroadcast(intent);
        Toast.makeText(this, "Dodano produkt", Toast.LENGTH_SHORT).show();

        pAdapter.swapCursor(getAllItemToList());

        editTextName.getText().clear();
        editTextAmmount.getText().clear();
        editTextPrice.getText().clear();
    }
    private void removeItem(String id)
    {
        //database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.Collumn1 + "=" + id, null);
        dr.child(id).removeValue();
        pAdapter.swapCursor(getAllItemToList());
        Toast.makeText(this, "Usunięto produkt", Toast.LENGTH_SHORT).show();
    }

//    private Cursor getAllItem()
//    {
//       return database.query(DatabaseHelper.TABLE_NAME, null, null,null,null,null, DatabaseHelper.Collumn1 + " Desc");
//
//    }

    private List<Product> getAllItemToList()
    {
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                productList.clear();

                for(DataSnapshot dbsnap: dataSnapshot.getChildren())
                {
                    Product product = dbsnap.getValue(Product.class);
                    productList.add(product);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

                return productList;
    }


    public void updateData(Product produckt)
    {
//        ContentValues cv = new ContentValues();
//        cv.put(DatabaseHelper.Collumn2, produckt.getName());
//        cv.put(DatabaseHelper.Collumn3, produckt.getCount());
//        cv.put(DatabaseHelper.Collumn4, produckt.getPrice());
//        cv.put(DatabaseHelper.Collumn5, produckt.getBought());
//        long result = database.update(DatabaseHelper.TABLE_NAME, cv, DatabaseHelper.Collumn1 + " =" +
//                produckt.getId() , null);
        dr.child(produckt.getSId()).setValue(produckt);
        Toast.makeText(this, "Zmieniono", Toast.LENGTH_SHORT).show();

    }
}
