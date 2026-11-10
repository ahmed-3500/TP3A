package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.model.Word
import java.util.LinkedList

class WordListAdapter(private val wordList: LinkedList<Word>) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    /**
     * Créer de nouvelles vues
     * (appelé par le gestionnaire de disposition "LayoutManager")
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        // créer une nouvelle vue
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.wordlist_item, parent, false)

        return WordViewHolder(adapterLayout, this)
    }

    /**
     * Remplacer le contenu d'une vue
     * (appelé par le gestionnaire de disposition "LayoutManager")
     */
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = wordList[position]
        holder.wordItemView.text = current.word
    }

    /**
     * Renvoyer la taille de votre ensemble de données
     * (appelé par le gestionnaire de disposition "LayoutManager")
     */
    override fun getItemCount(): Int = wordList.size

    inner class WordViewHolder(itemView: View, val adapter: WordListAdapter) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val wordItemView: TextView = itemView.findViewById(R.id.word_title)

        // AJOUT : Le bloc init connecte l'onClickListener à la vue courante
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            // Remplacement du TODO par le code demandé :

            // Obtenez la position de l'élément cliqué
            val position = layoutPosition

            // Utilisez cela pour accéder à l'élément affecté dans wordList.
            val element = wordList[position]

            // Modifiez le mot dans la liste wordList.
            element.word = "Clicked! ${element.word}"
            wordList[position] = element

            // Notifier l'adaptateur que les données ont changé afin qu'il
            // puisse mettre à jour le RecyclerView pour afficher les données
            adapter.notifyDataSetChanged()
        }
    }
}
