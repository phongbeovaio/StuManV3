package vn.edu.hust.studentman

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class EditStudentFragment : Fragment() {
    private var student: StudentModel? = null
    private var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            student = it.getParcelable("student")
            position = it.getInt("position", -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        val nameInput = view.findViewById<EditText>(R.id.edit_text_name)
        val idInput = view.findViewById<EditText>(R.id.edit_text_student_id)
        val btnEdit = view.findViewById<Button>(R.id.btn_edit_student)

        student?.let {
            nameInput.setText(it.studentName)
            idInput.setText(it.studentId)
        }

        btnEdit.setOnClickListener {
            val updatedName = nameInput.text.toString()
            val updatedId = idInput.text.toString()

            if (updatedName.isNotEmpty() && updatedId.isNotEmpty() && position != -1) {
                val updatedStudent = StudentModel(updatedName, updatedId)
                parentFragmentManager.popBackStack() // Quay lại MainFragment
                (parentFragment as? MainFragment)?.let {
                    it.studentAdapter.updateStudent(updatedStudent, position)
                }
            }
        }

        return view
    }

    companion object {
        fun newInstance(student: StudentModel, position: Int) = EditStudentFragment().apply {
            arguments = Bundle().apply {
                putParcelable("student", student)
                putInt("position", position)
            }
        }
    }
}
