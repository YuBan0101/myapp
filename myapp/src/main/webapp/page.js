function showContent(counturl,url,type,key){
          $(document).ready(function(){ 
        	  var currentPage = 1;
        	  $("input[name='currentPage']").val(parseInt(1));
        	  //获取count
              		$.ajax({
              			type:'GET',
              			contentType:"allication/json",
                		dataType: "json",
                		async:false,
                		data:{"type":type,"key":key},
                		url:counturl,
                		success: function(data){
                			
                			$(".float-right input[name='pageCount']").val(data.pageCount);
                			if(data.pageCount == 0){
                  				$(".float-right span").text("0-"+data.pageCount+"/"+data.pageCount);
                  			}else if(data.pageCount < 15){
                				$(".float-right span").text("1-"+data.pageCount+"/"+data.pageCount);
                			}else{
                				$(".float-right span").text((parseInt((currentPage-1)*15)+1)+"-"+currentPage*15+"/"+data.pageCount);
                			}
                		}
              		});
        	  		//初始化显示页面
        	  		$.ajax({
        	  			type:'GET',
        	  			contentType:"allication/json",
        	  			dataType: "json",
        	  			url:url,
        	  			async:false,
        	  			data:{'currentPage':currentPage,'pageSize':15,"type":type,"key":key},
        	  			success: function(data){
        	  				showProductTable(data,currentPage);
        	  			}
        	  		});
              		var pageCount = $(".float-right input[name='pageCount']").val();
						$(".float-right div button i[class='fa fa-chevron-left']").parent().attr("disabled",true);
              		if(pageCount < 15){
						$(".float-right div button i[class='fa fa-chevron-right']").parent().attr("disabled",true);
              		}else{
              			$(".float-right div button i[class='fa fa-chevron-right']").parent().attr("disabled",false);
              		}
              		
              	//向前翻页-------
					$(".float-right div button i[class='fa fa-chevron-left']").parent().off().on("click",function(){
							$("input[name='currentPage']").val($("input[name='currentPage']").val()-1);
							if($("input[name='currentPage']").val() == 1){
							$(".float-right div button i[class='fa fa-chevron-left']").parent().attr("disabled",true);
							$(".float-right div button i[class='fa fa-chevron-right']").parent().attr("disabled",false);
							}
							$(".float-right div button i[class='fa fa-chevron-right']").parent().attr("disabled",false);
						$.ajax({
	              			type:'GET',
	              			contentType:"allication/json",
	                		dataType: "json",
	                		url:url,
	                		data:{'currentPage':$("input[name='currentPage']").val(),'pageSize':15,"type":type,"key":key},
	                		success: function(data){
	                			currentPage = $("input[name='currentPage']").val();
	                			if(data.pageCount < 15){
	                				$(".float-right span").text("1-"+pageCount+"/"+pageCount);
	                			}else if(currentPage > parseInt(pageCount/15)){
	                				$(".float-right span").text((parseInt((currentPage-1)*15)+1)+"-"+pageCount+"/"+pageCount);
	                			}else{
	                			$(".float-right span").text((parseInt((currentPage-1)*15)+1)+"-"+currentPage*15+"/"+pageCount);
	                			}
	                			showProductTable(data,currentPage);
	                		}
	              		});
						
					}) ;  
					
              	//像后翻页
					$(".float-right div button i[class='fa fa-chevron-right']").parent().off().on("click",function(){
							$("input[name='currentPage']").val(parseInt($("input[name='currentPage']").val())+1);
							if($("input[name='currentPage']").val() > pageCount/15){
								$(".float-right div button i[class='fa fa-chevron-right']").parent().attr("disabled",true);
								$(".float-right div button i[class='fa fa-chevron-left']").parent().attr("disabled",false);
							}
							$(".float-right div button i[class='fa fa-chevron-left']").parent().attr("disabled",false);
						$.ajax({
	              			type:'GET',
	              			contentType:"allication/json",
	                		dataType: "json",
	                		url:url,
	                		data:{'currentPage':$("input[name='currentPage']").val(),'pageSize':15,"type":type,"key":key},
	                		success: function(data){
	                			currentPage = $("input[name='currentPage']").val();
	                			if(data.pageCount < 15){
	                				$(".float-right span").text("1-"+pageCount+"/"+pageCount);
	                			}else if(currentPage > parseInt(pageCount/15)){
	                				$(".float-right span").text((parseInt((currentPage-1)*15)+1)+"-"+pageCount+"/"+pageCount);
	                			}else{
	                			$(".float-right span").text((parseInt((currentPage-1)*15)+1)+"-"+currentPage*15+"/"+pageCount);
	                			}
	                			showProductTable(data,currentPage);
	                		}
	              		});
					});
					}); 
				}

function showProductTable(data,currentPage){
			$("#plist tbody").html('<tr><td class="mailbox-subject" style="text-align:center">ID</td>'+
                    '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品品牌</td>'+
                    '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品代号</td>'+
                    '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>库存余量</td>'+
                    '<td class="mailbox-attachment" style="text-align:center">产品类型</td>'+
                    '<td class="mailbox-subject" style="text-align:center">最后一次出库时间</td>'+
                    '<td class="mailbox-subject" style="text-align:center">最后一次入库时间</td></tr>'
    				);
			//害我不浅
			//$("input[name='currentPage']").val(data[0].currentPage);
			for(var i=0;i<data.length;i++){
				$("#plist tbody").append("<tr>"+
				'<td class="mailbox-subject" style="text-align:center">'+(i+parseInt((currentPage-1)*15)+1)+'</td>'+
				'<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].brand+'</a></td>'+
				'<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].model+'</a></td>'+
				'<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].count+'</a></td>'+
				'<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].type+'</a></td>'+
				'<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].lastDeliverDate+'</a></td>'+
				'<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].lastStoreDate+'</a></td>'+
				'</tr>');
			}
		}

