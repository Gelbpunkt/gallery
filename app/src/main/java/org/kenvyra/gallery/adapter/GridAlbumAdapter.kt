package org.kenvyra.gallery.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.MediaStoreSignature
import com.google.android.material.navigation.NavigationBarView
import org.kenvyra.gallery.Album
import org.kenvyra.gallery.GlideApp
import org.kenvyra.gallery.R
import org.kenvyra.gallery.databinding.AlbumHolderBinding
import org.kenvyra.gallery.ui.BottomNavFrag
import org.kenvyra.gallery.ui.MainActivity

class GridAlbumAdapter(private val frag: BottomNavFrag) : ListAdapter<Album,
        AlbumHolder>(Album.DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        return AlbumHolder(
            AlbumHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        GlideApp.with(holder.binding.ivThumbnailAlbum)
            .load(getItem(position).mediaItems[0].uri)
            .centerCrop()
            .signature(
                MediaStoreSignature(
                    null, getItem(position)
                        .mediaItems[0].dateModified, 0
                )
            )
            .error(R.drawable.ic_baseline_image_not_supported_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.ivThumbnailAlbum)

        holder.binding.tvAlbumName.text = getItem(position).name

        holder.binding.ivThumbnailAlbum.setOnClickListener {
            if ((frag.binding.bnvMain as NavigationBarView).selectedItemId == R.id.miFolders
                || frag.requireActivity().intent.action == Intent.ACTION_PICK || frag.requireActivity()
                    .intent.action ==
                Intent.ACTION_GET_CONTENT
            ) {
                MainActivity.currentListPosition = 0

                frag.setSharedAxisTransition()

                frag.findNavController().navigate(
                    R.id.action_bottomNavFrag_to_albumDetailFrag,
                    Bundle().apply {
                        putString("currentAlbumName", getItem(holder.layoutPosition).name)
                    }
                )
            }
        }
    }
}

class AlbumHolder(val binding: AlbumHolderBinding) :
    RecyclerView.ViewHolder(binding.root)