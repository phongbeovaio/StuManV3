package vn.edu.hust.studentman

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class AddStudentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        val nameInput = view.findViewById<EditText>(R.id.edit_text_name)
        val idInput = view.findViewById<EditText>(R.id.edit_text_student_id)
        val btnAdd = view.findViewById<Button>(R.id.btn_add_student)

        btnAdd.setOnClickListener {
            val name = nameInput.text.toString()
            val id = idInput.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                val newStudent = StudentModel(name, id)

                parentFragmentManager.popBackStack()
                (parentFragment as? MainFragment)?.let {
                    it.studentAdapter.addStudent(newStudent)
                }
            }
        }

        return view
    }
}
