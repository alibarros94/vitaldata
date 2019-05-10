/** Alterar para endereço do seu servidor **/
var url = 'http://31.220.48.152:8082';

$(document).ready(function() {
	
	$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
	return o;
};
	
	$('#form_data').validate({
        errorElement: 'span',        
        focusInvalid: false,
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        rules: {
            email: {
                required: true
            },
            password: {
                required: true
            }
        },
        errorClass: 'validation-error-label',
        successClass: 'validation-valid-label',
        validClass: "validation-valid-label",

        invalidHandler: function (event, validator) { //display error alert on form submit   
            $('.alert-error', $('.login-form')).show();
        },

        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
            $(element).closest('.select-search').addClass('border-warning');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
            $(element).closest('.select-search').removeClass('border-warning');
        },

        success: function (e) {
            $(e).closest('.form-group').removeClass('error').addClass('info');
            $(e).remove();
            // e.addClass("validation-valid-label").text("Sucesso.");
        },

        errorPlacement: function (error, element) {
            if (element.is(':checkbox') || element.is(':radio')) {
                var controls = element.closest('.controls');
                if (controls.find(':checkbox,:radio').length > 1) controls.append(error);
                else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
            }
            else if (element.is('.select2')) {
                error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
            }
            else if (element.is('.chzn-select')) {
                error.insertAfter(element.siblings('[class*="chzn-container"]:eq(0)'));
            }
                // Input group, styled file input
            else if (element.parent().hasClass('uploader') || element.parents().hasClass('input-group')) {
                error.appendTo(element.parent().parent());
            }
            else error.insertAfter(element);
        },

        submitHandler: function (form) {
        },
        invalidHandler: function (form) {
        }
    });

    $('button[name="btnLogin"]').click(function () {
        if ($('#form_data').valid()) { doLogin(); };
    });
        
});

/**
 * Acesso ao sistema atraves do login
 *
 *
 * @return void
 */

function doLogin() {

	$("#message").html("Aguarde...");

	var pars = $("#form_data").serializeObject();	
		pars.email = pars.username;
		
		delete pars.username;

	$.ajax({
		type : "POST",
		url : url + '/api/session',
		data:pars,
		cache: false,		
		error: function (response) {		
			switch(response.status) {			
				case 401:
					$("#message").html("usuario não autorizado");
					break;	
			 	default:
					$("#message").html("Erro no login");
					break;
			}
		},
		success: function (response, status, jXHR) {
			
			if (!response.disabled) {
			
				$(location).attr('href', url+'?token='+response.token);				
																					
			
			} else {
				$("#message").html("Erro no login");
			}		    			

		}
	});

}
					
						
	