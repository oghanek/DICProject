<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="bootstrap-3.3.4-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<!-- div class="starter-template"-->
<form action="Process" method="get">
<div class="form-group">
		<div class="row">
	        <!-- div class="col-md-4">
	    		<img src="wiki-logo.png" class="img-responsive" alt=""/>
	    	</div-->
			<br><br><h3 align="left" ><font color="#003D4C">Wikipedia Search:</font></h3><br>

	        <div id="custom-search-input">
	        
	                <div class="input-group col-md-3">
	                   <input type="text" name="queryText" class="form-control input-lg" placeholder="Search Here" />
	                    
	                    <span class="input-group-btn">
	                        <button class="btn btn-info btn-lg" type="submit">
	                            <i class="glyphicon glyphicon-search"></i>
	                        </button>
	                    </span>
	                </div>
	         </div>
		</div>
	</div>
	</div>
</form>
</div>
</body>
</html>
