package ru.ifmo.ctddev.gromov.vkphotosx

data class Data(var response: Response)
data class Response(var items: List<Item>)
data class Item(var id: Int, var text: String, var sizes: List<Size>)
data class Size(var url: String)