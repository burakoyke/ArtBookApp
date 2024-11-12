package com.burakoyke.artbookapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.burakoyke.artbookapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var artList : ArrayList<Art>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        binding.toolbar.setTitle("Art Book")
        setSupportActionBar( binding.toolbar)
        artList = ArrayList<Art>()
        try {
            val database = this.openOrCreateDatabase("Arts", MODE_PRIVATE,null)
            val cursor = database.rawQuery("SELECT * FROM arts",null)
            val artNameIx = cursor.getColumnIndex("artname")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()) {
                val name = cursor.getString(artNameIx)
                val id = cursor.getInt(idIx)
                val art = Art(name,id)
                artList.add(art)
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }

        for (art in artList) {
            Log.d("burak", art.artname)
        }
        binding.rv.layoutManager = LinearLayoutManager(this)
        val adapter = ArtAdapter(artList)
        binding.rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.art_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_art_item) {
            intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        binding.rv.layoutManager = LinearLayoutManager(this)
        val adapter = ArtAdapter(artList)
        binding.rv.adapter = adapter
    }

}
