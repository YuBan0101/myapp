function showTypeList(counturl,url){
          $(document).ready(function(){ 
        	  var currentPage = 1;
        	  $("input[name='typePage']").val(parseInt(1));
        	  //获取count
              		$.ajax({
              			type:'GET',
              			contentType:"allication/json",
                		dataType: "json",
                		async:false,
                		url:counturl,
                		success: function(data){
                			$(".card-tools input[name='typeCount']").val(data.pageCount);
                		}
              		});
            		
        	  		//初始化显示页面
        	  		$.ajax({
        	  			type:'GET',
        	  			contentType:"allication/json",
        	  			dataType: "json",
        	  			url:url,
        	  			async:false,
        	  			data:{'currentPage':currentPage,'pageSize':10},
        	  			success: function(data){
        	  				$("#typeUl").html("");
        	  				for(var o in data){
        	  						$("#typeUl").append('<li class="nav-item">'+
        	  			         '<a href="javascript:void(0)" class="nav-link">'+
        	  			         '<i class="fa fa-inbox"></i>   '+ data[o]+'</a></li>');
        	  					}
        	  				
        	  			}
        	  		});
              		var pageCount = $(".card-tools input[name='typeCount']").val();
						$("#typeleft").attr("disabled",true);
              		if(pageCount < 10){
						$("#typeright").attr("disabled",true);
              		}else{
              			$("#typeright").attr("disabled",false);
              		}
              		
              	//向前翻页-------
					$("#typeleft").off().on("click",function(){
							$("input[name='typePage']").val($("input[name='typePage']").val()-1);
							if($("input[name='typePage']").val() == 1){
							$("#typeleft").attr("disabled",true);
							$("#typeright").attr("disabled",false);
							}
							$("#typeright").attr("disabled",false);
						$.ajax({
	              			type:'GET',
	              			contentType:"allication/json",
	                		dataType: "json",
	                		async:false,
	                		url:url,
	                		data:{'currentPage':$("input[name='typePage']").val(),'pageSize':10},
	                		success: function(data){
	                			currentPage = $("input[name='typePage']").val();
	                			$("#typeUl").html("");
	                			for(var o in data){
	                					$("#typeUl").append('<li class="nav-item">'+
	                		         '<a href="javascript:void(0)" class="nav-link">'+
	                		         '<i class="fa fa-inbox"></i>   '+ data[o]+'</a></li>');
	                				}
	                		}
	              		});
					
					}) ;  
					
              	//像后翻页
					$("#typeright").off().on("click",function(){
							$("input[name='typePage']").val(parseInt($("input[name='typePage']").val())+1);
							if($("input[name='typePage']").val() > pageCount/10){
								$("#typeright").attr("disabled",true);
								$("#typeleft").attr("disabled",false);
							}
							$("#typeleft").attr("disabled",false);
						$.ajax({
	              			type:'GET',
	              			contentType:"allication/json",
	                		dataType: "json",
	                		url:url,
	                		async:false,
	                		data:{'currentPage':$("input[name='typePage']").val(),'pageSize':10},
	                		success: function(data){
	                			currentPage = $("input[name='typePage']").val();
	                			$("#typeUl").html("");
	                			for(var o in data){
	                					$("#typeUl").append('<li class="nav-item">'+
	                		         '<a href="javascript:void(0)" class="nav-link">'+
	                		         '<i class="fa fa-inbox"></i>   '+ data[o]+'</a></li>');
	                				}
	                		}
	              		});
					});
				}); 
			}


