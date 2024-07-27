package com.example.pharmachek.ui.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pharmachek.R
import com.example.pharmachek.database.ParmacheckDatabase
import com.example.pharmachek.databinding.FragmentBookmarkBinding
import com.example.pharmachek.utils.navigateTo
import com.example.pharmachek.utils.toastMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookmarkFragment : Fragment(R.layout.fragment_bookmark) {

    private var _binding: FragmentBookmarkBinding? = null

    private val binding: FragmentBookmarkBinding get() = _binding!!
    private val bookmarkAdapter: BookmarkAdapter = BookmarkAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookmarkBinding.bind(view)

        with(binding) {
            setupValues()
            setupViews()
            setupListeners()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun FragmentBookmarkBinding.setupListeners() {
        bookmarkAdapter.setOnFavoriteIconClickListener { data, position ->
            val database = ParmacheckDatabase.createDatabase(requireContext())
            val dao = database.parmacheckDao()
            lifecycleScope.launch(Dispatchers.IO) {
                dao.deleteDrug(data)
                withContext(Dispatchers.Main) {
                    if (dao.getDrugs().isEmpty()) {
                        bookmarkRecyclerView.visibility = View.INVISIBLE
                        noDrugsText.visibility = View.VISIBLE
                    } else {
                        noDrugsText.visibility = View.INVISIBLE
                        bookmarkRecyclerView.visibility = View.VISIBLE
                        bookmarkAdapter.submitList(dao.getDrugs())
                    }
                }
            }
        }
        bookmarkAdapter.setOnItemClickListener {
            val direction = BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment(
                it.imageUrls,
                it.productName.toString(),
                it.insComposition.toString(),
                it.positive,
                it.negative,
                it.neutral,
                it.insPharmacologicalProperties.toString(),
                it.insDosageAndAdministration.toString(),
                it.insSideEffects.toString(),
                it.insContraindications.toString(),
                it.insOverdose.toString(),
                it.insSpecialConditions.toString(),
                it.insInteractions.toString(),
                it.insStorageConditions.toString(),
                it.insShelfLife.toString(),
                it.insReleaseForm.toString(),
                it.insManufacturerInfo.toString(),
                it.mass.toString(),
                it.insIndications.toString(),
                "${it.producer.toString()}, ${it.countryOfOrigin.toString()}",
            )
            navigateTo(direction)
        }
    }

    private fun FragmentBookmarkBinding.setupValues() {}

    private fun FragmentBookmarkBinding.setupViews() {
        val database = ParmacheckDatabase.createDatabase(requireContext())
        val dao = database.parmacheckDao()
        val bookmarkLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        if (dao.getDrugs().isEmpty()) {
            toastMessage("isEmpty")
            bookmarkRecyclerView.visibility = View.INVISIBLE
            noDrugsText.visibility = View.VISIBLE
        } else {
            toastMessage("isNotEmpty")
            bookmarkRecyclerView.visibility = View.VISIBLE
            noDrugsText.visibility = View.INVISIBLE
            bookmarkRecyclerView.layoutManager = bookmarkLayoutManager
            bookmarkRecyclerView.adapter = bookmarkAdapter
            bookmarkAdapter.submitList(dao.getDrugs())
        }
    }
}