package org.kenvyra.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import org.kenvyra.gallery.R
import org.kenvyra.gallery.adapter.GridItemAdapter

class SearchResultsFrag : AlbumFrag() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.recyclerViewItems.observe(viewLifecycleOwner) { items ->
            (binding.rvAlbumDetail.adapter as GridItemAdapter).submitList(items) {
                scrollToFirst(binding.rvAlbumDetail)
            }
        }

        setUpAlbumFrag(
            inflater,
            container,
            R.id.action_searchResultsFrag_to_viewPagerFrag2
        )

        binding.tbAlbum.setNavigationOnClickListener {
            activity?.finish()
        }

        binding.rvAlbumDetail.layoutManager =
            GridLayoutManager(context, resources.getInteger(R.integer.spanCount)).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (binding.rvAlbumDetail.adapter?.getItemViewType(position)) {
                            GridItemAdapter.ITEM_VIEW_TYPE_HEADER -> resources.getInteger(R.integer.spanCount)
                            GridItemAdapter.ITEM_VIEW_TYPE_SEARCH -> resources.getInteger(R.integer.spanCount)
                            else -> 1
                        }
                    }
                }
            }

        binding.tbAlbum.title = "Search Results"

        return binding.root
    }
}