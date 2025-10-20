package com.geeks.notes.ui.on_board.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geeks.notes.data.models.OnBoardModel
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
            if (adapterPosition == (OnBoardList.size-1)) {
                binding.skip.visibility = View.INVISIBLE
                binding.mButton.setOnClickListener {
                    onStart()

                }
            }else{
                binding.mButton.visibility = View.INVISIBLE
                binding.skip.setOnClickListener {
                    onStart()
                }
            }

        }
        }
}