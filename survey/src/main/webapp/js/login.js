
$(document).ready(function(){
    $("#loginform").validation();

    //.登录
    $("#loginform").submit(function(event){
      if ($(this).valid()==false){
        return false;
      }else{
    	  $("#button").text("正在登录……");
    	  $("#button").attr('disabled', true);
    	  return true;
      }
    });
    
    $("#regbutton").click(function() {
    	window.location.href ="RegAction_toRegPage";
	});
});