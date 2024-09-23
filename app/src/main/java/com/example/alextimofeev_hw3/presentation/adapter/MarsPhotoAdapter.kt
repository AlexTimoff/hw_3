package com.example.alextimofeev_hw3.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.alextimofeev_hw3.databinding.MarsPhotoItemBinding

import com.example.alextimofeev_hw3.entity.PhotoItem

//Вспомогательный класс, который создает view  и заполняет данными
class MarsPhotoAdapter(
    private val onClick: (PhotoItem) -> Unit
): RecyclerView.Adapter<MarsPhotoViewHolder>() {

    private var data : List<PhotoItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoViewHolder {
        val binding = MarsPhotoItemBinding.inflate(LayoutInflater.from(parent.context))
        return MarsPhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarsPhotoViewHolder, position: Int) {
        val photo = data.getOrNull(position)
        with(holder.binding) {
            photo?.let {
                Glide.with(imagePhoto.context).load(it.url).into(imagePhoto)
            }
        }


        holder.binding.root.setOnClickListener {
           photo?.let(onClick)
        }
    }


    override fun getItemCount(): Int = data.size

    //Изменяет набор данных. обновит данные в адаптере. Это тяжеловесная операция.
    fun setData(data: List<PhotoItem>){
        this.data = data
        notifyDataSetChanged()
    }
}