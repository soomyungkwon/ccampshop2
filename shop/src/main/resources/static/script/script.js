$(function(){
    
    /*group_menu*/
    $(".menu").mouseover(function(){
        $(this).find(".submenu").stop().slideDown();
    }).mouseout(function(){
        $(this).find(".submenu").stop().slideUp();
    });
    
    
    /*날자와 시간 넣기*/
    
    
    
    
    /*part_menu 마우스 벗어나면 서브메뉴 사라짐*/
    /*
    $(".menu li").mouseover(function(){
        $(this).find(".submenu").stop().slideDown();
    }).mouseout(function(){
        $(this).find(".submenu").stop().slideUp();
    });
    */
    /*part_menu 마우스 벗어나도 서브메뉴 사라지지 않고, 다른 메뉴 클릭시에만 사라짐--수정요*/
    
    /*$(".menu li").mouseover(function(){
        $(this).find(".submenu").stop().slideDown();
    });
    
    $(".menu li").click(function(){
        $(this).find(".submenu").stop().slideUp();
    });*/
    
    
    /*full_menu*/
    
    /*$(".menu").mouseover(function(){
        $(this).find(".submenu").stop().show();
    }).mouseout(function(){
        $(this).find(".submenu").stop().hide();
    });*/
    
    
    /*left_sliding*/
    
    var currentIndex=0;
    
    setInterval(function(){
        if(currentIndex<3){
            currentIndex++;
        }else{
            currentIndex=0;
        }
        
        var sliderPosition=currentIndex*(-1200)+"px";
        
        $(".sliderList").animate({left:sliderPosition},400);
        
        console.log(currentIndex);
    },3000);
    
    
    
    /*top_sliding*/
    
    /*var currentIndex=0;
    
    setInterval(function(){
        if(currentIndex<3){
            currentIndex++;
        }else{
            currentIndex=0;
        }
        
        var sliderPosition=currentIndex*(-300)+"px";
        
        $(".sliderList").animate({top:sliderPosition},400);
        
        console.log(currentIndex);
    },3000);*/

    /*fadeIn-fadeOut*/
    /*$(".sliderList").children("div:gt(0)").hide();
    
    var currentIndex=0;
    setInterval(function(){
        var next=(currentIndex+1)%3;
        $(".sliderList").find("div").eq(currentIndex).fadeOut();
        $(".sliderList").find("div").eq(next).fadeIn();
        currentIndex=next;
    },3000);*/
    
    
    
    
    /*tab*/
    $(".tabmenu>li>a").click(function(){
        $(this).parent().addClass("active").siblings().removeClass("active");
    });
    
    /*layer popup*/
    $(".notice li:nth-child(1)").click(function(){
        $("#modal").addClass("active");
    });
    
    $(".close").click(function(){
        $("#modal").removeClass("active");
    });
    
    /*layer popup2*/
    $(".notice li:nth-child(5)").click(function(){
        $("#modal2").addClass("active");
    });
    
    $(".close2").click(function(){
        $("#modal2").removeClass("active");
    });
    
    
});