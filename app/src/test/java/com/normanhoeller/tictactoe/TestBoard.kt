package com.normanhoeller.tictactoe

import com.normanhoeller.tictactoe.model.Board
import com.normanhoeller.tictactoe.model.Player
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created by norman on 06.02.18.
 */
class TestBoard {

    private lateinit var board: Board
    private lateinit var player: Player

    @Before
    fun setup() {
        board = Board(CharArray(9))
        player = Player("Norman", 'x')
    }

    @Test
    fun row_0_and_column_0_map_to_0() {
        val pos = board.mapToPosition(0, 0)
        assertThat(pos, `is`(equalTo(0)))
    }

    @Test
    fun row_0_and_column_2_map_to_2() {
        val pos = board.mapToPosition(0, 2)
        assertThat(pos, `is`(equalTo(2)))
    }

    @Test
    fun row_2_and_column_2_map_to_8() {
        val pos = board.mapToPosition(2, 2)
        assertThat(pos, `is`(equalTo(8)))
    }

    @Test
    fun fully_set_row_by_one_player_returns_true() {
        // row 0
        board.storePosition(player, 0)
        board.storePosition(player, 1)
        board.storePosition(player, 2)

        assertThat(board.checkRow(player, 0), `is`(true))

    }

    @Test
    fun not_fully_set_row_by_one_player_returns_false() {
        // row 0
        board.storePosition(player, 0)
        board.storePosition(player, 2)

        assertThat(board.checkRow(player, 0), `is`(false))
    }

    @Test
    fun fully_set_col_by_one_player_returns_true() {
        // row 0
        board.storePosition(player, 0)
        board.storePosition(player, 3)
        board.storePosition(player, 6)

        assertThat(board.checkCol(player, 0), `is`(true))

    }

    @Test
    fun not_fully_set_col_by_one_player_returns_false() {
        // row 0
        board.storePosition(player, 0)
        board.storePosition(player, 6)

        assertThat(board.checkCol(player, 0), `is`(false))
    }


    @Test
    fun fully_set_diagonal_by_one_player_top_left_to_bottom_right_returns_true() {
        board.storePosition(player, 0)
        board.storePosition(player, 4)
        board.storePosition(player, 8)

        assertThat(board.checkDiagonalTopLeftBottomRight(player), `is`(true))
    }

    @Test
    fun not_fully_set_diagonal_by_one_player_top_left_to_bottom_right_returns_false() {
        board.storePosition(player, 4)
        board.storePosition(player, 8)

        assertThat(board.checkDiagonalTopLeftBottomRight(player), `is`(false))
    }

    @Test
    fun fully_set_diagonal_by_one_player_bottom_left_to_top_right_returns_true() {
        board.storePosition(player, 2)
        board.storePosition(player, 4)
        board.storePosition(player, 6)

        assertThat(board.checkDiagonalBottomLeftTopRight(player), `is`(true))
    }

    @Test
    fun not_fully_set_diagonal_by_one_player_bottom_left_to_top_right_returns_false() {
        board.storePosition(player, 4)
        board.storePosition(player, 8)

        assertThat(board.checkDiagonalBottomLeftTopRight(player), `is`(false))
    }
}