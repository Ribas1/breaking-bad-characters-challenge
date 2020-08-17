package com.pedroribeiro.breakingbadcharacterschallenge.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedroribeiro.breakingbadcharacterschallenge.R
import com.pedroribeiro.breakingbadcharacterschallenge.network.models.BreakingBadCharacter
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {

    private var characters: MutableList<BreakingBadCharacter> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)
    }

    fun updateData(characters: List<BreakingBadCharacter>) {
        this.characters.addAll(characters.toMutableList())
        notifyDataSetChanged()
    }

}

class CharacterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(character: BreakingBadCharacter) {
        itemView.item_character_name.text = character.name
        itemView.item_character_occupation.text = character.occupation[0]
        Glide.with(view).load(character.img).into(itemView.item_character_avatar)
    }

}
