package olga.pietrzyk.todo

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import java.util.logging.Logger


class TaskPreview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_preview)
        val id = intent.getIntExtra("id",0)
        println(id)


        val choosen_task = MainActivity.toDoRepository?.getToDoById(id)

        var chosen_task_title=findViewById<TextView>(R.id.taskTitle)
        var chosen_task_content=findViewById<TextView>(R.id.taskContent)
        chosen_task_title.setText(choosen_task?.content)
        chosen_task_content.setText(choosen_task?.title)


        findViewById<Button>(R.id.listPreview).setOnClickListener {
            val intent=  Intent(this, MainActivity::class.java)
            intent.putExtra("start", 0)
            startActivity(intent)
        }


        findViewById<Button>(R.id.updateTask).setOnClickListener{
            val intent=  Intent(this, Update_Task::class.java)
            intent.putExtra("id", id.toInt())
            startActivity(intent)

        }

        findViewById<Button>(R.id.deleteTask).setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete task")
            builder.setMessage("Do you really want to delete the task");

            builder.setPositiveButton("YES"){_, _ ->
                MainActivity.toDoRepository?.deleteToDoById(id)
                Toast.makeText(applicationContext,"You deleted the task", Toast.LENGTH_SHORT).show()
                print(MainActivity.toDoRepository)
                //val Log = Logger.getLogger(MainActivity::class.java.name)

                val intent=Intent(this, MainActivity::class.java)
                intent.putExtra("start", 0)
                startActivity(intent)
            }

            builder.setNegativeButton("No"){dialog,which ->
                Toast.makeText(applicationContext,"You didn't delete the task.",Toast.LENGTH_SHORT).show()
            }

            val dialog = builder.create()
            dialog.show()


        }
    }
}
