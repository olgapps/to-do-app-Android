package olga.pietrzyk.todo

class ToDoRepository {

    val toDos = mutableListOf<ToDo>()

    fun addToDo(title: String, content: String): Int {
        val id = when {
            toDos.count() == 0 -> 1
            else -> toDos.last().id + 1
        }
        toDos.add(
            ToDo(
                id,
                title,
                content
            )
        )
        return id
    }

    fun getAllToDos() = toDos

    fun getToDoById(id: Int) =
        toDos.find {
            it.id == id
        }

    fun deleteToDoById(id: Int) =
        toDos.remove(
            toDos.find {
                it.id == id
            }
        )

    fun updateToDoById(id: Int, newTitle: String, newContent: String) {

        getToDoById(id)?.run {
            title = newTitle
            content = newContent
        }

    }

    fun getLastId(): Int{
        return toDos.last().id + 1
    }


}