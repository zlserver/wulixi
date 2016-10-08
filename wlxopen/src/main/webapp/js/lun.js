

    //先获取box层
    var box=my$("box");
    //获取screen层
    var screen=box.children[0];
    //获取该层的宽度
    var imgWidth=screen.offsetWidth;
    //获取ul
    var ul=screen.children[0];
    //获取ul中所有的li
    var lis=ul.children;
    //获取ol
    var ol=screen.children[1];
    var pic=0;
    //创建li,根据ul中的所有的li的个数，循环遍历进行创建li并加入到ol中
    for(var i=0;i<lis.length;i++) {
        var li = document.createElement("li");
        ol.appendChild(li);
        li.innerHTML = (i + 1);
        li.setAttribute("index", i);
       // li.onclick onmousemove
        li.onclick = function () {
            for (var j = 0; j < ol.children.length; j++) {
                ol.children[j].removeAttribute("class");
            }
            this.className = "current";
            //移动图片
            //获取当前li的index属性的值和图片的宽度(screen的宽度）相乘
            var index = parseInt(this.getAttribute("index"));
            console.log(index);
            var target = -index * imgWidth;
            animate(ul, target);
            pic = parseInt(this.getAttribute("index"));
        };
    }
    //设置ol中第一个li标签引用类样式
    ol.children[0].className="current";
    //无缝显示，在ul中添加一个li
    ul.appendChild(ul.children[0].cloneNode(true));
    var setId=null;
    box.onmouseover=function(){
        my$("arr").style.display="block";
        clearInterval(setId);
    };
    box.onmouseout=function(){
        my$("arr").style.display="none";
        setId=setInterval(rightHandle,3000);
    };
    setId=setInterval(rightHandle,3000);

    //为焦点层中的左右按钮注册点击事件
    my$("right").onclick=rightHandle;
    function rightHandle(){
        if(pic==lis.length-1) {
            pic = 0;
            ul.style.left = 0 + "px";
        }
        pic++;
        var target=-pic*imgWidth;
        animate(ul,target);
        if(pic==lis.length-1){
            ol.children[0].className="current";
            ol.children[ol.children.length-1].removeAttribute("class");

        }else{
            for(var i=0;i<ol.children.length;i++){
                ol.children[i].removeAttribute("class");
            }
            ol.children[pic].className="current";
        }
    };
    my$("left").onclick=function(){
        if(pic==0){
            pic=lis.length-1;
            ul.style.left=-pic*imgWidth+"px";
        }
        pic--;
        var target=-pic*imgWidth;
        animate(ul,target);
        for(var i=0;i<ol.children.length;i++){
            ol.children[i].removeAttribute("class");
        }
        ol.children[pic].className="current";
    };
