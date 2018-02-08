package com.normanhoeller.tictactoe.presenter

/**
 * Created by norman on 08.02.18.
 */
interface Contract {
    fun subscribe()
    fun unSubscribe()

    fun clickOnCellWithPosition(row: Int, col: Int)
    fun reset()
}