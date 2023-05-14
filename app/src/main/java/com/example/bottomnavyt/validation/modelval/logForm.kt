package com.example.bottomnavyt.validation.modelval

import com.example.bottomnavyt.validation.ValidationResult

class logForm(
    private var password:String,
    private var email:String,


) {

    fun validatePassword(): ValidationResult {
        return if(password.isEmpty()){
            ValidationResult.Empty("password  is empty")
        }else if(password.length<6){
            ValidationResult.Invalid("password should have at least 10 characters")
        }else{
            ValidationResult.Valid
        }
    }





    fun validateEmail(): ValidationResult {
        return if(email.isEmpty()){
            ValidationResult.Empty("email  is empty")
        }else if(email.length<10){
            ValidationResult.Invalid("email should have at least  10 characters")
       }else{
            ValidationResult.Valid
        }
    }




}