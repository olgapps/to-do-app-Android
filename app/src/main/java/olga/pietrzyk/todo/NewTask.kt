package olga.pietrzyk.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class NewTask : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)


        findViewById<Button>(R.id.btn_create_new).setOnClickListener{

            if(MainActivity.toDoRepository?.toDos!!.isEmpty()){
                MainActivity.toDoRepository?.addToDo(
                    " ",
                    " "
                )
            }


            val id=MainActivity.toDoRepository?.getLastId()
            MainActivity.toDoRepository?.addToDo( findViewById<EditText>(R.id.input_new_task).text.toString(), findViewById<EditText>(R.id.input_new_content).getText().toString())
            val intent=  Intent(this, TaskPreview::class.java)
            intent.putExtra("id", id?.toInt())
            startActivity(intent)
            finish()
        }

    }
}
