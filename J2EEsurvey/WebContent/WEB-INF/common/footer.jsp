<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script language=JavaScript>
	var timerID = null;
	var timerRunning = false;
	function stopclock() {
		if (timerRunning)
			clearTimeout(timerID);
		timerRunning = false;
	}
	function startclock() {
		stopclock();
		showtime();
	}
	function showtime() {
		var now = new Date();
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds()
		var timeValue = now.getFullYear() + "年" + (now.getMonth() + 1) + "月"
				+ now.getDate() + "日" + ((hours >= 12) ? "下午 " : "上午 ")
		timeValue += ((hours > 12) ? hours - 12 : hours)
		timeValue += ((minutes < 10) ? ":0" : ":") + minutes
		timeValue += ((seconds < 10) ? ":0" : ":") + seconds
		$("#thetime").text(timeValue);
		timerID = setTimeout("showtime()", 1000);
		timerRunning = true;
	}
</script>
<body onload="startclock()">
	<div class="footer base-container">
		<p style="float: left;">&copy;2014 PAS Co., Ltd</p>
		<form name=clock>
			<p id="thetime" style="float: right;"></p>
		</form>
	</div>
</body>
