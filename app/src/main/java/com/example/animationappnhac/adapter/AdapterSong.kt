package com.example.animationappnhac.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animationappnhac.R
import com.example.animationappnhac.databinding.SongListItemBinding
import com.example.animationappnhac.model.Song
import java.util.ArrayList

class AdapterSong : RecyclerView.Adapter<AdapterSong.SongViewHolder>() {

    private var  listSong: List<Song> = ArrayList()
    var onItemClick: ((Song) -> Unit)? = null

     fun setData (listSong: List<Song>) {
        this.listSong = listSong
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.song_list_item, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
       val vb = SongListItemBinding.bind(holder.itemView)
        val song = listSong[position]

        vb.titleName.text=song.name +"${position}"
        vb.imgPhoto.setImageResource(song.poster)

        //click
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(song)
        }

    }

    override fun getItemCount(): Int {
       return listSong.size
    }

    class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun  getData( position: Int): Song{
        return listSong.get(position)
    }

}