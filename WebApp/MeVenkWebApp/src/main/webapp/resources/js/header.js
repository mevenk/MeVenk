/**
 *
 */

function startRunningTime() {
	var today = new Date();
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	m = formatTime(m);
	s = formatTime(s);
	$('#runningClock').html(h + ":" + m + ":" + s);
	setTimeout(startRunningTime, 1000);
}

// add zero in front of numbers < 10
function formatTime(i) {
	if (i < 10) {
		i = "0" + i
	}
	return i;
}

$(document).ready(documentReadyHeaderFunctions());

function documentReadyHeaderFunctions() {
	startRunningTime();
}
