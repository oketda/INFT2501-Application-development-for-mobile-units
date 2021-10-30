package com.example.task3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import java.io.Serializable

class ShowPersonaliaActivity: Activity() {
    var personalias: MutableList<Personalia> = arrayListOf()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_personalia)

        val personaliasList = intent.getSerializableExtra("personalias") as MutableList<Personalia>
        personalias = personaliasList

        val mListView: ListView = findViewById(R.id.listView) as ListView

        val adapter = PersonaliaListAdapter(this, R.layout.adapter_view_layout, personalias)
        mListView.adapter = adapter

        mListView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent("task3.EditPersonaliaActivity")
            intent.putExtra("personalia", personalias.get(position) as Serializable)
            intent.putExtra("position", position.toString())
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val intent = Intent()
            if (data != null) {
                intent.putExtra("name", data.getStringExtra("name"))
                intent.putExtra("date", data.getStringExtra("date"))
                intent.putExtra("position", data.getStringExtra("position"))
            }
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}