<%@ page language="java" pageEncoding="UTF-8"%>
<input name="" type="button" value="全 选" onclick="checkAll('id');"/>
<input name="" type="button" value="反 选" onclick="switchAll('id');"/>
<input name="" type="button" value="全不选" onclick="uncheckAll('id');"/>
<script type="text/javascript">
function checkAll(id) {
        var o = document.getElementsByName(id);     
        for (var i = 0; i < o.length; i++) {
            if (o[i].checked == false) 
                o[i].checked = true;
        }
    }
    function uncheckAll(id) {   
        var o = document.getElementsByName(id); 
        for (var i = 0; i < o.length; i++) {
            if (o[i].checked == true) 
                o[i].checked = false;
        }
    }
    function switchAll(id) {    
        var o = document.getElementsByName(id); 
        for (var i = 0; i < o.length; i++) {
            o[i].checked = !o[i].checked;
        }
    }
</script>