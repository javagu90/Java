var TIME = 120; // in seconds
var countTimer = TIME;
var processTimer;
var timer_is_on = 0;
var redirectPage = ""; //#{request.contextPath}/logout.xhtml";
//var countDownDiv = "dialog-countdown";
//var txtCountDown = document.getElementById(countDownDiv);

function getWidgetVarById(id) {
	var widgets = PrimeFaces.widgets;
	for (var propertyName in widgets) {
	var widget = widgets[propertyName];
	//if (widget && widget.id === id) {
	if (id == propertyName) {
	return widget;
	}
	}
	return null;
	}
 
 
  function startIdleMonitor() {
    countTimer = TIME;
    var txtCountDown = document.getElementById('dialog-countdown');
    txtCountDown.innerHTML = countTimer;
    getWidgetVarById('timeoutDialog').show();
  }
  
  function timedCount() {
	var txtCountDown = document.getElementById('dialog-countdown');
    txtCountDown.innerHTML = countTimer;
    if (countTimer == 0) {
        stopCount();
        window.location.href = redirectPage;
        return;
    }
    countTimer = countTimer - 1;
    processTimer = setTimeout("timedCount()", 1000);
  }
  
  function doTimer() {
    if (!timer_is_on) {
        timer_is_on = 1;
        timedCount();
    }
  }
  function stopCount() {
    clearTimeout(processTimer);
    timer_is_on = 0;
    keepAlive();
  }

  
      /*
var chartValues;
var chartLabels;

function handleGetDataValues(str) {	
	s1 = str;
}

function getChartValues(chartName) {
	return ChartData.getArray(chartName, handleGetDataValues);
}

function handleGetDataLabels(str) {	
	ticks = str;
}

function getChartLabels(chartName) {
	return ChartData.getLabels(chartName, handleGetDataLabels);
}

function drawChart() {
	
}

function plotChart(chartName) {
	dwr.engine.beginBatch();
	var s1 = JSON.parse('${ventaBean.array}');
    var ticks = JSON.parse('${ventaBean.labels}');  
	if (s1.length > 0) {
		$.jqplot.config.enablePlugins = true;
	    plot1 = $.jqplot('chart1', [s1], {
	        // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
	        animate: !$.jqplot.use_excanvas,
	        seriesDefaults:{
	            renderer:$.jqplot.BarRenderer,
	            pointLabels: { show: true }
	        },
	        axes: {
	            xaxis: {
	                renderer: $.jqplot.CategoryAxisRenderer,
	                ticks: ticks
	            }
	        },
	        highlighter: { show: false }
	    });
	
	    $('#chart1').bind('jqplotDataClick', 
	        function (ev, seriesIndex, pointIndex, data) {
	            $('#info1').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data);
	        }
	    );
	}
	dwr.engine.endBatch();

}
      */