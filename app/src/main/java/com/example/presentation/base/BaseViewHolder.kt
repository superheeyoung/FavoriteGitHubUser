package com.example.presentation.base

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(private var viewBinding: ConstraintLayout) : RecyclerView.ViewHolder(viewBinding)