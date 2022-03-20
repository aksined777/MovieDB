package com.themoviedb.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.themoviedb.presentation.R
import com.themoviedb.presentation.databinding.HomeFragmentBinding
import com.themoviedb.presentation.extension.findNavController
import com.themoviedb.presentation.extension.hide
import com.themoviedb.presentation.extension.show
import com.themoviedb.presentation.extension.showSnackBar
import com.themoviedb.presentation.ui.app.AppState
import com.themoviedb.presentation.ui.detail.DetailFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val moveAdapter = MoveAdapter()

    private val viewModel by viewModel<HomeViewModel>()

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }


    private fun initUI() {
        binding.rvMove.adapter = moveAdapter
        viewModel.getLiveData()
            .observe(viewLifecycleOwner, { state -> render(state as AppState) })

        viewModel.getIsEnglishLanguage().observe(viewLifecycleOwner, { isCheked ->
            binding.swLanguage.isChecked = isCheked
            binding.tvTitle.postDelayed({
                binding.tvTitle.text = requireActivity().resources.getString(R.string.raiting_move)
            }, 100)

        })

        binding.swLanguage.setOnClickListener({
            val check = (it as SwitchCompat).isChecked
            viewModel.clickSwitchMoves(check)
        })
    }

    private fun render(state: AppState) {
        when (state) {
            is AppState.Success<*> -> {
                val resp = state.data as LocalListResponce;
                binding.grDownload.hide()
                binding.grData.show()
                moveAdapter.setData(resp.moves)
                moveAdapter.setOnItemClickListener({
                    findNavController().navigate(
                        R.id.action_homeFragment_to_detailFragment,
                        bundleOf(
                            Pair(DetailFragment.MOVE_ID, it.id)
                        )
                    )
                })
            }
            is AppState.Error -> {
                binding.grDownload.show()
                binding.grData.hide()
                binding.root.showSnackBar(
                    state.error.message.toString(),
                    R.string.act_now,
                    90000,
                    { viewModel.getMoves() })
            }
            is AppState.Loading -> {
                binding.grDownload.show()
                binding.grData.hide()
            }
        }
    }

}