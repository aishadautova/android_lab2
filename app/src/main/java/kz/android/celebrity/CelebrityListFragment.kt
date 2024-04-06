package kz.android.celebrity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kz.android.celebrity.databinding.FragmentCelebrityListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CelebrityListFragment : Fragment() {

    private var _binding: FragmentCelebrityListBinding? = null
    private val binding get() = _binding!!
    private val adapter = CelebrityAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCelebrityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.etSearch.setOnEditorActionListener { it, actionId, event ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                search(it.text.toString())
                true
            } else false
        }
    }

    private fun search(query: String) {
        Repository.api.getCelebritiesByName(query).enqueue(object : Callback<List<Celebrity>> {
            override fun onResponse(call: Call<List<Celebrity>>, response: Response<List<Celebrity>>) {
                if (response.isSuccessful) {
                   adapter.submitList(response.body())
                } else {
                    Toast.makeText(requireContext(), "there is some error 1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Celebrity>>, t: Throwable) {
                Toast.makeText(requireContext(), "there is some error 2", Toast.LENGTH_SHORT).show()

            }
        })
    }
}

