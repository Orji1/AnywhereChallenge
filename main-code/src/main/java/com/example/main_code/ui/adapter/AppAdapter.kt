package com.example.main_code.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main_code.databinding.CharItemBinding
import com.example.main_code.network.model.RelatedTopic

class AppAdapter(
    private val characters: MutableList<RelatedTopic> = mutableListOf(),
    private val itemClick: (RelatedTopic) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {

    fun updateChars(newChar: List<RelatedTopic>) {
        characters.clear()
        characters.addAll(newChar)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(
            CharItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(characters[position], itemClick)
}

class CharacterViewHolder(
    private val binding: CharItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(characterModel: RelatedTopic, clickItem: (RelatedTopic) -> Unit) {
        binding.charName.text = characterModel.text?.split("-")?.get(0)

        itemView.setOnClickListener {
            clickItem(characterModel)
        }
    }

}