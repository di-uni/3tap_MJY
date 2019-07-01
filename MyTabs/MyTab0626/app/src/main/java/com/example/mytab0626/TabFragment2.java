package com.example.mytab0626;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TabFragment2 extends Fragment implements FruitAdapter.FruitAdapterListener{

    RecyclerView recyclerView;
    List<Fruits> fruitsList;
    FruitAdapter adapter;

    //Array of fruits names
    String[] names={"Moroco", "Georgia","Denmark","Hungary","Russia","Cuba","Argentina","Greece","Australia","New Zealand","Iceland","Spain","Madagascar","Mexico","Korea","Mongolia","Egypt","Uzbekistan","Jordan","Brazil"};
    //Array of fruits desc
    String[] sntfNames={"Kasablanca", "Kokasus","Kopenhagen","Budapest","St.Petersburg","Havana","Aguazu","Santorini","Sydney","Auckland","Snaefellense","Granada","Antananarivio","Mexico City","Seoul","Ulaanbaatar","Alexandria","Samarkand","Petra","Rio de Janeiro"};

    //Array of fruits images
    int[] image ={R.drawable.moroco,R.drawable.georgia,R.drawable.denmark,R.drawable.hungary,R.drawable.bussia,R.drawable.cuba,R.drawable.argentina,R.drawable.greece,R.drawable.australia,R.drawable.newzealand,R.drawable.iceland,R.drawable.spain,R.drawable.madagascar,R.drawable.mexico,R.drawable.korea,R.drawable.mongolia,R.drawable.egypt,R.drawable.uzbekistan,R.drawable.jorda,R.drawable.brazil};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);

        //finding views
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        //item decorator to separate the items
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        //setting layout
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //initialize fruits list
        fruitsList = new ArrayList<>();



        adapter = new FruitAdapter(getActivity(),fruitsList,this);




        //method to load fruits
        loadfruits();
        //onItemClickListener
        recyclerView.addOnItemTouchListener(new FruitTouchListener(getActivity().getApplicationContext(), new FruitTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getContext(), ImageActivity.class);
                intent.putExtra("name",names[position]);
                intent.putExtra("image", image[position]);
                startActivity(intent);
            }
        }));

        return view;
    }



    private void loadfruits() {

        for(int i=0; i<names.length;i++) {

            fruitsList.add(new Fruits(names[i], sntfNames[i], image[i]));
        }
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }





    @Override
    public void onFruitSelected(Fruits fruits) {
        Toast.makeText(getContext(), "Selected: " + fruits.getName() , Toast.LENGTH_LONG).show();
    }

}