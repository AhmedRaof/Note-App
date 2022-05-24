package com.example.yes

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.yes.databinding.FragmentLoginBinding
import com.example.yes.model.entity.NoteDataClass
import com.example.yes.model.userEntity.userDataClass
import com.example.yes.model.userLocal.UserDataBase
import com.example.yes.model.userLocal.userRepositoryImp
import kotlinx.coroutines.*


class FragmentLogin : Fragment() {
    lateinit var binding : FragmentLoginBinding
    lateinit var lacaluserRepo : userRepositoryImp
    var userList : List<userDataClass> = emptyList()
    var searchList : List<userDataClass> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var DB = UserDataBase.getInstance(requireContext())
        lacaluserRepo = userRepositoryImp(DB)


        binding.submitButton.setOnClickListener {
            getAllUsers()
            if(userList.size == 0){
                Toast.makeText(context,"Empty List",Toast.LENGTH_SHORT).show()
            }
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentHome)
        }

        binding.SignUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentSignup)
        }
    }

    fun getAllUsers(){
        GlobalScope.launch(Dispatchers.IO){
            var returnedUsers = async {
                lacaluserRepo.getUsers()
            }
            withContext(Dispatchers.Main){
                binding.progressBar2.visibility = View.VISIBLE
                userList = returnedUsers.await()
                binding.progressBar.visibility = View.GONE
                searchList = userList
            }
        }
    }


}