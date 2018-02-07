package com.normanhoeller.tictactoe

import android.support.v4.app.Fragment
import com.normanhoeller.tictactoe.model.Board
import com.normanhoeller.tictactoe.model.Player
import com.normanhoeller.tictactoe.ui.MainFragment
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by norman on 06.02.18.
 */
class TestBoard {

    private lateinit var board: Board
    private lateinit var player: Player
    private lateinit var view: Fragment

    @Before
    fun setup() {
        view = Mockito.mock(MainFragment::class.java)
        board = Board(view, CharArray(9))
        player = Player("Norman", 'x')
    }

    @Test
    fun testMappingOfRowAndColumnToPositionInArray() {
        var pos = board.mapToPosition(0, 0)
        assertThat(pos, `is`(equalTo(0)))

        pos = board.mapToPosition(0, 1)
        assertThat(pos, `is`(equalTo(1)))

        pos = board.mapToPosition(0, 2)
        assertThat(pos, `is`(equalTo(2)))

        pos = board.mapToPosition(1, 0)
        assertThat(pos, `is`(equalTo(3)))

        pos = board.mapToPosition(1, 1)
        assertThat(pos, `is`(equalTo(4)))

        pos = board.mapToPosition(1, 2)
        assertThat(pos, `is`(equalTo(5)))

        pos = board.mapToPosition(2, 0)
        assertThat(pos, `is`(equalTo(6)))

        pos = board.mapToPosition(2, 1)
        assertThat(pos, `is`(equalTo(7)))

        pos = board.mapToPosition(2, 2)
        assertThat(pos, `is`(equalTo(8)))
    }

    @Test
    fun testRowForCompleteness() {
        // row 0
        board.storePosition(player, 0)
        board.storePosition(player, 1)
        board.storePosition(player, 2)

        assertThat(board.checkRow(player, 0), `is`(true))

        // row 1
        board.storePosition(player, 3)
        board.storePosition(player, 4)
        board.storePosition(player, 5)

        assertThat(board.checkRow(player, 1), `is`(true))

        // row 2
        board.storePosition(player, 6)
        board.storePosition(player, 7)
        board.storePosition(player, 8)

        assertThat(board.checkRow(player, 2), `is`(true))
    }

    @Test
    fun testColForWinner() {
        // col 0
        board.storePosition(player, 0)
        board.storePosition(player, 3)
        board.storePosition(player, 6)

        assertThat(board.checkCol(player, 0), `is`(true))

        // col 1
        board.storePosition(player, 1)
        board.storePosition(player, 4)
        board.storePosition(player, 7)

        assertThat(board.checkCol(player, 1), `is`(true))

        // col 2
        board.storePosition(player, 2)
        board.storePosition(player, 5)
        board.storePosition(player, 8)

        assertThat(board.checkCol(player, 2), `is`(true))
    }

    @Test
    fun testDiagonalTopLeftToBottomRight() {
        board.storePosition(player, 0)
        board.storePosition(player, 4)
        board.storePosition(player, 8)

        assertThat(board.checkDiagonalTopLeftBottomRight(player), `is`(true))
    }

    @Test
    fun testDiagonalBottomLeftToTopRight() {
        board.storePosition(player, 2)
        board.storePosition(player, 4)
        board.storePosition(player, 6)

        assertThat(board.checkDiagonalBottomLeftTopRight(player), `is`(true))
    }

    @Test
    fun testCheckForWinner() {
        board.storePosition(player, 0)
        board.storePosition(player, 4)
        board.storePosition(player, 8)

        player.timesPlayed = 3

        assertThat(board.checkForWinner(player, 0), `is`(true))
        assertThat(board.checkForWinner(player, 4), `is`(true))
        assertThat(board.checkForWinner(player, 8), `is`(true))
    }
}