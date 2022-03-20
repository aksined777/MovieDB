package com.themoviedb.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.themoviedb.presentation.R
import com.themoviedb.presentation.databinding.DetailFragmentBinding
import com.themoviedb.presentation.extension.showSnackBar
import com.themoviedb.presentation.ui.app.AppState
import com.themoviedb.presentation.utils.argument
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val viewModel by viewModel<DetailViewModel>()

    companion object {
        const val MOVE_ID: String = "move_id"
        fun newInstance() = DetailFragment()
    }

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val moveId: Long by argument(MOVE_ID, -1)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        viewModel.getLiveData()
            .observe(viewLifecycleOwner, { state -> render(state as AppState) })
        viewModel.getMove(moveId)
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                val resp = state.data as LocalResponce;
                binding.tvName.text = resp.move.name
                binding.tvRating.text =
                    resources.getString(R.string.raiting) + ": " + resp.move.rating.toString()
                binding.tvScript.text = resp.move.script
                val path = "http://image.tmdb.org/t/p/w500${resp.move.poster}"
                Glide.with(requireContext())
                    .load(path)
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.ic_baseline_cancel_presentation_24)
                    .into(binding.ivPoster)
            }
            is AppState.Error -> {
                binding.root.showSnackBar(
                    state.error.message.toString(),
                    R.string.act_now,
                    90000,
                    { viewModel.getMove(moveId) })
            }

        }
    }
}