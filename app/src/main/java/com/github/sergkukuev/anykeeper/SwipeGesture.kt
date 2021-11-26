package com.github.sergkukuev.anykeeper

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

abstract class SwipeGesture() : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private var leftBackgroundColor: Int = 0
    private var leftActionIcon: Int = 0

    fun addSwipeLeftBackgroundColor(index: Int): SwipeGesture {
        leftBackgroundColor = index
        return this
    }

    fun addSwipeLeftActionIcon(index: Int): SwipeGesture {
        leftActionIcon = index
        return this
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addSwipeLeftBackgroundColor(leftBackgroundColor)
            .addSwipeLeftActionIcon(leftActionIcon)
            .create()
            .decorate()

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}