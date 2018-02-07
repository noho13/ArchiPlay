package com.normanhoeller.tictactoe.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.normanhoeller.tictactoe.R
import com.normanhoeller.tictactoe.model.Board
import com.normanhoeller.tictactoe.model.Player
import kotlinx.android.synthetic.main.fr_main.*


/**
 * Created by norman on 07.02.18.
 */
class MainFragment : Fragment() {

    companion object {
        fun createInstance() = MainFragment()
    }

    private lateinit var board: Board
    private lateinit var playerOne: Player

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fr_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        board = Board()
        playerOne = Player("Norman", 'X')

        tv_0_0.setOnClickListener { clickOnPosition(tv_0_0,0, 0) }
        tv_0_1.setOnClickListener { clickOnPosition(tv_0_1,0, 1) }
        tv_0_2.setOnClickListener { clickOnPosition(tv_0_2,0, 2) }

        tv_1_0.setOnClickListener { clickOnPosition(tv_1_0,1, 0) }
        tv_1_1.setOnClickListener { clickOnPosition(tv_1_1,1, 1) }
        tv_1_2.setOnClickListener { clickOnPosition(tv_1_2,1, 2) }

        tv_2_0.setOnClickListener { clickOnPosition(tv_2_0,2, 0) }
        tv_2_1.setOnClickListener { clickOnPosition(tv_2_1,2, 1) }
        tv_2_2.setOnClickListener { clickOnPosition(tv_2_2,2, 2) }

        tv_reset.setOnClickListener {reset()}

    }

    private fun reset() {
        board = Board()
        tv_0_0.text = ""
        tv_0_1.text = ""
        tv_0_2.text = ""

        tv_1_0.text = ""
        tv_1_1.text = ""
        tv_1_2.text = ""

        tv_2_0.text = ""
        tv_2_1.text = ""
        tv_2_2.text = ""

        tv_result.text = ""
    }

    private fun clickOnPosition(cell: TextView, row: Int, col: Int) {
        cell.text = playerOne.symbol.toString()
        playerOne.timesPlayed++
        val mappedPosition = board.mapToPosition(row, col)
        board.storePosition(playerOne, mappedPosition)

        if (board.checkForWinner(playerOne, mappedPosition)) {
            tv_result.text = "We have a winner: ${playerOne.name}"
        }
    }
}