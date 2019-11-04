package com.example.student.btandroid_dialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtid, txtname, txtaddress, txtemail;
    Button btnSelect, btnSave, btnUpdate, btnDelete, btnExit;
    GridView gv;
    Dialog dialog;
    DBHelper dbHelper;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TacGia tg = new TacGia();
                tg.setId(txtid.getText().toString());
                tg.setName(txtname.getText().toString());
                tg.setAddress(txtaddress.getText().toString());
                tg.setEmal(txtemail.getText().toString());
                if (dbHelper.insertTacGia(tg)) {
                    clear();
                } else {
                    Toast.makeText(getApplicationContext(), "Luu khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();
                ArrayList<TacGia> booklist = new ArrayList<>();
                String id = txtid.getText().toString();
                if (!id.isEmpty()) {
                    int idkq = Integer.parseInt(id);
                    TacGia tg = dbHelper.getTacGia(idkq);
                    list.add(tg.getId() + "");
                    list.add(tg.getName());
                    list.add(tg.getAddress());
                    list.add(tg.getEmal());
                } else {
                    booklist = dbHelper.getAllTacGia();
                    for (TacGia b : booklist) {
                        list.add(b.getId() + "");
                        list.add(b.getName());
                        list.add(b.getAddress());
                        list.add(b.getEmal());
                    }
                }
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                gv.setAdapter(adapter);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtid.getText().toString();
                if (!id.isEmpty()) {
                    int idkq = Integer.parseInt(id);
                    dbHelper.deleteTacGia(idkq);
                    adapter.notifyDataSetChanged();
                    clear();
                    Toast.makeText(getApplicationContext(), "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Xoa khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = txtid.getText().toString();
                String name = txtname.getText().toString();
                String address = txtaddress.getText().toString();
                String email = txtemail.getText().toString();
                if(!id.isEmpty()){
                    String idkq = id;
                    dbHelper.updateTacGia(idkq,name,address,email);
                    adapter.notifyDataSetChanged();
                    clear();
                    Toast.makeText(getApplicationContext(), "Update thanh cong", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Update khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clear() {
        txtid.setText("");
        txtname.setText("");
        txtaddress.setText("");
        txtemail.setText("");
        txtid.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tacgia, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_tg:
                dialog.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        dialog = new Dialog(this,android.R.style.Theme_Black);
        dialog.setTitle("Thông tin tác giả");
        dialog.setContentView(R.layout.dialog_tttg);

        txtid = dialog.findViewById(R.id.editText_ID);
        txtname = dialog.findViewById(R.id.editText_Name);
        txtaddress = dialog.findViewById(R.id.editText_Address);
        txtemail = dialog.findViewById(R.id.editText_Email);
        btnExit = dialog.findViewById(R.id.button_Exit);
        btnSelect = dialog.findViewById(R.id.button_Select);
        btnSave = dialog.findViewById(R.id.button_Save);
        btnUpdate = dialog.findViewById(R.id.button_Update);
        btnDelete = dialog.findViewById(R.id.button_Delete);
        gv = dialog.findViewById(R.id.grid);
        dbHelper = new DBHelper(this);
    }
}
