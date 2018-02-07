package com.normanhoeller.tictactoe.model

import android.support.annotation.VisibleForTesting

/**
 * Created by norman on 06.02.18.
 */
class Board {

    private val playStatus = arrayOfNulls<Char>(9)

    fun storePosition(player: Player, pos: Int) {
        playStatus[pos] = player.symbol
    }

    fun getValueForPosition(pos: Int) = playStatus[pos]

    fun mapToPosition(row: Int, col: Int) = row * 3 + col

    fun checkForWinner(player: Player, pos: Int): Boolean {
        if (player.timesPlayed < 3) {
            return false
        }
        when (pos) {
            0 -> return checkRow(player, 0) || checkCol(player, 0) || checkDiagonalTopLeftBottomRight(player)
            1 -> return checkRow(player, 0) || checkCol(player, 1)
            2 -> return checkRow(player, 0)  || checkCol(player, 2) || checkDiagonalBottomLeftTopRight(player)
            3 -> return checkRow(player, 1) || checkCol(player, 0)
            4 -> {
                return checkRow(player, 1)  ||checkCol(player, 1)||
                        checkDiagonalTopLeftBottomRight(player)|| checkDiagonalBottomLeftTopRight(player)
            }
            5 -> return checkRow(player, 1) || checkCol(player, 2)
            6 -> return checkRow(player, 2) || checkCol(player, 0)|| checkDiagonalBottomLeftTopRight(player)
            7 -> return checkRow(player, 2) || checkCol(player, 1)
            8 -> return checkRow(player, 2) || checkCol(player, 2) || checkDiagonalTopLeftBottomRight(player)
        }
        return false
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun checkRow(player: Player, row: Int): Boolean {
        val startAt = row * 3
        return (startAt..startAt + 2).none { playStatus[it] == null || playStatus[it] != player.symbol }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun checkCol(player: Player, col: Int): Boolean {
        return (col..col + 6 step (3)).none { playStatus[it] == null || playStatus[it] != player.symbol }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun checkDiagonalTopLeftBottomRight(player: Player): Boolean {
        return (0 until playStatus.size step (4)).none { playStatus[it] == null || playStatus[it] != player.symbol }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun checkDiagonalBottomLeftTopRight(player: Player): Boolean {
        return (2 until 7 step (2)).none { playStatus[it] == null || playStatus[it] != player.symbol }
    }
}