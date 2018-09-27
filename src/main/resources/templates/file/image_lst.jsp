<%@ include file="../common/common.jsp"%>
<html>
<style>
.drop_zone {
  text-align: center;
  border: dashed 3px #C1C3C5;
  width:  500px;
  height: 120px;
}
.drop_zone p h2 {
  color: #999;
}
</style>
<body>
	<p><h2><code><span id="currentPath"></span></code></h2></p>
	<div class="row">
		<div class="col-sm-5">
			<div id="drop_zone" class="drop_zone" ondrop="dropHandler(event);" ondragover="dragOverHandler(event);">
			  <p><h2>Try to update image by Drag and drop to here</h2></p>
			</div>
		</div>
		<div class="col-sm-7">
			<p><b>Note:</b>If you want display below images in your blog, you should use full path to indicate the physical location of them.<br/>
			The full path of the root folder is '/webForm/staticResources/customerResources/images/'<br/>
			<b>When you use mouse left click one image, it's full path will be copied to your clipboard automatically.</b>
			</p>
			<br/>
			<form id="imageAddForm" enctype="multipart/form-data">
				<div class="form-group">
					<input type="file" name="file" />
					<input id="fileNms" type="hidden" name="fileNms" value=""/>
					<input id="path" type="hidden" name="path" value=""/>
					<button id="upload" type="button" class="btn btn-primary glyphicon glyphicon-upload"> Upload</button>
					<button id="download" type="button" class="btn btn-primary glyphicon glyphicon-download"> Download</button>
					<button id="delete" type="button" class="btn btn-primary glyphicon glyphicon glyphicon-remove"> Delete</button>
					<button id="newFolder" type="button" class="btn btn-primary glyphicon glyphicon glyphicon-folder-open"> New Folder</button>
					<button id="refreshPage" type="button" class="btn btn-primary glyphicon glyphicon glyphicon glyphicon-repeat"> Refresh Page</button>
				</div>
			</form>
		</div>
	</div>
	<select class="image-picker" show-labels multiple>
	</select>
<script type="text/javascript">
var refreshPage = function(){
	var path = $('#path').val();
	$.ajax({
		url:'./file/imageLst',
		type:'GET',
		data:{'path':'/'},
		success:function(data,status){
			var rootUrl = '${ctxPath}';
			var imageLst = jQuery.parseJSON(data);
			var folderPath = "/";
			$('.image-picker').find('option').remove();
			$.each(imageLst,function(index, value){
			    var option = '<option data-img-label="'+value['name']+'" data-img-src="'+rootUrl+value['url']
			    +'" value="'+value['value']+'">'+value['value']+'</option>';
			    $(".image-picker").append(option);
			    if(index == 0){
			    	folderPath = value['folderPath'];
			    	$('#path').val(folderPath);
			    	$("#currentPath").html(folderPath);
			    }
			});
			$(".image-picker").imagepicker({
		        hide_select : true,
		        show_label: true,
		        selected: function(select, event){
		        	var path = 'http://localhost:8080'+$(event.target).attr('src');
		        	copyToClipboard(path);
		        },
		        changed: function(select, newValues, oldValues, event){
			    	$('#fileNms').val(newValues);
		        }
		    });
			var $container = $('.image_picker_selector');
		    // initialize
		    $container.imagesLoaded(function () {
		        $container.masonry({
		            columnWidth: 30,
		            itemSelector: '.thumbnail'
		        });
		    });
		},
		error:function(e){
			Shaunz.showError('Error',e);
		}
	});
};
var copyToClipboard = function(text) {
	  var $temp = $("<input>");
	  $("body").append($temp);
	  $temp.val(text).select();
	  document.execCommand("copy");
	  $temp.remove();
};
$(function(){
	refreshPage();
	$('#upload').click(function(){
		if($('input[type="file"]')[0].files[0]){
			var fileNm = $('input[type="file"]')[0].files[0].name;
			$('#fileNm').val(fileNm);
			var formData = new FormData($('#imageAddForm')[0]);
			upload(formData);
		} else {
			Shaunz.showWarning('Validation','Please choose a file first!');
		}
		
	});
	$('#download').click(function(){
		var path = $('#path').val();
		var fileNms = $('#fileNms').val();
		$.fileDownload('./file/image?path='+path+'&fileNms='+fileNms, {
		    successCallback: function (url) {
		 
		        alert('You just got a file download dialog or ribbon for this URL :' + url);
		    },
		    failCallback: function (html, url) {
		 
		        alert('Your file download just failed for this URL:' + url + '\r\n' +
		                'Here was the resulting error HTML: \r\n' + html
		                );
		    }
		});
	});
	
	$('#delete').click(function(){
		var path = $('#path').val();
		var fileNms = $('#fileNms').val();
		$.confirm({
		    title: 'Delete selected images?',
		    autoClose: 'cancelAction|8000',
		    buttons: {
		        deleteUser: {
		            text: 'Delete',
		            action: function () {
		            	var requestUrl = './file/image?path='+path+'&fileNms='+fileNms;
		            	$.ajax({
		            		url:requestUrl,
		            		type:'DELETE',
		            		success:function(data,status){
		            			var result = jQuery.parseJSON(data);
		            			if(result.result == 'success'){
		            				Shaunz.showSuccess('Success',result.message);
		            			} else {
		            				Shaunz.showError('Error',result.message);
		            			}
		            			refreshPage();
		            		},
		            		error:function(e){
		            			Shaunz.showError('Error',e);
		            			refreshPage();
		            		}
		            	});
		            }
		        },
		        cancelAction: function () {
		        	
		        }
		    }
		});
	});
	
	$('#newFolder').click(function(){
		
	});
	
	$('#refreshPage').click(function(){
		refreshPage();
	});
});


var dropHandler = function(ev) {
  ev.preventDefault();
  var formData = new FormData();
  if (ev.dataTransfer.items) {
    for (var i = 0; i < ev.dataTransfer.items.length; i++) {
      if (ev.dataTransfer.items[i].kind === 'file') {
        var file = ev.dataTransfer.items[i].getAsFile();
        formData.append('file',file);
      }
    }
  } else {
    for (var i = 0; i < ev.dataTransfer.files.length; i++) {
    	formData.append('file',ev.dataTransfer.files[i]);
    }
  }
  upload(formData);
  removeDragData(ev)
};
var dragOverHandler = function(ev) {
  ev.preventDefault();
};
var removeDragData = function(ev) {
  if (ev.dataTransfer.items) {
    ev.dataTransfer.items.clear();
  } else {
    ev.dataTransfer.clearData();
  }
};
var upload = function(formData){
	$.ajax({
	      url : './file/image',
	      type : 'POST',
	      data : formData,
	      cache : false,
	      contentType : false,
	      processData : false,
	      success:function(data,status){
				var result = jQuery.parseJSON(data);
				if(result.result == 'success'){
					Shaunz.showSuccess('Success',result.message);
				} else {
					Shaunz.showError('Error',result.message);
				}
				refreshPage();
			},
		  error:function(e){
				Shaunz.showError('Error',e);
				refreshPage();
		  }
	});
};
</script>
</body>
</html>
