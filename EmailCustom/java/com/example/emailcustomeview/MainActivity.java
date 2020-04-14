package com.example.emailcustomeview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<ItemModel> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list_view);
        items= new ArrayList<>();
        Faker faker = new Faker();
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false, "#5E97F6"));
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#FF8867"));
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#9BCA64"));
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#94A5AD"));
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false, "#5E97F6"));
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#B1D482"));
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#FF8867"));
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#9BCA64"));
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#9BCA64"));
        items.add(new ItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#9BCA64"));
        EmailAdapter emailAdapter= new EmailAdapter(items);
        listView.setAdapter(emailAdapter);

    }
}
