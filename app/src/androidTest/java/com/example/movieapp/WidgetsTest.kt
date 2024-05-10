package com.example.movieapp

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import org.junit.Rule
import org.junit.Test

class WidgetsTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun test_MovieRowの表示_AnimatedVisibility非表示() {
        // titleの確認
        composeTestRule.onAllNodesWithText("Narcos")
        // Directorの確認
        composeTestRule.onAllNodesWithText("Director:")
        // Yearの確認
        composeTestRule.onAllNodesWithText("Released:")
        // 画像の確認
        composeTestRule.onAllNodesWithContentDescription("Movie Image")
        // AnimatedVisibility非表示の確認
        composeTestRule.onNodeWithText("Plot:").assertDoesNotExist()
        composeTestRule.onNodeWithText("Actors:").assertDoesNotExist()
        composeTestRule.onNodeWithText("Rating:").assertDoesNotExist()
        composeTestRule.onAllNodesWithContentDescription("Down Arrow")
    }

    @Test
    fun test_MovieRowの表示_AnimatedVisibility表示() {
        // AnimatedVisibilityを表示させる
        composeTestRule.onAllNodesWithContentDescription("Down Arrow")
        composeTestRule.onAllNodesWithContentDescription("Down Arrow").onLast().performClick()
        // AnimatedVisibility表示の確認
        composeTestRule.onAllNodesWithContentDescription("Up Arrow")
        composeTestRule.onAllNodesWithText("Plot:")
        composeTestRule.onAllNodesWithText("Actors:")
        composeTestRule.onAllNodesWithText("Rating:")
    }

    @Test
    fun test_DetailsScreenの確認() {
        composeTestRule.onAllNodesWithText("Vikings")
        // DetailsScreenへの遷移後、Movie Imagesの表示確認
        composeTestRule.onNodeWithText("Movie Image")
        composeTestRule.onAllNodesWithContentDescription("Movie Image").onFirst()
            .performTouchInput {
                swipeRight()
                swipeLeft()
            }
        composeTestRule.onAllNodesWithContentDescription("Movie Image").onLast()
            .performTouchInput {
                swipeLeft()
                swipeRight()
            }
    }
}
