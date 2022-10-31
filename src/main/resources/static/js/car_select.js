//品牌事件冒泡
document.querySelector(".car-name").onclick = function(event){
    var element = event.target;
    var typeName =  element.nodeName;//标签名称
    var type =  element.type;//标签类型
//    console.log(typeName);
//    console.log(type);
//     console.log(element);
    var workArray = document.querySelector(".car-name").children;
    var temp = workArray[1].children[0].className
    // console.log(temp)
    $(".all").attr("class"," ")
    // for(var i = 0;i<workArray.length;i++){
    //  workArray[i].children[0].className = " "
    // }
    element.className = "active"
}