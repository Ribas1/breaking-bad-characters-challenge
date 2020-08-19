package com.pedroribeiro.breakingbadcharacterschallenge.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedroribeiro.breakingbadcharacterschallenge.R
import com.pedroribeiro.breakingbadcharacterschallenge.models.CharacterUiModel
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(
    private val onClickListener: (CharacterUiModel) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {

    private var characters: MutableList<CharacterUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character, onClickListener)
    }

    fun updateData(characters: List<CharacterUiModel>) {
        this.characters = characters.toMutableList()
        notifyDataSetChanged()
    }

}

class CharacterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        character: CharacterUiModel,
        onClickListener: (CharacterUiModel) -> Unit
    ) {
        itemView.item_character_name.text = character.name
        itemView.item_character_occupation.text = character.occupation[0]
        Glide.with(view).load(character.img).into(itemView.item_character_avatar)
        itemView.setOnClickListener {
            onClickListener(character)
        }
    }

}
