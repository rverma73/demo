package com.demo.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

import com.demo.demo.model.data
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus


private var datas = mutableListOf(

    // sample data
    data(1, "CryptoPunks", 100),
    data(2, "Sneaky Vampire Syndicate", 36),
    data(3, "The Sevens (Official)", 78),
    data(4, "Art Blocks Curated", 89),
    data(5, "Pudgy Penguins", 90),
)

@RestController
class DemoController {

    //get Request........
    @GetMapping("/")
    fun homePage()=datas


    // post Request.........
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun postData(@RequestBody data:data):data{
        val maxId=datas.map{it.id}.maxOrNull()?:0
        val nextId = maxId+1
        val newData = data(id=nextId,name=data.name,price=data.price)
        datas.add(newData)
        return newData
    }


    @GetMapping("/{id}")
    fun getDataById(@PathVariable id:Int):data?{
        return datas.firstOrNull{it.id==id}
    }


    @DeleteMapping("/delete/{id}")
    fun deleteDataById(@PathVariable id:Int):String{
//        datas.removeAt(id-1)
        return "Deleted!"
    }
}