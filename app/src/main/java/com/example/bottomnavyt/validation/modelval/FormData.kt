package com.example.bottomnavyt.validation.modelval

import com.example.bottomnavyt.validation.ValidationResult

class FormData(

    private var name:String,
    private var email:String,
    private var password:String,
    private var repassword:String,

) {


    fun validatename(): ValidationResult {
        return if(name.isEmpty()){
            ValidationResult.Empty("name  is empty")
        }else if(name.length<5){
            ValidationResult.Invalid("name should have 5 characters")
        }else if(name.length>10){
            ValidationResult.Invalid("name should have 10 characters")
        }else{
            ValidationResult.Valid
        }
    }




    fun validateEmail(): ValidationResult {
        return if(email.isEmpty()){
            ValidationResult.Empty("email  is empty")
        }else if(email.length<10){
            ValidationResult.Invalid("email should have 10 characters")
        }else if(email.length>10){
            ValidationResult.Invalid("email should have 10 characters")
        }else{
            ValidationResult.Valid
        }
    }

    fun validatePassword(): ValidationResult {
        return if(password.isEmpty()){
            ValidationResult.Empty("password  is empty")
        }else if(password.length<5){
            ValidationResult.Invalid("password should have 5 characters")
        }else if(password.length>10){
            ValidationResult.Invalid("password should have 10 characters")
        }else{
            ValidationResult.Valid
        }
    }

    fun validaterePassword(): ValidationResult {
        return if(repassword.isEmpty()){
            ValidationResult.Empty("password  is empty")
        }else if(repassword.length<5){
            ValidationResult.Invalid("password should have 5 characters")
        }else if(repassword.length>10){
            ValidationResult.Invalid("password should have 10 characters")
        }else if(repassword.length!==password.length){
            ValidationResult.Invalid("repassword and password mismatch")
        } else{
            ValidationResult.Valid
        }
    }

}