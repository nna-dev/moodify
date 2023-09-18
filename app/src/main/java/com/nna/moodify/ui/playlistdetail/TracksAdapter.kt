package com.nna.moodify.ui.playlistdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nna.moodify.R
import com.nna.moodify.databinding.ItemTrackBinding

class TracksAdapter : ListAdapter<TrackViewModel, TracksAdapter.TrackViewHolder>(TrackDiff) {
    class TrackViewHolder(val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = DataBindingUtil.inflate<ItemTrackBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_track,
            parent,
            false
        )
        return TrackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.binding.track = getItem(position)
    }
}

object TrackDiff : DiffUtil.ItemCallback<TrackViewModel>() {
    override fun areItemsTheSame(oldItem: TrackViewModel, newItem: TrackViewModel): Boolean {
        return oldItem.trackId == newItem.trackId
    }

    override fun areContentsTheSame(oldItem: TrackViewModel, newItem: TrackViewModel): Boolean {
        return oldItem == newItem
    }
}