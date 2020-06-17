package olga.pietrzyk.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        var toDoRepository: ToDoRepository?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var start=1
        start =intent.getIntExtra("start",1)



        if(start==1) {
            MainActivity.toDoRepository = ToDoRepository().apply {
                addToDo(
                    "Feed the pets",
                    "Give the cat a fish and the dog a cat."
                )
                addToDo(
                    "Exercise",
                    "Take a walk and listen to music."
                )
            }
        }
        var id_number=0
        for (task in MainActivity.toDoRepository!!.toDos){
           task.id= id_number
            id_number+=1
        }

        findViewById<Button>(R.id.create).setOnClickListener{
            startActivity(Intent(this, NewTask::class.java))
        }

        var listView = findViewById<ListView>(R.id.list_view)
        listView?.adapter = ArrayAdapter<ToDo>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            MainActivity.toDoRepository!!.getAllToDos()
        )

        listView?.setOnItemClickListener{parent, view, position, id->
            Toast.makeText(this, "click item"+position+""+id,Toast.LENGTH_SHORT).show()
            val intent=  Intent(this, TaskPreview::class.java)
            intent.putExtra("id", id.toInt())
            startActivity(intent)

        }
    }
}
