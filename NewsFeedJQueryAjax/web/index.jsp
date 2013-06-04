<!-- Home page for Olympic image search application -->
<%@ page language="java" import="java.util.*;"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
    <HEAD>
        <TITLE>News Feeds</TITLE>
     <script type="text/javascript" src="jquery-1.8.2.js"></script>
        <script type="text/javascript" language="javascript">
$(document).ready(function() {

            $("#button").click(function(){

                var search = $('#search').val();
                var source = $('#source').val();
                               
                var dataString = "search=" + search + "&source=" + source ;//Form query string
                 $.ajax({//Ajax call
                type:"POST",
                url:"NewsJqueryAjax",//servlet
                data: dataString,
                success: function(data){
                
                $('#results').show();

              $('#results').html(data);
            }
         });
        });
 });
</script>
    </HEAD>

    <BODY>
        
           
            <table width="600px" height="50px" border="1" align="left" style="background-color:#EDF6EA;">
                <tr><td><p align="center">News feed</p></td></tr>
            </table>
            <br/>
            <br/>
            <br/>
            <table width="600px" height="50px" border="1" align="left" style="background-color:#EDF6EA;">

                <tr style="background-color:#7BA88B;font-weight:bold;">
                    <td  colspan=50 align="center" height="5px">Please select a news feed</td> <td colspan=50 align="center" height="5px">
                        <select style="width:200px;" id="search" name="search">
                            <option value="Business">Business</option>
                            <option value="Technology">Technology </option>
                            <option value="World">World News</option>
                        </select>
                   </td> 

                </tr>
                <tr style="background-color:#7BA88B;font-weight:bold;">
                    <td  colspan=50 align="center" height="5px">Please select a source</td> <td colspan=50 align="center" height="5px">
                        <select style="width:200px;" id="source" name="source">
                            <option value="BBC">BBC</option>
                            <option value="NYT">New York Times </option>
                            <option value="SMH">Sydney Morning Herald</option>
                        </select>
                   </td> 

                </tr>

            </table>
            <br/><br/></br></br>
          
            <table width="600px" height="50px" align="left" border="1" style="background-color:#EDF6EA;">
                <tr><td align="center" > <input type="button" id="button" value="Submit"/></td></tr>
            </table>
           
 <div style="position: absolute; top: 0px; right: 0px; width: 700px">

<ul id="results">
</ul>

</div>
        </BODY>
</HTML>
