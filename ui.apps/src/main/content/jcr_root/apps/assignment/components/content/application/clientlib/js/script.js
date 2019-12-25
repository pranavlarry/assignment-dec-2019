var updateid=[];
var updateval=[];
$(document).ready(function(){
	getdata();
});

function dropdown(obj){
    if($(obj).html()!='recruited'){
        $(obj).html("<select class='statuschange' id='"+$(obj).attr('id')+"'>"+
                     "<option value='New'>New</option>"+
                     "<option value='in-progress'>In-progress</option>"+
                     "<option value='recruited'>recruited</option>"+
                     "</select>");
    }
};

$(document).on('change','.statuschange',function(){
	$(".submit").removeAttr("disabled");
	var val=$(this).val();
    var id=$(this).attr('id');
    if(val!='New'){
    	checkstatus(id,val);
		var index=updateid.indexOf(id);
        if(index != -1){
			updateid[index]=id;
            updateval[index]=val;
        }else{
			updateid.push(id);
            updateval.push(val);
        }
    }
});

function checkstatus(id,status){
    if(status=='recruited'){
		$('#'+id).css({"background-color": "green"});
    }
    else {
		$('#'+id).css({"background-color": "white"});
    }
}

function submitstatus(){
	    $.ajax({
          url: "/bin/MySql",
          //data: data,
            method:'POST',
             data: {
                 'id': updateid,
                 'status': updateval
             },
           success: function(resp){
				console.log('success');
       		}
        });
    getdata();
}

function getdata() {
    $('.applicationlist').find('tbody').remove();
    $('#test').empty();
    $(".submit").prop("disabled","true");
	        $.ajax({
          url: "/bin/MySql",
          //data: data,
            method:'GET',
            success: function(resp){
                var data=[];
                data=resp.data;
                console.log(data);
                for(var i=0;i<data.length;i++)
                {
                    $(".applicationlist").append(
                        "<tr id='"+data[i].id+"'>"+
						"<td>"+data[i].date+"</td>"+
                        "<td>"+data[i].firstName+"</td>"+
                        "<td>"+data[i].lastName+"</td>"+
                        "<td>"+data[i].nationality+"</td>"+
                        "<td>"+data[i].gender+"</td>"+
                        "<td>"+data[i].age+"</td>"+
                        "<td>"+data[i].gotSeaLegs+"</td>"+
                        "<td>"+data[i].partOfOurTeam+"</td>"+
                        "<td>"+data[i].friendOrRelativeWorkingWithUs+"</td>"+
                        "<td>"+data[i].areasOfInterest+"</td>"+
                        "<td>"+data[i].learnAboutThisJob+"</td>"+
                        "<td class='status' id='"+data[i].id+"' onclick='dropdown(this); this.onclick=null;'>"+data[i].status+"</td>"+
                        "</tr>"
                    );
                    checkstatus(data[i].id,data[i].status);
                }
			} 
        });
}

