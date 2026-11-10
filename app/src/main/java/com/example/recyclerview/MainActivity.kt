package com.example.recyclerview

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.Menu
import android.view.MenuItem
import com.example.recyclerview.adapter.WordListAdapter
import com.example.recyclerview.data.DataSource
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.Word // AJOUT : Importation du modèle Word

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WordListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datasource = DataSource()
        val wordList = datasource.loadWords()

        // Créer une instance de l'adaptateur en passant la liste des mots comme paramètre
        adapter = WordListAdapter(wordList)
        // Lier RecyclerView à son adaptateur
        binding.contentMain.recyclerview.adapter = adapter

        // Définir setHasFixedSize sur true pour améliorer les performances
        binding.contentMain.recyclerview.setHasFixedSize(true)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)

        // MODIFICATION : Remplacement du Snackbar par l'ajout dynamique d'un élément
        binding.fab.setOnClickListener {
            val wordListSize = wordList.size

            // Ajoutez un nouveau mot à la liste de mots.
            wordList.addLast(Word("+ Word $wordListSize"))

            // Notifiez l'adaptateur qu'une nouvelle donnée a été insérée.
            binding.contentMain.recyclerview.adapter?.notifyItemInserted(wordListSize)

            // Faites défiler jusqu'en bas
            binding.contentMain.recyclerview.smoothScrollToPosition(wordListSize)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
