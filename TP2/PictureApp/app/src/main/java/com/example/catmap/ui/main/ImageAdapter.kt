package com.example.catmap.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.catmap.R
import com.example.catmap.databinding.ItemImageBinding
import com.example.catmap.model.ImageItem

/**
 * RecyclerView adapter for the image grid on the main screen.
 *
 * Uses [ListAdapter] with [DiffUtil] for efficient, animated list updates
 * (supports the Refresh feature — Step 8 — without full redraws).
 *
 * Features supported:
 *  - Display images in a grid (feature 2).
 *  - Navigate to detail screen on tap (feature 5).
 *  - Search/filter by breed name (feature 7).
 *
 * @param onItemClick Called with the tapped [ImageItem] when a grid cell is pressed.
 */
class ImageAdapter(
    private val onItemClick: (ImageItem) -> Unit,
    private val onFavoriteClick: (ImageItem) -> Unit
) : ListAdapter<ImageItem, ImageAdapter.ImageViewHolder>(DiffCallback) {

    /** Full unfiltered list – kept so [filter] can restore items after clearing the query. */
    private var fullList: List<ImageItem> = emptyList()

    /** Set of image IDs currently marked as favorites. */
    private var favoriteIds: Set<String> = emptySet()

    // ── ListAdapter overrides ────────────────────────────────────────────────

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position), favoriteIds.contains(getItem(position).id))
    }

    // ── Public API ───────────────────────────────────────────────────────────

    /**
     * Updates the set of favorite image IDs and refreshes the displayed heart icons.
     *
     * @param ids New set of favorited image IDs.
     */
    fun setFavorites(ids: Set<String>) {
        favoriteIds = ids
        // Full refresh to update heart icons across all visible items
        notifyDataSetChanged()
    }

    /**
     * Replaces the dataset and resets any active filter.
     * Always call this (not [submitList] directly) so [fullList] stays in sync.
     *
     * @param items New list of images from the repository.
     */
    fun setImages(items: List<ImageItem>) {
        fullList = items
        submitList(items)
    }

    /**
     * Filters the currently displayed list by breed name (case-insensitive).
     * Passing an empty or blank [query] restores the full list.
     *
     * Supports feature 7: Search for images on the screen by breed.
     *
     * @param query Breed name search term.
     */
    fun filter(query: String) {
        val filtered = if (query.isBlank()) {
            fullList
        } else {
            fullList.filter { item ->
                item.breed?.name?.contains(query, ignoreCase = true) == true
            }
        }
        submitList(filtered)
    }

    // ── ViewHolder ───────────────────────────────────────────────────────────

    /**
     * Holds a reference to the inflated [ItemImageBinding] and binds an [ImageItem] to it.
     */
    inner class ImageViewHolder(
        private val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }

            binding.buttonFavorite.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onFavoriteClick(getItem(position))
                }
            }
        }

        /**
         * Loads the image URL into [ItemImageBinding.imageViewItem] using Glide.
         * Shows a colour placeholder while loading and cross-fades when complete.
         * Sets the heart icon based on [isFavorite].
         */
        fun bind(item: ImageItem, isFavorite: Boolean) {
            Glide.with(binding.imageViewItem.context)
                .load(item.url)
                .placeholder(R.color.card_stroke)   // subtle grey while loading
                .error(android.R.drawable.ic_menu_gallery)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.imageViewItem)

            // Update favorite icon state
            if (isFavorite) {
                binding.buttonFavorite.setImageResource(R.drawable.ic_favorite)
                binding.buttonFavorite.drawable.setTint(
                    binding.root.context.getColor(R.color.heart_red)
                )
            } else {
                binding.buttonFavorite.setImageResource(R.drawable.ic_favorite_border)
                binding.buttonFavorite.drawable.setTint(
                    binding.root.context.getColor(R.color.white)
                )
            }
        }
    }

    // ── DiffUtil callback ────────────────────────────────────────────────────

    companion object DiffCallback : DiffUtil.ItemCallback<ImageItem>() {

        /** Two items represent the same image if they share the same [ImageItem.id]. */
        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
            oldItem.id == newItem.id

        /** Items with the same id are considered unchanged if their URL hasn't changed. */
        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean =
            oldItem.url == newItem.url && oldItem.breed?.name == newItem.breed?.name
    }
}
