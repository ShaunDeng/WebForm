var Shaunz = new Object;
jQuery.i18n.properties({
	    name:'messages', 
	    path:'/webresources/i18n/', 
	    mode:'both',
	  	async: false,
	  	callback: function() {
	  		console.log('Loaded Language: ' + jQuery.i18n.browserLang());
	  	}
});
var tableIconBtn = {
		edit:'glyphicon glyphicon-edit text-success',
		detail:'glyphicon glyphicon-zoom-in text-info',
		delt:'glyphicon glyphicon-remove text-danger',
		grant:'glyphicon glyphicon-retweet text-primary',
		relate:'glyphicon glyphicon-random text-warning'
};

Shaunz.loadPage = function(container,url){
	$(container).load(url);
};

Shaunz.load = function(url){
	return Shaunz.loadPage('#FeatureContainer',url);
}

Shaunz.treeNodeOnclick = function(target){
	var nodeId = $(target).attr('data-nodeid');
	var treeMenu = $('#TreeMenu');
	var node = treeMenu.treeview('getNode',nodeId);
	if(node.state.expanded){
		treeMenu.treeview('collapseNode',node.nodeId);
	} else {
		treeMenu.treeview('expandNode',node.nodeId);
	}
	if(node.href != "#")
		$('#FeatureContainer').load(node.href);
};

Shaunz.generateTable = function(param){
	var headerHtml = "<thead><tr>";
	if(param.selectable){
		headerHtml += '<th></th>';
	}
	for(i = 0;i<param.header.length;i++){
		headerHtml += '<th>';
		headerHtml += param.header[i];
		headerHtml += '</th>';
	}
	if(param.needOpration){
		headerHtml += '<th>Oprations</th>';
	}
	headerHtml += "</tr></thead>";
	$.ajax({
		url:param.url,
		type: param.gethttpType,
		async: false,
		success: function(data,status){
			var objs = jQuery.parseJSON(data);
			var bodyHtml = "<tbody>";
			for(i=0;i<objs.length;i++){
				bodyHtml += "<tr>";
				if(param.selectable){
					bodyHtml += '<td></td>';
				}
				for(j=0;j<param.column.length;j++){
					var tdContent = '-';
					if(objs[i][param.column[j]]){
						tdContent = objs[i][param.column[j]];
						if(param.translate){
							if(param.translate.includes(param.column[j])){
								tdContent = jQuery.i18n.prop('frontPage.'+param.column[j]+'.'+tdContent);
							}
						}
					}
					bodyHtml = bodyHtml+"<td>"+tdContent+"</td>"; 
				}
				if(param.needOpration){
					bodyHtml += '<td>';
					var operations = param.operations;
					var methods = param.methods;
					bodyHtml += '<div class="row">';
					for(k=0;k<operations.length;k++){
						var operationBtnId = 'tableRow_'+objs[i].id+'_'+operations[k];
						bodyHtml += '<div id="'+operationBtnId+'" class="col-md-1"><span class="';
						bodyHtml += tableIconBtn[operations[k]];
						bodyHtml += '" data-toggle="tooltip" data-placement="bottom" title="'+jQuery.i18n.prop('frontPage.'+operations[k])+'"></span></div> &nbsp';
						
					}
					bodyHtml += '</div>';
					bodyHtml += '</td>';
				}
				bodyHtml += "</tr>";
			}
			bodyHtml += "</tbody>";
			$('#'+param.target).html(headerHtml+bodyHtml);
			
			if(param.selectable){
				var selectStyle = 'os';
				if(param.multiselect){
					selectStyle = 'multi';
				}
				var tableObj = $('#'+param.target).DataTable({
					columnDefs: [ {
			            targets:   0,
			            checkboxes: {
			               selectRow: true
			            }
			        } ],
			        select: {
			            style: selectStyle
			        },
			        order: [[ 1, 'asc' ]],
			        drawCallback: function(){
			        	if(param.relatedInput){
			        		var tableAPI = this.api();
			        		var arr = $('#'+param.relatedInput).val().split(',');
			        		$.each(arr,function(arrIndex){
			        			tableAPI.rows().every( function ( rowIdx, tableLoop, rowLoop ) {
			        			    var data = this.data();
			        			    if(arr[arrIndex] == this.data()[1]){
			        					this.select();
			        				}
			        			} );
			        		});
			        	}
			        }
				});
				
				tableObj
		        .on( 'select', function ( e, dt, type, indexes ) {
		        	var arr = [];
		            $.each(tableObj.rows('.selected').data(), function() {
		                arr.push(this[1]);
		            });
		            if(param.relatedInput){
		            	$('#'+param.relatedInput).val(arr);
		            }
		        } )
		        .on( 'deselect', function ( e, dt, type, indexes ) {
		        	var arr = [];
		            $.each(tableObj.rows('.selected').data(), function() {
		                arr.push(this[1]);
		            });
		            if(param.relatedInput){
		            	$('#'+param.relatedInput).val(arr);
		            }
		        } );
			} else {
				$('#'+param.target).DataTable();
			}
			
			if(param.needOpration){
				for(i=0;i<objs.length;i++){
					var methods = param.methods;
					for(k=0;k<methods.length;k++){
						var operationBtnId = 'tableRow_'+objs[i].id+'_'+operations[k];
						$('#'+param.target).on('click','#'+operationBtnId,objs[i],methods[k]);
					}
				}
			}
		},
		error: function(e){
			console.log(e);
		}
	});
};

Shaunz.alert = function(type,title,info){
	var html = '<div class="alert alert-'+type+' alter-box" role="alert">'+
		'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
		'<strong>'+title+'</strong><br/> '+info+
		'</div>';
	var newDiv = $(html);
	var lastDiv = $('div:visible').last();
	if(lastDiv.hasClass('alter-box')){
		var position = lastDiv.position();
		var height = 100;
		if(lastDiv.height() > 100){
			height = lastDiv.height();
		}
		console.log('top:' + position.top)
		newDiv.css({top: position.top-(height+2)});
		newDiv.insertAfter(lastDiv);
	} else {
		$('body').append(newDiv);
	}
	window.setTimeout(function() {
	    $(".alter-box").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 5000);
};

Shaunz.showSuccess = function(title,info){
	Shaunz.alert('success',title,info);
};

Shaunz.showInfo = function(title,info){
	Shaunz.alert('info',title,info);
};

Shaunz.showWarning = function(title,info){
	Shaunz.alert('warning',title,info);
};

Shaunz.showError = function(title,info){
	Shaunz.alert('danger',title,info);
};

Shaunz.submitForm = function(form,requestUrl,requestType){
	$.ajax({
			url:requestUrl,
			type:requestType,
			data:$(form).serialize(),
			success:function(data,status){
				var result = jQuery.parseJSON(data);
				if(result.result == 'success'){
					Shaunz.showSuccess('Success',result.message);
				} else {
					Shaunz.showError('Error',result.message);
				}
			},
			error:function(e){
				Shaunz.showError('Error',e);
			}
		});
};

Shaunz.SubmitMultipartForm = function(form,requestURL,requestType){
	var formData = new FormData(form);
	$.ajax({
	      url : requestURL,
	      type : requestType,
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
			},
		error:function(e){
				Shaunz.showError('Error',e);
			}
	});
}

Shaunz.ajaxRequest = function(params,requestUrl,requestType){
	$.ajax({
		url:requestUrl,
		type:requestType,
		data:params,
		success:function(data,status){
			var result = jQuery.parseJSON(data);
			if(result.result == 'success'){
				Shaunz.showSuccess('Success',result.message);
			} else {
				Shaunz.showError('Error',result.message);
			}
		},
		error:function(e){
			Shaunz.showError('Error',e);
		}
	});
};

Shaunz.showDetail = function(functionId,ObjId){
	BootstrapDialog.show({
    	title: jQuery.i18n.prop('frontPage.viewDetail'),
		size: BootstrapDialog.SIZE_WIDE,
		closable: false,
		type: BootstrapDialog.TYPE_PRIMARY,
    	message: function(dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        data: {
            'pageToLoad': './mngpages/detail.html?functionId='+functionId+'&objId='+ObjId
        },
        buttons: [{
            label: jQuery.i18n.prop('frontPage.close'),
            title: jQuery.i18n.prop('frontPage.closeDialog'),
            action: function(dialogItself){
                dialogItself.close();
            }
        }]
    });
};

Shaunz.preparePopChooseBox = function(inputIdFunctionMap){
	$.each($('.pop-picker'),function(index){
		var inputId = $($('.pop-picker')[index]).parent().prev().attr('id');
		var selectedIds = $($('.pop-picker')[index]).parent().prev().val();
		var functionId = inputIdFunctionMap[inputId];
		$($('.pop-picker')[index]).parent().click({inputId: inputId, functionId: functionId, selectedIds: selectedIds},function(event){
			BootstrapDialog.show({
		    	title: jQuery.i18n.prop('frontPage.viewDetail'),
		    	type: BootstrapDialog.TYPE_PRIMARY,
		    	closable: false,
				size: BootstrapDialog.SIZE_WIDE,
		    	message: function(dialog) {
		            var $message = $('<div></div>');
		            var pageToLoad = dialog.getData('pageToLoad');
		            $message.load(pageToLoad);
		            return $message;
		        },
		        data: {
		            'pageToLoad': './choosePage.html?multiselect=false&functionId='+event.data.functionId+'&relatedInput='+event.data.inputId+'&selectedIds='+event.data.selectedIds
		        },
		        buttons: [{
		            label: jQuery.i18n.prop('frontPage.submit'),
		            title: jQuery.i18n.prop('frontPage.submit'),
		            action: function(dialogItself){
		            	$('#'+inputId).val($('#ChooseInput').val());
		                dialogItself.close();
		            }
		        },
		        {
		        	label: jQuery.i18n.prop('frontPage.cancel'),
		            title: jQuery.i18n.prop('frontPage.cancel'),
		            action: function(dialogItself){
		                dialogItself.close();
		            }
		        }]
		    });
		});
	});
};

Shaunz.deleteConfirmBox = function(aliasNm,deleteFunc,cancelFunc){
	$.confirm({
	    title: jQuery.i18n.prop('frontPage.delt')+' ' + aliasNm +' ?',
	    autoClose: 'cancelAction|8000',
	    buttons: {
	        deleteUser: {
	            text: jQuery.i18n.prop('frontPage.delt'),
	            action: deleteFunc
	        },
	        cancelAction: cancelFunc
	    }
	});
};

//Picture chooser
(function ( $ ) {
    $.fn.imageUploader = function( options ) {
        // Define plugin options
        var settings = $.extend({
            // Input name attribute
            name: "",
            // Classes for styling the input
            class: "form-control btn btn-default btn-block",
            // Icon which displays in center of input
            icon: "glyphicon glyphicon-plus",
            preview: false,
          	src: ""
        }, options );
        
        // Create an input inside each matched element
        return this.each(function() {
            $(this).html(create_btn(this, settings));
        });
 
    };
 
    // Private function for creating the input element
    function create_btn(that, settings) {
        // The actual file input which stays hidden
        var picker_btn_input = $('<input type="file" name="'+settings.name+'" />');
        
   		// File load listener
        picker_btn_input.change(function() {
            if ($(this).prop('files')[0]) {
                // Use FileReader to get file
                var reader = new FileReader();
                
                // Create a preview once image has loaded
                reader.onload = function(e) {
                    var preview = create_preview(that, e.target.result, settings,picker_btn_input);
                    $(that).html(preview);
                }
                
                // Load image
                reader.readAsDataURL(picker_btn_input.prop('files')[0]);
            }                
        });
   		
      	if(settings.preview){
      		settings.preview = false;
	    	var preview = create_preview(that, settings.src, settings,picker_btn_input);
	    	return preview;
      	}
   		
   		// The input icon element
        var picker_btn_icon = $('<i class="'+settings.icon+'"></i>');
        // The actual element displayed
        var picker_btn = $('<div class="'+settings.class+' img-upload-btn"></div>')
            .append(picker_btn_icon)
            .append(picker_btn_input);
            
        return picker_btn
    };
    
    // Private function for creating a preview element
    function create_preview(that, src, settings, input) {
            // The preview image
            var picker_preview_image = $('<img src="'+src+'" class="img-responsive img-rounded" />');
            
            // The remove image button
            var picker_preview_remove = $('<button class="btn btn-link"><small>Remove</small></button>');
            
            // The preview element
            var picker_preview = $('<div class="text-center"></div>')
                .append(picker_preview_image)
                .append(input)
                .append(picker_preview_remove);

            // Remove image listener
            picker_preview_remove.click(function() {
                var btn = create_btn(that, settings);
                $(that).html(btn);
            });
            
            return picker_preview;
    };
}( jQuery ));


