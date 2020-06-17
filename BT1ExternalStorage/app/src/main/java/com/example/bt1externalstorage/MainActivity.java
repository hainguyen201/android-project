package com.example.bt1externalstorage;

import androidx.annotation.NonNull;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView listView;
    List<String> items;
    List<String> paths;
    Context activityContext;
    ArrayAdapter adapter;
    final int CHANGE_NAME=1;
    final int REMOVEFILE=2;
    final int REQUEST_CODE = 1234;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
        imageView=findViewById(R.id.img_main);
        activityContext=this;
        listView = findViewById(R.id.list_item);
        items= new ArrayList<String>();
        paths= new ArrayList<String>();
        if(Build.VERSION.SDK_INT>=19){
            File files[]=getExternalFilesDirs(null);
            items.add(files[0].getName());
            paths.add(files[0].getAbsolutePath());
            for(File f: files){
                Log.v("TAG", f.getAbsolutePath());
            }
        }
         adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File f=new File(paths.get(position));
                //Nếu file được chọn là folder
                if(f.isDirectory()){
                    File files[]=f.listFiles();
                    items.clear();
                    paths.clear();
                    items.add("..");
                    paths.add(f.getParent());
                    for(File file: files){
                        items.add(file.getName());
                        paths.add(file.getAbsolutePath());
                    }
                    adapter.notifyDataSetChanged();

                    Toast.makeText(getApplicationContext(),"folder",Toast.LENGTH_SHORT).show();
                }
                //Nếu file được chọn là file dữ liệu
                else
                {
                    String extension = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
                    if(extension.equals(".txt"))
                    try{
                        BufferedReader reader= new BufferedReader(new FileReader(f));
                        String aDataRow = "";
                        String aBuffer = "";
                        while ((aDataRow = reader.readLine()) != null) {
                            aBuffer += aDataRow + "\n";
                        }
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("file", aBuffer);
                        startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[]
            permissions, int[] grantResults) {
        if (requestCode == 1234)
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
                Log.v("TAG", "Permission Denied");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Action");
        menu.add(0, CHANGE_NAME, 0,"Đổi tên" );
        menu.add(0, REMOVEFILE, 0, "Xóa");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case CHANGE_NAME:
                showChangeNameDialog(items.get(info.position), paths.get(info.position), info.position);
                break;

            case REMOVEFILE:
                removeFolder(paths.get(info.position));
                items.remove(info.position);
                paths.remove(info.position);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
    public void showChangeNameDialog(String oldName, final String path, final int position){
        final String _path= path;
        final Dialog changeNameDialog= new Dialog(activityContext);
        changeNameDialog.setTitle("Đổi tên");
        changeNameDialog.setContentView(R.layout.custom_dialog_rename);
        final EditText edtChange= changeNameDialog.findViewById(R.id.edt_rename);
        edtChange.setText(oldName);
        Button btnChanged=changeNameDialog.findViewById(R.id.btn_changed);
        btnChanged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File fOld=new File(_path);
                File fNew=new File(fOld.getParent(), "/"+edtChange.getText().toString()+"/");
                items.remove(position);
                paths.remove(position);
                items.add(fNew.getName());
                paths.add(fNew.getAbsolutePath());
                boolean success= fOld.renameTo(fNew);
                changeNameDialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });
        changeNameDialog.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeNameDialog.dismiss();
            }
        });
        changeNameDialog.show();

    }

    public void getListFile() {

    }
    public boolean removeFolder(String path){
        File f= new File(path);
        if(f.exists()){
            File[] files= f.listFiles();
            if(files==null){
                return true;
            }
            for(int i=0; i<files.length; i++){
                if(files[i].isDirectory()){
                    removeFolder(files[i].getName());

                }else {
                    files[i].delete();
                }
            }
        }
        return f.delete();
    }


}
