
<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	//alert( window.screen.width );
	var cpro_id = "u2188178"; //default 960
	var pageWidth = document.body.clientWidth;//window.screen.width;
	var adContainerWidth = pageWidth * (9 / 12) * (11 / 12);
	//alert(adContainerWidth); 

	if (adContainerWidth < 600) {
		cpro_id = "u2188200"; //468
		//alert('468');
	}
		
	else if (adContainerWidth < 960) {
		cpro_id = "u2202318"; //600
		//alert('600');
	}

</script>
<script src="http://cpro.baidustatic.com/cpro/ui/c.js"
	type="text/javascript"></script>

