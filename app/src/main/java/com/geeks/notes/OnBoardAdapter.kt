package com.geeks.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.geeks.notes.databinding.ItemOnBoardBinding

class OnBoardAdapter(private val OnBoardList: List<OnBoardModel>, val onStart:()-> Unit) :
    RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: OnBoardViewHolder,
        position: Int
    ) {
        holder.onBind(OnBoardList[position])
    }

    override fun getItemCount(): Int {
        return OnBoardList.size

    }

    inner class OnBoardViewHolder(private val binding: ItemOnBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(onBoard: OnBoardModel){
            binding.tvTitle.text = onBoard.title
            binding.tvDesc.text = onBoard.desc
            binding.lottieImg.setAnimation(onBoard.lottieImg)
            binding.lottieImg.playAnimation()

        }
        }
}


