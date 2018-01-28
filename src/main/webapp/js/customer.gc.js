$(function(){

	loadCustomersLevel();
})



function loadCustomersLevel(){
	
	$.ajax({
		type:"post",
		url:ctx+"/customer/queryCustomersLevel",
		dataType:"json",
		success:function(data){
			if(data.code==200){
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document.getElementById('main'));
				// 指定图表的配置项和数据
				var option = {
						title: {
							text: '客户构成分析柱状图'
						},
						tooltip: {},
						legend: {
							data:['客户级别数量']
						},
						xAxis: {
							data: data.levels
						},
						yAxis: {},
						series: [{
							name: '客户级别数量',
							type: 'bar',
							data: data.totals
						}]
				};
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			}else{
				alert("暂无数据");
			}
		}
		
		
	})

	

}


