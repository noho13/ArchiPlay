package com.normanhoeller.tictactoe.presenter

import com.normanhoeller.tictactoe.model.Board
import com.normanhoeller.tictactoe.model.Player
import com.normanhoeller.tictactoe.view.UIInterface

/**
 * Created by norman on 08.02.18.
 */
class MainPresenter(private val model: Board, private val view: UIInterface) : Contract {

    private val playerOne = Player("Norman", 'X')
    private val playerTwo = Player("Stephanie", 'O')
    private var currentSymbol: Char? = null
    private var isSubscribed: Boolean = false

    override fun reset() {
        currentSymbol = null
        model.reset()
        view.update(model.playStatus, "")
    }

    override fun clickOnCellWithPosition(row: Int, col: Int) {
        val mappedPosition = model.mapToPosition(row, col)
        var currentPlayer: Player
        if (currentSymbol == null || currentSymbol == playerTwo.symbol) {
            currentSymbol = playerOne.symbol
            currentPlayer = playerOne
        } else {
            currentSymbol = playerTwo.symbol
            currentPlayer = playerTwo
        }
        model.storePosition(currentPlayer, mappedPosition)
        val message = if (model.checkForWinner(currentPlayer, mappedPosition)) {
            "We have a winner: ${currentPlayer.name}"
        } else {
            ""
        }
        view.update(model.playStatus, message)
    }


    override fun unSubscribe() {
        isSubscribed = false
    }

    override fun subscribe() {
        isSubscribed = true
    }

}