<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ThreadSafeServer App</title>
<link rel='stylesheet' type='text/css'
	href='/ThreadSafeServerApp/resources/styles/stylesheet.css' />

<script src="/ThreadSafeServerApp/resources/js/jquery-1.11.2.js"></script>
<script>
	$(document).ready(function topFive() {
		$.ajax({
			url : 'topFive',
			async : 'true',
			success : function(data) {
				$("#panes31").text(data.size);
				$("#panes32").text(data.headOftheQueue);
				$("#panes33").text(data.secondElement);
				$("#panes34").text(data.thirdElement);
				$("#panes35").text(data.fourthElement);
				$("#panes36").text(data.fifthElement);
			}
		});
	});

	$(document).ready(function printHead() {

		$.ajax({
			url : 'printHeader',
			async : 'true',
			success : function(data) {
				$("#panes21").text(data.size);
				$("#panes22").text(data.headOftheQueue);
			}
		});
	});
	$(document).ready(function() {
		$("#button1").click(function retrieveData() {
			$.ajax({
				url : 'retrieveData',
				async : 'true',
				success : function(data) {
					$("#panes31").text(data.size);
					$("#panes32").text(data.headOftheQueue);
					$("#panes33").text(data.secondElement);
					$("#panes34").text(data.thirdElement);
					$("#panes35").text(data.fourthElement);
					$("#panes36").text(data.fifthElement);
					$("#panes21").text(data.size);
					$("#panes22").text(data.headOftheQueue);
					$("#panes11").text(data.dataString);
				}
			});

		});

		$("#button2").click(function generateUUID() {
			$.ajax({
				url : 'printHeader',
				async : 'true',
				success : function(data) {
					$("#panes21").text(data.size);
					$("#panes22").text(data.headOftheQueue);
				}
			});

		});

		$("#button3").click(function getData() {
			$.ajax({
				url : 'topFive',
				async : 'true',
				success : function(data) {
					$("#panes31").text(data.size);
					$("#panes32").text(data.headOftheQueue);
					$("#panes33").text(data.secondElement);
					$("#panes34").text(data.thirdElement);
					$("#panes35").text(data.fourthElement);
					$("#panes36").text(data.fifthElement);
				}
			});
		});
	});
</script>
</head>
<body>
	<h3 align="center">Thread Safe Server App</h3>
	<div class="panes" id="panesone">
		<h4>Data received at client</h4>
		<table>
			<tr>
				<td><input type="button" value="Get Data" class="button"
					id="button1" /></td>
			</tr>
			<tr>
				<td><span>Data received :</span></td>
			</tr>
			<tr>
				<td><span id="panes11"></span></td>
			</tr>
		</table>
	</div>
	<div class="panes" id="panestwo">
		<h4>Head of the queue</h4>
		<table>

			<tr>
				<td><input type="button" value="Refresh" class="button"
					id="button2" /></td>
			</tr>
			<tr>
				<td><span>Size of the queue :</span></td>
			</tr>
			<tr>
				<td><span id="panes21"></span></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span> First element of the queue :</span></td>
			</tr>
			<tr>
				<td><span id="panes22"></span></td>
			</tr>
		</table>
	</div>
	<div class="panes" id="panesthree">
		<h4>Data in Queue</h4>
		<table>

			<tr>
				<td><input type="button" value="Refresh" class="button"
					id="button3" /></td>
			</tr>
			<tr>
				<td><span>Size of the queue :</span></td>
			</tr>
			<tr>
				<td><span id="panes31"></span></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span> First element of the queue :</span></td>
			</tr>
			<tr>
				<td><span id="panes32"></span></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span> Second element of the queue :</span></td>
			</tr>
			<tr>
				<td><span id="panes33"></span></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span> Third element of the queue :</span></td>
			</tr>
			<tr>
				<td><span id="panes34"></span></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span> Fourth element of the queue :</span></td>
			</tr>
			<tr>
				<td><span id="panes35"></span></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><span> Fifth element of the queue :</span></td>
			</tr>
			<tr>
				<td><span id="panes36"></span></td>
			</tr>
		</table>
	</div>
</body>
</html>