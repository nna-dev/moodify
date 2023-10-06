/*
 * Designed and developed by 2023 nna-dev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nna.moodify.ui.playlistdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nna.moodify.R
import com.nna.moodify.databinding.ItemTrackBinding

class TracksAdapter(
    private val onItemClick: (TrackViewModel) -> Unit
) : ListAdapter<TrackViewModel, TracksAdapter.TrackViewHolder>(TrackDiff) {
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
        holder.itemView.setOnClickListener { onItemClick(getItem(position)) }
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
