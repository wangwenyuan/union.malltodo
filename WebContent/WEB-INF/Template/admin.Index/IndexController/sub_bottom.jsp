<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
</div>
<script type="text/javascript">

$("#add").click(function () {
    loading = layer.load(2, {
        shade: [0.2, '#000']
    });
	var url=document.location.href;
	var data=$("#html_form").serialize();
	var ret=shop.ajax(url,data);
	if(ret=='error'){
        layer.close(loading);
		layer.msg('网络错误');
	}else{
        layer.close(loading);
		layer.msg(ret.info,{
			time:2000
		},function(){
			if(parseInt(ret.status)){
				parent.location.reload();
			}
		})
	}
})
</script>
</body>
</html>