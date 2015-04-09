/**
 * 调查结果分析js
 */
$(document).ready(function() {
	// 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
	require.config({
		paths : {
			echarts : './js/echarts/echarts' // echarts.js的路径
		}
	});

	/* 图表参数数据定义 */
	var titleText = "题目名称";
	var subTitleText = "题目名称";
	var legendData = [ "选项1", "选项2", "选项3", "选项4", "选项5" ];
	var seriesData = [ {
		value : 335,
		name : '选项1'
	}, {
		value : 310,
		name : '选项2'
	}, {
		value : 234,
		name : '选项3'
	}, {
		value : 135,
		name : '选项4'
	}, {
		value : 1548,
		name : '选项5'
	} ];
	var chartOption=null;

	/* 柱状图Option */
	var setOption = function(chartType) {
		//柱状图
		if(chartType==1){
			chartOption = {
					title : {
						text : titleText,
						x : 'center'
					},
					tooltip : {
						trigger : 'axis',
						formatter : "题目:{a} <br/>{b} : {c}"
					},
					toolbox : {
						show : true,
						feature : {
							mark : {
								show : true
							},
							magicType : {
								show : true,
								type : [ 'line', 'bar' ]
							},
							saveAsImage : {
								show : true
							}
						}
					},
					calculable : true,
					xAxis : [ {
						type : 'value',
						boundaryGap : [ 0, 0.01 ]
					} ],
					yAxis : [ {
						type : 'category',
						data : legendData
					} ],
					series : [ {
						type : 'bar',
						data : seriesData
					} ]
				};
		}
		//饼图
		if(chartType==0){
			chartOption= {
					title : {
						text : titleText,
						subtext : subTitleText,
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "题目:{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						x : 'left',
						data : legendData
					},
					toolbox : {
						show : true,
						feature : {
							saveAsImage : {
								show : true
							}
						}
					},
					calculable : false,
					series : [ {
						name : titleText,
						type : 'pie',
						radius : '55%',
						center : [ '50%', 225 ],
						data : seriesData
					} ]
				};
		}
	};

	/* 渲染ECharts图表 */
	function DrawEChart(ec) {
		// 图表渲染的容器对象
		var chartContainer = document.getElementById("main");
		// 加载图表
		var myChart = ec.init(chartContainer);
		myChart.setOption(chartOption);
	}

	/* 绘图函数 */
	var draw = function() {
		// Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
		require([ 'echarts', 'echarts/chart/bar','echarts/chart/pie' ],
		// 回调函数
		DrawEChart);
	};

	/**
	 * Ajax请求获取动态数据
	 */
	var getAnaData = function(formid) {
		$.ajax({
			cache : true,
			type : "POST",
			url : "SurveyAction_doAnalyze",
			data : $("#" + formid).serialize(),
			async : false,
			error : function(request) {
				alert("请求错误，请重试");
			},
			success : function(data) {
				var status = data.status;
				if (status != null && status == "ok") {
					// 成功
					titleText = data.title;
					legendData = data.legendData;
					seriesData = data.seriesData;
				} else {
					var info = data.info;
					if (null != info) {
						alert(info);
					} else {
						alert("请求数据出错，请重试");
					}
				}
			}
		});

	};

	/* 阻断表单提交 */
	$("form[name='anaForm']").submit(function() {
		var questionType=event.target.qtype.value;
		var questionId=event.target.id;
		getAnaData(questionId);
		//得到图表类型
		if(questionType<6){
			var chartType=$("#"+event.target.id+" select").val();
			setOption(chartType);
			draw();
		}else{
			//矩阵式
		}
		
		$.blockUI({
			message : $('#main'),
			css : {
				top : '50%',
				left : '50%',
				textAlign : 'left',
				marginLeft : '-400px',
				marginTop : '-200px',
				width : '800px',
				height : '400px',
			}
		});
		$('.blockOverlay').attr('title', '单击关闭').click($.unblockUI);
		return false;
	});
	/* end阻断表单提交 */
});