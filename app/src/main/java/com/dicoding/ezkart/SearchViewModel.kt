
import androidx.lifecycle.ViewModel
import com.dicoding.ezkart.model.Menu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchViewModel : ViewModel() {
    fun performSearch(itemList: List<Menu>, query: String): Flow<List<Menu>> = flow {
        val searchResults = if (query.isNotEmpty()) {
            itemList.filter { it.title.contains(query, ignoreCase = true) }
        } else {
            itemList
        }

        emit(searchResults)
    }
}

