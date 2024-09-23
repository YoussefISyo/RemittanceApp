package com.isyo.remitconnecct.uis.recipient

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isyo.remitconnecct.data.RecipientRepository
import com.isyo.remitconnecct.model.User
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * UI state for the Recipient screen
 */
sealed interface RecipientsUiState {
    data class Success(val users: Response<List<User>>) : RecipientsUiState
    object Error : RecipientsUiState
    object Loading : RecipientsUiState
}

class RecipientViewModel(private val recipientsRepository: RecipientRepository) : ViewModel() {

    var recipientsUiState: RecipientsUiState by mutableStateOf(RecipientsUiState.Loading)
        private set

    init {
        getRecipients()
    }


    private fun getRecipients() {
        viewModelScope.launch {
            recipientsUiState = RecipientsUiState.Loading
            recipientsUiState = try {
                RecipientsUiState.Success(recipientsRepository.getRecipients())
            } catch (e: IOException) {
                RecipientsUiState.Error
            } catch (e: HttpException) {
                RecipientsUiState.Error
            }
        }
    }

}