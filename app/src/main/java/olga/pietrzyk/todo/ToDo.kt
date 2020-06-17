package olga.pietrzyk.todo

data class ToDo(
    var id: Int,
    var title: String,
    var content: String
){

    override fun toString() = title

}