package com.geeks.notes.ui.on_board

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.geeks.notes.data.models.OnBoardModel
import com.geeks.notes.data.models.local.Pref
import com.geeks.notes.databinding.FragmentOnBoardBinding
import com.geeks.notes.databinding.ItemOnBoardBinding
import com.geeks.notes.ui.on_board.adapter.OnBoardAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoardFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var adapter: OnBoardAdapter
    private lateinit var pref: Pref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        pref = Pref(requireContext())
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadOnBoardData()
        initView()

    }



    private fun initView() {
        adapter =
            OnBoardAdapter(loadOnBoardData(), ::onStartBoard)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)


    }

    private fun onStartBoard(){
        pref.saveOnBoard(true)
        findNavController().navigate(_root_ide_package_.com.geeks.notes.R.id.action_onBoardFragment_to_mainFragment)

    }

    }

    private fun loadOnBoardData(): List<OnBoardModel> {
        return listOf<OnBoardModel>(
            OnBoardModel(
                title = "Удобство",
                desc = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно.",
                lottieImg = "board_design_strategy.json"
            ),
            OnBoardModel(
                title = "Организация",
                desc = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время.",
                lottieImg = "cutting_board.json"
            ),
            OnBoardModel(
                title = "Синхронизация",
                desc = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте.",
                lottieImg = "top_stocks.json"
            )
        )
    }
