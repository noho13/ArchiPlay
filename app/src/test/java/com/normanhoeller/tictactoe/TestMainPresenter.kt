package com.normanhoeller.tictactoe

import com.normanhoeller.tictactoe.model.Board
import com.normanhoeller.tictactoe.presenter.MainPresenter
import com.normanhoeller.tictactoe.view.UIInterface
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by norman on 08.02.18.
 */
class TestMainPresenter {

    private lateinit var presenter: MainPresenter
    private lateinit var model: Board
    private lateinit var view: UIInterface

    @Before
    fun setup() {
        model = Mockito.mock(Board::class.java)
        view = Mockito.mock(UIInterface::class.java)
        presenter = MainPresenter(model, view)
    }

    @Test
    fun message_indicates_a_winner_not_empty() {
        presenter.subscribe()
        model.playStatus = charArrayOf('X', 'X', 'O')
        presenter.clickOnCellWithPosition(0, 2)
        // TODO finish test
    }

    @Test
    fun message_indicates_no_winner_returns_empty() {
        presenter.subscribe()
        model.playStatus = charArrayOf('X', 'O', 'O')
        presenter.clickOnCellWithPosition(0, 2)
        // TODO finish test
    }

    @Test
    fun update_not_running_because_presenter_not_subscribed() {
        // TODO finish test
    }

}