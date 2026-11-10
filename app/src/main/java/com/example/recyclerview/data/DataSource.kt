package com.example.recyclerview.data

import com.example.recyclerview.model.Word
import java.util.LinkedList

class DataSource {
    fun loadWords(): LinkedList<Word> {
        // Mettre les données initiales dans la liste de mots.
        val wordList = LinkedList<Word>()
        for (i in 0 until 20) {
            wordList.addLast(Word("Word $i"))
        }
        return wordList
    }
}