<%-- 
    Document   : adminPage
    Created on : Aug 28, 2014, 5:43:20 PM
    Author     : Apostolis
--%>

<%@page import="db.AlgorithmDb"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/admin_header.jspf"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/AdminAlgorithmChoice.css" rel="stylesheet">
        <title>Enlistment Algorithm</title>
    </head>
    <body>
        <div class="bodyContainer">
            <h3>Algorithm Choice</h3>
            <hr id="hhr">
            <div class="leftContent">
                  <form action="AdminSetAlgoritm" method="post">
                <button class="btn btn-lg" type="submit" value="saw" name="saw" id="sawbutton"
                        <%if(AlgorithmDb.getAlgorithm().trim().equals("saw")){%>style="background-color: #36ba52;"<%}%>>Saw Algorithm</button>
                  </form>
                <hr class="hr3" style="margin-top: 65px;">
                 <p class="narrowP">
                    In decision theory, the weighted sum model (WSM)[1][2] is the best known and simplest multi-criteria decision analysis
                    (MCDA) / multi-criteria decision making method for evaluating a number of alternatives in terms of a number of decision
                    criteria. It is very important to state here that it is applicable only when all the data are expressed in exactly the
                    same unit. If this is not the case, then the final result is equivalent to "adding apples and oranges."
                 <h4><a href="http://en.wikipedia.org/wiki/Weighted_sum_model" target="blank">More...</a></h4>
                </p>
            </div>
            <div class="rightContent">
                <form action="AdminSetAlgoritm" method="post">
                    
                <button class="btn btn-lg" type="submit" value="topsis" name="topsis" id="topsisbutton"
                         <%if(AlgorithmDb.getAlgorithm().trim().equals("topsis")){%>style="background-color: #36ba52;"<%}%>>TOPSIS Algorithm</button>
                </form>
                <hr class="hr3">
                <p class="narrowP">
                    The Technique for Order of Preference by Similarity to Ideal Solution is
                    a multi-criteria decision analysis method TOPSIS is based on the concept that the chosen alternative should
                    have the shortest geometric distance from the positive ideal solution and the longest 
                    geometric distance from the negative ideal solution. It is a method of compensatory
                    aggregation that compares a set of alternatives by identifying weights for each criterion,
                    normalising scores for each criterion and calculating the geometric distance between
                    each alternative and the ideal alternative, which is the best score in each criterion.
                    An assumption of TOPSIS is that the criteria are monotonically increasing or decreasing.
                    Normalisation is usually required as the parameters or criteria are often of incongruous
                    dimensions in multi-criteria problems. Compensatory methods such as TOPSIS allow
                    trade-offs between criteria, where a poor result in one criterion can be negated by a
                    good result in another criterion. This provides a more realistic form
                    of modelling than non-compensatory methods, which include
                    or exclude alternative solutions based on hard cut-offs.
                <h4 style="margin-top: -30px; margin-left: 335px;"><a href="http://en.wikipedia.org/wiki/TOPSIS" target="blank">More...</a></h4>
                </p>
            </div>
    </body>
</html>
