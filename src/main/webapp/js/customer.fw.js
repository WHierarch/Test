$(function(){

	loadCustomerServeType();
})



function loadCustomerServeType(){

	$.ajax({
		type:"post",
		url:ctx+"/customerServe/queryCustomerServesGroupByType",
		dataType:"json",
		success:function(data){
			if(data.code==200){
				// 基于准备好的dom，初始化echarts实例
				setBzt01(data.serveType,data.vals);
				setNdGeMgt(data.serveType,data.vals);
				setBzt02(data.vals);
			}else{
				alert("暂无数据");
			}
		}


	})
}

function setBzt01(serveType,vals){
	var myChart = echarts.init(document.getElementById('main'));
	// 指定图表的配置项和数据
	var option = {
			title : {
				text: 'CRM客户服务饼状图分析',
				subtext: '来自crm',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {
						show: true,
						type: ['pie', 'funnel']
					},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			legend: {
				orient: 'vertical',
				left: 'left',
				data: serveType
			},
			series : [
			          {
			        	  name: '来自crm',
			        	  type: 'pie',
			        	  radius : '55%',
			        	  center: ['50%', '60%'],
			        	  data:vals,
			        	  itemStyle: {
			        		  emphasis: {
			        			  shadowBlur: 10,
			        			  shadowOffsetX: 0,
			        			  shadowColor: 'rgba(0, 0, 0, 0.5)'
			        		  }
			        	  }
			          }
			          ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}


function setNdGeMgt(serveType,vals){
	var myChart = echarts.init(document.getElementById('main02'));
	var option = {
			title : {
				text: '南丁格尔玫瑰图',
				subtext: '纯属虚构',
				x:'center'
			},
			tooltip : {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				x : 'center',
				y : 'bottom',
				data:serveType
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataView : {show: true, readOnly: false},
					magicType : {
						show: true,
						type: ['pie', 'funnel']
					},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			calculable : true,
			series : [
			          {
			        	  name:'半径模式',
			        	  type:'pie',
			        	  radius : [20, 110],
			        	  center : ['25%', '50%'],
			        	  roseType : 'radius',
			        	  label: {
			        		  normal: {
			        			  show: false
			        		  },
			        		  emphasis: {
			        			  show: true
			        		  }
			        	  },
			        	  lableLine: {
			        		  normal: {
			        			  show: false
			        		  },
			        		  emphasis: {
			        			  show: true
			        		  }
			        	  },
			        	  data:vals
			          },
			          {
			        	  name:'面积模式',
			        	  type:'pie',
			        	  radius : [30, 110],
			        	  center : ['75%', '50%'],
			        	  roseType : 'area',
			        	  data:vals
			          }
			          ]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);

}


function setBzt02(vals){
	var myChart = echarts.init(document.getElementById('main03'));
	var option = {
		    backgroundColor: '#2c343c',

		    title: {
		        text: 'Customized Pie',
		        left: 'center',
		        top: 20,
		        textStyle: {
		            color: '#ccc'
		        }
		    },

		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },

		    visualMap: {
		        show: false,
		        min: 80,
		        max: 600,
		        inRange: {
		            colorLightness: [0, 1]
		        }
		    },
		    series : [
		        {
		            name:'访问来源',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '50%'],
		            data:vals.sort(function (a, b) { return a.value - b.value; }),
		            roseType: 'radius',
		            label: {
		                normal: {
		                    textStyle: {
		                        color: 'rgba(255, 255, 255, 0.3)'
		                    }
		                }
		            },
		            labelLine: {
		                normal: {
		                    lineStyle: {
		                        color: 'rgba(255, 255, 255, 0.3)'
		                    },
		                    smooth: 0.2,
		                    length: 10,
		                    length2: 20
		                }
		            },
		            itemStyle: {
		                normal: {
		                    color: '#c23531',
		                    shadowBlur: 200,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            },

		            animationType: 'scale',
		            animationEasing: 'elasticOut',
		            animationDelay: function (idx) {
		                return Math.random() * 200;
		            }
		        }
		    ]
		};
	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}

