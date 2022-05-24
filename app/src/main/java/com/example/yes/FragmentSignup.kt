package com.example.yes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.yes.databinding.FragmentLoginBinding
import com.example.yes.databinding.FragmentSignupBinding
import com.example.yes.model.entity.NoteDataClass
import com.example.yes.model.local.LocalRepositoryImp
import com.example.yes.model.local.NoteDataBase
import com.example.yes.model.userEntity.userDataClass
import com.example.yes.model.userLocal.UserDataBase
import com.example.yes.model.userLocal.userRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class FragmentSignup : Fragment() {

    var userList : List<userDataClass> = emptyList()

    lateinit var binding: FragmentSignupBinding
    var first_name :String ?= null
    var last_name :String?= null
    var user_name :String?=null
    var user_password :String ?=null


    lateinit var localuserRepository : userRepositoryImp
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var DB = UserDataBase.getInstance(requireContext())
        localuserRepository = userRepositoryImp(DB)


        binding.registerButton.setOnClickListener {
            first_name = binding.firstNameTextField.text.toString()
            last_name = binding.secondNameTextField.text.toString()
            user_name = binding.userNameTextField.text.toString()
            user_password = binding.passwordTextField.text.toString()
            GlobalScope.launch (Dispatchers.IO){
                localuserRepository.insertUser(
                    userDataClass(0,first_name.toString(),last_name.toString(),
                        user_name.toString(),user_password.toString())
                )
            }
            Toast.makeText(context,"Successful Register",Toast.LENGTH_SHORT).show()
            Toast.makeText(context,first_name,Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_fragmentSignup_to_fragmentLogin)
        }
    }

}