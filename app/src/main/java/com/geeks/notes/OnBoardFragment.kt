package com.geeks.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geeks.notes.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding
    private lateinit var adapter: OnBoardAdapter
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Pref(requireContext())
        if (pref.isScreenShown()){
            findNavController().navigate(R.id.action_onBoardFragment_to_mainFragment)
        }
        loadOnBoardData()
        initView()
        onStartBoard()


    }



    private fun initView() {
        adapter = OnBoardAdapter(loadOnBoardData(), ::onStartBoard)
        binding.viewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val lastPage = adapter.itemCount - 1
                if (position == lastPage) {
                    binding.mButton.visibility = View.VISIBLE
                    binding.skip.visibility = View.INVISIBLE
                } else {
                    binding.mButton.visibility = View.INVISIBLE
                    binding.skip.visibility = View.VISIBLE
                }
            }
        })


    }

    private fun onStartBoard() {
        binding.skip.setOnClickListener {
            pref.setScreenShown(true)
            findNavController().navigate(R.id.action_onBoardFragment_to_mainFragment)
        }
        binding.mButton.setOnClickListener {
            pref.setScreenShown(true)
            findNavController().navigate(R.id.action_onBoardFragment_to_mainFragment)
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

}
