package vn.edu.hust.studentman

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {
    lateinit var studentAdapter: StudentAdapter
    private val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_students)
        studentAdapter = StudentAdapter(students) { student, position, action ->
            when (action) {
                "edit" -> openEditFragment(student, position)
                "delete" -> deleteStudent(position)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = studentAdapter

        val fabAddStudent = view.findViewById<FloatingActionButton>(R.id.fab_add_new)
        fabAddStudent.setOnClickListener {
            openAddFragment()
        }

        return view
    }

    private fun openAddFragment() {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, AddStudentFragment())
        transaction.addToBackStack(null) // Thêm vào BackStack để quay lại được
        transaction.commit()
    }

    private fun openEditFragment(student: StudentModel, position: Int) {
        val editFragment = EditStudentFragment.newInstance(student, position)
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, editFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun deleteStudent(position: Int) {
        students.removeAt(position)
        studentAdapter.notifyItemRemoved(position)
        Toast.makeText(requireContext(), "Đã xóa sinh viên!", Toast.LENGTH_SHORT).show()
    }
}
