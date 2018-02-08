package com.normanhoeller.tictactoe.controller

import com.normanhoeller.tictactoe.model.Player

/**
 * Created by norman on 07.02.18.
 */
interface ViewInterface {
    fun update(mappedPosition: Int?, playerName: Player?)
}