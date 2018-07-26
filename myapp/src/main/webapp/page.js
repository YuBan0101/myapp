function showContent(pageindex,counturl,url,type,key){
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
        	  				showTableChoose(pageindex,data,currentPage);
        	  				
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
	                		async:false,
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
	                			showTableChoose(pageindex,data,currentPage);
	                			hideDiv();
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
	                		async:false,
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
	                			showTableChoose(pageindex,data,currentPage);
	                			hideDiv();
	                		}
	              		});
						
					});
					
					hideDiv();
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
				'<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].count+' 台/米</a></td>'+
				'<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].type+'</a></td>'+
				'<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].lastDeliverDate+'</a></td>'+
				'<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].lastStoreDate+'</a></td>'+
				'</tr>');
			}
		}

function showShortSupplyProductTable(data,currentPage){

	$("#plist tbody").html('<tr><td class="mailbox-subject" style="text-align:center">ID</td>'+
    '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品品牌</td>'+
    '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品代号</td>'+
    '<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>库存余量</td>'+
    '<td class="mailbox-attachment" style="text-align:center">产品类型</td>'+
    '<td class="mailbox-subject" style="text-align:center">最后一次出库时间</td>'+
    '<td class="mailbox-subject" style="text-align:center">最后一次入库时间</td></tr>'
	);
	for(var i= 0;i<data.length;i++){
		if(data[i].count == 0){
			$("#plist tbody").append("<tr>"+
    				   '<td class="mailbox-subject" style="text-align:center">'+(i+parseInt((currentPage-1)*15)+1)+'</td>'+
    				   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].brand+'</a></td>'+
    				   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].model+'</a></td>'+
    				   '<td class="mailbox-name" style="text-align:center;"><a href="#" style="color:red;font-weight:bold">库存已空！</a></td>'+
    				   '<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].type+'</a></td>'+
    				   '<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].lastDeliverDate+'</a></td>'+
    				   '<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].lastStoreDate+'</a></td>'+
    				   '</tr>');
		}else {
				$("#plist tbody").append("<tr>"+
				'<td class="mailbox-subject" style="text-align:center">'+(i+parseInt((currentPage-1)*15)+1)+'</td>'+
				'<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].brand+'</a></td>'+
				'<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].model+'</a></td>'+
				'<td class="mailbox-name" style="text-align:center;"><a href="#" style="color:red;font-weight:bold">剩余   '+data[i].count+' 个</a></td>'+
				'<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].type+'</a></td>'+
				'<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].lastDeliverDate+'</a></td>'+
				'<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].lastStoreDate+'</a></td>'+
				'</tr>');
		}
	}
}

function showStoreTable(data,currentPage){
	$("#plist tbody").html('<tr><td class="mailbox-subject" style="text-align:center">ID</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品品牌</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品代号</td>'+
            '<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>入库数量</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>入库单价</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>入库小计</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>指导售价</td>'+
            '<td class="mailbox-attachment" style="text-align:center">产品类型</td>'+                   	           
            '<td class="mailbox-subject" style="text-align:center">入库时间</td></tr>'
			);
			for(var i= 0;i<data.length;i++){
			   $("#plist tbody").append("<tr>"+
			   '<td class="mailbox-subject" style="text-align:center">'+(i+parseInt((currentPage-1)*15)+1)+'</td>'+
			   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].brand+'</a></td>'+
			   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].model+'</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].count+' 台/米</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].price+' 元</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].count*data[i].price+' 元</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].sales+' 元</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].type+'</a></td>'+
			   '<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].dateString+'</a></td>'+
			   '</tr>');
            			}
}

function showSalesTable(data,currentPage){
	$("#plist tbody").html('<tr><td class="mailbox-subject" style="text-align:center">ID</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品品牌</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品代号</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>产品类型</td>'+
            '<td class="mailbox-subject" style="text-align:center">售价</td></tr>'
			);
			for(var i= 0;i<data.length;i++){
			   $("#plist tbody").append("<tr>"+
			   '<td class="mailbox-subject" style="text-align:center">'+(i+parseInt((currentPage-1)*15)+1)+'</td>'+
			   '<td class="mailbox-subject" style="text-align:center">'+data[i].brand+'</td>'+
			   '<td class="mailbox-subject" style="text-align:center">'+data[i].model+'</td>'+
			   '<td class="mailbox-name" style="text-align:center">'+data[i].type+'</td>'+
			   '<td class="mailbox-name" style="text-align:center">'+data[i].sales+' 元</td>'+
			   '</tr>');
            }
}

function showPriceTable(data,currentPage){
	$("#plist tbody").html('<tr><td class="mailbox-subject" style="text-align:center">ID</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品品牌</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品代号</td>'+
            '<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>最新进价</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>更新时间</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>上次进价</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>上次进货时间</td>'+
            '<td class="mailbox-subject" style="text-align:center">两次差价</td>'+
            '<td class="mailbox-subject" style="text-align:center">指导售价</td></tr>'
			);
			for(var i= 0;i<data.length;i++){
			   $("#plist tbody").append("<tr>"+
			   '<td class="mailbox-subject" style="text-align:center">'+(i+parseInt((currentPage-1)*15)+1)+'</td>'+
			   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].brand+'</a></td>'+
			   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].model+'</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].price+' 元</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].dateNow+'</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].oldPrice+' 元</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].datePass+'</a></td>'+
			   '<td class="mailbox-date" style="text-align:center"><a href="#">'+(data[i].price-data[i].oldPrice)+' 元</a></td>'+
			   '<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].sales+' 元</a></td>'+
			   '</tr>');
	       
		}
}

function showDeliverTable(data,currentPage){
	$("#plist tbody").html('<tr><td class="mailbox-subject" style="text-align:center">ID</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品品牌</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>产品代号</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>出售价格</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>出售个数（米）</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>金额小计</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>净赚（利润）</td>'+
            '<td class="mailbox-attachment" style="text-align:center">产品类型</td>'+                   	           
            '<td class="mailbox-subject" style="text-align:center">出库时间</td></tr>'
			);
			for(var i= 0;i<data.length;i++){
			   $("#plist tbody").append("<tr>"+
			   '<td class="mailbox-subject" style="text-align:center">'+(i+parseInt((currentPage-1)*15)+1)+'</td>'+
			   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].brand+'</a></td>'+
			   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].model+'</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].price+' 元</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].count+' 台/米</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].count*data[i].price+' 元</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+(data[i].price-data[i].sprice).toFixed(2)*data[i].count+' 元</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].type+'</a></td>'+
			   '<td class="mailbox-date" style="text-align:center"><a href="#">'+data[i].dateString+'</a></td>'+
			   '</tr>');
	       
		}
}

function showAccountinfoTable(data,currentPage){
	$("#plist tbody").html('<tr ><td class="mailbox-subject" style="text-align:center"><i class="fa fa-star-o"></i></td><td class="mailbox-subject" style="text-align:center">ID</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>简要信息</td>'+
            '<td class="mailbox-subject" style="text-align:center"><a href="#"></a>赊帐金额</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>联系电话</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>日期</td>'+
			'<td class="mailbox-subject" style="text-align:center;"><a href="#"></a>备注信息</td>'+
            '<td class="mailbox-subject" style="text-align:center">状态</td>'+
			'</tr>'
			);
			for(var i= 0;i<data.length;i++){
			   $("#plist tbody").append('<tr name="d1"><td class="mailbox-subject" style="text-align:center"><input type="checkbox" name="aid" value='+data[i].id+'></td></div>'+
			   '<td class="mailbox-subject" style="text-align:center">'+(i+parseInt((currentPage-1)*15)+1)+'</td>'+
			   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].content.substring(0,10)+'</a></td>'+
			   
			   '<td class="mailbox-subject" style="text-align:center"><a href="#">'+data[i].money+' 元</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].phone+'</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#" >'+data[i].dateString+'</a></td>'+
			   '<td class="mailbox-name" style="text-align:center"><a href="#">'+data[i].remark+'</a></td>'+
			   '<td class="mailbox-date" style="text-align:center" name="status"><a href="#">'+data[i].statusString+'</a></td>'+
               '</tr><tr name="d2"><td></td><td colspan ="8" class="mailbox-subject">'+data[i].content+'</td></tr>');
	       
		}
}

function hideDiv(){
		
		$("#plist tbody tr[name='d2']").hide();
		$("#plist tbody tr[name='d1']").on("click",function(){
			if($(this).find("input").is(":checked") != true){
				$("#plist tbody tr input[type='checkbox']").prop("checked",false);
				$("#plist tbody tr[name='d2']").hide();
				$(this).find("input").prop("checked",true);
				$(this).next().show();
				
			}else{
				$(this).find("input").prop("checked",false);
				$(this).next().hide();
			}
			
		})
	}

function showTableChoose(pageindex,data,currentPage){
	switch(pageindex){
		case "product" : showProductTable(data,currentPage); break;
		case "shortSupplyProduct" :showShortSupplyProductTable (data,currentPage); break;
		case "store" : showStoreTable(data,currentPage); break;
		case "price" : showPriceTable(data,currentPage); break;
		case "deliver" : showDeliverTable(data,currentPage); break;
		case "account" : showAccountinfoTable(data,currentPage); break;
		case "sales" : showSalesTable(data,currentPage); break;
		default :break;
		}
}

