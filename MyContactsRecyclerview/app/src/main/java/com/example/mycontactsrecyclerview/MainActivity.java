package com.example.mycontactsrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<DataList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.Recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<DataList>();
        StringBuilder builder = new StringBuilder();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (cursor.getCount() > 0){
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String profileImage = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {
                    Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (cursor2.moveToNext()) {
                        String number = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if (profileImage == null){
                            profileImage = "basic";
                        }
                        arrayList.add(new DataList(name, number, profileImage));
                    }
                    cursor2.close();
                }
            }
        }
        cursor.close();
        DataAdapter dataAdapter = new DataAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(dataAdapter);
    }
}
