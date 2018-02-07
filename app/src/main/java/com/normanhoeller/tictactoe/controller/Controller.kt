package com.normanhoeller.tictactoe.controller

import com.normanhoeller.tictactoe.model.Board
import com.normanhoeller.tictactoe.model.Player

/**
 * Created by norman on 07.02.18.
 */
class Controller(private val model: Board, private val view: ViewInterface) {

    private val playerOne = Player("Norman", 'X')
    private val playerTwo = Player("Stephanie", 'O')
    private var currentSymbol: Char? = null

    fun clickOnPosition(row: Int, col: Int) {
        val mappedPosition = model.mapToPosition(row, col)
        if (currentSymbol == null || currentSymbol == playerTwo.symbol) {
            currentSymbol = playerOne.symbol
            playerOne.timesPlayed++
            model.storePosition(playerOne, mappedPosition)
            view.update(mappedPosition, playerOne)
        } else {
            currentSymbol = playerTwo.symbol
            playerTwo.timesPlayed++
            model.storePosition(playerTwo, mappedPosition)
            view.update(mappedPosition, playerTwo)
        }
    }

    fun reset() {
        currentSymbol = null
        model.reset()
        view.update(null, null)
    }

}