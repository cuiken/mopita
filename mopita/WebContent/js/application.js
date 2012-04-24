// Place your application-specific JavaScript functions and classes here
// This file is automatically included by javascript_include_tag :defaults
var imglink = null;
var imgnowindex = 0;
var noset = true;
var request = false;

function playimgnext() {
    if (imglink == null) {
        imglink = new Array();
        var j=0;
        var imgs = document.getElementById("divallimg").childNodes;
        for (var i = 0; i < imgs.length; i++) {
        	if(imgs[i].nodeType==1){
        		
        		imglink[j++] = imgs.item(i).innerHTML;
        	}
        }
    }
    if (imgnowindex < imglink.length - 1) {
        imgnowindex++;
        document.getElementById("themeimg").src = imglink[imgnowindex];
        document.getElementById("divimgsize").innerHTML = "Preview(" + imglink.length + "-" + (imgnowindex + 1) + ")";
       document.getElementById("div_pop_form").style.display="block";
       document.getElementById("div_shade").style.display="block";
    }
    if (imgnowindex == imglink.length - 1) {
        document.getElementById("divimgpageplaynext").style.display = "none";
    }
    if (imgnowindex > 0) {
        document.getElementById("divimgpageplayprev").style.display = "block";
    }
}
function playimgprev() {
    if (imglink == null) {
        imglink = new Array();
        var imgs = document.getElementById("divallimg").childNodes;
        for (var i = 0; i < imgs.length; i++) {
            imglink[i] = imgs.item(i).innerHTML;
        }
    }
    if (imgnowindex >0) {
        imgnowindex--;
        document.getElementById("themeimg").src = imglink[imgnowindex];
        document.getElementById("divimgsize").innerHTML = "Preview(" + imglink.length + "-" + (imgnowindex + 1) + ")";

    }
    if (imgnowindex < imglink.length - 1) {
        document.getElementById("divimgpageplaynext").style.display = "block";
    }
    if (imgnowindex == 0) {
        document.getElementById("divimgpageplayprev").style.display = "none";
    }
}
function documentread() {
    creatrequest();

}
function creatrequest() {

    try {
        request = new XMLHttpRequest();

    } catch (trymin) {
        try {
            request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (tryothermic) {

            try {
                request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (no) {
                request = false;
            }

        }

    }

}
function read() {

    if (request.readyState == 4) {
        if (request.status == 200) {
            if (request.responseText == "1") {
                alert("评星级成功！");
                noset = false;
            } else {
                if (request.responseText == "2") {
                    alert("评星级成功！");
                    noset = false;
                } else {
                    alert(request.responseText);
                }
            }
        }

    }
}
function addvote(type) {

    if (noset) {
        creatrequest();
        if (request) {

            request.open("Post", "AddVote.aspx?type=" + type + "&id=" + document.getElementById("divthemeid").innerHTML, true);
            request.send(null);
            request.onreadystatechange = read;

        } else {
        alert("非常抱歉！您的浏览器不支持评星级！");
        }
    } else {
    alert("您已经评过了！");
    }
}
function imgload()
{
  document.getElementById("div_pop_form").style.display="none";
  document.getElementById("div_shade").style.display="none";
}