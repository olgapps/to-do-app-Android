package olga.pietrzyk.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class Update_Task : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update__task)


        val id=intent.getIntExtra("id", 0)
        val task= MainActivity.toDoRepository?.getToDoById(id)
        val title = task?.title
        val content = task?.content

        val title_text=findViewById<EditText>(R.id.input_update_title)
        val content_text = findViewById<EditText>(R.id.input_update_content)

        title_text.setText(title)
        content_text.setText(content)

        findViewById<Button>(R.id.updateTask).setOnClickListener{
            MainActivity.toDoRepository?.updateToDoById( id, title_text.getText().toString(), content_text.getText().toString())
            title_text.invalidate()
            content_text.invalidate()
            val intent=  Intent(this, TaskPreview::class.java)
            intent.putExtra("id", id.toInt())
            startActivity(intent)
            finish()
        }
    }
}
