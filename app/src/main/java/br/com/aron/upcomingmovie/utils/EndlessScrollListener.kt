package br.com.aron.upcomingmovie.utils

import android.widget.AbsListView

/**
 * Created by Aron on 25/02/2018.
 */
abstract class EndlessScrollListener : AbsListView.OnScrollListener {
    // The minimum number of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 5
    // The current offset index of data you have loaded
    private var currentPage = 0
    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0
    // True if we are still waiting for the last set of data to load.
    private var loading = true
    // Sets the starting page index
    private var startingPageIndex = 0

    constructor() {}

    constructor(visibleThreshold: Int) {
        this.visibleThreshold = visibleThreshold
    }

    constructor(visibleThreshold: Int, startPage: Int) {
        this.visibleThreshold = visibleThreshold
        this.startingPageIndex = startPage
        this.currentPage = startPage
    }


    override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
            currentPage++
        }

        if (!loading && firstVisibleItem + visibleItemCount + visibleThreshold >= totalItemCount) {
            loading = onLoadMore(currentPage + 1, totalItemCount)
        }
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int): Boolean

    override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {

    }
}