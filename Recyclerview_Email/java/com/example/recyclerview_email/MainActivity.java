package com.example.recyclerview_email;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.recyclerview_email.adapters.EmailItemAdapter;
import com.example.recyclerview_email.models.EmailItemModel;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {

    EditText edt;
    Button btnFavorite;
    CheckBox checkshow;
    RecyclerView recyclerView;
    List<EmailItemModel> items;
    EmailItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt=findViewById(R.id.edt);
        btnFavorite=findViewById(R.id.btn);
        checkshow=findViewById(R.id.checkshow);
        items = new ArrayList<>();
        Faker faker = new Faker();
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false, "#5E97F6"));
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#FF8867"));
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#9BCA64"));
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#94A5AD"));
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false, "#5E97F6"));
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#B1D482"));
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#FF8867"));
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#9BCA64"));
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#9BCA64"));
        items.add(new EmailItemModel(faker.name.firstName(),faker.lorem.paragraph(),"12:34PM",false,"#9BCA64"));

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final EmailItemAdapter adapter = new EmailItemAdapter(items);
        recyclerView.setAdapter(adapter);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s.toString());
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkshow.setChecked(!checkshow.isChecked());
                boolean check= checkshow.isChecked();
                if(check) adapter.getFilter2().filter("check");
                else  adapter.getFilter2().filter(null);

            }
        });
    }
}
